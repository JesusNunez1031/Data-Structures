public class HeapPQDriver {
    public static void main(String[] args) {
        HeapPriorityQueue<Integer, String> pqueue = new HeapPriorityQueue<>();
        pqueue.insert(133, "Jess");
        pqueue.insert(112, "Lane");
        pqueue.insert(32, "Bob");

        System.out.println(pqueue.hasLeft(0));
        System.out.printf("Parent of Lane: %s", pqueue.min().getValue());
    }
}
