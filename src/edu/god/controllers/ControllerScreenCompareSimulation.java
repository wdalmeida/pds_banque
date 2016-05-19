/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.views.ScreenCompareSimulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author florent
 */
public class ControllerScreenCompareSimulation implements ActionListener {

    private JButton btnCompareSimulation;
    private JButton btnClose;
    private ScreenCompareSimulation screenCompareSimulation;
    private int idCustomer;

    /**
     * @param idCustomer0
     * @param btnCompareSimulation0
     */
    public ControllerScreenCompareSimulation(int idCustomer0, JButton btnCompareSimulation0) {
        this.btnCompareSimulation = btnCompareSimulation0;
        this.idCustomer = idCustomer0;
    }

   public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0, JButton btnClose0) {
        this.btnClose = btnClose0;
        this.screenCompareSimulation = screenCompareSimulation0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCompareSimulation) {
            try {
                ScreenCompareSimulation scs = new ScreenCompareSimulation(idCustomer);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnClose) {
            screenCompareSimulation.dispose();
            screenCompareSimulation.setVisible(false);
        }
    }
}
