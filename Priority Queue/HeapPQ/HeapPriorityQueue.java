import java.util.ArrayList;
import java.util.Comparator;

/**
 * Implementation of a priority queue using an array-based heap
 * Adding and removing from the PQ heap now take O(log n) time
 */
public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    //Primary collection of priority queue entries
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    //Constructor - creates an empty PQ based on the natural ordering of its keys
    public HeapPriorityQueue() {
        super();
    }

    //Constructor - creates an empty PQ using the given comparator to order keys
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    //utilities
    //Returns the parent of a given node or "Entry"
    protected int parent(int node) {
        return (node - 1) / 2;
    }

    //Returns the left node or "Entry" of a given node
    protected int left(int node) {
        return 2 * node + 1;
    }

    //Returns the right node or "Entry" of a given node
    protected int right(int node) {
        return 2 * node + 2;
    }

    //Check if the given node has a left node
    protected boolean hasLeft(int node) {
        return left(node) < heap.size();
    }

    //Check if the given node has a right node
    protected boolean hasRight(int node) {
        return right(node) < heap.size();
    }

    //Exchanges the entries at node i and j of the array list
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    //Moves the entry at node j higher, if necessary, to satisfy the heap property
    protected void upHeap(int j) {
        while (j > 0) {         //loop until the node reaches the root or break statement
            int p = parent(j);
            if (compare(heap.get(j), heap.get(p)) >= 0) {        //heap property verified
                break;
            }
            swap(j, p);     //swap the node with its parent
            j = p;          //set j to the parent so on next iteration, we get the parent of the parent
        }
    }

    //Moves the entry at node j lower, if necessary, to restore the heap property
    protected void downHeap(int j) {
        while (hasLeft(j)) {        //continue to bottom or break statement
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;    //the right node might be smaller

            if (hasRight(j)) {
                int rightIndex = right(j);

                if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0) {
                    smallChildIndex = rightIndex;       //right child is smaller
                }
            }

            if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0) {
                break;      //heap property has been restored
            }
            swap(j, smallChildIndex);
            j = smallChildIndex;        //move to the smaller node and repeat
        }
    }

    //public methods
    //Returns the number of items in the PQ
    @Override
    public int size() {
        return heap.size();
    }

    //Returns but does not remove the entry with the smallest key (if any)

    @Override
    public Entry<K, V> min() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);        //the root of the heap, or first index, will always be the smallest value
    }

    //Inserts a key-value pair and returns the entry created
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);      //Check if key is valid
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upHeap(heap.size() - 1);      //place the new entry in the right position of the heap
        return newest;
    }

    //Removes and returns the entry with the smallest key (if any)

    @Override
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) {
            return null;
        }
        Entry<K, V> answer = heap.get(0);
        swap(0, heap.size() - 1);   //put the smallest value at the end
        heap.remove(heap.size() - 1);   //and remove it from the heap
        downHeap(0);    //place the new root at the correct position that satisfies the heap property
        return answer;
    }
}
