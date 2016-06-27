/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.charts.LineChart;
import edu.god.entities.Indicator;
import edu.god.entities.IndicatorTable;
import edu.god.models.AccessDB;
import edu.god.views.ScreenHome;
import edu.god.views.ScreenIndicators;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.jdesktop.swingx.JXDatePicker;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author nabil
 */
public class ControllerScreenIndicator implements ActionListener {

    private final AccessDB db;
    private JComboBox<String> AgencyComboBox;
    private JTable indicatorJtable;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTabbedPane jTabbedPane1;
    private JButton refreshJButton;
    private ScreenIndicators sci;
    private final int idConsultant;
    private JButton backHome;
    private JComboBox<String> chartComboBox;
    private JTable loanIndicatorTable;
    Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    private JButton submitButton;
    ArrayList<Indicator> indicators = new ArrayList();
    private JXDatePicker periodJDatePicker1, periodJDatePicker2;
    private String typeLoan;
    private String age;
    private int idC0;
    private JLabel label1, label2, label3, label4;

    public ControllerScreenIndicator(ScreenIndicators sci, JComboBox<String> indicatorComboBox, JTable indicatorJtable, JPanel indicatorPanel, JPanel indicatorDPanel, JPanel graphicPanel, int idC0) {
        this.db = AccessDB.getAccessDB();
        this.AgencyComboBox = indicatorComboBox;
        this.sci = sci;
        this.indicatorJtable = indicatorJtable;
        this.jPanel1 = indicatorPanel;
        this.jPanel2 = indicatorDPanel;
        this.jPanel3 = graphicPanel;
        this.idConsultant = idC0;

    }

    public ControllerScreenIndicator(ScreenIndicators sci, JComboBox<String> indicatorComboBox, JTable indicatorJtable, JPanel indicatorPanel, int idC0, JComboBox<String> comboBox) {
        this.db = AccessDB.getAccessDB();
        this.sci = sci;
        this.jPanel1 = indicatorPanel;
        this.AgencyComboBox = indicatorComboBox;
        this.indicatorJtable = indicatorJtable;
        this.idConsultant = idC0;
        this.chartComboBox = comboBox;
        this.jPanel3 = indicatorPanel;

    }

    public ControllerScreenIndicator(ScreenIndicators sci, int idC0, JButton backButton, JButton submit) {
        this.db = AccessDB.getAccessDB();
        this.sci = sci;
        this.backHome = backButton;
        this.idConsultant = idC0;
        this.submitButton = submit;
    }

    public ControllerScreenIndicator(ScreenIndicators sci, int idC0, JButton submit, JLabel label1, JLabel label2, JLabel label3, JLabel label4, String typeLoan, String Age, JXDatePicker date1, JXDatePicker date2) {
        this.db = AccessDB.getAccessDB();
        this.sci = sci;
        this.idConsultant = idC0;
        this.submitButton = submit;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.typeLoan = typeLoan;
        this.age = age;
        this.periodJDatePicker1 = date1;
        this.periodJDatePicker2 = date2;
    }

    public ControllerScreenIndicator(ScreenIndicators sci, int idC0, JPanel Cpanel, JComboBox<String> comboBox1) {
        this.db = AccessDB.getAccessDB();
        this.sci = sci;
        this.idConsultant = idC0;
        this.jPanel3 = Cpanel;
        this.chartComboBox = comboBox1;

    }

