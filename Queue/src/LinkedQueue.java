/**
 * Queue FIFO implemented using singly linked List from Linked List module
 * The capacity limitation here is avoided and is now bounded by the amount of memory available
 * All methods here also run on a time complexity of O(1)
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {
    private LinkedList<E> list = new LinkedList<>();

    //New Queue relies on an empty linked list
    public LinkedQueue() {
    }

    @Override
    public int size() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.getHead();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
}
