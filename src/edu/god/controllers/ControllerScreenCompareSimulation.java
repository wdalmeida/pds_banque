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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author florent
 */
public class ControllerScreenCompareSimulation implements ActionListener, MouseListener {

    private final ScreenCompareSimulation screenCompareSimulation;
    private int idCustomer;
    private int choix;
    private JTable tableCompareSims;
    private boolean firstSimulFill = false;
    private boolean secondSimulFill = false;
    private boolean thirdSimulFill = false;
    private boolean simulInserted = false;
    private ArrayList<String> simulation;
    private JComboBox typeLoan;
    private JButton btnClose;
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
     * @param screenCompareSimulation0
     * @param btnClose0
     */
    public ControllerScreenCompareSimulation(JTable tableCompareSims0, ScreenCompareSimulation screenCompareSimulation0, JButton btnClose0) {
        System.out.println("ControllerScreenCompareSimulation");
        this.btnClose = btnClose0;
        this.tableCompareSims = tableCompareSims0;
        simulation = new ArrayList<>();
        this.screenCompareSimulation = screenCompareSimulation0;

    }
    public String getDebtRatio(double debt)
    {
        float salary = AccessDB.getAccessDB().getSalaryOfCustomer(idCustomer);
        double debtRatio = salary+debt;
        System.out.println("Debt Ration = "+ debtRatio);
        return ""+debtRatio;
        
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
        //float monthyloan =Float.parseFloat(0.2);
        System.out.println("monthly loan = "+monthlyLoan.toString().trim().substring(0,monthlyLoan.toString().length()-2).replace(",",".").replace(" ",""));
        float debt = Float.parseFloat(rate.toString().trim()) + Float.parseFloat(monthlyInsurance.toString().trim());
        simulation.clear();
        simulation.add(ligne.toString());
        simulation.add(capital.toString());
        simulation.add(rate.toString());
        simulation.add(monthlyLoan.toString());
        simulation.add(monthlyInsurance.toString());
        simulation.add(duration.toString());
        simulation.add(totalPayment.toString());
        simulation.add("");
        System.out.println("Nouvelle simulation prete ID = " + ligne.toString());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            screenCompareSimulation.dispose();
            screenCompareSimulation.setVisible(false);
        } else if (e.getSource() == btnSubmit) {
            if (!typeLoan.getSelectedItem().toString().equals("Veullez selectionner un type de pret")) {
                screenCompareSimulation.loadDataInTable(
                        AccessDB.getAccessDB().getSimulationsLoanOfCustomer(
                                idCustomer, typeLoan.getSelectedItem().toString()));

            } else if (typeLoan.getSelectedItem().toString().equals("Veullez selectionner un type de pret")) {
                JOptionPane.showMessageDialog(
                        screenCompareSimulation,
                        "Veullez selectionner un type de pret",
                        "Veullez selectionner un type de pret",
                        JOptionPane.INFORMATION_MESSAGE); // indicate that the selection is wrong and met the user try again                             
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Simulation boolean =" + firstSimulFill + secondSimulFill + thirdSimulFill);
        if (e.getClickCount() == 2) {
            System.out.println("suppression en cours");
            simulInserted = screenCompareSimulation.isSimulationfill(
                    tableCompareSims.getModel().getValueAt(
                            tableCompareSims.getSelectedRow(), 0).toString());

            if (simulInserted) {
                choix = screenCompareSimulation.getSimulation(
                        tableCompareSims.getModel().getValueAt(
                                tableCompareSims.getSelectedRow(), 0).toString());
                System.out.println("Simulation dans la JFrame a supprimer = " + choix);
                switch (choix) {
                    case 1:
                        if (!secondSimulFill && !thirdSimulFill) {
                            firstSimulFill = false;
                        } else if (secondSimulFill && thirdSimulFill) {
                            thirdSimulFill = false;
                        } else if (secondSimulFill && !thirdSimulFill) {
                            secondSimulFill = false;
                        }
                        screenCompareSimulation.removeSimulation(
                                tableCompareSims.getModel().getValueAt(
                                        tableCompareSims.getSelectedRow(), 0).toString());
                        break;
                    case 2:
                        if (!thirdSimulFill) {
                            secondSimulFill = false;
                        } else if (thirdSimulFill) {
                            thirdSimulFill = false;
                        }
                        screenCompareSimulation.removeSimulation(
                                tableCompareSims.getModel().getValueAt(
                                        tableCompareSims.getSelectedRow(), 0).toString());
                        break;
                    case 3:
                        thirdSimulFill = false;
                        screenCompareSimulation.removeSimulation(
                                tableCompareSims.getModel().getValueAt(
                                        tableCompareSims.getSelectedRow(), 0).toString());
                        break;
                }
            }
        } else if (e.getClickCount() == 1) {
            System.out.println("insertion en cours");
            this.setListSimulation(tableCompareSims.getModel().getValueAt(
                    tableCompareSims.getSelectedRow(), 0),
                    tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 2),
                    tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 3),
                    tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 4),
                    tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 5),
                    tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 6),
                    tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 7));

            simulInserted = screenCompareSimulation.isSimulationfill(
                    tableCompareSims.getModel().getValueAt(
                            tableCompareSims.getSelectedRow(), 0).toString());

            System.out.println("ID existant " + simulInserted);
            if (!firstSimulFill && !secondSimulFill && !thirdSimulFill && !simulInserted) {
                System.out.println("1 er if");
                screenCompareSimulation.setJlabelSimul(simulation, 1);
                firstSimulFill = true;
                simulation.clear();
                simulInserted = false;
            } else if (firstSimulFill && !simulInserted && !secondSimulFill && !thirdSimulFill) {
                System.out.println("2 eme if");
                screenCompareSimulation.setJlabelSimul(simulation, 2);
                secondSimulFill = true;
                simulation.clear();
                simulInserted = false;
            } else if (firstSimulFill && secondSimulFill && !thirdSimulFill && !simulInserted) {
                System.out.println("3ème if");
                screenCompareSimulation.setJlabelSimul(simulation, 3);
                thirdSimulFill = true;
                simulation.clear();
                simulInserted = false;
            } else if (firstSimulFill && secondSimulFill && thirdSimulFill && simulInserted) {
                /*JOptionPane.showMessageDialog(
                        screenCompareSimulation,
                        "Veuillez deselectionner une simulation pour afficher la derniere simulation selectioner",
                        "Veuillez deselectionner une simulation pour afficher la derniere simulation selectionner",
                        JOptionPane.INFORMATION_MESSAGE); // indicate that the selection is wrong and met the user try again                             
                 */
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
