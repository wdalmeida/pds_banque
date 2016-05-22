/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.controllers;

import edu.god.entities.Indicator;
import edu.god.models.AccessDB;
import edu.god.views.ScreenIndicators;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author nabil
 */
public class ControllerScreenIndicator implements ActionListener{
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
    ArrayList<Indicator> indicators = new ArrayList();

    public ControllerScreenIndicator(ScreenIndicators sci,JComboBox<String> indicatorComboBox, JTable indicatorJtable,JPanel indicatorPanel,JPanel indicatorDPanel, JPanel graphicPanel ){
        this.db= AccessDB.getAccessDB();
        this.AgencyComboBox = indicatorComboBox;
        this.sci = sci;
        this.indicatorJtable = indicatorJtable;
        this.jPanel1 = indicatorPanel;
        this.jPanel2 = indicatorDPanel;
        this.jPanel3 = graphicPanel;

    }
    @Override
    
    public void actionPerformed(ActionEvent e) {
        
        }
        
   
    
    
} 
