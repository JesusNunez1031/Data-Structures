public class Heap {

    private Node[] heapArray;
    private int maxSize;    //size of array
    private int currentSize;    //number of nodes in array

    public Heap(int max) {
        maxSize = max;
        currentSize = 0;
        heapArray = new Node[maxSize];  //create array
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean insert(int value) {
        if (currentSize == maxSize)
            return false;

        Node newNode = new Node(value);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        while (index > 0 && heapArray[parent].getValue() < bottom.getValue()) {
            heapArray[index] = heapArray[parent];   //move it down
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }

    //Delete the item with the max value[assumes a non-empty list]
    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index];    //save root

        //while node has at least one child
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            //if larger child
            if (rightChild < currentSize && //right Child exists?
                    heapArray[leftChild].getValue() < heapArray[rightChild].getValue()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }
            //top >= largerChild?
            if (top.getValue() >= heapArray[largerChild].getValue()) {
                break;
            }
            //shift Child up
            heapArray[index] = heapArray[largerChild];
            index = largerChild;    //go down
        }
        heapArray[index] = top; //root to index
    }

    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize)
            return false;

        int oldValue = heapArray[index].getValue(); //remember old
        heapArray[index].setValue(newValue);    //change to new value

        if (oldValue < newValue) //if raised
            trickleUp(index);   //trickle it up
        else    //if lowered
            trickleDown(index); //trickle it down
        return true;
    }

    public void displayHeap() {
        System.out.print("heapArray: ");    //array format
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i] != null)
                System.out.print(heapArray[i].getValue() + " ");
            else
                System.out.print("-- ");
        }
        System.out.println();

        //heap format
        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int i = 0;  //current item
        String dots = "...............................";
        System.out.println(dots + dots);  //dotted top line

        //for each heap item
        while (currentSize > 0) {
            //first item in row?
            if (column == 0)
                for (int j = 0; j < nBlanks; j++) //preceding blanks
                    System.out.print(' ');

            //display item
            System.out.print(heapArray[i].getValue());
            if (++i == currentSize)  //done?
                break;

            if (++column == itemsPerRow) {//end of row?
                nBlanks /= 2;   //half the blanks
                itemsPerRow *= 2;   //twice the items
                column = 0; //start over on
                System.out.println();   //new row
            } else    //next item on row
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(" ");  //interim blanks
        }
        System.out.println("\n" + dots + dots); //dotted bottom line
    }
}
