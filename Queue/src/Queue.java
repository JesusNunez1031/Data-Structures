public class Queue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    //Constructor
    public Queue(int size){
        maxSize = size;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    //Insert an item to the rear of the queue
    public void insert(long value){
        if(rear == maxSize-1){  //deal with wraparound
            rear = -1;
        }
        queArray[++rear] = value;   //increment rear and insert
        nItems++;   //one more item
    }

    //Take item from front of queue
    public long remove() {
        long temp = queArray[front++];  //get value and increment front
        if(front == maxSize){   //deal with wraparound
            front = 0;
        }
        nItems--;
        return temp;
    }

    //Peek at the front of the queue
    public long peekFront(){
        return queArray[front];
    }

    //Returns true if the queue is empty
    public boolean isEmpty() {
        return (nItems == 0);
    }

    //Return true if the queue is full
    public boolean isFull(){
        return (nItems == maxSize);
    }

    //Returns the number of items in the queue
    public int size(){
        return nItems;
    }

    //Size Without the use of nItems
    public int sizeOfQueue(){
        if(rear >= front){
            return rear-front+1;
        }
        else {
            return (maxSize-front) + (rear + 1);
        }
    }

}
