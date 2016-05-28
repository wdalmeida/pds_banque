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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
    ArrayList<Indicator> indicators = new ArrayList();

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

    public ControllerScreenIndicator(ScreenIndicators sci, int idC0, JButton backButton) {
        this.db = AccessDB.getAccessDB();
        this.sci = sci;
        this.backHome = backButton;
        this.idConsultant = idC0;

    }

    public ControllerScreenIndicator(ScreenIndicators sci, int idC0, JPanel Cpanel, JComboBox<String> comboBox1) {
        this.db = AccessDB.getAccessDB();
        this.sci = sci;
        this.idConsultant = idC0;
        this.jPanel3 = Cpanel;
        this.chartComboBox = comboBox1;

    }
    
    
    public ControllerScreenIndicator(ScreenIndicators sci, int idC0,JComboBox<String> comboBox1,JTable loanIndicatorTable) {
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
        } else if (e.getSource() == chartComboBox) {
            // if (lineChartComboBox.getSelectedItem() ==) {
            try {
                LineChart chart = new LineChart(" Graphique d'évolution", "Number of Loan");
                jPanel3.add(chart, BorderLayout.CENTER);
                chart.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerScreenIndicator.class.getName()).log(Level.SEVERE, null, ex);
            }
            //}

        }

    }

    public String indicatorDynamic(JXDatePicker periodJDatePicker1, JXDatePicker periodJDatePicker2) {
        String constraint1 ="";
        if(periodJDatePicker1.equals(null) && periodJDatePicker1.equals(null)){
            constraint1="";
        }else if(!periodJDatePicker1.equals(null) && !periodJDatePicker1.equals(null)){
                constraint1 = "AND duration_Sim between '" + periodJDatePicker1 + "%' and '" + periodJDatePicker2 + "%'";
        }else if (!periodJDatePicker1.equals(null) && periodJDatePicker1.equals(null)){
                constraint1 = "AND duration_Sim = '" + periodJDatePicker1 + "%'";
                }
        else if (periodJDatePicker1.equals(null) && !periodJDatePicker1.equals(null)){
                constraint1 = "AND duration_Sim = '" + periodJDatePicker2 + "%'" ;
                }  
        return constraint1;
    }
    
     public String indicatorDynamicAge(JTextField ageJTextField) {
        String constraint2 ="";
        if(!ageJTextField.equals(null)){
            constraint2 = "AND birthday_Customer like '" + Integer.toString(year - Integer.parseInt(ageJTextField.getText()))+"%'";
        }
            
        return constraint2;
    }
    
    

}
