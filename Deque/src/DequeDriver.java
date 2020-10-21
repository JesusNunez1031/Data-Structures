import java.util.Arrays;

public class DequeDriver {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(10);

        System.out.println(deque.isEmpty());

        deque.addFirst(1);
        System.out.println("First value: " + deque.first());

        deque.addFirst(3);
        System.out.println("New First value: " + deque.first());
        System.out.println(deque.size());

        deque.removeFirst();
        System.out.println("First value: " + deque.first() + " New size: " + deque.size());

        deque.addLast(40);
        System.out.println("Last value: " + deque.last() + " size: " + deque.size());

        deque.addLast(41);
        System.out.println("New Last value: " + deque.last() + " size: " + deque.size());

        deque.removeLast();
        System.out.println("Last value: " + deque.last() + " size: " + deque.size());



    }
}
