import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

class GraphFrame extends JFrame {
    InfixToPostfix obj = new InfixToPostfix();
    // The graph panel
    GraphPanel graphPanel;
    // The text field for entering the infix expression
    JTextField expressionField;
    // The button for adding the function to the graph
    JButton addButton;
    // The button for clearing the graph
    JButton clearButton;
    // The color chooser for selecting the function color
    JColorChooser colorChooser;

    // Constructor
    GraphFrame() {
        setTitle("Graph Application"); // set the title of the frame
        setSize(800, 600); // set the size of the frame
        setLocationRelativeTo(null); // center the frame on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the default close operation
        setLayout(new BorderLayout()); // set the layout of the frame

        graphPanel = new GraphPanel(); // create the graph panel
        add(graphPanel, BorderLayout.CENTER); // add the graph panel to the center of the frame

        JPanel controlPanel = new JPanel(); // create a control panel
        controlPanel.setLayout(new FlowLayout()); // set the layout of the control panel
        add(controlPanel, BorderLayout.SOUTH); // add the control panel to the south of the frame


        //addButton = new JButton("Add"); // create the add button
        //InfixToPostfix obj = new InfixToPostfix();
        Color color = colorChooser.getColor(); // get the color from the color chooser
        Function f = new Function(obj.toPostFix(obj.inputToList(obj.getInfix())), color); // create a function object
        graphPanel.addFunction(f); // add the function to the graph panel

        setVisible(true);

        }


    // Check if a token is an operand
    boolean isOperand(String token) {
// Try to parse the token as a double
        try {
            Double.parseDouble(token);
            return true; // if successful, return true
        } catch (NumberFormatException e) {
            return false; // if not, return false
        }
    }

    // Check if a token is an operator
    boolean isOperator(String token) {
// Return true if the token is one of the four basic operators
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    // Get the precedence of an operator
    int precedence(String operator) {
// Return a higher value for higher precedence
        switch (operator) {
            case "+":
            case "-":
                return 1; // lowest precedence
            case "*":
            case "/":
                return 2; // higher precedence
            default:
                return 0; // invalid operator
        }
    }


}