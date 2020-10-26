public class PQDriver {
    public static void main(String[] args) throws InterruptedException {
        UnsortedPriorityQueue<Integer, String> upq = new UnsortedPriorityQueue<>();

        upq.insert(100, "Jess");
        upq.insert(1, "Jane");
        upq.insert(12, "Bill");
        upq.insert(0, "John");
        upq.insert(32, "Doe");
        upq.insert(50, "Maria");
        upq.insert(-50, "Vanessa");

        int i = 1;
        System.out.println("People removed from the queue in order of highest priority to lowest: ");
        while (!upq.isEmpty()) {
            System.out.printf("%d removed -> Name: %s \n", i++, upq.removeMin().getValue());
            Thread.sleep(1000);
        }

        SortedPriorityQueue<Integer, String> spq = new SortedPriorityQueue<>();

        spq.insert(120, "Billy");
        spq.insert(300, "Joe");
        spq.insert(1, "Bone");
        spq.insert(-1, "Hare");
        System.out.println(spq.min().getValue());
    }
}
