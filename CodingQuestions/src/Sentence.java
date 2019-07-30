import java.util.HashMap;
public class Sentence {

    public static HashMap<String, String> map;

    static {
        map = new HashMap();
        map.put("the", "valid");
        map.put("cat", "valid");
        map.put("jumps", "valid");
        map.put("high", "valid");
        map.put("hello", "valid");
        map.put("today", "valid");
        map.put("is", "valid");
        map.put("good", "valid");
        map.put("a", "valid");
        map.put("party", "valid");

    }

    public static boolean validSentence(String value, int index){
        value = value.toLowerCase();
        int i = index;
        String current = "";
        int j = 0;
        for(; j < value.length();j++) {
            current += value.charAt(j);
            if (map.containsKey(current)) {
                current = "";
                return validSentence(value.substring(j+1), index = i+1);
            }
            i++;
        }
        return current.isEmpty();
    }

    public static void main(String[] args) {
        //System.out.print(map.toString());
        System.out.println(validSentence("Thecatjumpshighs", 0));
    }

}
