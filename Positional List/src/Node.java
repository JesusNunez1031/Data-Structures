public class Node<E> implements Position<E> {
    private E element;      //reference to the element stored at this node
    private Node<E> prev;   //ref. to the previous node in the list
    private Node<E> next;   //ref. to the subsequent node in the list

    public Node(E e, Node<E> prev, Node<E> next) {
        this.element = e;
        this.prev = prev;
        this.next = next;
    }

    @Override
    public E getElement() throws IllegalStateException {
        if (next == null) {
            throw new IllegalStateException("Position no longer valid");
        }
        return this.element;
    }

    public Node<E> getPrev() {
        return this.prev;
    }

    public Node<E> getNext() {
        return this.next;
    }

    public void setElement(E e) {
        this.element = e;
    }

    public void setPrev(Node<E> p) {
        this.prev = p;
    }

    public void setNext(Node<E> n) {
        this.next = n;
    }
}
