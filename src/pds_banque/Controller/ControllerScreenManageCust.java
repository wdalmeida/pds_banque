/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Controller;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pds_banque.Customer;
import pds_banque.Model.AccessDB;
import pds_banque.View.ScreenManageCust;

/**
 *
 * @author florent
 */
public class ControllerScreenManageCust implements ActionListener {

    private ScreenManageCust smc;
    private int idConsultant;
    private JButton btnSubmit;
    private JButton btnBack;
    private JButton btnCreateCust;
    private JButton btnUpdateCust;
    private JButton btnDeleteCust;
    private AccessDB db;
    private JTextField firstName;
    private JTextField lastName;
    private JTable tableCustomer;
    private ArrayList<Customer> tabArrayListCustomer;

    public ControllerScreenManageCust(ScreenManageCust smc0, JTextField lastName0, JTextField firstName0, int idC0, JButton btnCreateCust0, JButton btnUpdateCust0, JButton btnDeleteCust0, JButton btnSubmit0, JButton btnBack0) {
        this.db = AccessDB.getAccessDB();
        this.btnBack = btnBack0;
        this.btnSubmit = btnSubmit0;
        this.firstName = firstName0;
        this.lastName = lastName0;
        this.btnCreateCust = btnCreateCust0;
        this.btnUpdateCust = btnUpdateCust0;
        this.btnDeleteCust = btnDeleteCust0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            System.out.println("bouton submit");
            btnCreateCust.setVisible(false);
            btnUpdateCust.setVisible(false);
            btnDeleteCust.setVisible(false);
            try {
                tabArrayListCustomer = db.getCustomer(lastName.getText(),firstName.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("reception donnes BDD");
            int i = 0;
            Customer[][] data = (Customer[][]) new Object[tabArrayListCustomer.size()][14];
            for (Customer line : tabArrayListCustomer) {
                //data[i] = line;
                i++;
            }
            
            String title[] = {"ID", "Civilité", "Nom", "Prenom", "Salaire", "Rue", "Code Postal", "Ville", "Telephone", "Email", "Date de Naissance", "Proprietaire", "Nationalité", "Statut"};
            JTable tableau = new JTable(data, title);
            System.out.println("Jtable = "+ tableau.getSize());
            smc.getContentPane().add(new JScrollPane(tableau));
        }
    }
}
