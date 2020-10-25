class QuickSort {
    /*
    Quicksort is a divide and conquer algorithm. It picks an element as a pivot and partions the given array around the picked pivot.
    Complexity: O(nlogn)
    Ex:
    1. Always pick the first element as a pivot
    2. Always pick last element as pivot 
    3. Pick a random element as the pivot
    4. Pick the median as the pivot

    Target of the partitions is, given an array and an element x of array as pivot, put x at its correct position in sorted array and put all smaller elements (smaller than x) before x, and
    put all greater elements (greater than x) after x. 

    Normally the best pivot would be the middle element but that can take time so the best thing to do is to take the last or first element in the data, median, or a random element
    */

    public static void quickSort(int arr[], int begin, int end) {
        //Check indices and continue only of there are still elements to be sorted
        if (begin < end) {
            //Get the index of the sorted pivot and use it to recursively call partion() method
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    //The partition method takes the last element as the pivot, and then it checks each element and swaps it before the pivot if its value is smaller.
    //By the end of the partition, all elements less than the pivot are on the left of it and all those bigger than the pivot are on the right of it.
    //The pivot is at its final sorted postion and the function returns this position.
    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        //index of smaller element
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            //if the current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                //increment the index of the smaller element
                i++;

                //swap arr[i] and ar[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        //place the pivot in the right spot, swap arr[i+1] and arr[end] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }

    public static void printArr(int[] a) {
        for (int j : a) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {10, 80, 30, 90, 40, 50, 70};

        printArr(arr);

        quickSort(arr, 0, arr.length - 1);

        printArr(arr);

    }
}