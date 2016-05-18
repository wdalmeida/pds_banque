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
import edu.god.views.ScreenHome;
import edu.god.views.ScreenManageCust;
import java.awt.Color;
import java.util.regex.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author warren
 */
public class ControllerScreenManageCust implements ActionListener, MouseListener, KeyListener {

    private final ScreenManageCust smc;
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
    private final boolean simulation;

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
        this.smc = smc0;
        this.simulation = false;
    }

    public ControllerScreenManageCust(ScreenManageCust smc0, JTextField lastName0, JTextField firstName0, JTextField txtPc, int idC0, JButton btnCreateCust0, JButton btnUpdateCust0, JButton btnDeleteCust0, JButton btnSubmit0, JButton btnBack0, JTable tableCust, JLabel lblError, boolean aSimulation) {
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
        this.smc = smc0;
        this.simulation = aSimulation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            lastName.setBorder(UIManager.getBorder("TextField.border"));
            firstName.setBorder(UIManager.getBorder("TextField.border"));
            postalCode.setBorder(UIManager.getBorder("TextField.border"));
            error.setText("");

            System.out.println("bouton submit");
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
                            ScreenCreateCust newWindow = new ScreenCreateCust(idConsultant, true);
                        } catch (SQLException ex) {
                            Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        } else if (e.getSource() == btnCreateCust) {
            smc.dispose();
            try {
                ScreenCreateCust newWindow = new ScreenCreateCust(idConsultant, true);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnBack) {
            smc.dispose();
            ScreenHome newWindow = new ScreenHome(idConsultant);
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

        if (!lastName.getText().isEmpty() && !firstName.getText().isEmpty() && !postalCode.getText().isEmpty()) {

            try {
                customers = db.getCustomer(lastName.getText(), firstName.getText(), postalCode.getText());
                System.out.println("Nom - prenom - code postal");
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
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
        if (e.getClickCount() == 2 && simulation) {
            smc.dispose();
            ScreenExistingSim newWindow = new ScreenExistingSim(idConsultant, tableCustomer.getModel().getValueAt(tableCustomer.getSelectedRow(), 0).toString());
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

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Les 3 sont non vides");
        if ((lastName.getText().length() < 50 && e.getSource() == lastName) || (firstName.getText().length() < 50 && e.getSource() == firstName)) {
            Pattern p = Pattern.compile("[a-zA-Z'\\s-éèïçÀÁÂÆÇÈÉÊËÌÍÎÏÑÒÓÔŒÙÚÛÜÝŸàáâæçèéêëìíîïñòóôœùúûüýÿ]");
            Matcher mName = p.matcher(String.valueOf(e.getKeyChar()));
            if (!mName.matches()) {
                e.consume();
                System.out.println("consume name");
            }
        } else if (lastName.getText().length() >= 50 && e.getSource() == lastName && lastName.getSelectedText() == null) {
            e.consume();
        } else if (firstName.getText().length() >= 50 && e.getSource() == firstName && firstName.getSelectedText() == null) {
            e.consume();
        }
        if (e.getSource() == postalCode && postalCode.getText().length() < 5) {
            Pattern p = Pattern.compile("[0-9]");
            Matcher mPostalCode = p.matcher(String.valueOf(e.getKeyChar()));
            if (!mPostalCode.matches()) {
                e.consume();
                System.out.println("consume pc" + e.getKeyChar());
            }
        } else if (postalCode.getText().length() >= 5 && postalCode.getSelectedText() == null) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {
    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
    }
}
