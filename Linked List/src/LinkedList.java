public class LinkedList<E> {

    private ListNode<E> head;
    private ListNode<E> tail;
    public int size;

    //Initialize a linked list constructor
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    //Function to check if list is empty
    public boolean isEmpty() {
        return this.size == 0;
    }

    //Function to get size of list
    public int getSize() {
        return this.size;
    }

    //Function to return the head
    public E getHead() {
        if (isEmpty()) {
            return null;
        }
        return this.head.getVal();
    }

    //Function to return the last Node(tail)
    public E getTail() {
        if (isEmpty()) {
            return null;
        }
        return this.tail.getVal();
    }

    //Adds an element to the front of the list
    public void addFirst(E val) {
        head = new ListNode<>(val, head);
        //if the list is empty, we also make the head the tail
        if (size == 0) {
            this.tail = head;
        }
        size++;     //increase the size of list
    }

    //Adds an element to the end of the list
    public void addLast(E val) {
        ListNode<E> node = new ListNode<>(val, null);
        //if the list is empty, the tail is also the head
        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;       //Set the next of the previous node (tail) to the new node
        }
        this.tail = node;       //New tail is the new node added
        size++;                 //increment the size of the list
    }

    //Function to insert an element at specified position
    public void addAtIndex(E val, int pos) {
        ListNode<E> node = new ListNode<>(val);

        if (isEmpty()) {
            addFirst(val);
        } else if (pos == size) {
            addLast(val);
        } else if (pos > getSize()) {
            System.out.println("invalid index");
        } else {
            ListNode<E> iter = head;

            while (pos > 1) {
                iter = iter.next;
                pos--;
            }
            node.next = iter.next;
            iter.next = node;
            size++;
        }
    }

    //Function to delete an element at specified position
    public void deleteAtPos(int pos) {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else if (pos > getSize()) {
            System.out.printf("Invalid index size %d for linked list of size %d \n", pos, getSize());
        } else if (pos == getSize()) {
            removeLast();
        } else if (pos == 1) {
            removeFirst();
        } else {
            ListNode<E> iter = head;

            while (pos > 1) {
                iter = iter.next;
                pos--;
            }
            iter.val = iter.next.val;
            iter.next = iter.next.next;
        }
        size--;
    }

    //Function to return value of specific index
    public E find(int index) {
        if (isEmpty()) {
            return null;
        } else if (index == 1) {
            getHead();
        } else if (index == size) {
            getTail();
        }
        ListNode<E> iter = head;

        while (index > 1) {
            iter = iter.next;
            index--;
        }
        return iter.val;
    }

    //Removes and returns the first node
    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
        }
        E node = head.getVal();
        head = head.next;
        size--;

        if (size == 0) {
            tail = null;
        }
    }

    //Delete the last node in the list
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
        }
        ListNode<E> iter = head;

        //Iterate through the list until we get to the second to last value
        while (iter.next.next != null) {
            iter = iter.next;
        }
        //save 2nd to last value as new tail
        tail = iter;
        //point it to null to lose the reference to the node we want to delete
        iter.next = null;
        size--;
    }

    //Function to display elements in list
    public void display() {
        ListNode<E> iter = head;
        while (iter != null) {
            if (iter.next == null) {
                System.out.printf("%s ", iter.val);
            } else {
                System.out.printf("%s -> ", iter.val);
            }
            iter = iter.next;
        }
    }
}
