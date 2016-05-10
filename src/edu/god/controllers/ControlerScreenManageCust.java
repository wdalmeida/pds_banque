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
import edu.god.models.AccessDB;
import edu.god.views.ScreenCreateCust;
import edu.god.views.ScreenExistingSim;
import edu.god.views.ScreenManageCust;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author warren
 */
public class ControlerScreenManageCust implements ActionListener, MouseListener {

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
    private final JLabel error;

    public ControlerScreenManageCust(ScreenManageCust smc0, JTextField lastName0, JTextField firstName0, JTextField aPostalCode, int idC0, JButton btnCreateCust0, JButton btnUpdateCust0, JButton btnDeleteCust0, JButton btnSubmit0, JButton btnBack0, JTable table, JLabel lblError) {
        this.db = AccessDB.getAccessDB();
        this.btnBack = btnBack0;
        this.btnSubmit = btnSubmit0;
        this.firstName = firstName0;
        this.lastName = lastName0;
        postalCode = aPostalCode;
        this.btnCreateCust = btnCreateCust0;
        this.btnUpdateCust = btnUpdateCust0;
        this.btnDeleteCust = btnDeleteCust0;
        tableCustomer = table;
        error = lblError;
        smc = smc0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            lastName.setBorder(UIManager.getBorder("TextField.border"));
            firstName.setBorder(UIManager.getBorder("TextField.border"));
            postalCode.setBorder(UIManager.getBorder("TextField.border"));
            error.setText("");

            System.out.println("bouton submit");
            btnCreateCust.setVisible(false);
            btnUpdateCust.setVisible(false);
            btnDeleteCust.setVisible(false);
            ArrayList<String[]> customers = search();

            if (customers != null) {
                if (!customers.isEmpty()) {
                    System.out.println("Customers = " + Arrays.toString(customers.get(0)));
                    loadDataInTable(customers);
                } else {
                    DefaultTableModel dtm = (DefaultTableModel) tableCustomer.getModel();
                    dtm.setRowCount(0);
                    String option[] = {"Ajouter un client", "Retour"};
                    int click = JOptionPane.showOptionDialog(smc, "Aucun client ne correspond à votre recherche", "Client inconnu",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            option,
                            option[1]);
                    if (click == JOptionPane.YES_OPTION) {
                        smc.dispose();
                        try {
                            ScreenCreateCust newWindow = new ScreenCreateCust(idConsultant);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControlerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        } else if (e.getSource() == btnCreateCust) {
            smc.dispose();
            try {
                ScreenCreateCust newWindow = new ScreenCreateCust(idConsultant);
            } catch (SQLException ex) {
                Logger.getLogger(ControlerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void loadDataInTable(ArrayList<String[]> customers) {
        String title[] = {"ID", "Civilité", "Nom", "Prenom", "Rue", "Code postal", "Ville", "Telephone", "Email", "Date de Naissance", "Nationalité"};
        DefaultTableModel model = new DefaultTableModel(title, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        for (String[] cu : customers) {
            model.addRow(cu);
        }
        tableCustomer.setModel(model);
        TableColumnModel tcm = tableCustomer.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));

    }

    private ArrayList<String[]> search() {
        ArrayList<String[]> customers = null;
        //check if PC = 5 figures
        //check last first = regex *[a-z][A-Z][-']
        if (!lastName.getText().isEmpty() && !firstName.getText().isEmpty() && !postalCode.getText().isEmpty()) {
            System.out.println("Les 3 sont non vides");
            try {
                customers = db.getCustomer(lastName.getText(), firstName.getText(), postalCode.getText());
                System.out.println("Nom - prenom - code postal");
            } catch (SQLException ex) {
                Logger.getLogger(ControlerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            error.setText("Veuillez saisir tous les champs");
            if (lastName.getText().isEmpty()) {
                lastName.requestFocus();
                lastName.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (firstName.getText().isEmpty()) {
                if (!lastName.isFocusOwner()) {
                    firstName.requestFocus();
                }
                firstName.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (postalCode.getText().isEmpty()) {
                if (!lastName.isFocusOwner() || !firstName.isFocusOwner()) {
                    postalCode.requestFocus();
                }
                postalCode.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        return customers;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            smc.dispose();
            ScreenExistingSim newWindow = new ScreenExistingSim(idConsultant,tableCustomer.getModel().getValueAt( tableCustomer.getSelectedRow(), 0).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
