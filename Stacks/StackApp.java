class StackApp{

    public static void main(String args[]){
        Stack theStack = new Stack(10); //initialize the new stack of size 10
        theStack.push(20);
        theStack.push(40);
        theStack.push(60);
        theStack.push(80);

        while(!theStack.isEmpty){
            //delete items from the stack
            long value = theStack.pop();
            System.out.print(value); //display it
            System.out.print(" ");
        }
        System.out.print(" ");
    }
}