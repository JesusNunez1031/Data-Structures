
public class HashTableChainingBinaryTrees {
    private BTNode[] table;
    private int size;

    /* Constructor */
    public HashTableChainingBinaryTrees(int tableSize) {
        table = new BTNode[ nextPrime(tableSize) ];
        size = 0;
    }

    // Function to check if the table is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Function to clear the hash table
    public void makeEmpty() {
        int l = table.length;
        table = new BTNode[l];
        size = 0;
    }

    // Function to get size
    public int getSize() {
        return size;
    }

    // Function to insert an element
    public void insert(int val) {
        size++;
        int pos = myhash(val);
        BTNode root = table[pos];
        root = insert(root, val);
        table[pos] = root;
    }

    // Function to insert data
    private BTNode insert(BTNode node, int data){
        if(node == null) {
            node = new BTNode(data);
        } else {
            if(data <= node.data) {
                node.left = insert(node.left, data);
            }
            else {
                node.right = insert(node.right, data);
            }
        }
        return node;
    }

    // Function to remove an element
    public void remove(int val) {
        int pos = myhash(val);
        BTNode root = table[pos];
        try {
            root = delete(root, val);
            size--;
        }
        catch (Exception e) {
            System.out.println("\nElement not present\n");
        }
        table[pos] = root;
    }

    // Function to remove an element
    private BTNode delete(BTNode root, int k) {
        BTNode p, p2, n;

        if(root.data == k) {
            BTNode lt, rt;
            lt = root.left;
            rt = root.right;
            if(lt == null && rt == null) {
                return null;
            } else if (lt == null) {
                p = rt;
                return p;
            }
            else {
                p2 = rt;
                p = rt;
                while(p.left != null) {
                    p = p.left;
                }
                p.left = lt;
                return p2;
            }
        }
        if (k < root.data) {
            n = delete(root.left, k);
            root.left = n;
        }
        else {
            n = delete(root.right, k);
            root.right = n;
        }
        return root;
    }

    // Hash Function
    private int myhash(Integer x) {
        int hashVal = x.hashCode();
        hashVal %= table.length;
        if(hashVal < 0) {
            hashVal += table.length;
        }
        return hashVal;
    }

    // Function to generate the next prime >= n
    private static int nextPrime(int n) {
        if(n % 2 == 0) {
            n++;
        }
        for(; !isPrime(n);n+=2);
        return n;
    }

    //Check if given value is prime
    private static boolean isPrime(int n) {
        if(n == 2 || n == 3){
            return true;
        }
        if(n == 1 || n % 2 == 0) {
            return false;
        }
        for(int i = 3;i * i <= n;i += 2){
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Function to print the hashtable
    public void printHashTable() {
        System.out.println();
        for(int i = 0;i < table.length;i++) {
            System.out.println("Bucket " + i + ": ");
            inOrder(table[i]);
            System.out.println();
        }
    }

    // inOrder Traversal
    private void inOrder(BTNode root) {
        if(root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }
}
