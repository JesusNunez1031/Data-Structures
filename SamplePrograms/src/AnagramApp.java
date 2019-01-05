import javax.sql.rowset.serial.SQLInputImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnagramApp {
    static int size;
    static int count;
    static char[] arrChar = new char[100];

    public static void doAnagram(int newSize){
        //Base case: if the size is too small then go no further
        if(newSize == 1){
            return;
        }
        for(int i = 0; i < newSize;i++){    //for each position
            doAnagram(newSize-1);   //anagram remaining
            if (newSize == 2){  //if innermost, display it
                displayWord();
            }
            rotate(newSize);    //rotate the word
        }
    }

    //Rotate left all chars from position to end
    public static void rotate(int newSize){
        int i;
        int position = size - newSize;
        char temp = arrChar[position];  //save first letter
        for(i = position+1; i < size;i++){  //shift all letters to the left
            arrChar[i-1] = arrChar[i];
        }
        arrChar[i-1] = temp;    //put the first letter on the right
    }

    //Display the word
    public static void displayWord(){
        if(count < 99)
            System.out.print(" ");
        if(count < 9)
            System.out.print(" ");
        System.out.print(++count + " ");
        for(int i = 0; i < size;i++){
            System.out.print(arrChar[i]);
        }
        System.out.print("   ");
        System.out.flush();
        if(count % 6 == 0)
            System.out.println("");
    }

    public static String getString() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }


    public static void main(String[] args) throws IOException {
        System.out.print("Enter a word: \n");   //get a word from the user
        String input = getString();
        size = input.length();  //get the size of the string

        count = 0;
        for(int i = 0;i < size;i++){
            arrChar[i] = input.charAt(i);   //store the input string in an array
        }
        doAnagram(size);
    }
}
