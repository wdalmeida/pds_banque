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
import static edu.god.serialisation.JsonEncoding.*;
import edu.god.server.ClientJavaSelect;
import edu.god.views.ScreenCreateCust;
import edu.god.views.ScreenExistingSim;
import edu.god.views.ScreenHome;
import edu.god.views.ScreenLoanSim;
import edu.god.views.ScreenManageCust;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.regex.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author warren
 */
public class ControllerScreenManageCust implements ActionListener, MouseListener, KeyListener, FocusListener {

    private final ScreenManageCust smc;
    private final int idConsultant;
    private final JButton btnSubmit;
    private final JButton btnBack;
    private final JButton btnCreateCust;
    private final JButton btnUpdateCust;
    private final JButton btnDeleteCust;
    private final JTextField firstName;
    private final JTextField lastName;
    private final JTextField postalCode;
    private final JTable tableCustomer;
    private final JLabel error;
    private final boolean simulation;

    /**
     * Constructor which do not autorise to do a simulation Use it to manage
     * customer only
     *
     * @param smc0 ScreenManageCust
     * @param lastName0 JTextField
     * @param firstName0 JTextField
     * @param txtPc JTextField
     * @param idC0 int
     * @param btnCreateCust0 JButton
     * @param btnUpdateCust0 JButton
     * @param btnDeleteCust0 JButton
     * @param btnSubmit0 JButton
     * @param btnBack0 JButton
     * @param tableCust JTable
     * @param lblError JLabel
     */
    public ControllerScreenManageCust(ScreenManageCust smc0, JTextField lastName0, JTextField firstName0, JTextField txtPc, int idC0, JButton btnCreateCust0, JButton btnUpdateCust0, JButton btnDeleteCust0, JButton btnSubmit0, JButton btnBack0, JTable tableCust, JLabel lblError) {
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
        idConsultant = idC0;
    }

