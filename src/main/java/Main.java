public class Main {
    public static void main(String[] args){
        InfixToPostfix infixToPostfix = new InfixToPostfix();
        //infixToPostfix.start();
        int y = infixToPostfix.calculator2("(8)*5-6");
        System.out.println(y);
    }
}
