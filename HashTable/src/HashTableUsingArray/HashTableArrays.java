import java.util.*;

class HashTableArrays {
    int[] arr;
    int capacity;

    //Constructor
    public HashTableArrays(int capacity) {
        this.capacity = nextPrime(capacity);
        arr = new int[this.capacity];
    }

    //Insert
    public void insert(int value) {
        arr[value % capacity] = value;
    }

    //Clear the table
    public void clear() {
        arr = new int[capacity];
    }

    //Contains
    public boolean contains(int value) {
        return arr[value % capacity] == value;
    }

    //Delete
    public void delete(int value) {
        if(arr[value % capacity] == value) {
            arr[value % capacity] = 0;
        }
        else {
            System.out.println("\nError: " + value + " not found\n");
        }
    }

    //Generate the next prime number >= n
    private static int nextPrime(int n){
        if(n % 2 == 0){
            n++;
        }
        for(;!isPrime(n);n += 2);

        return n;
    }


    //Check if a given number is prime
    private static boolean isPrime(int n){
        if(n == 2 || n == 3)
            return true;
        if(n == 1 || n % 2 == 0)
            return false;
        for(int i = 3;i * i <= n;i += 2){
            if(n % i == 0)
                return false;
        }
        return false;
    }

    //Print the hashtable
    public void printTable(){
        System.out.print("\nHash Table = ");
        for(int i = 0;i < capacity;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}