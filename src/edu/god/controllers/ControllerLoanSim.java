/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.models.AccessDB;
import edu.god.views.ScreenExistingSim;
import edu.god.views.ScreenHome;
import edu.god.views.ScreenLoanSim;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Warren
 */
public class ControllerLoanSim implements ActionListener, KeyListener {

    private String id;
    private final ScreenLoanSim sls;
    private final JButton btnHome;
    private final JLabel error;
    private final JTextField txtMonthlyWInsurance;
    private final JTextField txtMonthlyOfInsurance;
    private final JTextField txtMonthlyInsurance;
    private final JTextField txtTotal;
    private final JTextField txtTotalInsurance;
    private final JTextField txtCost;
    private final JButton btnSave;

    private final JPanel left;
    private final JComboBox cbxLoan;
    private final JTextField txtAmount;
    private final JTextField txtRate;
    private final JTextField txtInsurance;
    private final JTextField txtAmountInsurance;
    private final JTextField txtDuration;
    private final JXDatePicker txtDate;
    private final JTextField txtCapital;
    private final JButton btnCalculate;
    private final boolean modify;

    private final int idCons;
    private String idCust;

    /**
     * Default constructor
     *
     * @param sls ScreenLoanSim
     * @param txtMonthlyWInsurance JTextField
     * @param txtMonthlyOfInsurance JTextField
     * @param txtMonthlyInsurance JTextField
     * @param txtTotal JTextField
     * @param txtTotalInsurance JTextField
     * @param txtCost JTextField
     * @param cbxLoan
     * @param txtAmount JTextField
     * @param txtRate JTextField
     * @param txtInsurance JTextField
     * @param txtAmountInsurance JTextField
     * @param txtDuration JTextField
     * @param txtDate
     * @param txtCapital JTextField
     * @param btnCalculate JButton
     * @param btnSave JButton
     * @param btnHome JButton
     * @param panelLeft JPanel
     * @param lblError JLabel
     * @param modified boolean
     * @param idConsultant int
     * @param aId String
     */
    public ControllerLoanSim(ScreenLoanSim sls, JTextField txtMonthlyWInsurance, JTextField txtMonthlyOfInsurance, JTextField txtMonthlyInsurance, JTextField txtTotal, JTextField txtTotalInsurance, JTextField txtCost, JComboBox cbxLoan, JTextField txtAmount, JTextField txtRate, JTextField txtInsurance, JTextField txtAmountInsurance, JTextField txtDuration, JXDatePicker txtDate, JTextField txtCapital, JButton btnCalculate, JButton btnSave, JButton btnHome, JPanel panelLeft, JLabel lblError, boolean modified, int idConsultant, String aId) {
        this.sls = sls;
        this.txtMonthlyWInsurance = txtMonthlyWInsurance;
        this.txtMonthlyOfInsurance = txtMonthlyOfInsurance;
        this.txtMonthlyInsurance = txtMonthlyInsurance;
        this.txtTotal = txtTotal;
        this.txtTotalInsurance = txtTotalInsurance;
        this.txtCost = txtCost;
        this.cbxLoan = cbxLoan;
        this.txtAmount = txtAmount;
        this.txtRate = txtRate;
        this.txtInsurance = txtInsurance;
        this.txtAmountInsurance = txtAmountInsurance;
        this.txtDuration = txtDuration;
        this.txtDate = txtDate;
        this.txtCapital = txtCapital;
        this.btnCalculate = btnCalculate;
        this.btnSave = btnSave;
        this.btnHome = btnHome;
        this.left = panelLeft;
        error = lblError;
        modify = modified;
        idCons = idConsultant;
        //
        if (modify) {
            id = aId;
        } else {
            idCust = aId;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetAfterError();
        if (fieldsNotEmpty()) {
            if (e.getSource() == btnCalculate) {
                try {
                    if (checkLoanProperty()) {
                        calculLoan();
                        btnSave.setEnabled(true);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == btnSave) {
                String date = LocalDate.now().toString();
                if (modify) { // if the user choose an existing simulation to modified it. CASE UPDATE
                    try {
                        String idType = AccessDB.getAccessDB().getIdLoanType(cbxLoan.getSelectedItem().toString());
                        System.out.println("idtype = " + idType);
                        int updateLoanSim = AccessDB.getAccessDB().updateLoanSim(id, txtCapital.getText(), txtAmount.getText(),txtMonthlyInsurance.getText(), txtDuration.getText(), date, "Mise a jour le ", idCons, "1", "1", idType);
                        showDialog(updateLoanSim);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else { // if modify is false that means a new simulation will be insert. INSERT CASE 
                    try {
                        String idType = AccessDB.getAccessDB().getIdLoanType(cbxLoan.getSelectedItem().toString());
                        int insertLoanSim = AccessDB.getAccessDB().insertLoanSim(txtCapital.getText(), txtAmount.getText(),txtMonthlyInsurance.getText(), txtDuration.getText(), date, "Cree le " + date, idCons, idCust, "1", "1", idType);
                        showDialog(insertLoanSim);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (e.getSource() == btnHome) {
                sls.dispose();
                ScreenHome newWindow = new ScreenHome(Integer.parseInt(id));
            }
        } else { // end of if( fieldsNotEmpty()) ...
            error.setText("Veuillez saisir tous les champs obligatoires");
        }
    }

    /**
     * the method calcul the loan with the data previously input
     */
    private void calculLoan() {
        Float amount = Float.parseFloat(txtAmount.getText());
        Float rate = Float.parseFloat(txtRate.getText());
        Float rateInsurance = Float.parseFloat(txtInsurance.getText());
        Float amountInsurance = Float.parseFloat(txtAmountInsurance.getText());
        Float duration = Float.parseFloat(txtDuration.getText());
        String date = txtDate.getDate().toString();
        Float capital = Float.parseFloat(txtCapital.getText());

        Float monthlyWInsurance = (amount - capital) / duration;
        Float monthlyOfInsurance = 0.0f;
        Float monthlyInsurance = 0.0f;

        Float totalInsurance = 0.0f;
        Float cost = amount * (rate / 100);
        Float total = cost - amount;

        txtMonthlyWInsurance.setText(monthlyWInsurance.toString());
        txtMonthlyOfInsurance.setText("");
        txtMonthlyInsurance.setText("");
        txtTotal.setText(total.toString());
        txtTotalInsurance.setText("");
        txtCost.setText(cost.toString());
    }

    /**
     * The method return true if one of the field is EMPTY and return false when
     * all the fields aren't empty (the correct situation)
     *
     * @return notEmpty boolean
     */
    private boolean fieldsNotEmpty() {
        boolean notEmpty = true;
        ArrayList<JTextField> textFields = new ArrayList<>();
        for (Component aField : left.getComponents()) {
            if (aField.getClass() == JTextField.class && aField.getName() == null) {
                textFields.add((JTextField) aField);
            }
        }
        for (int i = textFields.size(); i > 0; i--) {
            if (textFields.get(i - 1).getText().isEmpty()) {
                textFields.get(i - 1).requestFocus();
                textFields.get(i - 1).setBorder(BorderFactory.createLineBorder(Color.RED));
                notEmpty = false;
            }
        }
        return notEmpty;
    }

    /**
     * The method set the border to the default color and the label to empty
     */
    private void resetAfterError() {
        ArrayList<JTextField> textFields = new ArrayList<>();
        for (Component aField : left.getComponents()) {
            if (aField.getClass() == JTextField.class && aField.getName() == null) {
                textFields.add((JTextField) aField);
            }
        }
        for (int i = textFields.size(); i > 0; i--) {
            if (textFields.get(i - 1).getText().isEmpty()) {
                textFields.get(i - 1).requestFocus();
                textFields.get(i - 1).setBorder(UIManager.getBorder("TextField.border"));
            }
        }
        error.setText("");
    }

    /**
     * The method pop up a dialog to confirm or not that the action (insert or
     * update is done)
     *
     * @param sqlResponse int
     * @throws SQLException
     */
    private void showDialog(int sqlResponse) throws SQLException {
        if (idCust == null) {
            idCust = AccessDB.getAccessDB().getIdCustInSim(id);
        }
        if (sqlResponse == 1) {
            String option[] = {"Accueil", "Comparer des simulations", "Nouvelle simulation"};
            int click = JOptionPane.showOptionDialog(sls, "La simulation a été enregistré", "Ajout simulation",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    option,
                    option[0]);
            switch (click) {
                case JOptionPane.CANCEL_OPTION:
                    sls.dispose();
                    try {
                        ScreenLoanSim newWindow = new ScreenLoanSim(idCons, idCust, false);
                    } catch (ParseException ex) {
                        Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case JOptionPane.YES_OPTION: {
                    sls.dispose();
                    ScreenHome newWindow = new ScreenHome(idCons);
                    break;
                }
                case JOptionPane.NO_OPTION: {
                    sls.dispose();
                    ScreenExistingSim newWindow = new ScreenExistingSim(idCons, idCust);
                    break;
                }
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(sls, "L'ajout de la simulation a échoué", "Ajout simulation", JOptionPane.ERROR_MESSAGE);

        }
    }

    /**
     * The method retrieve the param for the selected type of loan and check if
     * the input values are correct. Return true if the values are good
     *
     * @throws SQLException
     * @return checking boolean
     */
    private boolean checkLoanProperty() throws SQLException {
        boolean checking = false;
        String idType = AccessDB.getAccessDB().getIdLoanType(cbxLoan.getSelectedItem().toString());
        int param[] = AccessDB.getAccessDB().getParambyID(idType);
        int minAmount = param[0], maxAmount = param[1], minDuration = param[2], maxDuration = param[3];
        if (Integer.parseInt(txtAmount.getText()) >= minAmount && Integer.parseInt(txtAmount.getText()) <= maxAmount) {
            if (Integer.parseInt(txtDuration.getText()) >= minDuration && Integer.parseInt(txtDuration.getText()) <= maxDuration) {
                checking = true;
            } else {
                JOptionPane.showMessageDialog(sls, "La durée du prêt doit être compris entre " + minDuration + " et " + maxDuration, "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(sls, "Le montant du prêt doit être compris entre " + minAmount + " et " + maxAmount, "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        return checking;
    }

    @Override
    public void keyTyped(KeyEvent e) { // TODO TEST
        if (e.getKeyChar() == '.') {
            txtAmount.setText(txtAmount.getText() + ",");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
