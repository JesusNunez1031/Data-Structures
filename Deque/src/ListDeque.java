/**
 * Deque implementation using a doubly linked list. The deque is not bounded by a fixed length when using a linked list
 * data structure composed of nodes.
 **/
public class ListDeque<E> implements Deque<E> {

    private final DoublyLinkedList<E> list;

    /**
     * Initialize a ListDeque structure using a Doubly Linked List.
     */
    public ListDeque() {
        this.list = new DoublyLinkedList<>();
    }

    /**
     * Returns the number of items in the deque structure
     *
     * @return the number of items in the deque
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * Returns {@code true} if the deque contains no items, {@code false} otherwise
     *
     * @return {@code true} if the deque is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns the first item of the deque, {@code null} if the deque is empty
     *
     * @return the item at the front of the deque
     */
    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return list.first();
    }

    /**
     * Returns the last item in the deque, {@code null} if the deque is empty
     *
     * @return the item at the end of the deque, {@code null} if the deque is empty
     */
    @Override
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return list.last();
    }

    /**
     * Adds an item {@code e} to the front of the deque
     */
    @Override
    public void addFirst(E e) {
        list.addFirst(e);
    }

    /**
     * Adds an item {@code e} to the end of the deque
     */
    @Override
    public void addLast(E e) {
        list.addLast(e);
    }

    /**
     * Removes the first item in the deque and returns it, {@code null} if the deque is empty
     *
     * @return the removed item at the front of the deque, {@code null} if the deque is empty
     */
    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    /**
     * Removes the last item in the deque and returns it, {@code null} if the deque is empty
     *
     * @return the removed item at the end of the deque, {@code null} if the deque is empty
     */
    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return list.removeLast();
    }
}
