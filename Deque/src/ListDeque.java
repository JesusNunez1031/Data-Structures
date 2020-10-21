/**
 * Deque implementation using a linked list
 **/
public class ListDeque<E> implements Deque<E> {

    private DoublyLinkedList<E> list = new DoublyLinkedList<>();

    public ListDeque() {
    }


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return list.first();
    }

    @Override
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return list.last();
    }

    @Override
    public void addFirst(E e) {
        list.addFirst(e);
    }

    @Override
    public void addLast(E e) {
        list.addLast(e);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return list.removeLast();
    }
}
