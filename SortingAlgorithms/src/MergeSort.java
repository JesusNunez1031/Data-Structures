public class MergeSort {
    private long[] array;   //ref to the array
    private int nElems;     //number of data items

    public MergeSort(int max){
        array = new long[max];
        nElems = 0;
    }

    //insert elements to the array
    public void insert(long value){
        //insert the value and increase the size of the array
        array[nElems] = value;
        nElems++;
    }

    //display the values of the array
    public void display(){
        for(int i = 0; i < nElems;i++){
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

    public void mergeSort(){
        long[] workspace = new long[nElems];
        recMergeSort(workspace, 0, nElems-1);
    }

    private void recMergeSort(long[] workspace, int lowerBound, int upperBound) {
        if(lowerBound == upperBound)    //if the range is 1, then there is no need to sort
            return;
        else {
            //get the midpoint of the array
            int mid = (lowerBound + upperBound) / 2;

            //sort the lower half
            recMergeSort(workspace, lowerBound, mid);

            //sort the high half
            recMergeSort(workspace, mid+1, upperBound);

            //merge both sides
            merge(workspace, lowerBound, mid+1, upperBound);
        }
    }

    private void merge(long[] workspace, int lowerPrt, int highPrt, int upperBound) {
        int i = 0;  //workspace index
        int lowerBound = lowerPrt;
        int mid = highPrt - 1;
        int n = upperBound - lowerBound + 1;    //number of items

        while(lowerPrt <= mid && highPrt <= upperBound) {
            if (array[lowerPrt] < array[highPrt])
                workspace[i++] = array[lowerPrt++];
            else
                workspace[i++] = array[highPrt++];
        }

        while (lowerPrt <= mid){
            workspace[i++] = array[lowerPrt++];
        }

        while (highPrt <= upperBound){
            workspace[i++] = array[highPrt++];
        }

        for(i = 0; i < n;i++){
            array[lowerBound+i] = workspace[i];
        }
    }

    public static void main(String[] args){
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

        System.out.print("Unsorted array: " );
        array.display();

        array.mergeSort();

        System.out.print("Sorted array: " );
        array.display();
    }
}
