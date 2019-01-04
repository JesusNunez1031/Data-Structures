public class PriorityQ {
    private int maxSize;
    private long[] queArray;
    private int nItems;

    public PriorityQ(int size){ //Constructor
        maxSize = size;
        queArray = new long[maxSize];
        nItems = 0;
    }

    //Insert item to queue
    public void insert(long item){
        int i;
        //if there are no items currently in queue, insert at position 0
        if(nItems == 0){
            queArray[nItems++] = item;
        }
        else {
            //if its populated, then begin at the end
            for(i = nItems-1; i >= 0;i--){
                //if the new item is larger
                if(item > queArray[i]){
                    //shift upward
                    queArray[i+1] = queArray[i];
                }
                //if the item is smaller then end shifting
                else {
                    break;
                }
            }
            queArray[i+1] = item;   //insert it
            nItems++;
        }
    }

    //Remove min item
    public long remove(){
        return queArray[--nItems];
    }

    //Peek the minimal value
    public long peekMin(){
        return queArray[nItems-1];
    }

    //Returns true of the queue is empty
    public boolean isEmpty(){
        return (nItems == 0);
    }

    //Returns true if the queue is full
    public boolean isFull(){
        return (nItems == maxSize);
    }
}
