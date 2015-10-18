package dataStructure;

import java.util.NoSuchElementException;

/**
 * Created by mart on 18.10.15.
 */
public class Queue {
    Stack stack1;
    Stack stack2;

    public Queue(){
        stack1 = new Stack();
        stack2 = new Stack();
    }

    public void enqueue(Object object) {
        stack1.push(object);
    }

    public Object dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        if (stack2.isEmpty()) moveStack1ToStack2();
        return stack2.pop();    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    private void moveStack1ToStack2(){
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());
    }
}
