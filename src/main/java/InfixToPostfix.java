import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InfixToPostfix {
    private Stack<String> operatorStack = new Stack<>();
    private Stack<Double> numberStack = new Stack<>();
    List<String> postfix = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    boolean draw;


//    public void start() {
//        String infix = input.nextLine();
//        String postfix = toPostFix(infix);
//        int value = calculator(postfix);
//        System.out.println("Postfix expression: " + postfix + "\nCalculated value = " + value);
//
//    }

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

    public double calculator(List<String> postfix) {
        for (int i = 0; i < postfix.size(); i++) {


            if (postfix.get(i).matches("[0-9]+\\.?[0-9]*$")) {
                numberStack.push(Double.valueOf(postfix.get(i)));
            } else if (Objects.equals(postfix.get(i), "*")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 * num1);
            } else if (Objects.equals(postfix.get(i), "+")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 + num1);
            } else if (Objects.equals(postfix.get(i), "-")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 - num1);
            } else if (Objects.equals(postfix.get(i), "/")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 / num1);
            } else if (Objects.equals(postfix.get(i), "%")) {
                double num1 = numberStack.pop();
                double num2 = numberStack.pop();
                numberStack.push(num2 % num1);
            }

        }
        return numberStack.peek();
    }

   /*
    public int calculator2(String infix) {

        for (int i = 0; i < infix.length(); i++) {
            character = infix.charAt(i);

            if (Character.isDigit(character)) {
                intStack.push(Character.getNumericValue(character));
            } else if (character == '(') {
                charStack.push(character);
            } else if (character == ')') {
                while (charStack.peek() != '(') {
                    char op = charStack.pop();
                    int num1 = intStack.pop();
                    int num2 = intStack.pop();
                    switch (op) {
                        case '+':
                            intStack.push(num2 + num1);
                            break;
                        case '-':
                            intStack.push(num2 - num1);
                            break;
                        case '*':
                            intStack.push(num2 * num1);
                            break;
                        case '/':
                            intStack.push(num2 / num1);
                            break;
                        case '%':
                            intStack.push(num2 % num1);
                            break;
                    }
                }
                charStack.pop();
            } else {
                //TODO: check what if last condition in while is removed
                while (!charStack.isEmpty() && (priority(charStack.peek()) >= priority(character)) && charStack.peek() != '(') {
                    char op = charStack.pop();
                    int num1 = intStack.pop();
                    int num2 = intStack.pop();
                    switch (op) {
                        case '+':
                            intStack.push(num2 + num1);
                            break;
                        case '-':
                            intStack.push(num2 - num1);
                            break;
                        case '*':
                            intStack.push(num2 * num1);
                            break;
                        case '/':
                            intStack.push(num2 / num1);
                            break;
                        case '%':
                            intStack.push(num2 % num1);
                            break;
                    }
                }
                charStack.push(character);
            }
        }
        while (intStack.top != 0){
            int num1 = intStack.pop();
            int num2 = intStack.pop();
            char ch = charStack.pop();
            switch (ch) {
                case '+':
                    intStack.push(num2 + num1);
                    break;
                case '-':
                    intStack.push(num2 - num1);
                    break;
                case '*':
                    intStack.push(num2 * num1);
                    break;
                case '/':
                    intStack.push(num2 / num1);
                    break;
                case '%':
                    intStack.push(num2 % num1);
                    break;
            }
        }
        return intStack.peek();
    }

    */

    private int priority(String c) {
        if (Objects.equals(c, "+") || Objects.equals(c, "-")) return 1;
        else if (Objects.equals(c, "/") || Objects.equals(c, "*") || Objects.equals(c, "%")) return 2;
        return 0;
    }
}
