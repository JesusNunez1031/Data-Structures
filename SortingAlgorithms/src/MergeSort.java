public class MergeSort {
    private long[] array;   //ref to the array
    private int nElems;     //number of data items

    public MergeSort(int max) {
        array = new long[max];
        nElems = 0;
    }

    //insert elements to the array
    public void insert(long value) {
        //insert the value and increase the size of the array
        array[nElems] = value;
        nElems++;
    }

    //display the values of the array
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

    public void mergeSort() {
        long[] workspace = new long[nElems];
        recMergeSort(workspace, 0, nElems - 1);
    }

    private void recMergeSort(long[] array, int lowerBound, int upperBound) {
        if (lowerBound == upperBound)    //if the range is 1, then there is no need to sort
            return;
        else {
            //get the midpoint of the array
            int mid = (lowerBound + upperBound) / 2;

            //sort the lower half
            recMergeSort(array, lowerBound, mid);

            //sort the high half
            recMergeSort(array, mid + 1, upperBound);

            //merge both sides
            merge(array, lowerBound, mid + 1, upperBound);
        }
    }

    private void merge(long[] array, int lowerPrt, int highPrt, int upperBound) {
        int i = 0;  //array index
        int lowerBound = lowerPrt;
        int mid = highPrt - 1;
        int n = upperBound - lowerBound + 1;    //number of items

        while (lowerPrt <= mid && highPrt <= upperBound) {
            if (this.array[lowerPrt] < this.array[highPrt])
                array[i++] = this.array[lowerPrt++];
            else
                array[i++] = this.array[highPrt++];
        }

        while (lowerPrt <= mid) {
            array[i++] = this.array[lowerPrt++];
        }

        while (highPrt <= upperBound) {
            array[i++] = this.array[highPrt++];
        }

        for (i = 0; i < n; i++) {
            this.array[lowerBound + i] = array[i];
        }
    }

    public static void main(String[] args) {
        int maxSize = 100;
        MergeSort array = new MergeSort(maxSize);   //create the array by calling the ref

        array.insert(64);
        array.insert(21);
        array.insert(33);
        array.insert(70);
        array.insert(12);
        array.insert(85);
        array.insert(44);
        array.insert(3);
        array.insert(99);
        array.insert(0);
        array.insert(108);
        array.insert(36);

        System.out.print("Unsorted array: ");
        array.display();

        array.mergeSort();

        System.out.print("Sorted array: ");
        array.display();
    }
}
