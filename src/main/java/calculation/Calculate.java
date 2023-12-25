package calculation;

import structure.InfixToPostfix;
import structure.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Calculate {
    public static double[] xPoints;
    public static double[] yPoints;
    static final String numberRegex = "[0-9]+\\.?[0-9]*$";
    public static List<Point> pointList = new ArrayList<>();

    public static void fillPoints(){
        int xValue = -200;
        for (int i = 0; i < 401; i++) {
            pointList.add(new Point(xValue,evaluate(InfixToPostfix.postfixList, xValue)));
            xValue++;
        }
    }
    public static double evaluate(List<String> postfix, int x) {
        for (String s : postfix) {
            if (s.matches(numberRegex)) {
                InfixToPostfix.numberStack.push(Double.valueOf(s));
            } else if (s.equalsIgnoreCase("x")) {
                InfixToPostfix.numberStack.push((double) x);
            } else if (Objects.equals(s, "*")) {
                double num1 = InfixToPostfix.numberStack.pop();
                double num2 = InfixToPostfix.numberStack.pop();
                InfixToPostfix.numberStack.push(num2 * num1);
            } else if (Objects.equals(s, "+")) {
                double num1 = InfixToPostfix.numberStack.pop();
                double num2 = InfixToPostfix.numberStack.pop();
                InfixToPostfix.numberStack.push(num2 + num1);
            } else if (Objects.equals(s, "-")) {
                double num1 = InfixToPostfix.numberStack.pop();
                double num2 = InfixToPostfix.numberStack.pop();
                InfixToPostfix.numberStack.push(num2 - num1);
            } else if (Objects.equals(s, "/")) {
                double num1 = InfixToPostfix.numberStack.pop();
                double num2 = InfixToPostfix.numberStack.pop();
                InfixToPostfix.numberStack.push(num2 / num1);
            } else if (Objects.equals(s, "%")) {
                double num1 = InfixToPostfix.numberStack.pop();
                double num2 = InfixToPostfix.numberStack.pop();
                InfixToPostfix.numberStack.push(num2 % num1);
            }
            else if (Objects.equals(s, "^")) {
                double num1 = InfixToPostfix.numberStack.pop();
                double num2 = InfixToPostfix.numberStack.pop();
                InfixToPostfix.numberStack.push(Math.pow(num2, num1));
            }

        }
        return InfixToPostfix.numberStack.peek();
    }
}