/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.god.entities;

import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author florent
 */
public class SimulationTable {

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    //used to get the table information
    //Input: Principal amount, interest rate and term (in months)

    /**
     *
     * @param simulationtabledb
     */
    public SimulationTable(ArrayList<String[]> simulationtabledb) {
    String[] columnNames = {"Choix", "Type", "Capital", "Interet", "Mensualite pret", "Mensualite assurance", "Total a rembourser", "Durée"}; // column names

        for (int columnLength = 0; columnLength < 8; columnLength++) {
            model.addColumn(columnNames[columnLength]);
        }

       /* if (principalAmount > 0 && interestRate != 0 && termMonths != 0) {
            double newPrincipal = principalAmount;
            interestRate = interestRate / 100.0;
            double monthlyInterestRate = interestRate / 12.0;
            monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -termMonths))); //calculated monthly payment
            double monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -termMonths)));
        */
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            
            for (int numberOfTerms = 0; numberOfTerms < simulationtabledb.size(); numberOfTerms++) {
                Object data[] = new Object[8];
                data[0] = Integer.toString(numberOfTerms); //Simulation number
                System.out.println("Choix : " + data[0]);
                data[1] = simulationtabledb.get(numberOfTerms); // Loan Type
                /*data[2] = nf.format(simulationtabledb[numberOfTerms][2]); //interest paid
                data[3] = nf.format(simulationtabledb[numberOfTerms][3]); //principal paid
                data[4] = nf.format(simulationtabledb[numberOfTerms][4]); //ending balance
                data[5] = nf.format(simulationtabledb[numberOfTerms][5]); //ending balance
                data[6] = nf.format(simulationtabledb[numberOfTerms][6]); //ending balance
                data[7] = nf.format(simulationtabledb[numberOfTerms][7]); //ending balance
                */model.addRow(data);
            }
    }
    public JTable getJTable(){
        return this.table;
    }
}
