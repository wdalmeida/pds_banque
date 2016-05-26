/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.views;

import edu.god.controllers.ControllerScreenExistingSim;
import static edu.god.serialisation.JsonEncoding.*;
import edu.god.server.ClientJavaSelect;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Warren
 */
public class ScreenExistingSim extends javax.swing.JFrame {

    /**
     * Creates new form ScreenExistingSim
     *
     * @param idConsultant int
     * @param idCustomer String
     * @throws java.io.IOException
     * @throws java.io.FileNotFoundException
     * @throws org.json.simple.parser.ParseException
     * @throws java.security.NoSuchAlgorithmException
     */
    public ScreenExistingSim(int idConsultant, String idCustomer) throws IOException, FileNotFoundException, ParseException, NoSuchAlgorithmException {
        initComponents();
        setVisible(true);
        ArrayList<String[]> simList = new ArrayList<>();
        Object objetjson = ClientJavaSelect.clientTcpSelect("D", "3", encodingInfoSimCust(idCustomer));
        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;
        System.out.println(objetjson.toString());
        for (int i = 1; i <= jsonObject.size(); i++) {
            String[] test = new String[6];
            switch (i) {
                case 1:
                    System.arraycopy(jsonObject.get("1").toString().split(","), 0, test, 0, 6);
                    break;
                case 2:
                    System.arraycopy(jsonObject.get("2").toString().split(","), 0, test, 0, 6);
                    break;
                case 3:
                    System.arraycopy(jsonObject.get("3").toString().split(","), 0, test, 0, 6);
                    break;
                case 4:
                    System.arraycopy(jsonObject.get("4").toString().split(","), 0, test, 0, 6);
                    break;
                case 5:
                    System.arraycopy(jsonObject.get("5").toString().split(","), 0, test, 0, 6);
                    break;
                case 6:
                    System.arraycopy(jsonObject.get("6").toString().split(","), 0, test, 0, 6);
                    break;
                case 7:
                    System.arraycopy(jsonObject.get("7").toString().split(","), 0, test, 0, 6);
                    break;
                case 8:
                    System.arraycopy(jsonObject.get("8").toString().split(","), 0, test, 0, 6);
                    break;
                case 9:
                    System.arraycopy(jsonObject.get("9").toString().split(","), 0, test, 0, 6);
                    break;
                case 10:
                    System.arraycopy(jsonObject.get("10").toString().split(","), 0, test, 0, 6);
                    break;
                default:
                    break;
            }
            System.out.println(Arrays.toString(test));
            simList.add(test);
        }
        loadDataInTable(simList);
        getData(idCustomer);

        //Add actionListener
        btnCancel.addActionListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnCompareSimulation, btnNewSim, idCustomer, idConsultant));
        btnModified.addActionListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnCompareSimulation, btnNewSim, idCustomer, idConsultant));
        btnCompareSimulation.addActionListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnCompareSimulation, btnNewSim, idCustomer, idConsultant));
        btnNewSim.addActionListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnCompareSimulation, btnNewSim, idCustomer, idConsultant));

        //Add mouseListener
        tblSims.addMouseListener(new ControllerScreenExistingSim(this, tblSims, btnModified, btnCancel, btnCompareSimulation, btnNewSim, idCustomer, idConsultant));
    }

    /**
     * The method retrieve the customer last and first name
     *
     * @param idCustomer String
     */
    private void getData(String idCustomer) throws IOException, FileNotFoundException, ParseException {
        String res = ClientJavaSelect.clientTcpSelect("D", "5", encodingLastFirstNameCustomer(idCustomer));
        String test = res.replace("[", "");
        String test2 = test.replace("]", "");
        String[] consultant = test2.split(",");
        lblTitle.setText(lblTitle.getText() + consultant[0] + "." + consultant[1] + " " + consultant[2]);
    }

    /**
     * The method search and display some information about the customer's
     * simulations
     *
     *
     * @param simulations ArrayList<String[]>
     */
    private void loadDataInTable(ArrayList<String[]> simulations) {
        System.out.println(Arrays.toString(simulations.toArray()));
        String title[] = {"IDSIM", "Type", "Date", "taux d'interet", "mensualité", "Durée en mois"}; // add montant du pret 
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
        for (int i = 0; i < tblSims.getRowCount(); i++) {
            tblSims.setValueAt(tblSims.getModel().getValueAt(i, 0).toString().replace("[", ""), i, 0);
            tblSims.setValueAt(tblSims.getValueAt(i, 5).toString().replace("]", ""), i, 5);
        }
        tcm.removeColumn(tcm.getColumn(0));
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
        setTitle("Liste des simulations du client");

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
