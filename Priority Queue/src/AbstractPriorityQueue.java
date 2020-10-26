import java.util.Comparator;

/**
 * Abstract base class to assist in the implementation of the PQ interface
 **/
public abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

    //--------------------------PQEntry Class--------------------------
    protected static class PQEntry<K, V> implements Entry<K, V> {
        private K k;    //key
        private V v;    //value

        public PQEntry(K key, V value) {
            this.k = key;
            this.v = value;
        }

        //methods of the Entry interface
        public K getKey() {
            return this.k;
        }

        public V getValue() {
            return v;
        }

        //Additional setter methods
        protected void setKey(K key) {
            this.k = key;
        }

        protected void setValue(V value) {
            this.v = value;
        }
    } //---------------------End of PQEntry Class--------------------------

    //The comparator defining the ordering of keys in the priority queue
    private Comparator<K> comp;

    //Creates an empty PQ using the given comparator to order keys
    protected AbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }

    //Creates an empty PQ based on the natural ordering of its keys
    protected AbstractPriorityQueue() {
        this(new DefaultComparator<K>());
    }

    //Method for comparing two entries according to key
    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    //Determines whether a key is valid
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comp.compare(key, key) == 0);   //see if the key can be compared to itself
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key type");
        }
    }

    //Check if the PQ is empty
    public boolean isEmpty() {
        return size() == 0;
    }
}
