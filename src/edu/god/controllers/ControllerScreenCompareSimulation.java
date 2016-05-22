/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.views.ScreenCompareSimulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 *
 * @author florent
 */
public class ControllerScreenCompareSimulation implements ActionListener, MouseListener {
    private JButton btnClose;
    private ScreenCompareSimulation screenCompareSimulation;
    private int idCustomer;
    private JTable tableCompareSims;
    private boolean firstSimul = false;
    private boolean secondSimul = false;
    private boolean thirdSimul = false;
    private List<String> simulation;

    /**
     * @param screenCompareSimulation0
     * @param btnClose0
     */
    public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0, JButton btnClose0) {
        this.btnClose = btnClose0;
        this.screenCompareSimulation = screenCompareSimulation0;
        simulation = screenCompareSimulation.fillemptyList();
    }

    public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0, JTable tableCompareSims0, JButton btnClose0) {
        this.btnClose = btnClose0;
        this.screenCompareSimulation = screenCompareSimulation0;
        this.tableCompareSims = tableCompareSims0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource() == btnClose) {
            screenCompareSimulation.dispose();
            screenCompareSimulation.setVisible(false);
        }
    }

    public void setListSimulation(Object ligne, Object capital, Object rate, Object monthlyLoan, Object monthlyInsurance, Object duration, Object totalPayment) {

        simulation.add(ligne.toString());
        simulation.add(capital.toString());
        simulation.add(rate.toString());
        simulation.add(monthlyLoan.toString());
        simulation.add(monthlyInsurance.toString());
        simulation.add(duration.toString());
        simulation.add(totalPayment.toString());
        simulation.add("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            if (!screenCompareSimulation.isSimulationfill(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0).toString())) {
                this.setListSimulation(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 2), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 3), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 4), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 5), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 6), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 7));

                if (!firstSimul && !secondSimul && !thirdSimul) {
                    screenCompareSimulation.setJlabelSimul(simulation);
                    firstSimul = true;
                } else if (firstSimul && !secondSimul && !thirdSimul) {
                    screenCompareSimulation.setJlabelSimul(simulation);
                    secondSimul = true;
                } else if (firstSimul && secondSimul && !thirdSimul) {
                    screenCompareSimulation.setJlabelSimul(simulation);
                    thirdSimul = true;
                }
            } else if (screenCompareSimulation.isSimulationfill(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0).toString())) {
                this.setListSimulation("", "", "", "", "", "", "");
                screenCompareSimulation.setSimulOrder(simulation, tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0).toString());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
