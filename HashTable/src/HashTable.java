import javax.xml.crypto.Data;

public class HashTable {

    private DataItem[] hashArray;   // array that holds the hashtable
    private int arraySize;
    private DataItem nonItem;   //for deleted items

    public HashTable(int size){
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1); //deleted item key is -1
    }

    public void displayTable(){
        System.out.print("Table: ");
        for(int i = 0;i < arraySize;i++){
            if(hashArray[i] != null)
                System.out.print(hashArray[i].getKey() + " ");
            else
                //indicates that the cell is empty
                System.out.print("** ");
        }
        System.out.println("");
    }

    public int hashFunc(int key){
        return key % arraySize; //hash function
    }

    //Method to insert an item into the table
    public void insert(DataItem item){
        //assumes the table is not full
        int key = item.getKey();    //extract the key
        int hashVal = hashFunc(key);    //hash the key

        //until empty cell or -1
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1){
            ++hashVal;  //go to the next cell
            hashVal %= arraySize;   //wraparound if necessary
        }
        hashArray[hashVal] = item;  //insert item
    }

    //Delete a value in the hash table
    public DataItem delete(int key){
        int hashVal = hashFunc(key);    //hash the key

        //until empty cell
        while(hashArray[hashVal] != null){
            //found the key?
            if(hashArray[hashVal].getKey() == key){
                DataItem temp = hashArray[hashVal]; //save item
                hashArray[hashVal] = nonItem;   //delete item
                return temp;    //return the item
            }
            ++hashVal;  //go to next cell
            hashVal %= arraySize;   //wraparound if necessary
        }
        return null;    //cant find the item
    }

    //Find an item with a given key
    public DataItem find(int key){
        int hashVal = hashFunc(key);    //hash the key

        //until the empty cell
        while(hashArray[hashVal] != null){
            //found the key?
            if(hashArray[hashVal].getKey() == key){
                return hashArray[hashVal];  //if yes, return item
            }
            ++hashVal;  //go to the next cell
            hashVal %= arraySize;   //wraparound if necessary
        }
        return null;    //cant find the item
    }

}
