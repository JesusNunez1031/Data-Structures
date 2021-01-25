/*
    Union-Find / Disjoint-set data structure implementation using path compression

    Description: U-F/D-F is a data structure that stores a collection of disjoint (non-overlapping) sets of data. It
    keeps track of elements which are split into two or more disjoint sets; its two primary operations: find (search) & union (merge)

    Example:
        Objects:
            0 1 2 3 4 5 6 7 8 9           //grid points

        Disjoint sets of objects:
            0 1 {2 3 9} {5 6} 7 {4 8}     //subsets of connected grid points

        Find query: are objects 2 and 9 in the same set?
            0 1 {2 3 9} {5 6} 7 {4 8}     //are two grid points connected?
                 ↑   ↑

        Union command: merge sets containing 3 and 8
            0 1 {2 3 4 8 9} {5 6} 7     //add a connection between two grid points

    Applications:
        - Network
        - Web pages
        - Graphs
        - Image Processing
        - LCA in trees

    Methods
        Find: To find which component a particular element belongs to find the root of that component by following the parent
        nodes until a self loop is reached (a node who is its own parent)

        Union: To unify two elements find which are the root nodes of each component and if the root nodes are different
        make one of the root nodes be the parent of the other. Each union reduces the number of components by 1

        Note: Number of Components is the number of roots remaining; root nodes never increases

                                       Time Complexities of Operations
                             Algorithm    |    Average    |    Worst case
                     Space (construction) |      O(n)     |      O(n)
                         Find (search)    |     O(α(n))   |     O(α(n))
                         Union (merge)    |     O(α(n))   |     O(α(n))
                     Get component size   |     O(α(n))   |     O(α(n))
                     Check if connected   |     O(α(n))   |     O(α(n))
                     Count Components     |     O(α(n))   |     O(α(n))
     α(n) -> linear but not exactly, amortized meaning "average time taken per operation, if you do many operations", which
     here is constant
*/
public class unionFind {

    //Number of elements in the union find structure
    private final int size;

    //Used to track the size of each of the component
    private final int[] sz;

    /*
        id[i] points to the parent of i, if id[i] = i then i is a root node, p and q are connected if p[i] == q[i]
        Ex:
            i 0 1 2 3 4 5 6 7 8 9
        id[i] 0 1 9 9 9 6 6 7 8 9   5 and 6 are connected | 2 3 4 9 are connected
     */
    private final int[] id;

    //number of components in the union find
    private int numComponents;

    /**
     * Initialize a Union Find data structure of size {@code size}
     *
     * @param size capacity
     * @throws IllegalAccessException if a negative {@code size} is entered
     */
    public unionFind(int size) throws IllegalAccessException {
        //check for valid size
        if (size <= 0) {
            throw new IllegalAccessException("Size must be greater than 0");
        }
        //set the size of the union find and the numOfComponents
        this.size = size;
        this.numComponents = size;

        //initialize the sizes of the arrays to hold "size" components
        sz = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;  //link each components to itself (self root)
            sz[i] = 1;  //size of each component is 1 at start since there are no links
        }
    }

    /**
     * Display components/set's respective roots and sizes
     */
    public void display() {
        for (int component = 0; component < id.length; component++) {
            System.out.printf("Component %d -> root is: %d size: %d\n", component, id[component], sz[component]);
        }
    }


    /**
     * Finds which component/set element {@code p} belongs to, takes amortized constant time
     *
     * @param p element in the union find data structure
     * @return the root of element {@code p}
     */
    public int find(int p) {
        //find the root of the component/set 'p'
        int root = p;
        //search until the value at root doesnt self loop
        while (root != id[root]) {
            root = id[root];
        }

        //compress the path leading back to the root, this is the path compression that gives amortized time complexity
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    // recursive alternative of find method
//    public int find(int p) {
//        if (p == id[p]) {
//            return p;
//        }
//        return id[p] = find(id[p]);
//    }


    /**
     * Returns {@code true} if element {@code p} and {@code q} are in the same component/set, {@code false} otherwise
     *
     * @param p element {@code p}
     * @param q element {@code q}
     * @return {@code true} if element {@code p} and {@code q} are connected, {@code false} otherwise
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    /**
     * Returns the size of the component/set {@code p} belongs to
     *
     * @param p element {@code p}
     * @return the size of the components/set element {@code p} belongs to
     */
    public int componentSize(int p) {
        return sz[find(p)];
    }


    /**
     * Returns the number of elements in the union find data structure
     *
     * @return the number of elements in the union/disjoint set
     */
    public int size() {
        return size;
    }


    /**
     * Returns the number of remaining components/sets in the union find
     *
     * @return the number of remaining components/sets in the union find
     */
    public int components() {
        return numComponents;
    }


    /**
     * Unifies the components/sets containing elements {@code p} and {@code q}, {@code p} is the destination and {@code q}
     * is the source. The element that belongs to a larger component/set engulfs the smaller.
     *
     * @param p element {@code p}
     * @param q element {@code q}
     */
    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);

        //check if the elements are in the same component/set
        if (root1 == root2) {
            System.out.printf("Elements %d and %d belong to the same group", p, q);
            return;
        }

        //Merge the smaller component/set into the larger
        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }
        /*
            since p and q are not under the same root, after merging, p will be under q or vise verse so the number of
            component/set decreases by 1
         */
        numComponents--;
    }
}
