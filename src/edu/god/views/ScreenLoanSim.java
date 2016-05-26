/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.views;

import edu.god.controllers.ControllerLoanSim;
import static edu.god.serialisation.JsonEncoding.*;
import edu.god.server.ClientJavaSelect;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import javax.swing.*;
import org.json.simple.JSONObject;

/**
 *
 * @author Warren
 */
public class ScreenLoanSim extends JFrame {

    /**
     * Creates new form ScreenLoanSim
     *
     * @param idConsultant int
     * @param id String
     * @param simulation boolean
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws org.json.simple.parser.ParseException
     * @throws java.io.IOException
     */
    public ScreenLoanSim(int idConsultant, String id, boolean simulation) throws SQLException, ParseException, org.json.simple.parser.ParseException, IOException {
        initComponents();
        rootPane.getContentPane().setBackground(Color.WHITE);

        lblError.setText("");
        lblError.setForeground(Color.red);
        btnSave.setEnabled(false);
        
        String idAgency =  ClientJavaSelect.clientTcpSelect("D", "15", encodingIdAgency(String.valueOf(idConsultant)));
        String idPc =  ClientJavaSelect.clientTcpSelect("D", "16", encodingIdPc(idAgency));
        
        String mins = ClientJavaSelect.clientTcpSelect("D", "13", encodingRatePc(idPc));
        String rep1 = mins.replace("[", "");
        String rep2 = rep1.replace("]", "");
        String[] maxPc = rep2.split(",");
        System.out.println(Arrays.toString(maxPc));

        
        String maxs = ClientJavaSelect.clientTcpSelect("D", "14", encodingRateAg(idAgency));
        String rep11 = maxs.replace("[", "");
        String rep22 = rep11.replace("]", "");
        String[] minAg = rep22.split(",");
        System.out.println(Arrays.toString(minAg));
        
        //load the combobox
        String res = ClientJavaSelect.clientTcpSelect("D", "6", new JSONObject());
        String test = res.replace("[", "");
        String test2 = test.replace("]", "");
        String[] loanTypes = test2.split(",");
        System.out.println(Arrays.toString(loanTypes));

        cbxLoan.removeAllItems();
        cbxLoan.addItem("Sélectionner");
        for (String loanType : loanTypes) {
            if (loanType.startsWith(" ")) {
                cbxLoan.addItem(loanType.substring(1));
            } else {
                cbxLoan.addItem(loanType);
            }

        }

        // add ActionListener
        btnCaculate.addActionListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnBack, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        btnSave.addActionListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnBack, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        btnBack.addActionListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnBack, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        //add KeyListener
        txtAmount.addKeyListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnSave, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        txtRate.addKeyListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnSave, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        txtInsurance.addKeyListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnSave, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        txtDuration.addKeyListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnSave, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        txtCapital.addKeyListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtTotalInterest, txtTotalInsurrance, txtTotalLoan, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnSave, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));

        //
        cbxLoan.addItemListener(new ControllerLoanSim(this, txtMonthlyWInsurance, txtMonthlyInsurance, txtMonthly, txtCapital, txtTotalInsurrance, txtAmount, cbxLoan, txtAmount, txtRate, txtInsurance, txtDuration, txtCapital, btnCaculate, btnSave, btnSave, PLeft, lblError, simulation, idConsultant, id, lblMax, lblMin, minAg,maxPc));
        // if the simulation exist load in the fied the saved data
        if (simulation) {
            loadForm(id,minAg,maxPc);
        }
        setVisible(true);
    }

    /**
     * the method load the fields with the data saved in the database for a
     * given simulation
     *
     * @param idSim String
     * @throws ParseException
     */
    public void loadForm(String idSim, String[] mins , String[] maxs) throws ParseException, IOException, FileNotFoundException, org.json.simple.parser.ParseException {
        String res = ClientJavaSelect.clientTcpSelect("D", "7", encodingSimId(idSim));
        String test = res.replace("[", "");
        String test2 = test.replace("]", "");
        String test3 = test.replace(", ", ",");
        String[] infoSim = test3.split(",");
        cbxLoan.setSelectedItem(infoSim[8]);
        txtAmount.setText(infoSim[2]);
        lblMin.setText(lblMin.getText().substring(0, 5)+ mins[cbxLoan.getSelectedIndex()-1]);
        lblMax.setText(lblMax.getText().substring(0, 5)+ maxs[cbxLoan.getSelectedIndex()-1]);
        txtRate.setText(infoSim[3]);
        txtInsurance.setText(infoSim[7]);
        txtDuration.setText(infoSim[4]);
        txtCapital.setText(infoSim[1]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PLeft = new javax.swing.JPanel();
        lblLoan = new javax.swing.JLabel();
        cbxLoan = new javax.swing.JComboBox<>();
        lblAmount = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        lblRate = new javax.swing.JLabel();
        txtRate = new javax.swing.JTextField();
        lblinsurance = new javax.swing.JLabel();
        txtInsurance = new javax.swing.JTextField();
        lblDuration = new javax.swing.JLabel();
        txtDuration = new javax.swing.JTextField();
        lblCapital = new javax.swing.JLabel();
        txtCapital = new javax.swing.JTextField();
        btnCaculate = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();
        lblMax = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        PRight = new javax.swing.JPanel();
        lblMonthly = new javax.swing.JLabel();
        txtMonthly = new javax.swing.JTextField();
        lblMonthlyWInsurance = new javax.swing.JLabel();
        txtMonthlyWInsurance = new javax.swing.JTextField();
        lblMonthlyInsurance = new javax.swing.JLabel();
        txtMonthlyInsurance = new javax.swing.JTextField();
        lblTotalInterest = new javax.swing.JLabel();
        txtTotalInterest = new javax.swing.JTextField();
        lblTotalInsurance = new javax.swing.JLabel();
        txtTotalInsurrance = new javax.swing.JTextField();
        lblTotalLoan = new javax.swing.JLabel();
        txtTotalLoan = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulation de prêt");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("windows"); // NOI18N
        setSize(new java.awt.Dimension(800, 600));

        PLeft.setBackground(new java.awt.Color(255, 255, 255));
        PLeft.setPreferredSize(new java.awt.Dimension(300, 500));

        lblLoan.setText("Type de prêt");

        cbxLoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAmount.setText("Montant du prêt");

        lblRate.setText("Taux d'interêt");

        txtRate.setEditable(true);
        txtRate.setBackground(new java.awt.Color(255, 255, 255));

        lblinsurance.setText("Taux d'assurance");

        txtInsurance.setName("rateInsurance"); // NOI18N

        lblDuration.setText("Durée en mois");

        lblCapital.setText("Apport initial");

        btnCaculate.setText("Calculer");

        lblError.setText("jLabel1");

        lblMax.setText("Max : ");

        lblMin.setText("Min :");

        org.jdesktop.layout.GroupLayout PLeftLayout = new org.jdesktop.layout.GroupLayout(PLeft);
        PLeft.setLayout(PLeftLayout);
        PLeftLayout.setHorizontalGroup(
            PLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PLeftLayout.createSequentialGroup()
                .add(47, 47, 47)
                .add(PLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(lblCapital)
                    .add(PLeftLayout.createSequentialGroup()
                        .add(46, 46, 46)
                        .add(PLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(btnCaculate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(PLeftLayout.createSequentialGroup()
                                .add(32, 32, 32)
                                .add(lblError))))
                    .add(lblDuration)
                    .add(lblinsurance)
                    .add(lblAmount)
                    .add(lblLoan)
                    .add(cbxLoan, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(txtAmount)
                    .add(txtRate)
                    .add(txtInsurance)
                    .add(txtDuration)
                    .add(txtCapital, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(PLeftLayout.createSequentialGroup()
                        .add(lblMax)
                        .add(28, 28, 28)
                        .add(lblRate)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(lblMin)
                        .add(22, 22, 22)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        PLeftLayout.setVerticalGroup(
            PLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PLeftLayout.createSequentialGroup()
                .add(56, 56, 56)
                .add(lblLoan)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cbxLoan, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(25, 25, 25)
                .add(lblAmount)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtAmount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(25, 25, 25)
                .add(PLeftLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(lblRate)
                    .add(lblMax)
                    .add(lblMin))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtRate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(25, 25, 25)
                .add(lblinsurance)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtInsurance, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(27, 27, 27)
                .add(lblDuration)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtDuration, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(28, 28, 28)
                .add(lblCapital)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtCapital, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(33, 33, 33)
                .add(lblError)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(btnCaculate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(53, 53, 53))
        );

        PRight.setBackground(new java.awt.Color(255, 255, 255));
        PRight.setPreferredSize(new java.awt.Dimension(300, 500));

        lblMonthly.setText("Mensualité hors assurance");

        txtMonthly.setEditable(false);
        txtMonthly.setBackground(new java.awt.Color(235, 235, 235));

        lblMonthlyWInsurance.setText("Mensualité de l'assurance");

        txtMonthlyWInsurance.setEditable(false);
        txtMonthlyWInsurance.setBackground(new java.awt.Color(235, 235, 235));

        lblMonthlyInsurance.setText("Mensualité avec assurrance");

        txtMonthlyInsurance.setEditable(false);
        txtMonthlyInsurance.setBackground(new java.awt.Color(235, 235, 235));

        lblTotalInterest.setText("Total interets");

        txtTotalInterest.setEditable(false);
        txtTotalInterest.setBackground(new java.awt.Color(235, 235, 235));

        lblTotalInsurance.setText("Total assurance");

        txtTotalInsurrance.setEditable(false);
        txtTotalInsurrance.setBackground(new java.awt.Color(235, 235, 235));

        lblTotalLoan.setText("Cout total du prêt");

        txtTotalLoan.setEditable(false);
        txtTotalLoan.setBackground(new java.awt.Color(235, 235, 235));

        btnSave.setText("Enregister");

        org.jdesktop.layout.GroupLayout PRightLayout = new org.jdesktop.layout.GroupLayout(PRight);
        PRight.setLayout(PRightLayout);
        PRightLayout.setHorizontalGroup(
            PRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PRightLayout.createSequentialGroup()
                .add(50, 50, 50)
                .add(PRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(lblTotalInsurance)
                    .add(lblTotalInterest)
                    .add(lblMonthlyInsurance)
                    .add(lblMonthlyWInsurance)
                    .add(lblMonthly)
                    .add(lblTotalLoan)
                    .add(txtMonthly, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .add(txtMonthlyWInsurance)
                    .add(txtMonthlyInsurance)
                    .add(txtTotalInterest)
                    .add(txtTotalInsurrance)
                    .add(txtTotalLoan))
                .add(50, 50, 50))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, PRightLayout.createSequentialGroup()
                .addContainerGap()
                .add(btnSave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(95, 95, 95))
        );
        PRightLayout.setVerticalGroup(
            PRightLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(PRightLayout.createSequentialGroup()
                .add(65, 65, 65)
                .add(lblMonthly)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtMonthly, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(lblMonthlyWInsurance)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtMonthlyWInsurance, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(lblMonthlyInsurance)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtMonthlyInsurance, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(lblTotalInterest)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtTotalInterest, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(lblTotalInsurance)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtTotalInsurrance, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(26, 26, 26)
                .add(lblTotalLoan)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(txtTotalLoan, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 44, Short.MAX_VALUE)
                .add(btnSave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(20, 20, 20))
        );

        btnBack.setText("Retour");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .add(PLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 40, Short.MAX_VALUE)
                .add(PRight, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(btnBack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(319, 319, 319))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(PRight, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                    .add(PLeft, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 531, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(btnBack, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PLeft;
    private javax.swing.JPanel PRight;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCaculate;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbxLoan;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblCapital;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblLoan;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblMonthly;
    private javax.swing.JLabel lblMonthlyInsurance;
    private javax.swing.JLabel lblMonthlyWInsurance;
    private javax.swing.JLabel lblRate;
    private javax.swing.JLabel lblTotalInsurance;
    private javax.swing.JLabel lblTotalInterest;
    private javax.swing.JLabel lblTotalLoan;
    private javax.swing.JLabel lblinsurance;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtCapital;
    private javax.swing.JTextField txtDuration;
    private javax.swing.JTextField txtInsurance;
    private javax.swing.JTextField txtMonthly;
    private javax.swing.JTextField txtMonthlyInsurance;
    private javax.swing.JTextField txtMonthlyWInsurance;
    private javax.swing.JTextField txtRate;
    private javax.swing.JTextField txtTotalInsurrance;
    private javax.swing.JTextField txtTotalInterest;
    private javax.swing.JTextField txtTotalLoan;
    // End of variables declaration//GEN-END:variables
}
