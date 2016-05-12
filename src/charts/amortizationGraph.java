package charts;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import java.text.NumberFormat;
import javax.swing.*;


public class amortizationGraph extends JPanel implements MouseListener, Printable {

    JScrollPane scrollingPane = null;
    //takes in parameter the name of the class calling the graph class
    JPanel panel = new JPanel();
    JFrame f = new JFrame();
    private javax.swing.JButton printGraphButton;

    double[] dGraph = new double[newGui.tMonths];
    final int PAD = 100;
    double monthlyPayment;
    double interestPaid;
    double principalPaid;
    double principalAmount;
    double interestRate;
    double termMonths;
    double totalInterests;
    double totalPayments;
    double initialAmount;

    //This class adds the table to the JFrame
    public void getGraphInfo(String principalAmountTextField, String interestRateTextField, String termMonthsTextField, String totalInterestsTextField, String totalPaymentsTextField) {

        
        initialAmount = Double.parseDouble(principalAmountTextField); //the actual amount of the loan
        principalAmount = Double.parseDouble(principalAmountTextField); //principal amount
        interestRate = Double.parseDouble(interestRateTextField); //interest rate
        termMonths = Integer.parseInt(termMonthsTextField);  // term (in months)
        totalInterests = Double.parseDouble(totalInterestsTextField);  // total of the interests
        totalPayments = Double.parseDouble(totalPaymentsTextField);  // total of the payment
        double newPrincipal = principalAmount;
        interestRate = interestRate / 100.0;
        double monthlyInterestRate = interestRate / 12.0; //monthly interest rate
        monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -termMonths))); //calculated monthly payment

        if (principalAmount > 0 && interestRate != 0 && termMonths != 0) {

            for (int numberOfTerms = 0; numberOfTerms < termMonths; numberOfTerms++) { //the data is calculated for each of the terms

                interestPaid = principalAmount * (monthlyInterestRate); //interest paid
                principalPaid = monthlyPayment - interestPaid; //principal paid
                principalAmount = principalAmount - principalPaid; //principal amount
                newPrincipal = principalAmount;
                this.dGraph[numberOfTerms] = principalAmount; // graph is drawn ( number of terms vs remaining balance)

            }

        }

        System.out.println((int) Double.parseDouble(principalAmountTextField));

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        //MyComponent.setChartValues(85, 10, 5); 
        int principalAmountPercent = (Integer.parseInt(principalAmountTextField) * 100) / (int) totalPayments;
        int totalInterestsPercent = ((int) totalInterests * 100) / (int) totalPayments;

        MyComponent.setChartValues(principalAmountPercent, totalInterestsPercent, 1); //first parameter needs to take insurance in account
        // percent = (n * 100.0f) / v;
        
        printGraphButton = new javax.swing.JButton();
        printGraphButton.setText("Imprimer");
          printGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
             //   printGraphButtonActionPerformed(evt);
              printJavaComponent();
            }
        });

        
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 500));
        panel.add(new MyComponent());
        this.add(printGraphButton);

        GridLayout layout = new GridLayout(1, 2);
        f.setLayout(layout);
        f.add(this);
        f.add(panel);
        f.setSize(800, 500);
        f.setLocation(200, 200);
        f.setTitle("Graphiques"); //the title of the window
        f.setVisible(true);
        f.setBackground(Color.WHITE);
        f.pack();

        addMouseListener(this);

    }

    //paint component     
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD)); // draw the y-axis

        g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD)); // draw the labels

        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();

        String s = "Reste à rembourser"; // x-axis label
        float sy = PAD + ((h - 2 * PAD) - s.length() * sh) / 2 + lm.getAscent();
        for (int i = 0; i < s.length(); i++) {

            String letter = String.valueOf(s.charAt(i));
            float sw = (float) font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw) / 2;
            g2.drawString(letter, sx, sy);
            sy += sh;

        }

        s = "Nombre de mensualités"; //y-axis label
        sy = h - PAD + (PAD - sh) / 2 + lm.getAscent();
        float sw = (float) font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw) / 2;
        g2.drawString(s, sx, sy);

        double xInc = (double) (w - 2 * PAD) / (dGraph.length - 1); //draw the lines
        double scale = (double) (h - 2 * PAD) / getMax();
        g2.setPaint(Color.green.darker());

        for (int i = 0; i < dGraph.length - 1; i++) {

            double x1 = PAD + i * xInc;
            double y1 = h - PAD - scale * dGraph[i];
            double x2 = PAD + (i + 1) * xInc;
            double y2 = h - PAD - scale * dGraph[i + 1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));

        }

        //draw the data points
        g2.setPaint(Color.blue);

        for (int i = 0; i < dGraph.length; i++) {

            double x = PAD + i * xInc;
            double y = h - PAD - scale * dGraph[i];
            g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));

        }

    }

    private double getMax() {

        double max = -Double.MAX_VALUE;

        for (int i = 0; i < dGraph.length; i++) {

            if (dGraph[i] > max) {
                max = dGraph[i];
            }

        }

        return max;

    }

    public void printJavaComponent() {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setJobName("Print Java Component");
 
    job.setPrintable (new Printable() {    
        public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
            if (pageIndex > 0) {
                return(NO_SUCH_PAGE);
            } else {
                Graphics2D g2d = (Graphics2D)g;
                g2d.translate(pageFormat.getImageableX(), 
                pageFormat.getImageableY());
 
                panel.paint(g2d);
 
                return(PAGE_EXISTS); 
            }
        }
    });
         
    if (job.printDialog()) {
        try {
            job.print();
        } catch (PrinterException e) {
            System.err.println(e.getMessage()); 
        }
    }
}
   

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //the mouseOver
        //JOptionPane.showConfirmDialog(this, "Tout plein d'informations", "Bien ou bien", JOptionPane.YES_NO_OPTION);
        panel.setToolTipText("Somme empruntée: " + initialAmount + " Euros" + '\n' + "Total des intérèts payés: " + totalInterests + " Euros" + '\n' + "Total de la somme à rembourser: " + totalPayments + " Euros");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
