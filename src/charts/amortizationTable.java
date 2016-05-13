package charts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//amortization table 
public class amortizationTable extends JFrame {

    double interestPaid;
    double principalPaid;
    double monthlyPayment;
    private javax.swing.JButton printTableButton;

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);
    JPanel p = new JPanel(); 

    //used to get the table information
    //Input: Principal amount, interest rate and term (in months)
    public void getTableInfo(String principalAmountTextField, String interestRateTextField, String termMonthsTextField) {

        double principalAmount = Double.parseDouble(principalAmountTextField); //parsed principal amount from the principal amount text field
        double interestRate = Double.parseDouble(interestRateTextField);  //parsed interest rate from the interest rate text field
        int termMonths = Integer.parseInt(termMonthsTextField);  ////parsed term in months from the term (in months) text field
        String[] columnNames = {"Numéro de mensualité", "Restant à payer avant paiement", "Montant des intérèts de la mensualité", "Montant du capital remboursé", "Restant à payer après paiement"}; // column names

        for (int columnLength = 0; columnLength < 5; columnLength++) {
            model.addColumn(columnNames[columnLength]);
        }

        if (principalAmount > 0 && interestRate != 0 && termMonths != 0) {
            double newPrincipal = principalAmount;
            interestRate = interestRate / 100.0;
            double monthlyInterestRate = interestRate / 12.0;
            monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -termMonths))); //calculated monthly payment
            double monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -termMonths)));

            NumberFormat nf = NumberFormat.getCurrencyInstance();

            for (int numberOfTerms = 0; numberOfTerms < termMonths; numberOfTerms++) {
                Object data[] = new Object[5];
                data[0] = Integer.toString(numberOfTerms + 1); //payment number
                data[1] = nf.format(newPrincipal);  //begin balance
                data[2] = nf.format(interestPaid = principalAmount * (monthlyInterestRate)); //interest paid
                data[3] = nf.format(principalPaid = monthlyPayment - interestPaid); //principal paid
                data[4] = nf.format(principalAmount = principalAmount - principalPaid); //ending balance
                newPrincipal = principalAmount;
                model.addRow(data);
            }
        }

        //JFrame f = new JFrame();
        printTableButton = new javax.swing.JButton();
        printTableButton.setText("Imprimer le tableau");
        printTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printTableButtonActionPerformed(evt);
            }
        });
        
        this.setSize(600, 600);
        this.setTitle("Tableau d'amortissement"); //the title of the table
        //f.add(new JScrollPane(table));
        this.setVisible(true);
        
     
        p.add(new JScrollPane(table));
        p.add(printTableButton);
        this.getContentPane().add(p);
    }

        private void printGradesTable() {
        /* Fetch printing properties from the GUI components */

        MessageFormat header = null;
        MessageFormat footer = null;
        
        try {
            /* print the table */
            boolean complete = table.print();

            /* if printing completes */
            if (complete) {
                /* show a success message */
                JOptionPane.showMessageDialog(this,
                                              "Printing Complete",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            } else {
                /* show a message indicating that printing was cancelled */
                JOptionPane.showMessageDialog(this,
                                              "Printing Cancelled",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            /* Printing failed, report to the user */
            JOptionPane.showMessageDialog(this,
                                          "Printing Failed: " + pe.getMessage(),
                                          "Printing Result",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

 private void printTableButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        
         printGradesTable();
        
    }                                                
}
