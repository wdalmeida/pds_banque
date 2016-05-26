/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.serialisation.JsonEncoding;
import edu.god.server.ClientJavaSelect;
import edu.god.views.ScreenExistingSim;
import edu.god.views.ScreenHome;
import edu.god.views.ScreenLoanSim;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

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
    private final JTextField txtMonthlyInsurance;
    private final JTextField txtMonthly;
    private final JTextField txtTotalInterest;
    private final JTextField txtTotalInsurance;
    private final JTextField txtTotalLoan;
    private final JButton btnSave;

    private final JPanel left;
    private final JComboBox cbxLoan;
    private final JTextField txtAmount;
    private final JTextField txtRate;
    private final JTextField txtInsurance;
    private final JTextField txtDuration;
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
     * @param txtMonthlyInsurance JTextField
     * @param txtMonthly
     * @param txtTotal JTextField
     * @param txtTotalInsurance JTextField
     * @param txtCost JTextField
     * @param cbxLoan
     * @param txtAmount JTextField
     * @param txtRate JTextField
     * @param txtInsurance JTextField
     * @param txtDuration JTextField
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
    public ControllerLoanSim(ScreenLoanSim sls, JTextField txtMonthlyWInsurance, JTextField txtMonthlyInsurance, JTextField txtMonthly, JTextField txtTotal, JTextField txtTotalInsurance, JTextField txtCost, JComboBox cbxLoan, JTextField txtAmount, JTextField txtRate, JTextField txtInsurance, JTextField txtDuration, JTextField txtCapital, JButton btnCalculate, JButton btnSave, JButton btnHome, JPanel panelLeft, JLabel lblError, boolean modified, int idConsultant, String aId) {
        this.sls = sls;
        this.txtMonthlyWInsurance = txtMonthlyWInsurance;
        this.txtMonthlyInsurance = txtMonthlyInsurance;
        this.txtMonthly = txtMonthly;
        this.txtTotalInterest = txtTotal;
        this.txtTotalInsurance = txtTotalInsurance;
        this.txtTotalLoan = txtCost;
        this.cbxLoan = cbxLoan;
        this.txtAmount = txtAmount;
        this.txtRate = txtRate;
        this.txtInsurance = txtInsurance;
        this.txtDuration = txtDuration;
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
        if (e.getSource() == btnCalculate) {
            if (fieldsNotEmpty()) {
                try {
                    if (checkLoanProperty()) {
                        calculLoan();
                        btnSave.setEnabled(true);
                    }
                } catch (SQLException | IOException | org.json.simple.parser.ParseException ex) {
                    Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { // end of if( fieldsNotEmpty()) ...
                error.setText("Veuillez saisir tous les champs obligatoires");
            }
        } else if (e.getSource() == btnSave) {
            String date = LocalDate.now().toString();
            if (modify) { // if the user choose an existing simulation to modified it. CASE UPDATE
                try {
                    String idType = ClientJavaSelect.clientTcpSelect("D", "10", JsonEncoding.encodingIdLoanType(cbxLoan.getSelectedItem().toString()));
                    System.out.println("idtype = " + idType);
                    String updateLoanSim = ClientJavaSelect.clientTcpSelect("D", "8", JsonEncoding.encodingUpLoanSim(id, txtCapital.getText(), txtAmount.getText(), txtMonthly.getText(), txtDuration.getText(), date, "Mise a jour le ", idCons, "1", "1", idType));
                    showDialog(Integer.parseInt(updateLoanSim));
                } catch (SQLException | IOException | org.json.simple.parser.ParseException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { // if modify is false that means a new simulation will be insert. INSERT CASE 
                try {
                    String idType = ClientJavaSelect.clientTcpSelect("D", "10", JsonEncoding.encodingIdLoanType(cbxLoan.getSelectedItem().toString()));
                    String insertLoanSim = ClientJavaSelect.clientTcpSelect("D", "9", JsonEncoding.encodingInLoanSim(txtCapital.getText(), txtAmount.getText(), txtMonthly.getText(), txtDuration.getText(), date, "Mise a jour le ", String.valueOf(idCons), idCust, "1", "1", idType));
                    showDialog(Integer.parseInt(insertLoanSim));
                } catch (SQLException | IOException | org.json.simple.parser.ParseException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (e.getSource() == btnHome) {
            sls.dispose();
            ScreenHome newWindow = new ScreenHome(idCons);
        }
    }

    /**
     * the method calcul the loan with the data previously input
     */
    private void calculLoan() {
        DecimalFormat df = new DecimalFormat("0.00");

        float amount = Float.parseFloat(txtAmount.getText());
        float rate = Float.parseFloat(txtRate.getText());
        float insurance = Float.parseFloat(txtInsurance.getText());
        float duration = Float.parseFloat(txtDuration.getText());
        float capital = Float.parseFloat(txtCapital.getText());

        float interestRate = 1 + (rate / 100);
        float insuranceRate = insurance / 100;

        float monthlyInterestRate = interestRate / 12;
        float monthlyInssuranceRate = insuranceRate / 12;

        float monthly = ((amount - capital) * interestRate)/duration;
        float monthlyInsurance = ((amount - capital) * monthlyInssuranceRate);
        float monthlyWInsurance = monthly + monthlyInsurance;

        if (insuranceRate == 0) {
            insuranceRate = 1;
        }
        float totalLoan = (float) (amount * interestRate + (monthlyInsurance*duration));
        float totalInsurance = (amount * monthlyInssuranceRate ) * duration;
        float totalInterest = totalLoan - amount -totalInsurance;

        txtMonthly.setText(df.format(monthly));
        txtMonthlyWInsurance.setText(df.format(monthlyInsurance));
        txtMonthlyInsurance.setText(df.format(monthlyWInsurance));
        txtTotalInterest.setText(df.format(totalInterest));
        txtTotalInsurance.setText(df.format(totalInsurance));
        txtTotalLoan.setText(df.format(totalLoan));
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

        if (cbxLoan.getSelectedIndex() == 0) {
            notEmpty = false;
            cbxLoan.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        if (txtInsurance.getText().isEmpty()) {
            txtInsurance.setText("0");
        }
        for (Component aField : left.getComponents()) {
            if (aField.getClass() == JTextField.class
                    && aField.getName() == null) {
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
            if (aField.getClass() == JTextField.class
                    && aField.getName() == null) {
                textFields.add((JTextField) aField);
            }
        }
        for (int i = textFields.size(); i > 0; i--) {
            if (textFields.get(i - 1).getText().isEmpty()) {
                textFields.get(i - 1).requestFocus();
                textFields.get(i - 1).setBorder(UIManager.getBorder("TextField.border"));
            }
        }
        cbxLoan.setBorder(UIManager.getBorder("TextField.border"));
        error.setText("");
    }

    /**
     * The method pop up a dialog to confirm or not that the action (insert or
     * update is done)
     *
     * @param sqlResponse int
     * @throws SQLException
     */
    private void showDialog(int sqlResponse) throws SQLException, IOException, FileNotFoundException, org.json.simple.parser.ParseException, NoSuchAlgorithmException {
        if (idCust == null) {
            idCust = ClientJavaSelect.clientTcpSelect("D", "11", JsonEncoding.encodingIdCustInSim(id));
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
                        Logger.getLogger(ControllerLoanSim.class
                                .getName()).log(Level.SEVERE, null, ex);
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
    private boolean checkLoanProperty() throws SQLException, IOException, FileNotFoundException, org.json.simple.parser.ParseException {
        boolean checking = false;
        String idType = ClientJavaSelect.clientTcpSelect("D", "10", JsonEncoding.encodingIdLoanType(cbxLoan.getSelectedItem().toString()));
        String res = ClientJavaSelect.clientTcpSelect("D", "12", JsonEncoding.encodingParambyID(idType));
        String test = res.replace("[", "");
        String test2 = test.replace("]", "");
        String test3 = test2.replace(" ", "");
        String[] param = test3.split(",");
        System.out.println(Arrays.toString(param));
        int minAmount = Integer.parseInt(param[0]), maxAmount = Integer.parseInt(param[1]), minDuration = Integer.parseInt(param[2]), maxDuration = Integer.parseInt(param[3]);
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
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ',') {
            e.setKeyChar('.');
        }
        Pattern p = Pattern.compile("[0-9.]");
        Matcher m = p.matcher(String.valueOf(e.getKeyChar()));
        if (!m.matches()) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
