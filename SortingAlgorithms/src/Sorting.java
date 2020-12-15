import java.util.Scanner;

public class Sorting {
    /*
        Sorting Algorithms showcased are Bubble, Selection, and Insertion sort.
        All time complexities are O(n^2)
     */

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
    public void bubbleSort() {
        //for every value in the array, we search the array and swap values if the next is greater
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
        for (int i = 0; i < nElems - 1; i++) {
            int min = i; //assume the current value i is the smallest value in the array
            //search through i + 1 values and find the value smaller than min
            for (int j = i + 1; j < nElems; j++) {
                if (array[j] < array[min])
                    min = j;        //new min value
            }
            //when inner loop ends, we found a value smaller than the value at index i so we swap them
            swap(i, min);
        }
    }

    //Apply the insertion Sort method
    public void insertionSort() {
        int i, j;
        /*
            for every value in the array, we save it in a temp variable, and search backwards from the values position
            shifting values j to the right that are greater than temp, in the end, place the temp value at position j
            where j - 1 is < temp
         */
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
        int maxSize = 11; //array size
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
            case 1 -> {
                System.out.println("Bubble Sort: ");
                array.bubbleSort(); //Bubble Sort the values
                array.display();
            }
            case 2 -> {
                System.out.println("Selection Sort: ");
                array.selectionSort();
                array.display();
            }
            case 3 -> {
                System.out.println("Insertion Sort: ");
                array.insertionSort();
                array.display();
            }
        }
    }
}
