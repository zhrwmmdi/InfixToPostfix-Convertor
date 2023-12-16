import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InfixToPostfix {
    private Stack<Character> charStack = new Stack<>();
    private Stack<Integer> intStack = new Stack<>();

    private Scanner input = new Scanner(System.in);
    char character;
    boolean draw;


    public void start() {
        String infix = input.nextLine();
        String postfix = toPostFix(infix);
        int value = calculator(postfix);
        System.out.println("Postfix expression: " + postfix + "\nCalculated value = " + value);

    }

    public List<String> inputToList(String infix){
        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < infix.length(); i++) {
            String postfix = "";
            if (Character.isDigit(infix.charAt(i))) {
                while (Character.isDigit(infix.charAt(i))) {
                    postfix = postfix + infix.charAt(i);
                    i++;
                }
                i--;
                stringList.add(postfix);
            } else if (Character.isLetter(infix.charAt(i))) {
                while (Character.isLetter(infix.charAt(i))) {
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

    private String toPostFix(String infix) {
        for (int i = 0; i < infix.length(); i++) {


            //if we have number, add it to result
            if (Character.isDigit(infix.charAt(i))) {
                while (Character.isDigit(infix.charAt(i))) {
                    postfix = postfix + infix.charAt(i);
                    i++;
                }
                i--;

            } else if (Character.isLetter(character)) {
                while (Character.isLetter(infix.charAt(i))) {
                    postfix = postfix + infix.charAt(i);
                    i++;
                }
                i--;
                draw = true;
            }

            //if we have ( add it to stack
            else if (infix.charAt(i) == '(') {
                charStack.push(character);
            }


            //if we have ) pop items from stack to result till reach the ( and ignore both ( & )
            else if (infix.charAt(i) == ')') {
                while (charStack.peek() != '(') {
                    postfix = postfix + charStack.pop();
                }
                charStack.pop();
            }

            //if we have operators, pop them to result according to their priority
            else {
                //TODO: check what if last condition in while is removed
                while (!charStack.isEmpty() && (priority(charStack.peek()) >= priority(infix.charAt(i))) && charStack.peek() != '(') {
                    postfix = postfix + charStack.pop();
                }
                charStack.push(infix.charAt(i));
            }

        }

        //when all chars in infix scanned, any item left in stack is pop into result
        while (!charStack.isEmpty()) {
            postfix = postfix + charStack.pop();
        }
        return postfix;
    }

    private int calculator(String postfix) {
        for (int i = 0; i < postfix.length(); i++) {
            character = postfix.charAt(i);

            if (Character.isDigit(character)) {
                intStack.push(Character.getNumericValue(character));
            } else if (character == '*') {
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2 * num1);
            } else if (character == '+') {
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2 + num1);
            } else if (character == '-') {
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2 - num1);
            } else if (character == '/') {
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2 / num1);
            } else if (character == '%') {
                int num1 = intStack.pop();
                int num2 = intStack.pop();
                intStack.push(num2 % num1);
            }

        }
        return intStack.peek();
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

    private int priority(char c) {
        if (c == '+' || c == '-') return 1;
        else if (c == '/' || c == '*' || c == '%') return 2;
        return 0;
    }
}
