/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.entities;

import edu.god.controllers.ControllerScreenIndicator;
import edu.god.models.AccessDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nabil
 */
public class IndicatorTable extends AbstractTableModel {
    
    private List<Indicator> indicators = new ArrayList();            
    private String[] header = {"Indicateur","Valeur","Agence"};
    private AccessDB db = AccessDB.getAccessDB();
    private final int idConsultant;
    private final int idAgency;
    private  String city = "nab";
            
    public IndicatorTable(int idC0) throws SQLException {
        super();
        System.out.println(city);
        this.idConsultant = idC0;
        this.idAgency = db.getidAgency(idC0);
        this.city = db.getAgencyCity(idC0);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR); 
        indicators.add(new Indicator("Nombre de prêt",db.getLoanNumber(year),city));
        indicators.add(new Indicator("Type de prêt le plus contracté","Consommation",city));
        indicators.add(new Indicator("Durée moyenne des prêts","37 "+ " mois",city));
        indicators.add(new Indicator("Nombre de simulation",db.getNumberSimulation(idAgency),city));
        System.out.println(city);
        
        
    }
 
    public int getRowCount() {
        return indicators.size();
    }
 
    public int getColumnCount() {
        return header.length;
    }
 
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }
 
     public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return indicators.get(rowIndex).getName();
            case 1:
                return indicators.get(rowIndex).getValue();
            case 2:
                return indicators.get(rowIndex).getEntity();
            default:
                return null; //Ne devrait jamais arriver
        }
     }
}
