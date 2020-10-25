import java.util.TreeMap;
import java.util.Map;

public class rabinKarp {
    public static Map<Character, Integer> codes = new TreeMap<>();     //map to hold all of our mapped values to a specific character literal


//    public int calculateStringValue(String s) {
//        if(s.length() < 1) {
//            return 0;
//        }
//        int power = 0;
//
//        for(int i = 0; i < s.length();i++) {
//        }
//    }

    /*Insert the desired values to be mapped to a character ->
          Example:
            'a' -> 1
            'b' -> 2
            'c' -> 3
            ...
            'z' -> 26
     */
    public static void assignStringsCode() {
        char firstLetter = 'a';

        for (int i = 1; i <= 26; i++) {
            codes.put(firstLetter, i);
            firstLetter += 1;
        }
    }

    public static void displayCharacterCodes() {
        for (char c : codes.keySet()) {
            System.out.printf("%s -> %d \n", c, codes.get(c));
        }
    }

    public static void main(String[] args) {
        assignStringsCode();
        displayCharacterCodes();
    }
}
