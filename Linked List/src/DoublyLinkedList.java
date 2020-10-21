public class DoublyLinkedList<E> {

    private ListNode<E> header;   //header sentinel of the list (not the actual head of the list)
    private ListNode<E> trailer;   //tail sentinel of the list (not the actual tail of the list)
    private int size = 0;       //number of elements in the list

    //Construct a new empty list
    public DoublyLinkedList() {
        header = new ListNode<>(null, null, null);    //Head with null value, null previous, and null next
        trailer = new ListNode<>(null, header, null);            //tail with null value, previous is set to the head, and its next is null
        header.next = trailer;       //point the next of head to the tail
    }

    //Return the number of elements in the linked list
    public int size() {
        return this.size;
    }

    //Test whether the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns but does not remove the first element of the list
    public E first() {
        if (isEmpty()) {
            return null;
        }
        return this.header.next.getVal();
    }

    //Returns but does not remove the last element in the list
    public E last() {
        if (isEmpty()) {
            return null;
        }
        return trailer.prev.getVal();
    }

    /**
     * Public Update Methods
     **/

    //Adds an element e to the front of the list
    public void addFirst(E e) {
        addBetween(e, header, header.next);         //place e just after the header sentinel value
    }

    //Adds an element to the end of the list
    public void addLast(E e) {
        addBetween(e, trailer.prev, trailer);      //place e just before the trailer sentinel
    }

    //Removes and returns the first element of the list
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        return remove(header.next);       //remove the value after the header
    }

    //Removes and returns the last element of the list
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(trailer.prev);    //remove the value previous to the trailer
    }

    /**
     * Private update methods
     **/

    //Adds and element e to the linked list in between the given nodes
    private void addBetween(E e, ListNode<E> predecessor, ListNode<E> successor) {
        //create and link a new node
        ListNode<E> newNode = new ListNode<>(e, predecessor, successor);
        predecessor.next = newNode;
        successor.prev = newNode;
        size++;
    }

    //Remove the given node from the list and return its value
    private E remove(ListNode<E> node) {
        ListNode<E> predecessor = node.prev;
        ListNode<E> successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;
        return node.getVal();
    }
}
