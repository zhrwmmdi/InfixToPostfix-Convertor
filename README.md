# Infix to Postfix Convertor Application

This is an educational project that converts an infix mathematical expression (e.g., 3+6-(8/4)%2) to its postfix version and evaluates it.
It also works for decimals.
In case the input is not numeric and is parametric, the application will produce its function and draw its graph.
For infix to postfix conversion structure.Stack data structure is needed that is manually designed is this application.

### The project is divided into four packages.
**manager package** is for getting the infix expression as input from user and convert it to a list for later uses.
InputManager class in this package is the only class that is initialized in main method. Everything starts here!

**calculation package** is designed for evaluation of postfix expressions if is numeric, or finding the points of its curve
if it is parameter based.

**graphic package** is used for drawing the curve of the parameter-type expressions using Swing & AWT.

**structure package** contains the classes which are the base of the program, the Stack class and the InfixToPostfix class
that uses the Stack to convert the infix expression to postfix, and also Point class that consists of two double parameters (X & Y).
This class stores the points of a curve and is used later in graphic package.




