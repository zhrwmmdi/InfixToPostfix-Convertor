package manager;

import structure.InfixToPostfix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputManager {
    String infixString;
    static List<String> infixList;
    static boolean drawable;

    public static List<String> getInfixList() {
        return infixList;
    }
    public InputManager(){
        Scanner input = new Scanner(System.in);
        infixString = input.nextLine();
        infixList = inputToList(infixString);
        new InfixToPostfix().start();
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
                drawable = true;
            }
            else {
                stringList.add(String.valueOf(infix.charAt(i)));
            }
        }
        return stringList;
    }
    public static boolean isDrawable(){
        return drawable;
    }
}
