/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.entities;

import edu.god.models.AccessDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nabil
 */
public class LoanTable extends AbstractTableModel {

    private List<LoanIndicator> loanTable;
    private String[] header = {"Prénom", "Nom", "Taux", "Mensualité", "Durée du prêt", "Date de Naissance"};
    private AccessDB db = AccessDB.getAccessDB();
    private final int idConsultant;

    public LoanTable(int idConsultant) {
        super();
        this.idConsultant = idConsultant;
        try {
            loanTable = db.getLaonInformation();
        } catch (SQLException ex) {
            Logger.getLogger(LoanTable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getRowCount() {
        return loanTable.size();
    }

    public int getColumnCount() {
        return header.length;
    }

    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {

        //for (int i = 0; i < loanTable.size(); i++) {
        switch (columnIndex) {
            case 0:
                return loanTable.get(rowIndex).getFirstName();
            case 1:
                return loanTable.get(rowIndex).getLastName();
            case 2:
                return loanTable.get(rowIndex).getPercentage_Rate();
            case 3:
                return loanTable.get(rowIndex).getMonthly_Sim();
            case 4:
                return loanTable.get(rowIndex).getDuration_Sim();
            case 5:
                return loanTable.get(rowIndex).getBirthday_Customer();

            default:
                return null; //Ne devrait jamais arriver
            }
    }

}
