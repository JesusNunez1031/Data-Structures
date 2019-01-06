import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HashTableApp {

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws IOException {
        DataItem input;
        int aKey, size, n, keysPerCell;
        System.out.print("Enter the size of the hash table: "); //get the size from user
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;

        //make the table
        HashTable table = new HashTable(size);

        //insert items into the table
        for (int i = 0; i < n; i++) {
            aKey = (int) (java.lang.Math.random() * keysPerCell * size);
            input = new DataItem(aKey);
            table.insert(input);
        }

        //User menu
        while (true) {
            System.out.print("Enter the number for the following choices: ");
            System.out.print("1. show | 2. insert | 3. delete | 4. find \n");

            int choice = getInt();

            switch (choice) {
                case 1:
                    table.displayTable();
                    break;
                case 2:
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    input = new DataItem(aKey);
                    table.insert(input);
                    break;
                case 3:
                    System.out.print("Enter the key value to delete: ");
                    aKey = getInt();
                    table.delete(aKey);
                    break;
                case 4:
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    input = table.find(aKey);
                    if (input != null) {
                        System.out.println("Found " + aKey);
                    } else
                        System.out.println("Could not find " + aKey);
                    break;

                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }
}
