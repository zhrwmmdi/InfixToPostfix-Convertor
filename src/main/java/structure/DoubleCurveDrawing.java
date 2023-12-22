package structure;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

class CurvePanel extends JPanel {
    private double[] xPoints = InfixToPostfix.Xs;
    private double[] yPoints = InfixToPostfix.Ys;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawAxes(g);
        drawCurve(g);
    }

    private void drawAxes(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        // Draw X-axis
        g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        // Draw Y-axis
        g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }

    private void drawCurve(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Convert double-type points to int arrays
        int[] xIntPoints = Arrays.stream(xPoints).mapToInt(value -> (int) value).toArray();
        int[] yIntPoints = Arrays.stream(yPoints).mapToInt(value -> (int) value).toArray();

//        // Draw the curve using drawPolyline
//        g2d.setColor(Color.BLUE);
//        g2d.drawPolyline(xIntPoints, yIntPoints, xIntPoints.length);

        // Draw points without coordinates
        g2d.setColor(Color.RED);
        for (int i = 0; i < xIntPoints.length; i++) {
            int x = xIntPoints[i] + getWidth() / 2; // Adjust x-coordinate
            int y = getHeight() / 2 - yIntPoints[i]; // Adjust y-coordinate

            // Draw the point
            g2d.fillOval(x - 3, y - 3, 6, 6);
        }
    }

}

public class DoubleCurveDrawing extends JFrame {
    public DoubleCurveDrawing() {
        setTitle("Double Curve Drawing Example");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create an instance of CurvePanel and add it to the frame
        CurvePanel curvePanel = new CurvePanel();
        add(curvePanel);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            DoubleCurveDrawing doubleCurveDrawing = new DoubleCurveDrawing();
//            doubleCurveDrawing.setVisible(true);
//        });
//    }
}
