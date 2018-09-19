import java.util.Random;

public class LinkedList {
    //Class Variables
    protected Node head;
    protected Node end;
    public int size;


    public LinkedList() {
        head = null;
        end = null;
        size = 0;
    }

    //Function to check if list is empty
    public boolean isEmpty() {
        return head == null;
    }

    //Function to get size of list
    public int getSize() {
        return size;
    }

    //Function to return the head
    public Node getHead() {
        return head;
    }

    //Function to return the last Node(tail)
    public Node getTail() {
        return end;
    }

    //Function to insert an element at the beginning
    public void insertAtStart(int val) {
        Node nptr = new Node(val, null);
        size++;
        if (head == null) {
            head = nptr;
            end = head;
        } else {
            nptr.setLink(head);
            head = nptr;

        }
    }

    //Function to insert element at the end
    public void insertAtEnd(int val) {
        Node nptr = new Node(val, null);
        Node temp = head;
        if (head == null) {
            head = nptr;
            end = head;
        } else {
            end.setLink(nptr);
            end = nptr;
            size++;
        }
    }

    //Function to insert an element at specified position
    public void insertAtPos(int val, int pos) {
        Node nptr = new Node(val, null);
        Node ptr = head;
        pos = pos - 1;
        for (int i = 1; i < size; i++) {
            if (i == pos) {
                Node tmp = ptr.getLink();
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size++;
    }

    //Function to delete an element at specified position
    public void deleteAtPos(int pos) {
        if (pos == 1) {
            head = head.getLink();
            size--;
            return;
        }
        if (pos == size) {
            Node s = head;
            Node t = head;
            while (s != end) {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(null);
            size--;
            return;
        }
        Node ptr = head;
        pos = pos - 1;
        for (int i = 1; i < size - 1; i++) {
            if (i == pos) {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size--;
    }

    //Function to return value of specific index
    public Node find(int index) {
        if (index > size) {
            System.out.println("Index is not in list, enter new index");
            //find(index);
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.link;
        }
        return temp;
    }

    //Function to delete element at the end [returns null pointer exception]
//    public Node deleteLast(){
//        if(head == end){
//            System.out.println(isEmpty());
//        }
//        Node result = null;
//        if(head != end){
//            size--;
//            result = end;
//            if(end.prev != null){
//                end.prev.next = null;
//                end = end.prev;
//            }
//            else {
//                head = null;
//                end = null;
//            }
//        }
//        return result;
//    }

    //Function to display elements
    public void display() {
        System.out.print("\nSingly Linked List: ");
        if (size == 0) {
            System.out.print("empty LL\n");
            return;
        }
        if (head.getLink() == null) {
            System.out.println(head.getData());
            return;
        }
        Node ptr = head;
        System.out.print(head.getData() + "->");
        ptr = head.getLink();
        while (ptr.getLink() != null) {
            System.out.print(ptr.getData() + "->");
            ptr = ptr.getLink();
        }
        System.out.print(ptr.getData() + "\n");
    }

    //Function to generate a linkedList of random values between 0-100
    public void rngPopulateList(LinkedList list, int length) {
        Random random = new Random();
        int pick = random.nextInt(100);
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                list.insertAtEnd(1 + (int) (Math.random() * 100));
                list.display();
            }
        }
    }
}
