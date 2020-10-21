public class LinkedStack<E> implements Stack<E> {
    /*
        implementing a stack using a linked list does not limit user to a fixed size. Since a linked list uses
        memory proportional to the data being added, we can theoretically use this stack until memory expires
     */

    private LinkedList<E> list = new LinkedList<>(); //We use the linked list implementation from the LinkedList module

    //New stack relies on an empty list
    public LinkedStack() {
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
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E peek() {
        return list.getHead();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }
}
