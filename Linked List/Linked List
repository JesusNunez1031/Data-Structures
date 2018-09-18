import java.util.Scanner;

public class SinglyLinkedList {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Creating object of class LinkedList
        LinkedList list = new LinkedList();
        System.out.println("Singly Linked List\n");
        char ch;

        //Preform list operations

        do {
            System.out.println("\nSingly Linked List Operations\n");
            System.out.println("0. Create a list of random values of length n");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at end");
            System.out.println("3. Insert at position");
            System.out.println("4. Delete at position");
            System.out.println("5. Check if empty");
            System.out.println("6. Get Size");
            System.out.println("7. Get head");
            System.out.println("8. Get last node");
            System.out.println("9. Delete Last value");
            System.out.println("10.Look for value of specified index");

            int choice = scan.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Enter the length of the list: ");
                    int n = scan.nextInt();
                    list.rngPopulateList(list, n);
                    break;

                case 1:
                    System.out.println("Enter desired integer element to the beginning of LL");
                    list.insertAtStart(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Enter desired integer to add to end");
                    list.insertAtEnd(scan.nextInt());
                    break;
                case 3:
                    System.out.println("Enter integer element to insert at desired position");
                    int num = scan.nextInt();
                    System.out.println("Enter position");
                    int pos = scan.nextInt();
                    if (pos <= 1 || pos > list.getSize()) {
                        System.out.println("Invalid position\n");
                    } else {
                        list.insertAtPos(num, pos);
                    }
                    break;
                case 4:
                    System.out.println("Enter the index to delete");
                    int p = scan.nextInt();
                    if (p < 1 || p > list.getSize()) {
                        System.out.println("Invalid index\n");
                    } else {
                        list.deleteAtPos(p);
                    }
                    break;
                case 5:
                    System.out.println("Empty Status = " + list.isEmpty());
                    break;
                case 6:
                    System.out.println("Size of list: " + list.getSize());
                    break;
                case 7:
                    System.out.println("Head: " + list.getHead().getData());
                    break;
                case 8:
                    System.out.println("Last node: " + list.getTail().getData());
                    break;
                case 9:
                    list.deleteAtPos(list.size);
                    System.out.println("Last value was deleted, new list: ");
                    break;
                case 10:
                    System.out.println("Enter the index you wish to see the value for: ");
                    int selection = scan.nextInt();
                    System.out.println("Value at index " + selection + ": " + list.find(selection).getData());
                    break;
                default:
                    System.out.println("Invalid entry! \n");
                    break;
            }

            //Display the list
            list.display();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
