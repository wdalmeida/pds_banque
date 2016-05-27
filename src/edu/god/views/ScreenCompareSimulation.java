/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.views;

import edu.god.controllers.ControllerScreenCompareSimulation;
import edu.god.models.AccessDB;
import static edu.god.serialisation.JsonEncoding.encodingLoanType;
import edu.god.server.ClientJavaSelect;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author florent
 */
public class ScreenCompareSimulation extends javax.swing.JFrame {

    /**
     * Creates new form ScreenCompareSimulation
     */
    private final int NBRPARAMETERS = 8;
    private String idCustomer;
    private ArrayList<String> simul1;
    private ArrayList<String> simul2;
    private ArrayList<String> simul3;
    private ArrayList<String> choiceTypeLoan;

    public ScreenCompareSimulation(String idCustomer0) throws SQLException, ParseException, IOException {
        System.out.println("ScreenCompareSimulation");
        initComponents();
        this.idCustomer = idCustomer0;
        Object objetjson = ClientJavaSelect.clientTcpSelect("P", "1", encodingLoanType(idCustomer));
        JSONParser parser = new JSONParser();
        String object = objetjson.toString();
        objetjson = parser.parse(object);
        JSONObject jsonObject = (JSONObject) objetjson;
        System.out.println(objetjson.toString());
       /* this.choiceTypeLoan = AccessDB.getAccessDB().getTypeLoanCustomer(idCustomer);
        choiceTypeLoan.stream().forEach((choiceStatu) -> {
            typeLoan.addItem(choiceStatu);
        });*/
        btnSubmit.addActionListener(new ControllerScreenCompareSimulation(this, tableCompareSims, idCustomer, typeLoan, btnSubmit));
        simul1 = new ArrayList<>(); // initialize list
        simul2 = new ArrayList<>();
        simul3 = new ArrayList<>();
        this.setVisible(true);
        btnClose.addActionListener(new ControllerScreenCompareSimulation(this, btnClose)); // add ActionListener to btnClose
        tableCompareSims.addMouseListener(new ControllerScreenCompareSimulation(tableCompareSims, this, btnClose));  // Add MouseListener to tableCompareSims

    }

    public int getSimulation(String id) {
        System.out.println("getSimulation");
        int tmp = 0;
        if (simul1.get(0).equals(id)) {
            tmp = 1;
        } else if (simul2.get(0).equals(id)) {
            tmp = 2;
        } else if (simul3.get(0).equals(id)) {
            tmp = 3;
        }
        return tmp;
    }

    /**
     * return a empty list
     *
     * @return temp
     */
    public ArrayList fillemptyList() {
        System.out.println("filleEmptyList");
        ArrayList temp = new ArrayList<>();
        for (int cpt = 0; cpt < NBRPARAMETERS; cpt++) {
            temp.add("");
        }
        return temp;
    }

    public void cleanSimulation(int choix) {
        System.out.println("cleanSimulation");
        switch (choix) {
            case 1:
                simul1 = (ArrayList<String>) this.fillemptyList().clone();
                break;
            case 2:
                simul2 = (ArrayList<String>) this.fillemptyList().clone();
                break;
            case 3:
                simul3 = (ArrayList<String>) this.fillemptyList().clone();
                break;
        }
        this.setJlabelSimul(simul1, 1);
        this.setJlabelSimul(simul2, 2);
        this.setJlabelSimul(simul3, 3);
        this.showSimulation();
    }

    /**
     * Load data in the Jtable here tableCompareSims
     *
     * @param simulations
     */
    public void loadDataInTable(ArrayList<String[]> simulations) {
        System.out.println("LoadDataInTable");
        DefaultTableModel model = (DefaultTableModel) tableCompareSims.getModel();
        tableCompareSims.setModel(model);

        for (String[] sim : simulations) {
            model.addRow(sim);  // add all simulations of a specific customer in the model of the JTable
        }
    }

    /**
     * Check if one of 3 list is filled, return false if the lists are empty or
     * true if one of them is not empty and is equals to row
     *
     * @param row
     * @return tmp
     */
    public boolean isSimulationfill(String row) {
        boolean tmp = false;
        System.out.println("id a inserer = " + row);
        if (!simul1.isEmpty()) {
            if (simul1.get(0).equals(row)) {
                tmp = true;
            }
        }
        if (!simul2.isEmpty()) {
            if (simul2.get(0).equals(row)) {
                tmp = true;
            }
        }
        if (!simul3.isEmpty()) {
            System.out.println("simul 3 " + simul3.get(0));
            if (simul3.get(0).equals(row)) {
                tmp = true;
            }
        }
        return tmp;
    }

