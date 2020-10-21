/**
 * Double ended queue or deque (D.Q.) is a queue were insertion and deletions are
 * possible at bot the front and back of queue
 **/
public interface Deque<E> {
    //Returns the number of items in the deque
    int size();

    //Checks if the deque is empty
    boolean isEmpty();

    //Returns the first element is the deque but does not remove it (null if empty)
    E first();

    //Returns the last element in the deque but does not remove it (null if empty)
    E last();

    //Inserts an element at the front of the deque
    void addFirst(E e);

    //Inserts an element at the end of the deque
    void addLast(E e);

    //Removes and returns the first element of the deque (null if empty)
    E removeFirst();

    //Removes and returns the last element of the deque (null if empty)
    E removeLast();
}
