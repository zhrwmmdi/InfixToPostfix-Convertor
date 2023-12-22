package calculation;

import structure.InfixToPostfix;
import java.util.List;
import java.util.Objects;

public class Calculate {
    public static double[] xPoints;
    public static double[] yPoints;
    static final String numberRegex = "[0-9]+\\.?[0-9]*$";

    public static void fillPoints(){
        yPoints = new double[401];
        xPoints = new double[401];

        int xValue = -200;
        for (int i = 0; i < 401; i++) {
            xPoints[i] = xValue;
            yPoints[i] = evaluate(InfixToPostfix.postfixList, (int) xPoints[i]);
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