    /**
     * Constructor which can allow to do a simulation
     *
     *
     * @param smc0 ScreenManageCust
     * @param lastName0 JTextField
     * @param firstName0 JTextField
     * @param txtPc JTextField
     * @param idC0 int
     * @param btnCreateCust0 JButton
     * @param btnUpdateCust0 JButton
     * @param btnDeleteCust0 JButton
     * @param btnSubmit0 JButton
     * @param btnBack0 JButton
     * @param tableCust JTable
     * @param lblError JLabel
     * @param aSimulation boolean if true a simulation can be made
     */
    public ControllerScreenManageCust(ScreenManageCust smc0, JTextField lastName0, JTextField firstName0, JTextField txtPc, int idC0, JButton btnCreateCust0, JButton btnUpdateCust0, JButton btnDeleteCust0, JButton btnSubmit0, JButton btnBack0, JTable tableCust, JLabel lblError, boolean aSimulation) {
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
        idConsultant = idC0;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        resetAfterError();
        if (e.getSource() == btnSubmit) {
            System.out.println("bouton submit");
            ArrayList<String[]> customers = null;
            try {
                customers = search();
            } catch (IOException | org.json.simple.parser.ParseException | NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (customers != null) {
                if (!customers.isEmpty()) {
                    System.out.println("Customers = " + Arrays.toString(customers.get(0)));
                    loadDataInTable(customers);
                } else {
                    DefaultTableModel dtm = (DefaultTableModel) tableCustomer.getModel();
                    dtm.setRowCount(0);
                    String option[] = {"Ajouter un client", "Retour"};
                    int click = JOptionPane.showOptionDialog(smc, "Aucun client ne correspond à votre recherche", "Client inconnu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[1]);
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
                ScreenCreateCust newWindow = new ScreenCreateCust(idConsultant, simulation);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == btnBack) {
            smc.dispose();
            ScreenHome newWindow = new ScreenHome(idConsultant);
        }
    }

    /**
     * Load the table with the different customers' informations
     *
     * @param customers ArrayList<String[]>
     */
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

    /**
     * Search the matching customer and check if the fields aren't empty
     *
     * @return res ArrayList<String[]>
     */
    private ArrayList<String[]> search() throws IOException, FileNotFoundException, org.json.simple.parser.ParseException, NoSuchAlgorithmException {
        ArrayList<String[]> customers = null;
        if (!lastName.getText().isEmpty() && !firstName.getText().isEmpty() && !postalCode.getText().isEmpty() && postalCode.getText().length() == 5) {
           customers= new ArrayList<>();
            Object objetjson = ClientJavaSelect.clientTcpSelect("D", "1", encodingSearchCustomer(lastName.getText(), firstName.getText(), postalCode.getText()));
            JSONParser parser = new JSONParser();
            String object = objetjson.toString();
            objetjson = parser.parse(object);
            JSONObject jsonObject = (JSONObject) objetjson;
            System.out.println(objetjson.toString());
            System.out.println(objetjson.toString().substring(1,objetjson.toString().length()));
            for (int i = 1; i <= jsonObject.size(); i++) {
                String[] test = new String[11];
                switch (i) {
                    case 1:
                        System.arraycopy(jsonObject.get("1").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 11);
                        break;
                    case 2:
                        System.arraycopy(jsonObject.get("2").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 11);
                        break;
                    case 3:
                        System.arraycopy(jsonObject.get("3").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 11);
                        break;
                    case 4:
                        System.arraycopy(jsonObject.get("4").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 11);
                        break;
                    case 5:
                        System.arraycopy(jsonObject.get("5").toString().replace("[", "").replace("]", "").split(","), 0, test, 0, 11);
                        break;
                    default:
                        break;
                }
                System.out.println(Arrays.toString(test));
                customers.add(test);
            }
            System.out.println("Nom - prenom - code postal");
        } else {
            error.setText("Veuillez saisir tous les champs");
            if (lastName.getText().isEmpty()) {
                lastName.grabFocus();
                lastName.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (firstName.getText().isEmpty()) {
                if (!lastName.isFocusOwner()) {
                    firstName.grabFocus();
                }
                firstName.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
            if (postalCode.getText().isEmpty()) {
                if (!lastName.isFocusOwner() || !firstName.isFocusOwner()) {
                    postalCode.grabFocus();
                }
                postalCode.setBorder(BorderFactory.createLineBorder(Color.RED));
            } else if (postalCode.getText().length() < 5) {
                error.setText("le code postal doit être composé de 5 chiffres");
                postalCode.setBorder(BorderFactory.createLineBorder(Color.RED));

            }

        }
        return customers;
    }

    /**
     * Change the border to their default color and set the label to empty
     *
     */
    private void resetAfterError() {
        lastName.setBorder(UIManager.getBorder("TextField.border"));
        firstName.setBorder(UIManager.getBorder("TextField.border"));
        postalCode.setBorder(UIManager.getBorder("TextField.border"));
        error.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && simulation) {
            smc.dispose();
            JSONObject jsonObject = null;
            try {
                Object objetjson = ClientJavaSelect.clientTcpSelect("D", "3", encodingInfoSimCust(tableCustomer.getModel().getValueAt(tableCustomer.getSelectedRow(), 0).toString()));
                JSONParser parser = new JSONParser();
                String object = objetjson.toString();
                objetjson = parser.parse(object);
                jsonObject = (JSONObject) objetjson;
            } catch (IOException | org.json.simple.parser.ParseException | NoSuchAlgorithmException f) {

            }
            if (jsonObject != null && !jsonObject.isEmpty()) {
                try {
                    ScreenExistingSim newWindow = new ScreenExistingSim(idConsultant, tableCustomer.getModel().getValueAt(tableCustomer.getSelectedRow(), 0).toString());
                } catch (IOException | org.json.simple.parser.ParseException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    ScreenLoanSim newWindow = new ScreenLoanSim(idConsultant, tableCustomer.getModel().getValueAt(tableCustomer.getSelectedRow(), 0).toString(), false);
                } catch (SQLException | ParseException | org.json.simple.parser.ParseException | IOException ex) {
                    Logger.getLogger(ControllerScreenManageCust.class.getName()).log(Level.SEVERE, null, ex);
                }

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

    @Override
    public void keyTyped(KeyEvent e) {
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

    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getComponent().getClass() == JButton.class) {
            JButton jb = (JButton) e.getSource();
            smc.getRootPane().setDefaultButton(jb);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }
}
