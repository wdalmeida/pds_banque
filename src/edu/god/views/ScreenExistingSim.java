/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.views;

import edu.god.controllers.ControllerScreenCompareSimulation;
import edu.god.controllers.ControllerScreenExistingSim;
import edu.god.models.AccessDB;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Warren
 */
public class ScreenExistingSim extends javax.swing.JFrame {

    /**
     * Creates new form ScreenExistingSim
     *
     * @param idConsultant
     * @param idCustomer
     */
    public ScreenExistingSim(int idConsultant, String idCustomer) {
        initComponents();
        setTitle(idConsultant + " - " + idCustomer);
        setVisible(true);
        loadDataInTable(AccessDB.getAccessDB().getDateTypeSims(idCustomer));
        getData(idCustomer);

        btnCancel.addActionListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnNewSim, idCustomer, idConsultant));
        btnModified.addActionListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnNewSim, idCustomer, idConsultant));
        btnCompareSimulation.addActionListener(new ControllerScreenCompareSimulation(Integer.parseInt(idCustomer), btnCompareSimulation));
        btnNewSim.addActionListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnNewSim, idCustomer, idConsultant));

        tblSims.addMouseListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnNewSim, idCustomer, idConsultant));
    }

    public void getData(String idCustomer) {
        AccessDB bdd = AccessDB.getAccessDB();
        String[] consultant = bdd.getLastFirstNameCustomer(idCustomer);
        lblTitle.setText(lblTitle.getText() + consultant[0] + "." + consultant[1] + " " + consultant[2]);

    }

    public void loadDataInTable(ArrayList<String[]> simulations) {
        System.out.println(Arrays.toString(simulations.toArray()));
        String title[] = {"IDSIM", "Type", "Date", "taux d'interet", "montant", "mensuailté", "IDCONSULTANT"};
        DefaultTableModel model = new DefaultTableModel(title, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        for (String[] sim : simulations) {
            model.addRow(sim);
        }
        tblSims.setModel(model);
        TableColumnModel tcm = tblSims.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
        tcm.removeColumn(tcm.getColumn(tblSims.getColumnCount() - 1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        btnModified = new javax.swing.JButton();
        SPSim = new javax.swing.JScrollPane();
        tblSims = new javax.swing.JTable();
        btnCompareSimulation = new javax.swing.JButton();
        btnNewSim = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitle.setText("Simulation enregistré pour ");

        btnModified.setText("Modifier la simulation");

        tblSims.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        SPSim.setViewportView(tblSims);

        btnCompareSimulation.setText("Comparer Simulation");

        btnNewSim.setText("Nouvelle simulation");

        btnCancel.setText("Annuler");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(SPSim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnNewSim)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModified)
                .addGap(18, 18, 18)
                .addComponent(btnCompareSimulation)
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(SPSim, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModified)
                    .addComponent(btnCompareSimulation)
                    .addComponent(btnNewSim))
                .addGap(30, 30, 30)
                .addComponent(btnCancel)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane SPSim;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCompareSimulation;
    private javax.swing.JButton btnModified;
    private javax.swing.JButton btnNewSim;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblSims;
    // End of variables declaration//GEN-END:variables
}
