public class ListNode<E> {
    public ListNode<E> next;   //reference to the subsequent node in the list
    public E val;               //reference to the value in the current node
    public ListNode<E> prev;    //reference to the previous node in the list

    public ListNode(E val) {
        this.val = val;
    }

    public ListNode(E val, ListNode<E> next) {
        this.next = null;
        this.val = val;
    }

    public ListNode(E val, ListNode<E> previous, ListNode<E> next) {
        this.next = next;
        this.val = val;
        this.prev = previous;
    }

    public E getVal() {
        return this.val;
    }
}
