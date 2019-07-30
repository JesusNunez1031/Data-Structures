import java.util.Scanner;

public class HTCBTDriver {

    public static void print(String txt) {
        System.out.println(txt);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");

        //create the object HTCBT
        HashTableChainingBinaryTrees htree = new HashTableChainingBinaryTrees(input.nextInt());

        char ch;

        do {
            print("\nHash Table Operations\n");
            print("1. Insert ");
            print("2. Remove ");
            print("3. Clear ");
            print("4. Size ");
            print("5. Check if Empty ");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    print("Enter the integer element to insert: ");
                    htree.insert(input.nextInt());
                    break;
                case 2:
                    print("Enter an integer value to delete: ");
                    htree.remove(input.nextInt());
                    break;
                case 3:
                    htree.makeEmpty();
                    print("Hash Table Cleared\n");
                    break;
                case 4:
                    print("Size = " + htree.getSize());
                    break;
                case 5:
                    print("Empty status = " + htree.isEmpty());
                    break;
                default:
                    print("Wrong Entry \n ");
                    break;
            }
            // Display table
            htree.printHashTable();

            System.out.println("\nDo you want to continue? (Type y or n) \n");
            ch = input.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
