import java.util.Scanner;

public class LinkedListDriver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //Creating object of class LinkedList
        LinkedList<Integer> list = new LinkedList<>();
        System.out.println("Singly Linked List\n");
        char ch;

        //Preform list operations

        do {
            System.out.println("\nSingly Linked List Operations\n");
            System.out.println("1. Insert at beginning");
            System.out.println("2. Insert at end");
            System.out.println("3. Insert at position");
            System.out.println("4. Delete at position");
            System.out.println("5. Check if empty");
            System.out.println("6. Get Size");
            System.out.println("7. Get head");
            System.out.println("8. Get last node");
            System.out.println("9. Delete First value");
            System.out.println("10. Delete Last value");
            System.out.println("11.Look for value of specified index");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter desired integer element to the beginning of LL");
                    list.addFirst(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Enter desired integer to add to end");
                    list.addLast(scan.nextInt());
                    break;
                case 3:
                    System.out.println("Enter integer element to insert at desired position");
                    int num = scan.nextInt();
                    System.out.println("Enter position");
                    int pos = scan.nextInt();
                    list.addAtIndex(num, pos);
                    break;
                case 4:
                    System.out.println("Enter the index to delete");
                    int p = scan.nextInt();
                    list.deleteAtPos(p);
                    break;
                case 5:
                    System.out.println("Empty Status = " + list.isEmpty());
                    break;
                case 6:
                    System.out.println("Size of list: " + list.getSize());
                    break;
                case 7:
                    System.out.println("Head: " + list.getHead());
                    break;
                case 8:
                    System.out.println("Last node: " + list.getTail());
                    break;
                case 9:
                    list.removeFirst();
                    System.out.println("First value was deleted, new list: ");
                    break;
                case 10:
                    list.removeLast();
                    System.out.println("Last value was deleted, new list: ");
                    break;
                case 11:
                    System.out.println("Enter the index you wish to see the value for: ");
                    int selection = scan.nextInt();
                    System.out.println("Value at index " + selection + ": " + list.find(selection));
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
