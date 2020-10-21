public interface Stack<E> {

    //Returns the size of the stack, returns the number of items in the stack
    int size();

    //Returns true if the stack is empty and false otherwise
    boolean isEmpty();

    //Adds an item to the top of the stack, e is the element to be inserted
    void push(E e);

    //Returns the value at the top of the stack but does not remove it (null if empty)
    E peek();

    //Removes and returns the top element in the stack (null if empty)
    E pop();

}
