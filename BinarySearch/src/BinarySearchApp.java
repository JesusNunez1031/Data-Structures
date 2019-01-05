public class BinarySearchApp {
    //Binary Search that utilized recursion
    private long[] array;   //ref to the array
    private int nElems;     //number of data items

    public BinarySearchApp(int max){
        array = new long[max];  //create the array of size max
        nElems = 0;
    }

    public int size(){
        return nElems;
    }

    public int find(long searchKey){
        return recFind(searchKey, 0, nElems-1);
    }

    private int recFind(long searchKey, int lowerBound, int upperBound){
        int mid;    //center of the array
        mid = ((lowerBound + upperBound)/2);
        if(array[mid] == searchKey)
            return mid;    //found the searched item
        else if(lowerBound > upperBound)
            return nElems;  //cant find the item
        else  {//divide the range into two parts
            if(array[mid] < searchKey) //its in the beginning half
                return recFind(searchKey, mid+1, upperBound);
            else    //its in the lower half
                return recFind(searchKey,lowerBound,mid-1);
        }
    }

    //Insert an element in the array in the order it belongs
    public void insert(long value){
        int i;
        //find out where the new value belongs
        for(i = 0; i < nElems;i++){
            if (array[i] > value)   //linear search
                break;
        }
        //move the bigger values up
        for(int j = nElems; j > i;j--){
            array[j] = array[j-1];
        }
        array[i] = value;   //insert the value and inc the size
        nElems++;
    }

    public void display(){
        for(int i = 0; i < nElems;i++){
            System.out.println(array[i] + " ");
        }
    }

    public static void main(String[] args){
        int maxSize = 100;
        BinarySearchApp array = new BinarySearchApp(maxSize);

        array.insert(72);
        array.insert(90);
        array.insert(45);
        array.insert(126);
        array.insert(54);
        array.insert(99);
        array.insert(144);
        array.insert(27);
        array.insert(135);
        array.insert(81);
        array.insert(18);
        array.insert(108);
        array.insert(9);
        array.insert(117);
        array.insert(63);
        array.insert(36);

        array.display();

        int searchKey = 27;
        if(array.find(searchKey) != array.size()){
            System.out.println("Found " + searchKey);
        }
        else {
            System.out.println("Cant find " + searchKey);
        }

    }
}
