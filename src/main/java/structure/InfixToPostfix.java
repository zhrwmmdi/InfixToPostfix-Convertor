package structure;

import javax.swing.*;
import java.util.*;
import java.util.List;
import graphic.CurveDrawing;
import calculation.Calculate;
import manager.InputManager;

public class InfixToPostfix {
    private final static Stack<String> operatorStack = new Stack<>();
    public final static Stack<Double> numberStack = new Stack<>();
    final String numberRegex = "[0-9]+\\.?[0-9]*$";
    final String letterRegex = "[a-z]*|[A-Z]*";
    public static List<String> postfixList;

    public void start() {
        postfixList= toPostFix(InputManager.getInfixList());

        if (!InputManager.isDrawable()){
            double value = Calculate.evaluate(postfixList,0);
            System.out.println("Postfix expression: " + postfixList + "\nCalculated value = " + value);
        }else {
            System.out.println("Postfix expression: " + postfixList);
            Calculate.fillPoints();
            SwingUtilities.invokeLater(() -> {
                CurveDrawing doubleCurveDrawing = new CurveDrawing();
                doubleCurveDrawing.setVisible(true);
            });
        }
    }

    public List<String> toPostFix(List<String> infix) {
        List<String> postfix = new ArrayList<>();
        for (String s : infix) {
            //if we have number or letter, add it to result
            if (s.matches(numberRegex)) {
                postfix.add(s);
            } else if (s.matches(letterRegex)) {
                postfix.add(s);
            }
            //if we have ( add it to stack
            else if (s.equals("(")) {
                operatorStack.push(s);
            }
            //if we have ")" pop items from stack to result till reach the "(" and ignore both "(" & ")"
            else if (s.equals(")")) {
                while (!Objects.equals(operatorStack.peek(), "(")) {
                    postfix.add(operatorStack.pop());
                }
                operatorStack.pop();
            }
            //if we have operators, pop them to result according to their priority
            else {
                while (!operatorStack.isEmpty() && (priority(operatorStack.peek()) >= priority(s)) && !Objects.equals(operatorStack.peek(), "(")) {
                    postfix.add(operatorStack.pop());
                }
                operatorStack.push(s);
            }
        }
        //when all chars in infix scanned, any item left in stack is popped into result
        while (!operatorStack.isEmpty()) {
            postfix.add(operatorStack.pop());
        }
        return postfix;
    }
    private int priority(String c) {
        if (Objects.equals(c, "+") || Objects.equals(c, "-")) return 1;
        else if (Objects.equals(c, "/") || Objects.equals(c, "*") || Objects.equals(c, "%") || Objects.equals(c, "^")) return 2;
        return 0;
    }
}
