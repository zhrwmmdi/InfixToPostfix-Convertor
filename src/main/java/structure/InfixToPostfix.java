package structure;


import java.util.*;
import java.util.List;

public class InfixToPostfix {
    private final Stack<String> operatorStack = new Stack<>();
    private final Stack<Double> numberStack = new Stack<>();
    private final Scanner input = new Scanner(System.in);
    private boolean draw;
    String infix = input.nextLine();
    List<GraphPoint> points = new ArrayList<>();


    public String getInfix(){
        return infix;
    }

    public void start() {

        final List<String> postfix = toPostFix(inputToList(infix));

        if (!draw){
            double value = calculator(postfix,0);
            System.out.println("Postfix expression: " + postfix + "\nCalculated value = " + value);
        }else {
            //when we have function
            System.out.println("Postfix expression: " + postfix);

            for (int i = 1; i <= 20; i++) {
                double y = calculator(postfix,i);
                points.add(new GraphPoint(i,y));
            }

            for (GraphPoint point : points) {
                System.out.println("X= " + point.getX() + "\t\tY= " + point.getY());
            }
        }

    }

    public List<String> inputToList(String infix){
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
                draw = true;
            }
            else {
                stringList.add(String.valueOf(infix.charAt(i)));
            }
        }
        return stringList;
    }

    public List<String> toPostFix(List<String> infix) {
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

    private double calculator(List<String> postfix, int x) {
        for (String s : postfix) {
            if (s.matches("[0-9]+\\.?[0-9]*$")) {
                numberStack.push(Double.valueOf(s));
            } else if (s.equalsIgnoreCase("x")) {
                numberStack.push((double) x);
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
            else if (Objects.equals(s, "^")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(Math.pow(num2, num1));
            }

        }
        return numberStack.peek();
    }


    private void draw(){

    }

    private int priority(String c) {
        if (Objects.equals(c, "+") || Objects.equals(c, "-")) return 1;
        else if (Objects.equals(c, "/") || Objects.equals(c, "*") || Objects.equals(c, "%") || Objects.equals(c, "^")) return 2;
        return 0;
    }
}
