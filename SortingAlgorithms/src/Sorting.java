import java.util.Scanner;

public class Sorting {

    private long[] array;   //ref to array
    private int nElems;     //number of elements

    public Sorting(int max) {
        array = new long[max]; //create the array
        nElems = 0; // no items yet
    }

    public void insert(long value) {
        array[nElems] = value;  //insert in array
        nElems++; //increment the size of elements
    }

    //Display the elements in the array
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.printf("%d ", array[i]);
        }
        System.out.println();
    }

    //Apply the sorting method of Bubble Sort
    public void sortBubble() {
        for (int i = nElems - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    //Apply the Selection Sort method
    public void selectionSort() {
        int min;
        for (int i = 0; i < nElems - 1; i++) {
            min = i;
            for (int j = i + 1; j < nElems; j++) {
                if (array[j] < array[min])
                    min = j; //new min value
                swap(j, min);    //swap elements
            }
        }
    }

    //Apply the insertion Sort method
    public void insertionSort() {
        int i, j;
        for (i = 1; i < nElems; i++) {
            long temp = array[i];    //remove marked item
            j = i;   //start shifts at i
            while (j > 0 && array[j - 1] >= temp) { //until one is smaller
                array[j] = array[j - 1];   //shift item to the right
                --j; //go left one position
            }
            array[j] = temp; //insert marked item
        }
    }

    //Swap method for sort
    private void swap(int one, int two) {
        long temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }

    public static void main(String[] args) {
        int maxSize = 100; //array size
        Sorting array = new Sorting(maxSize);

        array.insert(7);
        array.insert(9);
        array.insert(4);
        array.insert(5);
        array.insert(2);
        array.insert(8);
        array.insert(1);
        array.insert(0);
        array.insert(6);
        array.insert(3);

        System.out.println("Unsorted array: ");
        array.display();

        Scanner input = new Scanner(System.in);
        System.out.println("Which kind of sorting would you like to preform?");
        System.out.println("1. Bubble Sort | 2. Selection Sort | 3. Insertion Sort");
        int user_input = input.nextInt();

        switch (user_input) {
            case 1:
                System.out.println("Bubble Sort: ");
                array.sortBubble(); //Bubble Sort the values
                array.display();
                break;

            case 2:
                System.out.println("Selection Sort: ");
                array.selectionSort();
                array.display();
                break;
            case 3:
                System.out.println("Insertion Sort: ");
                array.insertionSort();
                array.display();
                break;
        }
    }
}
