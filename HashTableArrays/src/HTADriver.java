import java.util.Scanner;

public class HTADriver {
    static void print(String text) {
        System.out.println(text);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        print("----------Hash Table Test----------\n\n");
        print("Enter size: ");

        //int size = input.nextInt();

        // Hash Table object
        HashTable table = new HashTable(input.nextInt());

        char ch;
        // Preform the Hash Table operations
        do {
            print("\nHash Table Operations\n");
            print("1. Insert");
            print("2. Remove");
            print("3. Contains");
            print("4. Clear");
            print("5. Show Table");

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    print("Enter the integer value to insert...");
                    table.insert(input.nextInt());
                    break;
                case 2:
                    print("Enter Integer value to delete...");
                    table.delete(input.nextInt());
                    break;
                case 3:
                    print("Enter integer value to check if it is present in table");
                    print("Contains: " + table.contains(input.nextInt()));
                    break;
                case 4:
                    table.clear();
                    print("Hash Table Cleared\n");
                    break;
                case 5:
                    print("Current table: ");
                    table.printTable();
                    break;
                default:
                    print("Error: Wrong Entry \n");
                    break;
            }
            //Display hashtable
            table.printTable();

            print("Do you want to continue (Type y or n) \n");
            ch = input.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
