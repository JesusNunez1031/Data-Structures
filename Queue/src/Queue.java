public interface Queue<E> {

    //Returns the number of elements in the queue
    int size();

    //Returns true if queue is empty and false otherwise
    boolean isEmpty();

    //Inserts an element e at the rear of the queue [Java ADT also includes add(e) -> throws Exception,  and offer(e) -> returns special value]
    void enqueue(E e);

    //Returns but does not remove the first element in the queue (null if empty) [Java ADT also includes element() -> throws Exception, and peek() -> returns special value]
    E first();

    //Removes and returns the first element of the queue (null if empty) [Java ADT also includes remove() -> throws Exception, and poll() -> returns special value]
    E dequeue();


}
