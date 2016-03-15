/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Controller;

import java.awt.event.*;
import javax.swing.*;
import pds_banque.Model.AccesBDD;
import pds_banque.View.ScreenHome;
import pds_banque.View.ScreenCreateCust;

/**
 *
 * @author florent
 */
public class ControllerScreenHome implements ActionListener {

    private AccesBDD bdd;
    private JButton btnCreateCustomer;
    private JButton btnSimulateLoan;
    private ScreenHome ecranAccueil;

    /**
     *
     * @param ecranAccueil0
     * @param btnCreateCustomer0
     * @param btnSimulateLoan0
     */
    public ControllerScreenHome(ScreenHome ecranAccueil0,JButton btnCreateCustomer0, JButton btnSimulateLoan0) {
        this.bdd = AccesBDD.getAccesBDD();
        this.btnCreateCustomer = btnCreateCustomer0;
        this.btnSimulateLoan = btnSimulateLoan0;
        this.ecranAccueil = ecranAccueil0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCreateCustomer) {
            this.ecranAccueil.dispose();
            ecranAccueil.setVisible(false);
            ScreenCreateCust fen2 = new ScreenCreateCust();
            fen2.insertion();
            
        } else if (e.getSource() == btnSimulateLoan) {
            System.out.println("bouton loan");
        }
    }
}