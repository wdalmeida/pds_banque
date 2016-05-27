package edu.god.views;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;


/**
 * 
 * @author Florian
 * Pie Chart component, needs to provide the values of the slices to be properly displayed
 * 
 */
class PieChart {

    double value;
    Color color;

    public PieChart(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}

class MyComponent extends JComponent {

    static double principalAmount;
    static double totalInterests;
    static double totalInsurance;

    public static void setChartValues(double value1, double value2, double value3) {
        MyComponent.principalAmount = value1;
        MyComponent.totalInterests = value2;
        MyComponent.totalInsurance = value3;

    }

    PieChart[] slices = {
        new PieChart(principalAmount, Color.black),
        new PieChart(totalInterests, Color.green),
        new PieChart(totalInsurance, Color.red)};

    MyComponent() {
    }
    
    Rectangle bounds = new Rectangle(0, 0, 200, 200);
    public void paint(Graphics g) {
        drawPie((Graphics2D) g, bounds , slices);
    }

    void drawPie(Graphics2D g, Rectangle area, PieChart[] slices) {
        double total = 0.0D;
        for (int i = 0; i < slices.length; i++) {
            total += slices[i].value;
        }
        double curValue = 0.0D;
        int startAngle = 0;
        for (int i = 0; i < slices.length; i++) {
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (slices[i].value * 360 / total);
            g.setColor(slices[i].color);
            g.fillArc(area.x, area.y, area.width, area.height,
                    startAngle, arcAngle);
            curValue += slices[i].value;
        }
    }

  /*  public static void main(String[] argv) {
        JFrame frame = new JFrame();

        setChartValues(85, 10, 5);

        frame.getContentPane().add(new MyComponent());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
*/
}
