/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.views;

import java.security.NoSuchAlgorithmException;
import edu.god.controllers.ControllerScreenHome;
import edu.god.models.AccessDB;
import javax.swing.JFrame;

/**
 *
 * @author florent
 */
public class ScreenHome extends javax.swing.JFrame {

    private final AccessDB bdd;
    private int idConsultant ;

    /**
     * Creates new form ScreenHome
     *
     * @param idC
     */
    public ScreenHome(int idC) {
        initComponents();
        this.bdd = AccessDB.getAccessDB();
        this.getConsultantInfo(idC);
        this.idConsultant=idC;
        /*get information about a Consultant */
        // Add ActionListener
        btnCreateCustomer.addActionListener(new ControllerScreenHome(this, idC, btnCreateCustomer, btnSimulateLoan,indicatorButton));
        btnSimulateLoan.addActionListener(new ControllerScreenHome(this, idC, btnCreateCustomer, btnSimulateLoan,indicatorButton));
        indicatorButton.addActionListener(new ControllerScreenHome(this,idC,btnCreateCustomer,btnSimulateLoan,indicatorButton));
        //Add FocusListener
        btnCreateCustomer.addFocusListener(new ControllerScreenHome(this, idC, btnCreateCustomer, btnSimulateLoan,indicatorButton));
        btnSimulateLoan.addFocusListener(new ControllerScreenHome(this, idC, btnCreateCustomer, btnSimulateLoan,indicatorButton));
        
        this.setVisible(true);
    }

    public void getConsultantInfo(int IdC) {
        String[] consultant = bdd.getConsultantInfo(IdC);
        title.setText("Bienvenue " + consultant[1] + "  " + consultant[0]);
    }

    public void changeScreen(String login, String pwd) throws NoSuchAlgorithmException {
        String[] tab = bdd.Info_Consultant(login, pwd);
        title.setText("Bienvenue " + tab[1] + "  " + tab[0]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnSimulateLoan = new javax.swing.JButton();
        btnCreateCustomer = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        indicatorButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 200));

        btnSimulateLoan.setText("Simuler le prêt");

        btnCreateCustomer.setText("Créer un client");

        title.setText("TEST");

        jButton1.setText("Gestion des taux ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        indicatorButton.setText("Indicateurs");
        indicatorButton.setActionCommand("");
        indicatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indicatorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(133, 133, 133))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSimulateLoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreateCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(indicatorButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimulateLoan)
                    .addComponent(btnCreateCustomer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(indicatorButton))
                .addGap(10, 10, 10))
        );

        indicatorButton.getAccessibleContext().setAccessibleName("consulter les indicateurs");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFrame ScreenType = new ScreenType(idConsultant);
        ScreenType.setVisible(true);
        this.dispose();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void indicatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indicatorButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indicatorButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateCustomer;
    private javax.swing.JButton btnSimulateLoan;
    private javax.swing.JButton indicatorButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
