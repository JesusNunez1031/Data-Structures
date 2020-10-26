import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Implementation of a positional List stored as a Doubly Linked List
 *              Methods            |       Runtimes
 *              size()             |         O(1)
 *              isEmpty()          |         O(1)
 *          fist(), last()         |         O(1)
 *      before(p), after(p)        |         O(1)
 *  addFirst(e), addLast(e)        |         O(1)
 * addBefore(p, e), addAfter(p, e) |         O(1)
 *              set(p, e)          |         O(1)
 *              remove(p)          |         O(1)
 */
public class LinkedPositionalList<E> implements PositionalList<E> {
    //Instance variables
    private Node<E> header;     //header sentinel
    private Node<E> trailer;    //trailer sentinel
    private int size = 0;       //number of elements in the list

    //Construct new empty list
    public LinkedPositionalList() {
        this.header = new Node<>(null, null, null);     //create header
        this.trailer = new Node<>(null, header, null);      //trailer preceded by header
        this.header.setNext(trailer);                                //header is followed by trailer
    }

    //Validates the position and returns it as a node
    private Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) {
            throw new IllegalArgumentException("Invalid p");
        }
        Node<E> node = (Node<E>) p;

        //Check if node has been removed, invalidated
        if (node.getNext() == null) {
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return node;
    }

    //Returns the given node as a position (or null if it is a sentinel)
    private Position<E> position(Node<E> node) {
        //if the node is a sentinel "dummy node" return null
        if (node == header || node == trailer) {
            return null;
        }
        return node;
    }

    /**
     * Accessor Methods
     **/

    //Returns the number of elements in the list
    public int size() {
        return this.size;
    }

    //Check if the list is empty
    public boolean isEmpty() {
        return this.size == 0;
    }

    //Returns the first position in the linked list (or null if empty)
    public Position<E> first() {
        return position(header.getNext());
    }

    //Returns the last Position in the linked list (or null if empty)
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    //Returns the position immediately before Position p (or null if empty)
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getPrev());
    }

    //Returns the position immediately after Position p (or null if empty)
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return position(node.getNext());
    }

    //Adds an element e to the linked list between the given nodes
    private Position<E> addBetween(E e, Node<E> predecessor, Node<E> successor) {
        //given the prev and next node, we link those to the new node added
        Node<E> newest = new Node<>(e, predecessor, successor);
        //Link prev and next nodes to the new node added
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
        return newest;
    }

    /**
     * Update Methods
     **/

    //Inserts element e at the front of the linked list and returns its new Position
    public Position<E> addFirst(E e) {
        //add node in between the sentinel node and its next node
        return addBetween(e, header, header.getNext());
    }

    //Inserts element at the back of the linked list and returns its new Position
    public Position<E> addLast(E e) {
        //Add the new node just before the trailer node
        return addBetween(e, trailer.getPrev(), trailer);
    }

    //Inserts an element e immediately before Position p, and returns its new Position
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node.getPrev(), node.getNext());
    }

    //Inserts an element e immediately after Position p, and returns its new Position
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        return addBetween(e, node, node.getNext());
    }

    //Replaces the element stored at Position p and returns the replaced element
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E answer = node.getElement();
        node.setElement(e);
        return answer;
    }

    //Removes the element stored at Position p and returns it (invalidating p)
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        //Get the next and prev of the node to be deleted
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();

        //Set the next of the prev to the nodes next and set the prev of next node to the to be deleted nodes prev
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;

        E answer = node.getElement();
        node.setElement(null);
        node.setPrev(null);
        return answer;
    }

    /**
     * Nested Class for Position Iterator
     */
    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();   //Position of the next element to report
        private Position<E> recent = null;      //Position of last reported element

        //Test whether the iterator has a next object
        public boolean hasNext() {
            return cursor != null;
        }

        //Returns the next position in the iterator
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) {
                throw new NoSuchElementException("nothing left");
            }
            recent = cursor;    //element at this position might later be removed
            cursor = after(cursor); //move the cursor forward by one element
            return recent;
        }

        //Removes the element returned by most recent call to next
        public void remove() throws IllegalStateException {
            if (recent == null) {
                throw new IllegalStateException("nothing to remove");
            }
            LinkedPositionalList.this.remove(recent);       //remove from outer list
            recent = null;  //don't allow remove again until next is called
        }
    }   // End of nester Iterator class

    //Returns an iterable representation of the lists positions
    public Iterable<Position<E>> positions() {
        return new PositionIterable();      //create a new instance of the inner class
    }

    //Iterable Class for Position class
    private class PositionIterable implements Iterable<Position<E>> {
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    } // End of Iterable class

    //Nested ElementIterator class
    //This class adapts the iteration produced by positions() to return elements
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> postIterator = new PositionIterator();

        @Override
        public boolean hasNext() {
            return postIterator.hasNext();
        }

        @Override
        public E next() {
            return postIterator.next().getElement();    //return the element
        }

        @Override
        public void remove() {
            postIterator.remove();
        }

        public Iterator<E> iterator() {
            return new ElementIterator();
        }
    } //End of ElementIterator class
}
