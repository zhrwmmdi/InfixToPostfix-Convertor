import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        InfixToPostfix infixToPostfix = new InfixToPostfix();


        String n = "(1+9)*(89-5)-8";
        List<String> g = infixToPostfix.inputToList(n);
//        g.add("3");
//        g.add("+");
//        g.add("6");
//        g.add("-");
//        g.add("9");
        System.out.println(g);

        System.out.println( infixToPostfix.toPostFix(g));





    }
}
