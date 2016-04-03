/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Controller;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pds_banque.Model.AccessDB;
import pds_banque.View.ScreenHome;
import pds_banque.View.ScreenCreateCust;

/**
 *
 * @author florent
 */
public class ControllerScreenHome implements ActionListener {

    private AccessDB bdd;
    private JButton btnCreateCustomer;
    private JButton btnSimulateLoan;
    private ScreenHome ecranAccueil;
    private int idConsultant;

    /**
     *
     * @param ecranAccueil0
     * @param idC
     * @param btnCreateCustomer0
     * @param btnSimulateLoan0
     */
    public ControllerScreenHome(ScreenHome ecranAccueil0,int idC,JButton btnCreateCustomer0, JButton btnSimulateLoan0) {
        this.bdd = AccessDB.getAccessDB();
        this.btnCreateCustomer = btnCreateCustomer0;
        this.btnSimulateLoan = btnSimulateLoan0;
        this.ecranAccueil = ecranAccueil0;
        this.idConsultant = idC;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreateCustomer) {
            try {
                this.ecranAccueil.dispose();
                ecranAccueil.setVisible(false);
                ScreenCreateCust fen2 = new ScreenCreateCust(idConsultant);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenHome.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (e.getSource() == btnSimulateLoan) {
            System.out.println("bouton loan");
        }
    }
}
