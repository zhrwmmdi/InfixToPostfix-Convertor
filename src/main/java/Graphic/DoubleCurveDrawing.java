package Graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoubleCurveDrawing extends JFrame {
    // Create an instance of CurvePanel and add it to the frame
    CurvePanel curvePanel;
    public DoubleCurveDrawing() {
        setTitle("Curve Drawing");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        curvePanel = new CurvePanel();
        add(curvePanel);

        // Add Zoom In button
        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curvePanel.setScale(curvePanel.scale * 1.2); // Adjust the scaling factor
            }
        });

        // Add Zoom Out button
        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                curvePanel.setScale(curvePanel.scale / 1.2); // Adjust the scaling factor
            }
        });

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(zoomInButton);
        buttonPanel.add(zoomOutButton);

        // Add button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
