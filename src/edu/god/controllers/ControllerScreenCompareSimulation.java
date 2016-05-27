/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import static edu.god.serialisation.JsonEncoding.encodingSalaryCustomer;
import static edu.god.serialisation.JsonEncoding.encodingSimsLoan;
import edu.god.server.ClientJavaSelect;
import edu.god.views.ScreenCompareSimulation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author florent
 */
public class ControllerScreenCompareSimulation implements ActionListener, MouseListener {

    private final ScreenCompareSimulation screenCompareSimulation;
    private final String idCustomer;
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
    public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0, String idCustomer0,JButton btnClose0) {
        this.btnClose = btnClose0;
        this.screenCompareSimulation = screenCompareSimulation0;
        simulation = new ArrayList<>();
        this.idCustomer = idCustomer0;
    }

    public ControllerScreenCompareSimulation(ScreenCompareSimulation screenCompareSimulation0, JTable tableCompareSims0, String idCustomer0, JComboBox typeLoan0, JButton btnSubmit0) {
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
    public ControllerScreenCompareSimulation(JTable tableCompareSims0, ScreenCompareSimulation screenCompareSimulation0,String idCustomer0, JButton btnClose0) {
        System.out.println("ControllerScreenCompareSimulation");
        this.btnClose = btnClose0;
        this.tableCompareSims = tableCompareSims0;
        simulation = new ArrayList<>();
        this.screenCompareSimulation = screenCompareSimulation0;
        this.idCustomer = idCustomer0;

    }

    public String getDebtRatio(float debt) throws IOException, FileNotFoundException, ParseException {
        
        System.out.println("getDebtRation");
        String objetjson = ClientJavaSelect.clientTcpSelect("P", "3", encodingSalaryCustomer(this.idCustomer));
        
        System.out.println("idCustomer getDebtRatio="+ idCustomer);
        
        float salary = Float.parseFloat(objetjson);
        
        System.out.println("salary = " + salary);
        
        float debtRatio = debt/salary;
        System.out.println("Debt Ratio = " + debtRatio);
        int percentage = (int) Math.ceil((debtRatio) * 100);
        
        return Integer.toString(percentage);
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
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws org.json.simple.parser.ParseException
     */
    public void setListSimulation(Object ligne, Object capital, Object rate, Object monthlyLoan, Object monthlyInsurance, Object duration, Object totalPayment) throws IOException, FileNotFoundException, ParseException {
       
        float debt = Float.parseFloat((String) totalPayment) /Float.parseFloat((String) duration);
        System.out.println("Charge = "+debt);       
        
        simulation.clear();
        simulation.add(ligne.toString());
        simulation.add(capital.toString());
        simulation.add(rate.toString());
        simulation.add(monthlyLoan.toString());
        simulation.add(monthlyInsurance.toString());
        simulation.add(duration.toString());
        simulation.add(totalPayment.toString());
        simulation.add(this.getDebtRatio(debt));
        System.out.println("Nouvelle simulation prete ID = " + ligne.toString());

    }

    public ArrayList<String[]> getAllLoans() throws IOException, FileNotFoundException, org.json.simple.parser.ParseException, NoSuchAlgorithmException {
        ArrayList<String[]> tab = new ArrayList();
        System.out.println("Client numero = " + idCustomer + " type pret =" + typeLoan.getSelectedItem().toString());
        
        Object objetjson = ClientJavaSelect.clientTcpSelect("P", "2", encodingSimsLoan(idCustomer, typeLoan.getSelectedItem().toString()));
        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;
        
        for (int i = 1; i <= jsonObject.size(); i++) {
            String[] test = new String[8];
            switch (i) {
                case 1:
                    System.arraycopy(jsonObject.get("1").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 2:
                    System.arraycopy(jsonObject.get("2").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 3:
                    System.arraycopy(jsonObject.get("3").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 4:
                    System.arraycopy(jsonObject.get("4").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 5:
                    System.arraycopy(jsonObject.get("5").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 6:
                    System.arraycopy(jsonObject.get("6").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 7:
                    System.arraycopy(jsonObject.get("7").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 8:
                    System.arraycopy(jsonObject.get("8").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 9:
                    System.arraycopy(jsonObject.get("9").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                case 10:
                    System.arraycopy(jsonObject.get("10").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 8);
                    break;
                default:
                    break;
            }
            tab.add(test);
        }
        return tab;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            screenCompareSimulation.dispose();
            screenCompareSimulation.setVisible(false);
        } else if (e.getSource() == btnSubmit) {
            if (!typeLoan.getSelectedItem().toString().equals("Veullez selectionner un type de pret")) {
                try {
                    screenCompareSimulation.loadDataInTable((ArrayList<String[]>)this.getAllLoans().clone());
                } catch (IOException | ParseException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(ControllerScreenCompareSimulation.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            try {
                this.setListSimulation(tableCompareSims.getModel().getValueAt(
                        tableCompareSims.getSelectedRow(), 0),
                        tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 2),
                        tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 3),
                        tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 4),
                        tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 5),
                        tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 6),
                        tableCompareSims.getModel().getValueAt(tableCompareSims.getSelectedRow(), 7));
            } catch (IOException ex) {
                Logger.getLogger(ControllerScreenCompareSimulation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ControllerScreenCompareSimulation.class.getName()).log(Level.SEVERE, null, ex);
            }

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
