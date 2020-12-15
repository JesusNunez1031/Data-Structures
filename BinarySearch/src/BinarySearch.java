public class BinarySearch {
    /*
        Binary search implementation using both recursive and iterative approaches
        Recursive: Time complexity: O(log n) Space: O(n) due to recursive stack
        Iterative: Time Complexity: O(log n) Space: O(1) constant space
     */
    private static final int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};   //ref to array
    private static final int nElems = array.length;     //number of data items

    //Binary Search method using an iterative approach
    static int findIterative(int target) {
        int lower_bound = 0;             //index of the fist value in the array
        int upper_bound = nElems - 1;    //index of the last value in the array

        while (lower_bound <= upper_bound) {
            //find the middle index in the section between lower_bound and upper_bound, to avoid integer flow, add the lower_bound value
            int middle = lower_bound + (upper_bound - lower_bound) / 2;

            //if the target was found, return its index
            if (array[middle] == target) {
                return middle;
            } else {
                //if the current value at the middle is less than the target, search right, else we search left
                if (array[middle] < target) {
                    lower_bound = middle + 1;
                } else {
                    upper_bound = middle - 1;
                }
            }
        }
        return -1;  //target was not found
    }

    //Binary Search method using recursion to find a target element
    static int findRecursive(int target) {
        return findRecursiveHelper(0, nElems - 1, target);
    }

    //helper method to use recursion
    static int findRecursiveHelper(int lower_bound, int upper_bound, int target) {
        int mid = lower_bound + (upper_bound - lower_bound) / 2;

        if (lower_bound > upper_bound) {
            return -1;  //target was not found
        } else if (array[mid] == target) {
            return mid; //return the index of the value
        } else {
            if (array[mid] < target) {
                return findRecursiveHelper(mid + 1, upper_bound, target); //call method searching the right of the array
            } else {
                return findRecursiveHelper(lower_bound, mid - 1, target);//call method searching the left of the array
            }
        }
    }

    public static void main(String[] args) {
        int target = 5;
        int result = findIterative(target);

        if(result < 0) {
            System.out.println("Index was not found");
        } else {
            System.out.printf("Target was found at index %d", result);
        }
    }
}