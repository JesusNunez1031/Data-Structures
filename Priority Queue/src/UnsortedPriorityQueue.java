import java.util.Comparator;

/*
 * PQ implementation using an unsorted List
 *              Method      |       Runtime
 *              size        |        O(1)
 *            isEmpty       |        O(1)
 *             insert       |        O(1)
 *              min         |        O(N)
 *            removeMin     |        O(n)
 */
public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    //Primary collection of PQ entries
    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    //Creates an empty PQ based on the natural ordering of its keys
    public UnsortedPriorityQueue() {
        super();
    }

    //Creates an empty PQ using the given comparator to oder keys
    public UnsortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    //Returns the Position of an entry having minimal key
    private Position<Entry<K, V>> findMin() {
        //only called when the queue is not empty
        Position<Entry<K, V>> small = list.first();

        for (Position<Entry<K, V>> walk : list.positions()) {
            if (compare(walk.getElement(), small.getElement()) < 0) {
                small = walk;  //compare first key with all others and if a smaller is found return it
            }
        }
        return small;
    }

    //Inserts a key-value pair and returns the entry created
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);      //check key (can throw exception if key is not valid)
        Entry<K, V> newest = new PQEntry<>(key, value);
        list.addLast(newest);
        return newest;
    }

    //Returns but does not remove the entry with the smallest key value
    public Entry<K, V> min() {
        if (list.isEmpty()) {
            return null;
        }
        return findMin().getElement();
    }

    //Removes and returns the entry with the smallest key value
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(findMin());
    }

    //Returns the number of items in the priority queue
    public int size() {
        return list.size();
    }
}
