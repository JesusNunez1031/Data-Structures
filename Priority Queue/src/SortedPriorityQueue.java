import java.util.Comparator;

/*
 * PQ implementation using an sorted List, trade offs from unsorted are now all insertions are in O(n) time since
 * we compare new inserted key with other keys already in the queue and place it in its appropriate location based on the
 * comparator value of order. As opposed to the unsorted PQ, we can now get and remove the min value in O(1) time since it is
 * always guaranteed to be the first element
 *              Method      |       Runtime
 *              size        |        O(1)
 *            isEmpty       |        O(1)
 *             insert       |        O(N)
 *              min         |        O(1)
 *            removeMin     |        O(1)
 */
public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    //Creates an empty PQ based on the natural ordering of its keys
    public SortedPriorityQueue() {
        super();
    }

    //Creates an empty PQ using the given comparator to oder keys
    public SortedPriorityQueue(Comparator<K> comp) {
        super(comp);
    }


    //Inserts a key-value pair and returns the entry created
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);      //check if added key is valid
        Entry<K, V> newest = new PQEntry<>(key, value); //create a new Entry
        Position<Entry<K, V>> walk = list.last();   //Walk is our iterator for the list, starting from the last position

        //Walk backwards, looking for smaller key value
        while (walk != null && compare(newest, walk.getElement()) < 0) {
            walk = list.before(walk);   //move back one
        }

        //if walk made it to the front of the list we either make new the first entry or 2nd entry
        if (walk == null) {
            list.addFirst(newest);      //new key is the smallest
        } else {
            list.addAfter(walk, newest);    //newest goes after walk, 2nd smallest value
        }
        return newest;
    }

    //Returns but does not remove the entry with the minimal key
    public Entry<K, V> min() {
        if (list.isEmpty()) {
            return null;
        }
        return list.first().getElement();
    }

    //Removes and returns the entry with the smallest key value
    public Entry<K, V> removeMin() {
        if (list.isEmpty()) {
            return null;
        }
        return list.remove(list.first());
    }

    //Returns the number of items in the PQ
    public int size() {
        return list.size();
    }
}
