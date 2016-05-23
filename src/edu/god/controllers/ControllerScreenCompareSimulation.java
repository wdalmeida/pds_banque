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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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

    public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0, JTable tableCompareSims0, int idCustomer0, JComboBox typeLoan0, JButton btnSubmit0) {
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
            if (!typeLoan.getSelectedItem().toString().equals("Veullez selectionner un type de pret")) {
                screenCompareSimulation.loadDataInTable(AccessDB.getAccessDB().getSimulationsLoanOfCustomer(idCustomer, typeLoan.getSelectedItem().toString()));
            } else if (typeLoan.getSelectedItem().toString().equals("Veullez selectionner un type de pret")) {
                JOptionPane.showMessageDialog(screenCompareSimulation, "Veullez selectionner un type de pret", "Veullez selectionner un type de pret", JOptionPane.INFORMATION_MESSAGE); // indicate that the selection is wrong and met the user try again                             
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            this.setListSimulation(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 2), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 3), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 4), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 5), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 6), tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 7));
            if (!firstSimulFill && !secondSimulFill && !thirdSimulFill) {
                System.out.println("3 liste false");
                screenCompareSimulation.setJlabelSimul(simulation);
                System.out.println("apres setJLabel");
                firstSimulFill = true;
                simulation.clear();
                System.out.println("simulation Clear");
            } else if (firstSimulFill && !secondSimulFill && !thirdSimulFill && !screenCompareSimulation.isSimulationfill(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0).toString())) {
                System.out.println("first true");
                screenCompareSimulation.setJlabelSimul(simulation);
                secondSimulFill = true;
                simulation.clear();
            } else if (firstSimulFill && secondSimulFill && !thirdSimulFill && !screenCompareSimulation.isSimulationfill(tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0).toString())) {
                System.out.println("third false");
                screenCompareSimulation.setJlabelSimul(simulation);
                thirdSimulFill = true;
                simulation.clear();
            } else if (firstSimulFill && secondSimulFill && thirdSimulFill) {
                JOptionPane.showMessageDialog(screenCompareSimulation, "Veuillez deselectionner une simulation pour afficher la derniere selectionner", "Veuillez deselectionner une simulation pour afficher la derniere selectionner", JOptionPane.INFORMATION_MESSAGE); // indicate that the selection is wrong and met the user try again                             

            }
            //screenCompareSimulation.setSimulOrder(simulation, tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 0).toString());
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
