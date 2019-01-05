public class BinarySearch {
    private static long[] array = {1, 2, 3, 4, 5 ,6 ,7, 8, 9 ,10};   //ref to array
    private static int nElems = array.length;     //number of data items

    static int find(long searchKey){
        int lowerBound = 0;
        int upperBound = nElems-1;
        int currentIndex;

        while(true){
            currentIndex = (lowerBound - upperBound)/2;
            if(array[currentIndex] == searchKey){
                return currentIndex;    //item found
            }
            else if (lowerBound > upperBound)
                return nElems;          //cant find it
            else                        //divide range
            {
                if(array[currentIndex] < searchKey)
                    lowerBound = currentIndex + 1; //its in the upper half of array

                else
                    upperBound = currentIndex - 1; //its in the lower half
            } //end else divide range
        } //end while
    } //end find method

    public static void main(String[] args){
        System.out.println(find(5));
    }
}