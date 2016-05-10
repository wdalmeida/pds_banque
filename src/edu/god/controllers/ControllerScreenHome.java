/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import edu.god.models.AccessDB;
import edu.god.views.ScreenHome;
import edu.god.views.ScreenCreateCust;
import edu.god.views.ScreenManageCust;

/**
 *
 * @author florent
 */
public class ControllerScreenHome implements ActionListener {

    private AccessDB bdd;
    private JButton btnCreateCustomer;
    private JButton btnSimulateLoan;
    private ScreenHome screenHome;
    private int idConsultant;

    /**
     *
     * @param screenHome0
     * @param idC
     * @param btnCreateCustomer0
     * @param btnSimulateLoan0
     */
    public ControllerScreenHome(ScreenHome screenHome0,int idC,JButton btnCreateCustomer0, JButton btnSimulateLoan0) {
        this.bdd = AccessDB.getAccessDB();
        this.btnCreateCustomer = btnCreateCustomer0;
        this.btnSimulateLoan = btnSimulateLoan0;
        this.screenHome = screenHome0;
        this.idConsultant = idC;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreateCustomer) {
            try {
                this.screenHome.dispose();
                screenHome.setVisible(false);
                ScreenCreateCust fen2 = new ScreenCreateCust(idConsultant);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenHome.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (e.getSource() == btnSimulateLoan) {
            System.out.println("pds_banque.Controller.ControllerScreenHome.actionPerformed()");
            screenHome.dispose();
            screenHome.setVisible(false);
            ScreenManageCust fen2 = new ScreenManageCust(idConsultant);
        }
    }
}
