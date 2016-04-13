/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pds_banque.Controller;

import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import pds_banque.Customer;
import pds_banque.Model.*;
import pds_banque.View.ScreenHome;
import pds_banque.View.ScreenCreateCust;
import org.jdesktop.swingx.*;

/**
 *
 * @author florent
 */
public class ControllerScreenCreateCust implements ActionListener {

    private AccessDB bdd;
    private JXDatePicker birthday;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JTextField city;
    private javax.swing.JComboBox title;
    private javax.swing.JTextField email;
    private javax.swing.JTextField firstName;
    private javax.swing.JTextField lastName;
    private javax.swing.JTextField nationality;
    private javax.swing.JCheckBox owner;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField postalCode;
    private javax.swing.JTextField salary;
    private javax.swing.JComboBox status;
    private javax.swing.JTextField street;
    private Customer customer;
    private ScreenCreateCust scc;
    private int idConsultant;

    public ControllerScreenCreateCust(ScreenCreateCust scc0, int idConsultant0, JButton back, JButton submit) {
        this.btnSubmit = submit;
        this.btnBack = back;
        this.scc = scc0;
        this.idConsultant = idConsultant0;

    }

    public ControllerScreenCreateCust(ScreenCreateCust scc0, JComboBox title0, JTextField lastName0, JTextField firstName0, JXDatePicker birthday0, JTextField nationality0, JTextField phoneNumber0, JTextField email0, JCheckBox owner0, JTextField salary0, JComboBox status0, JTextField street0, JTextField city0, JTextField postalCode0, int idC0, JButton btnSubmit0, JButton btnBack0) {
        this.btnSubmit = btnSubmit0;
        this.btnBack = btnBack0;
        this.scc = scc0;
        this.idConsultant = idC0;
        this.bdd = AccessDB.getAccessDB();
        this.title = title0;
        this.lastName = lastName0;
        this.firstName = firstName0;
        this.birthday = birthday0;
        this.nationality = nationality0;
        this.phoneNumber = phoneNumber0;
        this.email = email0;
        this.owner = owner0;
        this.salary = salary0;
        this.status = status0;
        this.street = street0;
        this.city = city0;
        this.postalCode = postalCode0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int res;
        if (e.getSource() == btnSubmit) {
            this.customer = new Customer(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), birthday.getDate(), nationality.getText(), phoneNumber.getText(), email.getText(), owner.isValid(), Float.valueOf(salary.getText()), status.getSelectedIndex(), street.getText(), city.getText(), postalCode.getText().substring(0, 5), idConsultant, -1); // -1 default number 
            // if(birthday.getDate() instanceof DATE && Integer.parseInt(salary.getText()) instanceof(Integer) && Integer.parseInt(phoneNumber.getText()) instanceof Integer && Integer.parseInt(postalCode.getText()) instanceof Integer)
            //  {
            try {
                //System.out.println("Civilité = " + title.getSelectedItem().toString() + " Nom = " + lastName.getText() + " Prenom = " + firstName.getText() + " date de Naissance = " + birthday.getDate() + " Nationalite " + nationality.getText() + " telephone = " + phoneNumber.getText() + " email =" + email.getText() + " Proprietaire = "+owner.isSelected()+ "Salaire = " + Float.valueOf(salary.getText()) + " statut = " + status.getSelectedItem().toString() + " rue = " + street.getText() + " ville= " + city.getText() + " CP = " + postalCode.getText());
                res = bdd.insertCustomer(this.customer, idConsultant);

                if (res == 1) {
                    JOptionPane.showMessageDialog(scc,"Le client a été enregistré");
                    this.scc.dispose();
                    scc.setVisible(false);
                    ScreenHome fen2 = new ScreenHome(idConsultant);
                } else {
                    System.out.println("insertio ko");
                }
                /*  }else {
                 // here mesagge for wrong format of birthday or salary or phonenumber or Postal code
                 }*/
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerScreenCreateCust.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnBack) {
            this.scc.dispose();
            scc.setVisible(false);
            ScreenHome screenHome = new ScreenHome(idConsultant);

        }
    }
}
