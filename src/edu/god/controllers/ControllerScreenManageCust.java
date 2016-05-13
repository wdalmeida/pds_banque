/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import edu.god.entities.Customer;
import edu.god.models.AccessDB;
import edu.god.views.ScreenLoanSim;
import edu.god.views.ScreenManageCust;
import java.text.ParseException;

/**
 *
 * @author warren
 */
public class ControllerScreenManageCust implements ActionListener, MouseListener, KeyListener {

    private ScreenManageCust smc;
    private int idConsultant;
    private final JButton btnSubmit;
    private final JButton btnBack;
    private final JButton btnCreateCust;
    private final JButton btnUpdateCust;
    private final JButton btnDeleteCust;
    private final AccessDB db;
    private final JTextField firstName;
    private final JTextField lastName;
    private final JTextField postalCode;
    private final JTable tableCustomer;
    //private final ArrayList<Customer> tabArrayListCustomer;
    private final JLabel error;

    public ControllerScreenManageCust(ScreenManageCust smc0, JTextField lastName0, JTextField firstName0, JTextField txtPc, int idC0, JButton btnCreateCust0, JButton btnUpdateCust0, JButton btnDeleteCust0, JButton btnSubmit0, JButton btnBack0, JTable tableCust, JLabel lblError) {
        this.db = AccessDB.getAccessDB();
        this.btnBack = btnBack0;
        this.btnSubmit = btnSubmit0;
        this.firstName = firstName0;
        this.lastName = lastName0;
        this.btnCreateCust = btnCreateCust0;
        this.btnUpdateCust = btnUpdateCust0;
        this.btnDeleteCust = btnDeleteCust0;
        this.postalCode = txtPc;
        this.tableCustomer = tableCust;
        this.error = lblError;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList customers = new ArrayList();
        if (e.getSource() == btnSubmit) {
            System.out.println("bouton submit");
            btnCreateCust.setVisible(false);
            btnUpdateCust.setVisible(false);
            btnDeleteCust.setVisible(false);
            if (!lastName.getText().isEmpty() && !firstName.getText().isEmpty() && !lastName.getText().isEmpty()) {
                try {
                    customers = db.getCustomer(lastName.getText(), firstName.getText(), "450");
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //      tabArrayListCustomer = db.getCustomer(lastName.getText(),firstName.getText());
            System.out.println("reception donnes BDD");
            System.out.println(customers.toString());
            /*  int i = 0;
            Customer[][] data = (Customer[][]) new Object[tabArrayListCustomer.size()][14];
            for (Customer line : tabArrayListCustomer) {
                //data[i] = line;
                i++;
            }
            
            String title[] = {"ID", "Civilité", "Nom", "Prenom", "Salaire", "Rue", "Code Postal", "Ville", "Telephone", "Email", "Date de Naissance", "Proprietaire", "Nationalité", "Statut"};
            JTable tableau = new JTable(data, title);
            System.out.println("Jtable = "+ tableau.getSize());
            smc.getContentPane().add(new JScrollPane(tableau));*/
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            smc.dispose();
            try {
                ScreenLoanSim newWindow = new ScreenLoanSim(idConsultant, tableCustomer.getModel().getValueAt(tableCustomer.getSelectedRow(), 0).toString());
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(ControllerScreenExistingSim.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (postalCode.getText().length() >= 5) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
