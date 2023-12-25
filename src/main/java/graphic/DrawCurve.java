package graphic;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DrawCurve extends JPanel {
    private final List<structure.Point> points;

    public DrawCurve(List<structure.Point> points) {
        this.points = points;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2dForAxis = (Graphics2D) g;
        Graphics2D g2dForCurve = (Graphics2D) g;

        // Draw coordinate axis
        g2dForAxis.setColor(Color.BLACK);
        g2dForAxis.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); // X-axis
        g2dForAxis.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight()); // Y-axis

        // Draw the curve by connecting points
        g2dForCurve.setColor(Color.RED);
        if (points.size() > 1) {
            for (int i = 0; i < points.size() - 1; i++) {
                int x1 = (int) points.get(i).getX() + getWidth() / 2;
                int y1 = getHeight() / 2 - (int) points.get(i).getY();
                int x2 = (int) points.get(i + 1).getX() + getWidth() / 2;
                int y2 = getHeight() / 2 - (int) points.get(i + 1).getY();

                g2dForCurve.drawLine(x1, y1, x2, y2);
            }
        }
    }

    public void draw() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Draw Curve");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DrawCurve drawCurve = new DrawCurve(points);
            frame.add(drawCurve);

            frame.setVisible(true);
        });
    }
}

