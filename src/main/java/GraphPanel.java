import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Vector;

class GraphPanel extends JPanel {
    // The list of functions to plot
    Vector<Function> functions;
    // The scale of the x and y axes
    double xScale, yScale;
    // The origin of the coordinate system
    int originX, originY;

    // Constructor
    GraphPanel() {
        functions = new Vector<>(); // initialize the function list
        xScale = 50; // set the default x scale
        yScale = 50; // set the default y scale
        originX = getWidth() / 2; // set the default origin x
        originY = getHeight() / 2; // set the default origin y
        setBackground(Color.WHITE); // set the background color
        addMouseWheelListener(new MouseWheelListener() { // add a listener for mouse wheel events
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
// If the user scrolls the mouse wheel, zoom in or out the graph
                int rotation = e.getWheelRotation(); // get the rotation of the wheel
                if (rotation < 0) { // if the rotation is negative, zoom in
                    xScale *= 1.1; // increase the x scale by 10%
                    yScale *= 1.1; // increase the y scale by 10%
                } else { // if the rotation is positive, zoom out
                    xScale /= 1.1; // decrease the x scale by 10%
                    yScale /= 1.1; // decrease the y scale by 10%
                }
                repaint(); // repaint the graph
            }
        });
        addMouseMotionListener(new MouseMotionListener() { // add a listener for mouse motion events
            @Override
            public void mouseDragged(MouseEvent e) {
// If the user drags the mouse, move the origin of the graph
                originX = e.getX(); // set the origin x to the mouse x
                originY = e.getY(); // set the origin y to the mouse y
                repaint(); // repaint the graph
            }

            @Override
            public void mouseMoved(MouseEvent e) {
// Do nothing
            }
        });
    }

    // Add a function to the graph
    void addFunction(Function f) {
        functions.add(f); // add the function to the list
        repaint(); // repaint the graph
    }

    // Clear all the functions from the graph
    void clearFunctions() {
        functions.clear(); // clear the function list
        repaint(); // repaint the graph
    }

    // Paint the graph
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the superclass method
        Graphics2D g2 = (Graphics2D) g; // cast the graphics object to Graphics2D
        g2.setStroke(new BasicStroke(2)); // set the stroke width to 2
        g2.setColor(Color.BLACK); // set the color to black
        g2.drawLine(0, originY, getWidth(), originY); // draw the x axis
        g2.drawLine(originX, 0, originX, getHeight()); // draw the y axis
        g2.drawString("0", originX - 10, originY + 15); // draw the origin label
        for (Function f : functions) { // for each function in the list
            g2.setColor(f.color); // set the color to the function color
            Polygon p = new Polygon(); // create a polygon to represent the function curve
            for (int x = 0; x < getWidth(); x++) { // for each pixel on the x axis
                double xValue = (x - originX) / xScale; // convert the pixel to the x value
                double yValue = f.evaluate(xValue); // evaluate the function at the x value
                int y = originY - (int) (yValue * yScale); // convert the y value to the pixel
                p.addPoint(x, y); // add the point to the polygon
            }
            g2.drawPolyline(p.xpoints, p.ypoints, p.npoints); // draw the polygon as a polyline
        }
    }
}
