import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InfixToPostfix {
    private final Stack<String> operatorStack = new Stack<>();
    private final Stack<Double> numberStack = new Stack<>();
    private final Scanner input = new Scanner(System.in);


    public void start() {
        String infix = input.nextLine();
        List<String> postfix = toPostFix(inputToList(infix));
        double value = calculator(postfix);
        System.out.println("Postfix expression: " + postfix + "\nCalculated value = " + value);
    }

    private List<String> inputToList(String infix){
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < infix.length(); i++) {
            String postfix = "";
            if (Character.isDigit(infix.charAt(i))) {
                while (((i < infix.length() && Character.isDigit(infix.charAt(i)))) || (i < infix.length() && infix.charAt(i) == '.')) {
                    postfix = postfix + infix.charAt(i);
                    i++;
                }
                i--;
                stringList.add(postfix);
            } else if (Character.isLetter(infix.charAt(i))) {
                while (i < infix.length() && Character.isLetter(infix.charAt(i))) {
                    postfix = postfix + infix.charAt(i);
                    i++;
                }
                i--;
                stringList.add(postfix);
                boolean draw = true;
            }
            else {
                stringList.add(String.valueOf(infix.charAt(i)));
            }
        }
        return stringList;
    }

    private List<String> toPostFix(List<String> infix) {
        List<String> postfix = new ArrayList<>();
        for (String s : infix) {
            //if we have number or letter, add it to result
            if (s.matches("[0-9]+\\.?[0-9]*$")) {
                postfix.add(s);

            } else if (s.matches("[a-z]*|[A-Z]*")) {
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

    private double calculator(List<String> postfix) {
        for (String s : postfix) {
            if (s.matches("[0-9]+\\.?[0-9]*$")) {
                numberStack.push(Double.valueOf(s));
            } else if (Objects.equals(s, "*")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 * num1);
            } else if (Objects.equals(s, "+")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 + num1);
            } else if (Objects.equals(s, "-")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 - num1);
            } else if (Objects.equals(s, "/")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 / num1);
            } else if (Objects.equals(s, "%")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 % num1);
            }

        }
        return numberStack.peek();
    }
    private int priority(String c) {
        if (Objects.equals(c, "+") || Objects.equals(c, "-")) return 1;
        else if (Objects.equals(c, "/") || Objects.equals(c, "*") || Objects.equals(c, "%")) return 2;
        return 0;
    }
}
