import java.util.TreeMap;
import java.util.Map;

public class rabinKarp {
    public static Map<Character, Integer> codes = new TreeMap<>();     //map to hold all of our mapped values to a specific character literal, we use a TreeMap to hold the order in which values are added so display function shows mappings in order


    /*
    To avoid miss matches, we multiply each letters hashcode value by 10 ^ a power, for example:
    suppose we want to see how many time "dba" appears in "ccaccaaedba"
     dba encodes to 7 since d-> 4 + b-> 2 + a-> 1, however, cca also encodes to 7 and our algorithm would have to further
     compare the string characters to mark a miss match. This would give give us a worst time complexity of O(h * n) where
     n is the needle and h is the haystack since we compare every string. Multiplying each character code by 10 would bring the average time to O(h - n)
                    Ex:
                        dba -> (4 * 10^2) + (2 * 10^1) + (1 * 10^0) = 421
                        cca -> (3 * 10^2) + (3 * 10^1) + (1 * 10^0) = 331

                        so now 421 != 331 and we get no false matches
         */
    public static int calculateHashCode(String s) {
        if (s.length() < 1) {
            return 0;
        }
        int power = s.length() - 1;
        int hashCode = 0;

        for (int i = 0; i < s.length(); i++) {
            hashCode += codes.get(s.charAt(i)) * Math.pow(10, power--);
        }
        return hashCode;
    }

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
            firstLetter++;
        }
    }

    public static void displayCharacterCodes() {
        for (char c : codes.keySet()) {
            System.out.printf("%s -> %d \n", c, codes.get(c));
        }
    }

    //Method returns the number of times a string needle appears in a haystack using rabin Karp algorithm approach to string match
    public static int countOccurrences(String haystack, String needle) {
        if (needle.length() == 0 || needle.length() > haystack.length()) {
            return 0;
        }
        //Get the hashcode value of the needle string
        int neddleHC = calculateHashCode(needle);

        int i = 0, occurrence = 0;
        while (i <= haystack.length() - needle.length()) {
            //We look at a substring in the haystack equal to i to the length of the needle
            String str = haystack.substring(i, i + needle.length());

            if (neddleHC == calculateHashCode(str)) {
                occurrence++;
            }
            i++;
        }
        return occurrence;
    }

    public static void main(String[] args) {
        displayCharacterCodes();
        assignStringsCode();

        String haystack = "ccaccaaedba";
        String needle = "dba";

        System.out.printf("Number of times %s appears in %s is %d", needle, haystack, countOccurrences(haystack, needle));
    }
}
