import java.util.TreeMap;
import java.util.Map;

public class rabinKarp {
    //map to hold all of our mapped values to a specific character literal, we use a TreeMap to hold the order in which values are added so display function shows mappings in order
    public static Map<Character, Integer> codes = new TreeMap<>();


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

    //Method returns the number of times a string needle appears in a haystack using rabin Karp algorithm with rolling hash approach to string match
    public static int countOccurrences(String haystack, String needle) {
        if (needle.length() == 0 || needle.length() > haystack.length()) {
            return 0;
        }

        long neddleHC = calculateHashCode(needle);  //Hash code of the given needle
        long power = (long) Math.pow(10, needle.length() - 1);  //highest power the first character in needle is raised to
        /*
            take the hash code of the first substring of needle length from the haystack, this value will be modified
            using a rolling function that subtracts the value of the first character in the substring and adds the new
            last character currently in the haystack
         */

        long substring_hash = calculateHashCode(haystack.substring(0, needle.length()));

        int i = 1, occurrence = 0;
        while (i <= haystack.length() - needle.length()) {
            //subtract the value of the first character in the substring
            substring_hash -= (calculateHashCode(haystack.substring(i - 1, i)) * power);
            /*
                the new substring is now missing a value of magnitude of "power", (10^needle.length - 1), since the the start
                character takes the power of the deleted character
                Ex:
                    "hello" ==> substring = "he" | hash = ('h' - 'a') * 2 ^ 1 + ('e' - 'a') * 2 ^ 0
                when the value of "h" is removed, 'e' value needs to be changed by a magnitude of 2, since the new substring
                will be "el" | hash = ('e' - 'a') * 10 ^ 1 + ('l' - 'a') * 10 ^ 0 hence we multiply by 10
            */
            substring_hash *= 10;

            //finally we add the new hash value of the next character, its power will always be 0 since its the last character in the substring
            substring_hash += calculateHashCode(String.valueOf(haystack.charAt(i + needle.length() - 1)));

            //if the hashcode value of the needle matches the hashcode value of str, we found a match
            if (neddleHC == substring_hash) {
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
