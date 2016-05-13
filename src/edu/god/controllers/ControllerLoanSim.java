/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.models.AccessDB;
import edu.god.views.ScreenHome;
import edu.god.views.ScreenLoanSim;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.UIManager;
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
    
    
    public ControllerLoanSim(ScreenLoanSim sls, JTextField txtMonthlyWInsurance, JTextField txtMonthlyOfInsurance, JTextField txtMonthlyInsurance, JTextField txtTotal, JTextField txtTotalInsurance, JTextField txtCost, JComboBox cbxLoan, JTextField txtAmount, JTextField txtRate, JTextField txtInsurance, JTextField txtAmountInsurance, JTextField txtDuration, JXDatePicker txtDate, JTextField txtCapital, JButton btnCalculate, JButton btnSave, JButton btnHome, JPanel panelLeft, JLabel lblError) {
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
        error=lblError;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetAfterError();
        if (fieldsNotEmpty()) {
            if (e.getSource() == btnCalculate) {
                calculLoan();
                btnSave.setEnabled(true);
            } else if (e.getSource() == btnSave) {
                if (id != null) {
                    try {
                        int updateLoanSim = AccessDB.getAccessDB().updateLoanSim(id, id, id, id, id, id, id, id, id, id);
                        // Dialog
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        AccessDB.getAccessDB().insertLoanSim(id, id, id, id, id, id, id, id, id, id);
                        // Dialog
                    } catch (SQLException ex) {
                        Logger.getLogger(ControllerLoanSim.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if (e.getSource() == btnHome) {
            sls.dispose();
            ScreenHome newWindow = new ScreenHome(0);
        } else {
            error.setText("Veuillez saisir tous les champs obligatoires");
        }
    }

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

    private boolean fieldsNotEmpty() {
        boolean notEmpty = true;
        ArrayList<JTextField> textFields = null;
        for (Component aField : left.getComponents()) {
            if (aField.getClass() == JTextField.class && aField.getName() == null) {
                textFields.add((JTextField) aField);
            }
        }
        for (int i = textFields.size(); 0 < i; i--) {
            if (textFields.get(i).getText().isEmpty()) {
                textFields.get(i).requestFocus();
                textFields.get(i).setBorder(BorderFactory.createLineBorder(Color.RED));
                notEmpty = false;
            }
        }
        return notEmpty;
    }

    private void resetAfterError() {
        ArrayList<JTextField> textFields = null;
        for (Component aField : left.getComponents()) {
            if (aField.getClass() == JTextField.class && aField.getName() == null) {
                textFields.add((JTextField) aField);
            }
        }
        for (int i = textFields.size(); 0 < i; i--) {
            if (textFields.get(i).getText().isEmpty()) {
                textFields.get(i).requestFocus();
                textFields.get(i).setBorder(UIManager.getBorder("TextField.border"));
            }
        }
        error.setText("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_PERIOD)
            txtAmount.setText(txtAmount.getText()+",");
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
