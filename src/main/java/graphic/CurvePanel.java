package graphic;

import calculation.Calculate;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

class CurvePanel extends JPanel {
    private final double[] xPoints = Calculate.xPoints;
    private final double[] yPoints = Calculate.yPoints;
    double scale = 1.0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // Apply scaling transformation
        g2d.scale(scale, scale);

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

        // Draw points without coordinates
        g2d.setColor(Color.RED);
        for (int i = 0; i < xIntPoints.length; i++) {
            int x = xIntPoints[i] + getWidth() / 2; // Adjust x-coordinate
            int y = getHeight() / 2 - yIntPoints[i]; // Adjust y-coordinate

            // Draw the point
            g2d.fillOval(x - 3, y - 3, 6, 6);
        }
    }
    public void setScale(double scale) {
        this.scale = scale;
        repaint();
    }
}



