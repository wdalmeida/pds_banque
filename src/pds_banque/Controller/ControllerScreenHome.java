/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Controller;

import java.awt.event.*;
import javax.swing.*;
import pds_banque.View.ScreenHome;
import pds_banque.View.ScreenManageCust;

/**
 *
 * @author florent
 */
public class ControllerScreenHome implements ActionListener {

    private JButton btnManageCustomer;
    private JButton btnSimulateLoan;
    private ScreenHome screenHome;
    private int idConsultant;

    /**
     *
     * @param screenHome
     * @param idC
     * @param btnManageCustomer0
     * @param btnSimulateLoan0
     */
    public ControllerScreenHome(ScreenHome screenHome, int idC, JButton btnManageCustomer0, JButton btnSimulateLoan0) {
        this.btnManageCustomer = btnManageCustomer0;
        this.btnSimulateLoan = btnSimulateLoan0;
        this.screenHome = screenHome;
        this.idConsultant = idC;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnManageCustomer) {
            this.screenHome.dispose();
            screenHome.setVisible(false);
            ScreenManageCust fen2 = new ScreenManageCust(idConsultant);

        } else if (e.getSource() == btnSimulateLoan) {
            System.out.println("button loan");
        }
    }
}
