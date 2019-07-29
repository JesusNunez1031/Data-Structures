public class HashTable {
    int[] arr;
    int capacity;

    /** constructor **/
    public HashTable(int capacity)
    {
        // the capacity is set to the closest next prime from the number entered
        this.capacity = nextPrime(capacity);
        arr = new int[this.capacity];
    }

    /** function to insert **/
    public void insert(int value)
    {
        arr[value % capacity] = value;
    }

    /** function to clear **/
    public void clear()
    {
        arr = new int[capacity];
    }

    /** function contains **/
    public boolean contains(int value)
    {
        return arr[value % capacity] == value;
    }

    /** function to delete **/
    public void delete(int value)
    {
        if (arr[value % capacity] == value)
            arr[value % capacity] = 0;
        else
            System.out.println("\nError : Element " + value + " not found\n");
    }

    /** Function to generate next prime number >= n **/
    private static int nextPrime( int n )
    {
        if (n % 2 == 0)
            n++;
        for (; !isPrime(n); n += 2);

        return n;
    }

    /** Function to check if given number is prime **/
    private static boolean isPrime(int n)
    {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    /** function to print hash table **/
    public void printTable()
    {
        System.out.print("\nHash Table = ");
        for (int i = 0; i < capacity; i++)
            System.out.print(arr[i] +" ");
        System.out.println();
    }
}
