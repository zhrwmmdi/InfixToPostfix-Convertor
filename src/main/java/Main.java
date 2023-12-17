import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        InfixToPostfix infixToPostfix = new InfixToPostfix();


        //String n1 = "A+B*(C-D)";
        String n1 = "A*B/C+D-E*F";
        List<String> g = infixToPostfix.inputToList(n1);
        List<String> p = infixToPostfix.toPostFix(g);
        System.out.println(p);







    }
}
