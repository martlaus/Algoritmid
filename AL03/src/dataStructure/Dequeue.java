package dataStructure;

/**
 * Created by mart on 18.10.15.
 */
public class Dequeue {
    Stack head;
    Stack tail;


    public Dequeue() {
        head = new Stack();
        tail = new Stack();
    }

    public void pushFirst(Object object) {
        head.push(object);
    }

    public void pushLast(Object object) {
        tail.push(object);
    }

    public Object popFirst() {
        if (head.isEmpty()) {
            while (!tail.isEmpty()) {
                head.push(tail.pop());
            }
        }
        return head.pop();
    }

    public Object popLast() {
        if (tail.isEmpty()) {
            while (!head.isEmpty()) {
                tail.push(head.pop());
            }
        }
        return tail.pop();
    }

    public boolean isEmpty() {
        return head.isEmpty() && tail.isEmpty();
    }
}
