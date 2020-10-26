import java.util.Comparator;

public interface PriorityQueue<K, V> {

    //Returns the number of elements in the queue
    int size();

    //Checks if the queue is empty
    boolean isEmpty();

    //Inserts a value V with key K
    Entry<K, V> insert(K key, V value) throws IllegalArgumentException;

    //Returns but does not remove the element with the smallest key value in the queue
    Entry<K, V> min();

    //Returns and removes the element with the smallest key value in the queue
    Entry<K, V> removeMin();

    //Any key type is allowed, method implements a comparator based on the natural ordering of its elements
    public class DefaultComparator<E> implements Comparator<E> {
        public int compare(E a, E b) throws ClassCastException {
            return ((Comparable<E>) a).compareTo(b);
        }
    }
}
