import java.util.ArrayList;
public class Stack <T>{
    int top = -1;
    private final ArrayList<T> elements = new ArrayList<>();

    public boolean isEmpty(){
        return top==-1;
    }
    void push(T ch){
         elements.add(ch);
         top++;
    }
    T pop(){
        if (isEmpty()){
            System.out.println("Stack is empty, no item found!");
            return null;
        }

        T data = elements.get(top);
        elements.remove(top);
        top--;
        return data;

    }
    T peek(){
        return elements.get(top);
    }

}
