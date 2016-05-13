package charts;

import charts.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;

class Pie_Chart {



    double value;
    Color color;

    public Pie_Chart(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}

class MyComponent extends JComponent {

    static int principalAmount;
    static int totalInterests;
    static int totalInsurance;

    public static void setChartValues(int value1, int value2, int value3) {
        MyComponent.principalAmount = value1;
        MyComponent.totalInterests = value2;
        MyComponent.totalInsurance = value3;

    }

    Pie_Chart[] slices = {
        new Pie_Chart(principalAmount, Color.black),
        new Pie_Chart(totalInterests, Color.green),
        new Pie_Chart(totalInsurance, Color.red)};

    MyComponent() {
    }
    
    Rectangle bounds = new Rectangle(0, 0, 200, 200);
    public void paint(Graphics g) {
        drawPie((Graphics2D) g, bounds , slices);
    }

    void drawPie(Graphics2D g, Rectangle area, Pie_Chart[] slices) {
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

    public static void main(String[] argv) {
        JFrame frame = new JFrame();

        setChartValues(85, 10, 5);

        frame.getContentPane().add(new MyComponent());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}
