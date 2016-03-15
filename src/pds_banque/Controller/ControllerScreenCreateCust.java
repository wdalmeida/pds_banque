/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Controller;

import java.awt.event.*;
import javax.swing.*;
import pds_banque.Customer;
import pds_banque.Model.*;
import pds_banque.View.ScreenHome;
import pds_banque.View.ScreenCreateCust;

/**
 *
 * @author florent
 */
public class ControllerScreenCreateCust implements ActionListener {

    private AccesBDD bdd;
    private javax.swing.JTextField birthday;
    private javax.swing.JTextField city;
    private javax.swing.JTextField email;
    private javax.swing.JTextField firstName;
    private javax.swing.JTextField lastName;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField postalCode;
    private javax.swing.JTextField salary;
    private javax.swing.JTextField street;
    private JButton btnSubmit;
    private JButton btnBack;
    private Customer customer;
    private ScreenCreateCust scc;

    public ControllerScreenCreateCust(JButton back, JButton submit) {
        this.btnSubmit = submit;
        this.btnBack = back;
    }

    public ControllerScreenCreateCust(ScreenCreateCust scc0, JTextField city, JTextField email, JTextField firstName, JTextField lastName, JTextField phoneNumber, JTextField postalCode, JTextField salary, JTextField street, JButton submit, JButton back) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
        this.btnSubmit = submit;
        this.btnBack = back;
        this.scc = scc0;
        this.bdd = AccesBDD.getAccesBDD();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int res;
        int idConsultant =bdd.getIDConsultant(this.customer);
        if (e.getSource() == btnSubmit) {
            this.customer = new Customer(lastName.getText(), firstName.getText(), email.getText(), street.getText(), city.getText(), postalCode.getText(), salary.getText(), phoneNumber.getText());
            res = bdd.insertCustomer(this.customer);
            
            if (res == 1) {
                this.scc.dispose();
                scc.setVisible(false);
                ScreenHome fen2 = new ScreenHome(idConsultant);
            } else {
                System.out.println("insertio ko");
            }
        } else if (e.getSource() == btnBack) {
            ScreenHome screenHome = new ScreenHome(idConsultant);

        }
    }
}
