
import java.io.IOException;

public class PriorityQApp {

    public static void main (String[] args) throws IOException {
        PriorityQ thePQ = new PriorityQ(5);
        thePQ.insert(1);
        thePQ.insert(2);
        thePQ.insert(6);
        thePQ.insert(4);
        thePQ.insert(20);

        while(!thePQ.isEmpty()){
            long item = thePQ.remove();
            System.out.print(item + " "); //1, 2, 4, 6, 20
        }
        System.out.print("");
    }
}
