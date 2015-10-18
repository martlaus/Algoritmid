import dataStructure.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting...");

        Stack stack = new Stack();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        while (!stack.isEmpty()) {
            Object value = stack.pop();
            System.out.print(value);
            System.out.print(" ");
        }
        System.out.println("");    }
}