    public ControllerScreenIndicator(ScreenIndicators sci, int idC0, JComboBox<String> comboBox1, JTable loanIndicatorTable) {
        this.db = AccessDB.getAccessDB();
        this.sci = sci;
        this.idConsultant = idC0;
        this.loanIndicatorTable = loanIndicatorTable;
        this.chartComboBox = comboBox1;

    }

    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == refreshJButton) {
            try {
                indicatorJtable.removeAll();
                indicatorJtable.setModel(new IndicatorTable(idConsultant));
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenIndicator.class.getName()).log(Level.SEVERE, null, ex);
            }
            indicatorJtable.setVisible(true);
        } else if (e.getSource() == backHome) {
            sci.dispose();
            sci.setVisible(false);
            ScreenHome sch = new ScreenHome(idConsultant);
            sch.setVisible(true);
            /*} else if (e.getSource() == chartComboBox) {
            // if (lineChartComboBox.getSelectedItem() ==) {
            /try {
                LineChart chart = new LineChart(" Graphique d'évolution", "Number of Loan");
                jPanel3.add(chart, BorderLayout.CENTER);
                chart.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenIndicator.class.getName()).log(Level.SEVERE, null, ex);
            }*/

        } else if (e.getSource() == submitButton) {
            //String constraint="";
            String constraint = setIndicatorDynamic(periodJDatePicker1.getDate(), periodJDatePicker2.getDate(), typeLoan, age);
            System.out.println(typeLoan);
            ResultSet rs = db.getLoanIndicators(idC0, constraint);
            this.indicatorDynamic(rs, label1, label2, label3, label4);

        }

    }

    public String setIndicatorDynamic(Date periodJDatePicker1, Date periodJDatePicker2, String typeLoan, String age) {
        /*String query = "SELECT first_Name_Customer,last_Name_Customer, percentage_Rate, monthly_Sim, duration_Sim,birthday_Customer, description_LoanRef "
                + "FROM LoanSimulation ls, Loan l, Customer c, Agency a,Consultant ct, LoanRef lr  "
                + "WHERE l.id_Sim = ls.id_Sim "
                + "AND c.id_Customer = ls.id_Customer "
                + "AND ct.id_Consultant = ls.id_Consultant "
                + "AND a.id_Agency = ct.id_Agency "
                + "AND ls.id_LoanRef = lr.id_LoanRef "
                + "AND a.id_Agency = ? ;";*/

        String constraint = "";

        /*if (!periodJDatePicker1.equals(null) && !periodJDatePicker1.equals(null)) {
            constraint = constraint + " AND duration_Sim between '" + periodJDatePicker1 + "%' and '" + periodJDatePicker2 + "%'";
        } else if (!periodJDatePicker1.equals(null) && periodJDatePicker1.equals(null)) {
            constraint = constraint + " AND duration_Sim = '" + periodJDatePicker1 + "%'";
        } else if (periodJDatePicker1.equals(null) && !periodJDatePicker1.equals(null)) {
            constraint = constraint + " AND duration_Sim = '" + periodJDatePicker2 + "%'";
        }*/
        switch (typeLoan) {
            case "Consommation":
                constraint = constraint + " AND lr.description_LoanRef = 'Comsuption'";
            case "Immobilier":
                constraint = constraint + " AND lr.description_LoanRef = 'Immobilier'";
            case "Automobile":
                constraint = constraint + " AND lr.description_LoanRef = 'Automobile'";
            case "Véhicule":
                constraint = constraint + " AND lr.description_LoanRef = 'Comsuption'";
            case "Travaux":
                constraint = constraint + " AND lr.description_LoanRef = 'Travaux'";
            case "Personnel":
                constraint = constraint + " AND lr.description_LoanRef = 'Personnel'";
            case "Rachat de crédit":
                constraint = constraint + " AND lr.description_LoanRef = 'Rachat de credit'";
            default:
                constraint = constraint;

        }
        return constraint;
    }

    public String indicatorDynamicAge(JTextField ageJTextField) {
        String constraint2 = "";
        if (!ageJTextField.equals(null)) {
            constraint2 = "AND birthday_Customer like '" + Integer.toString(year - Integer.parseInt(ageJTextField.getText())) + "%'";
        }

        return constraint2;
    }

    public void indicatorDynamic(ResultSet rs, JLabel label1, JLabel label2, JLabel label3, JLabel label4) {
        try {
            int nbrLoan = 0;
            Double average = 0.0;
            int loanDuration = 0;
            int avgDuration = 0;
            while (rs.next()) {
                nbrLoan += 1;
                average += rs.getInt("amount_Sim");
                loanDuration += rs.getInt("duration_Sim");
            }
            try {
                avgDuration = (loanDuration / nbrLoan);
            } catch (ArithmeticException e) {
                avgDuration = 0;
            }

            label1.setText("Nombre de prêt : " + nbrLoan);
            label2.setText("Montant total des prêts : " + average);
            label3.setText("Durée moyenne des prêts : " + avgDuration);
            label4.setText("Benefice total");
        } catch (SQLException ex) {
            Logger.getLogger(ControllerScreenIndicator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
