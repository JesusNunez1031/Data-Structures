import java.util.HashMap;

public class DriverTest {

    public static void main (String[] args){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Helelp");
        map.put(2,"Heldlp"); map.put(5,"Helddddselp");
        map.put(3,"Hellsssp");  map.put(4,"Heldddlp");

        for(int i = 0; i < map.size();i++){
            System.out.println(map.get(i+1));
        }

        String name = map.get(1);
        System.out.println(name);
        map.size();
    }
}