    public void showSimulation() {
        System.out.println("Simulation 1 =" + Arrays.toString(simul1.toArray()));
        System.out.println("Simulation 2 =" + Arrays.toString(simul2.toArray()));
        System.out.println("Simulation 3 =" + Arrays.toString(simul3.toArray()));
    }

    /**
     * reset the order of the 3 lists
     *
     * @param row
     */
    public void removeSimulation(String row) {
        System.out.println("removeSimulation = " + row);
        if (!simul1.isEmpty()) {
            if (!simul1.get(0).equals("")) {
                System.out.println("Simul 1 fill");
                if (simul1.get(0).equals(row)) {
                    System.out.println("delete Simul 1 = " + row);
                    if (simul2.get(0).equals("") && simul3.get(0).equals("")) {
                        System.out.println("simul 2 && simul 3 = vide");
                        this.cleanSimulation(1);
                        this.setJlabelSimul(simul1, 1);
                    }
                    if (!simul2.isEmpty()) {
                        System.out.println("simul 2 not empty");
                        if (!simul2.get(0).equals("")) {
                            System.out.println("simul 2 =" + simul2.get(0));
                            simul1 = (ArrayList<String>) simul2.clone();
                            this.cleanSimulation(2);
                            this.setJlabelSimul(simul1, 1);
                            if (!simul3.isEmpty()) {
                                System.out.println("simul 3 not empty");
                                if (!simul3.get(0).equals("")) {
                                    System.out.println("simul 3 =" + simul3.get(0));
                                    simul2 = (ArrayList<String>) simul3.clone();
                                    this.cleanSimulation(3);
                                    this.setJlabelSimul(simul2, 2);
                                    if (simul2.get(0).equals("") && simul3.get(0).equals("")) {
                                        System.out.println("simul 2 && simul 3 = vide");
                                        this.cleanSimulation(1);
                                        this.setJlabelSimul(simul1, 1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!simul2.isEmpty()) {
            if (!simul2.get(0).equals("")) {
                System.out.println("Simul 2 fill");
                if (simul2.get(0).equals(row)) {
                    System.out.println("delete Simul 2");
                    System.out.println("simul 2 =" + simul2.get(0));
                    simul2 = (ArrayList<String>) simul3.clone();
                    this.setJlabelSimul(simul2, 2);
                    this.cleanSimulation(3);
                }
                if (!simul3.isEmpty()) {
                    if (!simul3.get(0).equals("")) {
                        System.out.println("Simul 3 fill");
                        if (simul3.get(0).equals(row)) {
                            System.out.println("simul 3 =" + simul3.get(0));
                            this.cleanSimulation(3);
                        }
                    } else {
                        this.cleanSimulation(3);
                    }
                }

            }
        }
        if (!simul3.isEmpty()) {
            if (!simul3.get(0).equals("")) {
                System.out.println("Simul 3 fill");
                if (simul3.get(0).equals(row)) {
                    System.out.println("Suppresion Simul 3");
                    System.out.println("simul 3 =" + simul3.get(0));
                    this.cleanSimulation(3);
                }
            }
        }
    }

    /**
     * set JLabels for a simulation
     *
     * @param simulation
     * @param choix
     */
    public void setJlabelSimul(ArrayList<String> simulation, int choix) {
        System.out.println("setJlabelSimul");
        switch (choix) {
            case 1:
                simul1 = this.fillemptyList();
                capitalSimul1.setText(simulation.get(1));
                rateSimul1.setText(simulation.get(2));
                monthlyLoanSimul1.setText(simulation.get(3));
                monthlyInsuranceSimul1.setText(simulation.get(4));
                durationSimul1.setText(simulation.get(5));
                totalAmountSimul1.setText(simulation.get(6));
                ratioDebtSimul1.setText(simulation.get(7));
                simul1 = (ArrayList<String>) simulation.clone();
                simulation = this.fillemptyList();
                System.out.println(" Sortie update simul 1");
                break;
            case 2:
                simul2 = this.fillemptyList();
                capitalSimul2.setText(simulation.get(1));
                rateSimul2.setText(simulation.get(2));
                monthlyLoanSimul2.setText(simulation.get(3));
                monthlyInsuranceSimul2.setText(simulation.get(4));
                durationSimul2.setText(simulation.get(5));
                totalAmountSimul2.setText(simulation.get(6));
                ratioDebtSimul2.setText(simulation.get(7));
                simul2 = (ArrayList<String>) simulation.clone();
                simulation = this.fillemptyList();
                System.out.println(" Sortie update simul 2 ");
                break;
            case 3:
                simul3 = fillemptyList();
                capitalSimul3.setText(simulation.get(1));
                rateSimul3.setText(simulation.get(2));
                monthlyLoanSimul3.setText(simulation.get(3));
                monthlyInsuranceSimul3.setText(simulation.get(4));
                durationSimul3.setText(simulation.get(5));
                totalAmountSimul3.setText(simulation.get(6));
                ratioDebtSimul3.setText(simulation.get(7));
                simul3 = (ArrayList<String>) simulation.clone();
                simulation = this.fillemptyList();
                System.out.println(" Sortie update simul 3");
                break;
            default:
                break;
        }
        this.showSimulation();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCompareSims = new javax.swing.JTable();
        btnClose = new javax.swing.JButton();
        lblsubtitle = new javax.swing.JLabel();
        lblCapital = new javax.swing.JLabel();
        lblRate = new javax.swing.JLabel();
        lblMonthlyLoan = new javax.swing.JLabel();
        lblMonthlyInsurance = new javax.swing.JLabel();
        lblDuration = new javax.swing.JLabel();
        lblTotalAmount = new javax.swing.JLabel();
        lblSimulation1 = new javax.swing.JLabel();
        capitalSimul1 = new javax.swing.JLabel();
        rateSimul1 = new javax.swing.JLabel();
        monthlyLoanSimul1 = new javax.swing.JLabel();
        monthlyInsuranceSimul1 = new javax.swing.JLabel();
        durationSimul1 = new javax.swing.JLabel();
        totalAmountSimul1 = new javax.swing.JLabel();
        lblSimulation2 = new javax.swing.JLabel();
        capitalSimul2 = new javax.swing.JLabel();
        rateSimul2 = new javax.swing.JLabel();
        monthlyLoanSimul2 = new javax.swing.JLabel();
        monthlyInsuranceSimul2 = new javax.swing.JLabel();
        durationSimul2 = new javax.swing.JLabel();
        totalAmountSimul2 = new javax.swing.JLabel();
        lblSimulation3 = new javax.swing.JLabel();
        capitalSimul3 = new javax.swing.JLabel();
        rateSimul3 = new javax.swing.JLabel();
        monthlyLoanSimul3 = new javax.swing.JLabel();
        monthlyInsuranceSimul3 = new javax.swing.JLabel();
        durationSimul3 = new javax.swing.JLabel();
        totalAmountSimul3 = new javax.swing.JLabel();
        lblRatioDebt = new javax.swing.JLabel();
        ratioDebtSimul1 = new javax.swing.JLabel();
        ratioDebtSimul2 = new javax.swing.JLabel();
        ratioDebtSimul3 = new javax.swing.JLabel();
        typeLoan = new javax.swing.JComboBox<>();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Comparer Simulation");

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Comparaison Simulation");

        tableCompareSims.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Choix", "Type", "Capital", "Interet", "Mens Pret", "Mens Ass", "Duree", "Total à rembourser"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableCompareSims);
        if (tableCompareSims.getColumnModel().getColumnCount() > 0) {
            tableCompareSims.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableCompareSims.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableCompareSims.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableCompareSims.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableCompareSims.getColumnModel().getColumn(4).setPreferredWidth(100);
            tableCompareSims.getColumnModel().getColumn(5).setPreferredWidth(50);
            tableCompareSims.getColumnModel().getColumn(6).setPreferredWidth(50);
            tableCompareSims.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        btnClose.setText("Fermer");

        lblsubtitle.setText("Veullez selectionner 1 à 3 simulations à comparer :");

        lblCapital.setText("Capital");

        lblRate.setText("Interet");

        lblMonthlyLoan.setText("Mensualite Pret");

        lblMonthlyInsurance.setText("Mensualite Assurance");

        lblDuration.setText("Duree");

        lblTotalAmount.setText("Total à rembourser");

        lblSimulation1.setText("Simulation 1");

        capitalSimul1.setText("11111");

        rateSimul1.setText("11111");

        monthlyLoanSimul1.setText("11111");

        monthlyInsuranceSimul1.setText("11111");

        durationSimul1.setText("11111");

        totalAmountSimul1.setText("11111");

        lblSimulation2.setText("Simulation 2");

        capitalSimul2.setText("22222");

        rateSimul2.setText("22222");

        monthlyLoanSimul2.setText("22222");

        monthlyInsuranceSimul2.setText("22222");

        durationSimul2.setText("22222");

        totalAmountSimul2.setText("22222");

        lblSimulation3.setText("Simulation 3");

        capitalSimul3.setText("33333");

        rateSimul3.setText("33333");

        monthlyLoanSimul3.setText("33333");

        monthlyInsuranceSimul3.setText("33333");

        durationSimul3.setText("33333");

        totalAmountSimul3.setText("33333");

        lblRatioDebt.setText("Taux d'endettement");

        ratioDebtSimul1.setText("11111");

        ratioDebtSimul2.setText("22222");

        ratioDebtSimul3.setText("33333");

        typeLoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Veullez selectionner un type de pret" }));

        btnSubmit.setText("Valider");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(btnClose))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblsubtitle))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(typeLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnSubmit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMonthlyLoan)
                            .addComponent(lblCapital)
                            .addComponent(lblRate)
                            .addComponent(lblMonthlyInsurance)
                            .addComponent(lblDuration)
                            .addComponent(lblTotalAmount)
                            .addComponent(lblRatioDebt))
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(capitalSimul1)
                            .addComponent(rateSimul1)
                            .addComponent(monthlyLoanSimul1)
                            .addComponent(monthlyInsuranceSimul1)
                            .addComponent(durationSimul1)
                            .addComponent(totalAmountSimul1)
                            .addComponent(ratioDebtSimul1)
                            .addComponent(lblSimulation1))
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalAmountSimul2)
                            .addComponent(durationSimul2)
                            .addComponent(monthlyInsuranceSimul2)
                            .addComponent(monthlyLoanSimul2)
                            .addComponent(rateSimul2)
                            .addComponent(capitalSimul2)
                            .addComponent(ratioDebtSimul2)
                            .addComponent(lblSimulation2))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ratioDebtSimul3)
                            .addComponent(capitalSimul3)
                            .addComponent(rateSimul3)
                            .addComponent(monthlyLoanSimul3)
                            .addComponent(monthlyInsuranceSimul3)
                            .addComponent(durationSimul3)
                            .addComponent(totalAmountSimul3)
                            .addComponent(lblSimulation3))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit))
                .addGap(51, 51, 51)
                .addComponent(lblsubtitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSimulation3)
                    .addComponent(lblSimulation2)
                    .addComponent(lblSimulation1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCapital)
                            .addComponent(capitalSimul1)
                            .addComponent(capitalSimul2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblRate)
                            .addComponent(rateSimul1)
                            .addComponent(rateSimul2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMonthlyLoan)
                            .addComponent(monthlyLoanSimul1)
                            .addComponent(monthlyLoanSimul2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMonthlyInsurance)
                            .addComponent(monthlyInsuranceSimul1)
                            .addComponent(monthlyInsuranceSimul2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDuration)
                            .addComponent(durationSimul1)
                            .addComponent(durationSimul2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalAmount)
                            .addComponent(totalAmountSimul1)
                            .addComponent(totalAmountSimul2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(capitalSimul3)
                        .addGap(18, 18, 18)
                        .addComponent(rateSimul3)
                        .addGap(18, 18, 18)
                        .addComponent(monthlyLoanSimul3)
                        .addGap(18, 18, 18)
                        .addComponent(monthlyInsuranceSimul3)
                        .addGap(18, 18, 18)
                        .addComponent(durationSimul3)
                        .addGap(18, 18, 18)
                        .addComponent(totalAmountSimul3)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRatioDebt)
                    .addComponent(ratioDebtSimul1)
                    .addComponent(ratioDebtSimul2)
                    .addComponent(ratioDebtSimul3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel capitalSimul1;
    private javax.swing.JLabel capitalSimul2;
    private javax.swing.JLabel capitalSimul3;
    private javax.swing.JLabel durationSimul1;
    private javax.swing.JLabel durationSimul2;
    private javax.swing.JLabel durationSimul3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCapital;
    private javax.swing.JLabel lblDuration;
    private javax.swing.JLabel lblMonthlyInsurance;
    private javax.swing.JLabel lblMonthlyLoan;
    private javax.swing.JLabel lblRate;
    private javax.swing.JLabel lblRatioDebt;
    private javax.swing.JLabel lblSimulation1;
    private javax.swing.JLabel lblSimulation2;
    private javax.swing.JLabel lblSimulation3;
    private javax.swing.JLabel lblTotalAmount;
    private javax.swing.JLabel lblsubtitle;
    private javax.swing.JLabel monthlyInsuranceSimul1;
    private javax.swing.JLabel monthlyInsuranceSimul2;
    private javax.swing.JLabel monthlyInsuranceSimul3;
    private javax.swing.JLabel monthlyLoanSimul1;
    private javax.swing.JLabel monthlyLoanSimul2;
    private javax.swing.JLabel monthlyLoanSimul3;
    private javax.swing.JLabel rateSimul1;
    private javax.swing.JLabel rateSimul2;
    private javax.swing.JLabel rateSimul3;
    private javax.swing.JLabel ratioDebtSimul1;
    private javax.swing.JLabel ratioDebtSimul2;
    private javax.swing.JLabel ratioDebtSimul3;
    private javax.swing.JTable tableCompareSims;
    private javax.swing.JLabel title;
    private javax.swing.JLabel totalAmountSimul1;
    private javax.swing.JLabel totalAmountSimul2;
    private javax.swing.JLabel totalAmountSimul3;
    private javax.swing.JComboBox<String> typeLoan;
    // End of variables declaration//GEN-END:variables
}
