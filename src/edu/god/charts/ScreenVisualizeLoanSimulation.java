package edu.god.charts;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.SwingUtilities;

/**
 *
 * @author Florian
 */
public class ScreenVisualizeLoanSimulation extends JFrame implements Printable {

    //variable declaration
    public static int tMonths;
    double principalAmount;
    double interestRate;
    double totalInterestsValue;
    double insuranceRate;
    String monthlyPayment;
    String totalPayment;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    //every Swing component is located inside the initComponents method
    public ScreenVisualizeLoanSimulation() {
        super("Afficher et imprimer les résultats d'un calcul de prêt");
        initComponents();

    }

    //Swing components and action listeners
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principalAmountTextField = new javax.swing.JTextField();
        interestRateTextField = new javax.swing.JTextField();
        termMonthsTextField = new javax.swing.JTextField();
        monthlyPaymentTextField = new javax.swing.JTextField();
        totalPaymentsTextField = new javax.swing.JTextField();
        totalInterestsTextField = new javax.swing.JTextField();
        tableButton = new javax.swing.JButton();
        graphButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        printWindowButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        startDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        endDateTextField = new javax.swing.JTextField();
        insuranceRateLabel = new javax.swing.JLabel();
        insuranceRateTextfield = new javax.swing.JTextField();
        totalInsuranceLabel = new javax.swing.JLabel();
        totalInsuranceTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableButton.setText("Générer le tableau");
        tableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableButtonActionPerformed(evt);
            }
        });

        graphButton.setText("Générer le graphique");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Capital emprunté");

        jLabel2.setText("Taux d'intérèt");

        jLabel3.setText("Durée (en mois)");

        jLabel4.setText("Montant des mensualités");

        jLabel5.setText("Paiement total");

        jLabel6.setText("Total des intérèts");

        printWindowButton.setText("Imprimmer fenêtre");
        printWindowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printWindowButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Date de début du prêt");

        jLabel8.setText("Date de fin de prêt");

        insuranceRateLabel.setText("Taux d'assurance");

        totalInsuranceLabel.setText("Total de l'assurance");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(printWindowButton)
                        .addGap(336, 336, 336))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(196, 196, 196)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)))
                                    .addComponent(tableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                    .addComponent(insuranceRateLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel4)))
                                .addGap(35, 35, 35))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addComponent(totalInsuranceLabel)
                                .addGap(41, 41, 41)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(totalInsuranceTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(insuranceRateTextfield, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(principalAmountTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(interestRateTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(termMonthsTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(monthlyPaymentTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalPaymentsTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalInterestsTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endDateTextField, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(163, 163, 163)
                                .addComponent(graphButton)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(127, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tableButton)
                            .addComponent(graphButton))
                        .addGap(190, 190, 190))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(principalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insuranceRateLabel)
                            .addComponent(insuranceRateTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(interestRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(termMonthsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(startDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(41, 41, 41)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monthlyPaymentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalPaymentsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalInterestsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalInsuranceLabel)
                            .addComponent(totalInsuranceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(endDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)))
                .addComponent(printWindowButton)
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //launch the creation of the amortization table
    private void tableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableButtonActionPerformed

        try { //checks whether the entered values for principal amount, interest rate and term (in months) are numeric or not

            float pAmount, iRate, aRate, tMonths;
            pAmount = Float.parseFloat(principalAmountTextField.getText());
            aRate = Float.parseFloat(insuranceRateTextfield.getText());
            iRate = Float.parseFloat(interestRateTextField.getText());
            tMonths = Float.parseFloat(termMonthsTextField.getText());

        } catch (NumberFormatException exception) { // number format exception is caught when these values are not numeric

            JOptionPane.showConfirmDialog(null, "Merci d'entrer des valeurs numériques", "Exception", JOptionPane.PLAIN_MESSAGE);

        }
        AmortizationCalculation am = new AmortizationCalculation();
        String payInfo = null;
        payInfo = am.getpayInfo(principalAmountTextField.getText(), interestRateTextField.getText(), insuranceRateTextfield.getText(), termMonthsTextField.getText());

        String mPay = payInfo.split("####")[0]; // monthly payment
        String tPay = payInfo.split("####")[1]; //total payment
        String tInterests = payInfo.split("####")[2]; //total interests
        String mInsurance = payInfo.split("####")[3];  //monthly insurance
        String tInsurance = payInfo.split("####")[4]; //total insurance

        monthlyPaymentTextField.setText(mPay); //monthly payment
        totalPaymentsTextField.setText(tPay);  //total payment
        totalInterestsTextField.setText(tInterests); //total interests
        totalInsuranceTextField.setText(tInsurance); //total insurance

        AmortizationTable amT = new AmortizationTable(); // an object of the amortization table
        amT.getTableInfo(principalAmountTextField.getText(), interestRateTextField.getText(), insuranceRateTextfield.getText(), termMonthsTextField.getText());

        endDateTextField.setText(dateFormat.format(addMonths(startDatePicker.getDate(), Integer.parseInt(termMonthsTextField.getText()))));

    }//GEN-LAST:event_tableButtonActionPerformed

    //lancement de la creation des graphiques
    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
        //DataValuesClass.calculate(Double.parseDouble(txtPrincipal.getText()), Integer.parseInt(txtTerms.getText()), Double.parseDouble(txtRate.getText()));
        //DataValuesClass.calculate(Double.parseDouble("100000"), Integer.parseInt("24"), Double.parseDouble("5"));
        //GraphClass.callGraphClass(); // To Display Graph
        try { //checks if the entered values are numeric or not

            float pAmount, iRate, tMonths;
            pAmount = Float.parseFloat(principalAmountTextField.getText());
            iRate = Float.parseFloat(interestRateTextField.getText());
            tMonths = Float.parseFloat(termMonthsTextField.getText());

        } catch (NumberFormatException exception) { // number format exception is caught when these values are not numeric
            //displays an error message
            JOptionPane.showConfirmDialog(null, "Merci d'entrer des valeurs numériques", "Exception", JOptionPane.PLAIN_MESSAGE);

        }
        AmortizationCalculation am = new AmortizationCalculation();
        String payInfo = null;
        payInfo = am.getpayInfo(principalAmountTextField.getText(), interestRateTextField.getText(), insuranceRateTextfield.getText(), termMonthsTextField.getText());

        String mPay = payInfo.split("####")[0]; // monthly payment
        String tPay = payInfo.split("####")[1]; //total payment
        String tInterests = payInfo.split("####")[2]; //total interests

        monthlyPaymentTextField.setText(mPay);
        totalPaymentsTextField.setText(tPay);
        totalInterestsTextField.setText(tInterests);
        tMonths = Integer.parseInt(termMonthsTextField.getText());

        AmortizationGraph amG = new AmortizationGraph();
        amG.getGraphInfo(principalAmountTextField.getText(), interestRateTextField.getText(), termMonthsTextField.getText(), totalInterestsTextField.getText(), totalPaymentsTextField.getText(), insuranceRateTextfield.getText());

        endDateTextField.setText(dateFormat.format(addMonths(startDatePicker.getDate(), Integer.parseInt(termMonthsTextField.getText()))));


    }//GEN-LAST:event_graphButtonActionPerformed

    private void printWindowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printWindowButtonActionPerformed

        PrinterJob job = PrinterJob.getPrinterJob();
        PageFormat format = job.defaultPage();
        format.setOrientation(PageFormat.LANDSCAPE);

        job.setPrintable(this, format);

        try {
            if (job.printDialog()) {
                job.print();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_printWindowButtonActionPerformed

    //actual printing function
    public int print(Graphics g, PageFormat format, int pagenum) {

        if (pagenum > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        g.translate((int) format.getImageableX(), (int) format.getImageableY());

        float pageWidth = (float) format.getImageableWidth();
        float pageHeight = (float) format.getImageableHeight();
        float imageHeight = (float) this.getHeight();
        float imageWidth = (float) this.getWidth();
        float scaleFactor = Math.min((float) pageWidth / (float) imageWidth, (float) pageHeight / (float) imageHeight);
        int scaledWidth = (int) (((float) imageWidth) * scaleFactor);
        int scaledHeight = (int) (((float) imageHeight) * scaleFactor);

        BufferedImage canvas = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gg = canvas.createGraphics();
        this.paint(gg);
        Image img = canvas;

        g.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);

        return Printable.PAGE_EXISTS;

    }


    /*public static Date addMonths(int months) {
     Calendar cal = Calendar.getInstance();
     Date todayDate = new Date();
     cal.setTime(todayDate);
     cal.add(Calendar.MONTH, months); //minus number would decrement the days
     return cal.getTime();
     }
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months); //minus number would decrement the days
        return cal.getTime();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ScreenVisualizeLoanSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScreenVisualizeLoanSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScreenVisualizeLoanSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenVisualizeLoanSimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                ScreenVisualizeLoanSimulation gui = new ScreenVisualizeLoanSimulation();
                gui.setVisible(true);

            }
        });
    }

    //getters and setters
    public void setMonthlyPayment(String monthlyPayment) {
        //this.monthlyPayment = monthlyPayment;
        monthlyPaymentTextField.setText(monthlyPayment);
    }

    public void setTotalPayment(String totalPayment) {
        //this.totalPayment = totalPayment;
        totalPaymentsTextField.setText(totalPayment);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField endDateTextField;
    private javax.swing.JButton graphButton;
    private javax.swing.JLabel insuranceRateLabel;
    private javax.swing.JTextField insuranceRateTextfield;
    private javax.swing.JTextField interestRateTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField monthlyPaymentTextField;
    private javax.swing.JTextField principalAmountTextField;
    private javax.swing.JButton printWindowButton;
    private org.jdesktop.swingx.JXDatePicker startDatePicker;
    private javax.swing.JButton tableButton;
    private javax.swing.JTextField termMonthsTextField;
    private javax.swing.JLabel totalInsuranceLabel;
    private javax.swing.JTextField totalInsuranceTextField;
    private javax.swing.JTextField totalInterestsTextField;
    private javax.swing.JTextField totalPaymentsTextField;
    // End of variables declaration//GEN-END:variables
}
