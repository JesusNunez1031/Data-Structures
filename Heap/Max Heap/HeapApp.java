import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//The heap operations operate in O(logN)

public class HeapApp {

    public static int getInt() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        return Integer.parseInt(input);
    }

    public static void main(String[] args) throws IOException {
        int value, value2;
        Heap theHeap = new Heap(31);    //make a Heap; max size 31
        boolean success;

        //insert 10 items
        theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        while (true) {  //until [Ctrl] - [C]
            System.out.print("Select one of the following choices: ");
            System.out.println("1. Show | 2. Insert | 3. Remove | 4. Change: ");
            int choice = getInt();

            switch (choice){
                case 1:
                    theHeap.displayHeap();
                    break;
                case 2:
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    success = theHeap.insert(value);
                    if(!success){
                        System.out.println("Cant insert; heap is full");
                        break;
                    }
                case 3:
                    if(!theHeap.isEmpty())
                        theHeap.remove();
                    else
                        System.out.println("Cant remove; heap is empty");
                    break;
                case 4:
                    System.out.print("Enter current index of item: ");
                    value = getInt();
                    System.out.print("Enter new value: ");
                    value2 = getInt();
                    success = theHeap.change(value, value2);
                    if(!success)
                        System.out.println("Invalid index");
                    break;
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }
}
