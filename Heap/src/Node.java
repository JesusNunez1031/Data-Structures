public class Node {

    private int data;   //data item(key)

    //Constructor
    public Node(int key){
        data = key;
    }

    public int getValue(){
        return data;
    }

    public void setValue(int id){
        data = id;
    }
}
