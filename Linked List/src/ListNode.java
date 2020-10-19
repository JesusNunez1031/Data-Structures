public class ListNode <E> {
    public ListNode <E> next;
    public E val;

    public ListNode(E val) {
        this.val = val;
    }

    public ListNode(E val, ListNode <E> next){
        this.next = null;
        this.val = val;
    }

    public E getVal(){
        return this.val;
    }

}
