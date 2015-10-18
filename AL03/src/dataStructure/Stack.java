package dataStructure;

/**
 * Created by mart on 18.10.15.
 */
public class Stack {
    private Object[] stackArray;
    private int top;

    public Stack() {
        stackArray = new Object[1000];
        top = -1;
    }

    public void push(Object object) {
        if(top > stackArray.length - 2) {
            //System.out.println("resizing " + stackArray.length);
            stackArray = resize(stackArray);
            //System.out.println("resized " + stackArray.length);

        }
        stackArray[++top] = object;
    }
    public Object pop() {
        return stackArray[top--];
    }
    public boolean isEmpty() {
        return (top == -1);
    }

    private Object[] resize(Object[] original){
        Object[] tmp = new Object[original.length*2];
        System.arraycopy(original,0,tmp,0,original.length);
        return tmp;
    }
}
