/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.models.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import edu.god.entities.*;
import edu.god.views.*;
import edu.god.views.ScreenCreateCust;
import org.jdesktop.swingx.*;

import org.json.simple.parser.ParseException;
import static edu.god.serialisation.JsonEncoding.encodageCustomer;
import edu.god.server.*;
import java.sql.SQLException;

/**
 *
 * @author florent
 */
public class ControllerScreenCreateCust implements ActionListener {

    private AccessDB bdd;
    private JXDatePicker birthday;
    private final JButton btnBack;
    private final JButton btnSubmit;
    private JTextField city;
    private JComboBox title;
    private JTextField email;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField nationality;
    private JCheckBox owner;
    private JTextField phoneNumber;
    private JTextField postalCode;
    private JTextField salary;
    private JComboBox status;
    private JTextField street;
    private Customer customer;
    private final ScreenCreateCust scc;
    private final int idConsultant;
    private boolean goToSim = false;

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

    public ControllerScreenCreateCust(ScreenCreateCust scc0, JComboBox title0, JTextField lastName0, JTextField firstName0, JXDatePicker birthday0, JTextField nationality0, JTextField phoneNumber0, JTextField email0, JCheckBox owner0, JTextField salary0, JComboBox status0, JTextField street0, JTextField city0, JTextField postalCode0, int idC0, JButton btnSubmit0, JButton btnBack0, boolean sim) {
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
        goToSim = sim;
    }

    @Override
    public void actionPerformed(ActionEvent e) { // TODO SION VIENS DE SIMULATION ALLER SUR LOAN SIM SINON RETOUR SUR ACUEUIL
        //int res;
        if (e.getSource() == btnSubmit) {
            try {

                this.customer = new Customer(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), birthday.getDate(), nationality.getText(), phoneNumber.getText(), email.getText(), owner.isValid(), Float.valueOf(salary.getText()), status.getSelectedIndex(), street.getText(), city.getText(), postalCode.getText().substring(0, 5), idConsultant, -1); // -1 default number
                java.sql.Date sqlDate = new java.sql.Date(birthday.getDate().getTime());
                String dateString = sqlDate.toString();

                ClientJavaInsert.clientTcpInsert(encodageCustomer(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), Float.valueOf(salary.getText()), street.getText(), postalCode.getText().substring(0, 5), city.getText(), phoneNumber.getText(), email.getText(), dateString, owner.isValid(), nationality.getText(), idConsultant, -1, status.getSelectedIndex()));
                String idCustomer = bdd.getIDCustomer(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), Float.valueOf(salary.getText()), street.getText(), postalCode.getText().substring(0, 5), city.getText(), phoneNumber.getText(), email.getText(), dateString, owner.isValid(), nationality.getText(), idConsultant, -1, status.getSelectedIndex());
                JOptionPane.showMessageDialog(scc, "Le client a été ajouté", "Ajout client", JOptionPane.INFORMATION_MESSAGE);
                if(goToSim){
                scc.dispose();
                ScreenLoanSim newWindow = new ScreenLoanSim(idConsultant,idCustomer,false);
                }
                //Object obj = encodageCustomer(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), Float.valueOf(salary.getText()), street.getText(), postalCode.getText().substring(0, 5), city.getText(), phoneNumber.getText(), email.getText(), dateString, owner.isValid(), nationality.getText(), idConsultant, -1, status.getSelectedIndex());

                // if(birthday.getDate() instanceof DATE && Integer.parseInt(salary.getText()) instanceof(Integer) && Integer.parseInt(phoneNumber.getText()) instanceof Integer && Integer.parseInt(postalCode.getText()) instanceof Integer)
                //  {
                /*try {
                 //System.out.println("Civilité = " + title.getSelectedItem().toString() + " Nom = " + lastName.getText() + " Prenom = " + firstName.getText() + " date de Naissance = " + birthday.getDate() + " Nationalite " + nationality.getText() + " telephone = " + phoneNumber.getText() + " email =" + email.getText() + " Proprietaire = "+owner.isSelected()+ "Salaire = " + Float.valueOf(salary.getText()) + " statut = " + status.getSelectedItem().toString() + " rue = " + street.getText() + " ville= " + city.getText() + " CP = " + postalCode.getText());
                 res = bdd.insertCustomer(this.customer, idConsultant);

                 if (res == 1) {
                 this.scc.dispose();
                 scc.setVisible(false);
                 ScreenHome fen2 = new ScreenHome(idConsultant);
                 } else {
                 System.out.println("insertio ko");
                 }
                 /*  }else {
                 // here mesagge for wrong format of birthday or salary or phonenumber or Postal code
                 }
                 } catch (NoSuchAlgorithmException ex) {
                 Logger.getLogger(ControllerScreenCreateCust.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
            } catch (IOException | ParseException | SQLException | java.text.ParseException ex) {
                Logger.getLogger(ControllerScreenCreateCust.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnBack) {
            this.scc.dispose();
            scc.setVisible(false);
            ScreenHome screenHome = new ScreenHome(idConsultant);

        }
    }

}
