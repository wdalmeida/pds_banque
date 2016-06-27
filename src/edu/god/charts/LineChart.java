/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.charts;

/**
 *
 * @author nabil
 */
import edu.god.models.AccessDB;
import java.awt.Dimension;
import java.awt.Panel;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart extends JPanel {

    private AccessDB db;

    public LineChart(String chartTitle) throws SQLException {
        this.db = AccessDB.getAccessDB();
        JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Years", "Number of loans", createDataset(), PlotOrientation.VERTICAL, true, true, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(300,300));
        
    }

    DefaultCategoryDataset createDataset() throws SQLException {
        //System.out.println("Test 1");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        //System.out.println("Test 2");
        for (int i = 0; i < 11; i++) {
        //System.out.println("Test 3");
        dataset.addValue(Integer.parseInt(db.getLoanNumber((year-10)+i)), "Number of Loans", Integer.toString((year - 10) + i));
        }
        //System.out.println("Test sortie boucle");
        return dataset;
    }
   
   
   

    /*public static void main(String[] args) throws SQLException {
        LineChart chart = new LineChart("School Vs Years", "Numer of Schools vs years");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);

    }*/
}
