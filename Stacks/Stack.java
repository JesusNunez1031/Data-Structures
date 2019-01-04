class Stack {
    private int maxSize;    //size of stack array
    private long[] stackArray;
    private int top;    //top of stack

    //Constructor
    public Stack(int s) {
        maxSize = s;
        stackArray = new long[maxSize];
        top = -1;   //no items yet
    }

    //put an item on the top of stack
    public void push(long j){
        stackArray[++top] = j;  //increment top, insert item
    }

    //pop (take) an item from the stack
    public long pop() {
        return stackArray[top--];   //access the item of the top and then decrement it
    }

    //Peek the top of the stack
    public long peek(){
        return stackArray[top];
    }

    //Will return true if the stack has no items in it
    public boolean isEmpty(){
     return (top == -1);
    }

    //Return true if the stack is full
    public boolean isFull(){
        return (top == maxSize - 1);
    }
}