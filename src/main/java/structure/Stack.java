package structure;

import java.util.ArrayList;
public class Stack <T>{
    private int top = -1;
    private final ArrayList<T> elements = new ArrayList<>();

    public boolean isEmpty(){
        return top==-1;
    }
    public void push(T ch){
         elements.add(ch);
         top++;
    }
    public T pop(){
        if (isEmpty()){
            System.out.println("structure.Stack is empty, no item found!");
            return null;
        }

        T data = elements.get(top);
        elements.remove(top);
        top--;
        return data;

    }
    public T peek(){
        return elements.get(top);
    }

}
