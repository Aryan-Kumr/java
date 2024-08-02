import java.util.ArrayList;

class Stack<T> {
    private ArrayList<T> stack = new ArrayList<>();

    public void push(T element) {
        stack.add(element);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public void clear() {
        stack.clear();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void display() {
        System.out.println(stack);
    }
}

public class Main {
    public static void main(String[] args) {
        Stack<Object> mixedStack = new Stack<>();
        mixedStack.push("Apple");
        mixedStack.push(42);
        mixedStack.push("Cherry");
        mixedStack.push(7);

        mixedStack.display(); // Output: [Apple, 42, Cherry, 7]
        System.out.println("Popped: " + mixedStack.pop()); // Output: Popped: 7
        mixedStack.display(); // Output: [Apple, 42, Cherry]
        mixedStack.clear();
        System.out.println("Is empty: " + mixedStack.isEmpty()); // Output: Is empty: true
    }
}
