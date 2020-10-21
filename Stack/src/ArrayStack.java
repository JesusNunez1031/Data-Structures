public class ArrayStack<E> implements Stack<E> {

    /*Stack implementation using a simple array*/

    public static final int CAPACITY = 1000;    //default array capacity
    private E[] data;                           //generic array used for storage
    private int t = -1;                         //index of the top element in the stack

    //Constructs stack with default capacity
    public ArrayStack() {
        this(CAPACITY);
    }

    //Constructs stack with given capacity
    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return this.t + 1;
    }

    @Override
    public boolean isEmpty() {
        return this.t == -1;
    }

    @Override
    public void push(E e) throws IllegalStateException {
        if (size() == data.length) {
            throw new IllegalStateException("Stack is full");
        }
        data[++t] = e;          //Increment t before storing new item since t = -1 initially
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return data[t];
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E ans = data[t];
        data[t] = null;
        t--;
        return ans;
    }
}
