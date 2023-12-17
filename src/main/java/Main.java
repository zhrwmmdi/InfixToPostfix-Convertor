import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        InfixToPostfix infixToPostfix = new InfixToPostfix();


        String n1 = "3+(4*8)";
        //String n1 = "3+4*8";
        List<String> g = infixToPostfix.inputToList(n1);
        List<String> p = infixToPostfix.toPostFix(g);
        System.out.println(infixToPostfix.calculator(p));

    }
}
