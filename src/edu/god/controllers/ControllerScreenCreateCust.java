/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

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
import static edu.god.serialisation.JsonEncoding.*;
import edu.god.server.*;
import java.sql.SQLException;

/**
 *
 * @author florent
 */
public class ControllerScreenCreateCust implements ActionListener {

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
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSubmit) {

            try {
                this.customer = new Customer(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), birthday.getDate(), nationality.getText(), phoneNumber.getText(), email.getText(), owner.isValid(), Float.valueOf(salary.getText()), status.getSelectedIndex(), street.getText(), city.getText(), postalCode.getText().substring(0, 5), idConsultant, -1); // -1 default number
                java.sql.Date sqlDate = new java.sql.Date(birthday.getDate().getTime());
                String dateString = sqlDate.toString();

                String idCustomer = ClientJavaSelect.clientTcpSelect("D", "4", encodingCustomerId(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), Float.valueOf(salary.getText()), street.getText(), postalCode.getText().substring(0, 5), city.getText(), phoneNumber.getText(), email.getText(), dateString, owner.isValid(), nationality.getText(), idConsultant, status.getSelectedIndex()));
                ClientJavInsert.clientTcpInsert("L", "1", encodageCustomer(title.getSelectedItem().toString(), lastName.getText(), firstName.getText(), Float.valueOf(salary.getText()), street.getText(), postalCode.getText().substring(0, 5), city.getText(), phoneNumber.getText(), email.getText(), dateString, owner.isValid(), nationality.getText(), idConsultant, -1, status.getSelectedIndex()));

                JOptionPane.showMessageDialog(scc, "Le client a été ajouté", "Ajout client", JOptionPane.INFORMATION_MESSAGE);
                // if the boolean is true, the next window will be loan simulation
                if (goToSim) {
                    scc.dispose();
                    ScreenLoanSim newWindow = new ScreenLoanSim(idConsultant, idCustomer, false);
                }
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
