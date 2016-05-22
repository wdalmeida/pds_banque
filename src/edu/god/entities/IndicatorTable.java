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
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nabil
 */
public class IndicatorTable extends AbstractTableModel {
    
    private List<Indicator> indicators;            
    private String[] header = {"Indicateur","Valeur","Agence"};
    AccessDB db = AccessDB.getAccessDB();
    public IndicatorTable() throws SQLException {
        super();
        
        indicators.add(new Indicator("Taux Moyen",db.getRateAverage(),"Paris"));
        indicators.add(new Indicator("Nombre de prêt",db.getLoanNumber(),"Paris"));

        
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
