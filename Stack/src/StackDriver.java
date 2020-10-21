public class StackDriver {

    public static void main(String[] args) {
        //stack using a linked list
        LinkedStack<Integer> stack = new LinkedStack<>();

        System.out.println(stack.isEmpty());

        stack.push(1);
        stack.push(2);

        System.out.println(stack.peek());
        System.out.println(stack.pop());

        System.out.println(stack.size());
    }
}
