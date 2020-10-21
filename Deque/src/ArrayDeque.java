/**
 * Implementation of Deque using an array of fixed-length (the array is treated as a circular array)
 * limitation include the fixed size of the array, however, all operations are O(1) time complexity
 **/
public class ArrayDeque<E> implements Deque<E> {
    private static final int CAPACITY = 1000;
    private E[] data;               //generic array used for storage
    private int f = 0;              //index of the front element
    private int L = f + 1;          //index of the last element
    private int size = 0;           //current number of elements

    //Constructors
    public ArrayDeque() {    //constructs a queue with default capacity
        this(CAPACITY);
    }

    public ArrayDeque(int capacity) {       //constructs a queue with given capacity
        data = (E[]) new Object[capacity];  //safe type cast
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void addFirst(E e) throws IllegalStateException {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }

        /*
          since this is a circular array, in order to calculate the index where to insert a new value, we use modulo
          Ex: if length is 10 and we start at f = 0, (0 + 0) % 10 = 0[first index], we add a new item, f is still 0
          since f is the front so we insert at, (0 + 1) % 10 = 1[second index]...and so on, f changes when we dequeue
         */
        /*in order to avoid possible negative indexes, we use the formula (f - 1 + N) where N is ihe length of the array,
          this allows us to properly wrap around the list
         */
        f = (f - 1 + data.length) % data.length;
        data[f] = e;
        size++;
    }

    @Override
    public void addLast(E e) throws IllegalStateException {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }

        L = (L + size) % data.length;
        data[L] = e;
        size++;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return data[f];
    }

    @Override
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return data[L];
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E ans = data[f];
        data[f] = null;
        f = (f + 1) % data.length;  //Move f one index forward after deletion to point to the new item at the front of the queue
        size--;
        return ans;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        E ans = data[L];
        data[L] = null;
        L = (L + 1 - size) % data.length;   //Move L one index back to the old last value
        size--;
        return ans;
    }
}
