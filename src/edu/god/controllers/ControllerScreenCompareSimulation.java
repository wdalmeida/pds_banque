/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.models.AccessDB;
import edu.god.views.ScreenCompareSimulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author florent
 */
public class ControllerScreenCompareSimulation implements ActionListener, MouseListener {

    private JButton btnClose;
    private JButton btnCompareSimulation;
    private ScreenCompareSimulation screenCompareSimulation;
    private int idCustomer;
    private JTable tableCompareSims;
    private boolean firstSimulFill = false;
    private boolean secondSimulFill = false;
    private boolean thirdSimulFill = false;
    private List<String> simulation;
    private JComboBox typeLoan;
    private JButton btnSubmit;

    /**
     * @param screenCompareSimulation0
     * @param btnClose0
     */
    public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0, JButton btnClose0) {
        this.btnClose = btnClose0;
        this.screenCompareSimulation = screenCompareSimulation0;
        simulation = new ArrayList<>();
    }

    public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0,JTable tableCompareSims0, int idCustomer0, JComboBox typeLoan0, JButton btnSubmit0) {
        this.tableCompareSims = tableCompareSims0;
        this.typeLoan = typeLoan0;
        this.idCustomer = idCustomer0;
        this.btnSubmit = btnSubmit0;
        this.screenCompareSimulation = screenCompareSimulation0;

    }

    /**
     * use to fill up the JTable
     *
     * @param tableCompareSims0
     * @param btnClose0
     */
    public ControllerScreenCompareSimulation(JTable tableCompareSims0, JButton btnClose0) {
        this.btnClose = btnClose0;
        this.tableCompareSims = tableCompareSims0;
        simulation = new ArrayList<>();
    }


    /**
     * fill a temporary list to add to a simulation after
     *
     * @param ligne
     * @param capital
     * @param rate
     * @param monthlyLoan
     * @param monthlyInsurance
     * @param duration
     * @param totalPayment
     */
    public void setListSimulation(Object ligne, Object capital, Object rate, Object monthlyLoan, Object monthlyInsurance, Object duration, Object totalPayment) {
        simulation.add(ligne.toString());
        simulation.add(capital.toString());
        simulation.add(rate.toString());
        simulation.add(monthlyLoan.toString());
        simulation.add(monthlyInsurance.toString());
        simulation.add(duration.toString());
        simulation.add(totalPayment.toString());
        simulation.add("");
        System.out.println("List temp " + Arrays.toString(simulation.toArray()));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            screenCompareSimulation.dispose();
            screenCompareSimulation.setVisible(false);
        } else if (e.getSource() == btnSubmit) {
            System.out.println("Customer = "+idCustomer + " selectType "+typeLoan.getSelectedItem().toString());
            screenCompareSimulation.loadDataInTable(AccessDB.getAccessDB().getSimulationsLoanOfCustomer(idCustomer, typeLoan.getSelectedItem().toString()));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            if (!screenCompareSimulation.isSimulationfill(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0).toString())) {
                this.setListSimulation(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 2), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 3), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 4), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 5), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 6), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 7));
                if (!firstSimulFill && !secondSimulFill && !thirdSimulFill) {
                    screenCompareSimulation.setJlabelSimul(simulation);
                    firstSimulFill = true;
                    simulation.clear();
                } else if (firstSimulFill && !secondSimulFill && !thirdSimulFill) {
                    screenCompareSimulation.setJlabelSimul(simulation);
                    secondSimulFill = true;
                    simulation.clear();
                } else if (firstSimulFill && secondSimulFill && !thirdSimulFill) {
                    screenCompareSimulation.setJlabelSimul(simulation);
                    thirdSimulFill = true;
                    simulation.clear();
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
