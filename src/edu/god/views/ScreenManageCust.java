/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.views;

import edu.god.controllers.ControllerScreenManageCust;
import java.awt.Color;
import java.text.ParseException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author florent
 */
public class ScreenManageCust extends javax.swing.JFrame {

    /**
     * Creates new form ScreenManageCust
     *
     * @param idC0 int id of the current user
     * @param simulation boolean true if the button was "simulate a loan"
     * @throws java.text.ParseException
     */
    public ScreenManageCust(int idC0, boolean simulation) throws ParseException {
        initComponents();
        //define the table's column
        String title[] = {"Civilité", "Nom", "Prenom", "Rue", "Code postal", "Ville", "Telephone", "Email", "Date de Naissance", "Nationalité"};
        DefaultTableModel model = new DefaultTableModel(title, 0);
        tblCustomer.setModel(model);

        //add keyListener
        txtPc.addKeyListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError, simulation));
        txtLastName.addKeyListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError, simulation));
        txtFirstName.addKeyListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError, simulation));

        //add MouseListener
        tblCustomer.addMouseListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError, simulation));

        //add ActionLitener
        btnCreate.addActionListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError, simulation));
        btnUpdate.addActionListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError, simulation));
        btnDelete.addActionListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError, simulation));
        btnSubmit.addActionListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError));
        btnBack.addActionListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError));

        // add focus listener
        btnSubmit.addFocusListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError));
        btnBack.addFocusListener(new ControllerScreenManageCust(this, txtLastName, txtFirstName, txtPc, idC0, btnCreate, btnUpdate, btnDelete, btnSubmit, btnBack, tblCustomer, lblError));

        lblError.setText("");
        lblError.setForeground(Color.red);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws ParseException{

        lblLastName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JFormattedTextField();
        lblFirstName = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JFormattedTextField();
        btnCreate = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblManageCust = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        SPManage = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        txtPc = new javax.swing.JFormattedTextField();
        lblPc = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestion client");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("FManageCustomer"); // NOI18N
        setSize(new java.awt.Dimension(800, 600));

        lblLastName.setText("Nom");

        lblFirstName.setText("Prenom");

        btnCreate.setText("Creer");

        btnUpdate.setText("Modifier");

        btnDelete.setText("Supprimer");

        lblManageCust.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManageCust.setText("Gestion d'un Client");

        btnSubmit.setText("Valider");

        btnBack.setText("Retour");

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nom", "Prenom", "Adresse", "Ville", "Code Postal", "Date de naissance", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14"
            }
        ) {public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tblCustomer.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCustomer.setRequestFocusEnabled(false);
        tblCustomer.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        SPManage.setViewportView(tblCustomer);
        tblCustomer.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblCustomer.getColumnModel().getColumnCount() > 0) {
            tblCustomer.getColumnModel().getColumn(0).setResizable(false);
            tblCustomer.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblCustomer.getColumnModel().getColumn(1).setResizable(false);
            tblCustomer.getColumnModel().getColumn(1).setPreferredWidth(50);
            tblCustomer.getColumnModel().getColumn(3).setResizable(false);
            tblCustomer.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        lblPc.setText("Code postal");

        lblError.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblManageCust, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLastName)
                        .addGap(35, 35, 35)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSubmit)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack)
                                .addGap(45, 45, 45))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFirstName)
                                .addGap(18, 18, 18)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addComponent(lblPc)
                        .addGap(35, 35, 35)
                        .addComponent(txtPc, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SPManage))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(360, Short.MAX_VALUE)
                .addComponent(lblError)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblManageCust)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblLastName)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblFirstName)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPc)
                            .addComponent(txtPc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 176, Short.MAX_VALUE)
                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(btnDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblError)
                        .addGap(39, 39, 39)
                        .addComponent(SPManage, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane SPManage;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblManageCust;
    private javax.swing.JLabel lblPc;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JFormattedTextField txtFirstName;
    private javax.swing.JFormattedTextField txtLastName;
    private javax.swing.JFormattedTextField txtPc;
    // End of variables declaration//GEN-END:variables
}
