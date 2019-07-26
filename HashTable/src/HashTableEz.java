
import java.util.*;

class HashTableEz {

    public static void print(String text){
        System.out.println(text);
    }

    public static void Basics(Hashtable<Integer,String>table, Hashtable<Integer,String>table2){
        //Create a clone of a table
        table2 = (Hashtable<Integer, String>)table.clone();

        //print out the values to confirm copy
        System.out.println("Clone: " + table2);

        //Clear the clone
        table.clear();

        //Original Cleared
        System.out.println("Original: " + table);
    }

    //Method computeIfAbesnt --> if the key does not exist then add the new values associated with key
    public static void AdvMethods(Hashtable<Integer,String>table){
        //print the table
        System.out.println("Table: " + table);
        
        //provide value for new key which is absent using the computeIfAbsent method
        table.computeIfAbsent(4, n -> "odogaron");
        table.computeIfAbsent(1, n -> "Narudo");

        System.out.println("Modifed Table: " + table);

    }

    public static void Checker(Hashtable<Integer,String>table) {
        print("Table " + table);

        if(table.contains("Narga")) {
            print("Narga is in the table");
        }
        else {
            print("Narga is not in the table");
        }

        if(table.containsKey(6)){
            print("Value tied to requested key: ");
            print(table.get(6));
        }
        else {
            print("No values are associated with given key");
        }
    }

    //Enumeration to display the values in the table
    public static void displayValues(Hashtable<Integer,String>table){
        //create enumeration
        Enumeration e = table.elements();
        Enumeration e1 = table.keys();

        print("----------Enumeration----------");

        print("Values: ");
        while(e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
        print("Keys: ");
        while(e1.hasMoreElements()){
            System.out.println(e1.nextElement());
        }

        print("----------Sets----------");
        Set s = table.entrySet();
        Set k = table.keySet();

        print("Set entries: " + s);
        print("Set keys: " + k);
    } 


    public static void main(String args[]){
        //create the Hashtable
        Hashtable<Integer, String> table = new Hashtable<Integer, String>();

        Hashtable<Integer, String> table2 = new Hashtable<Integer, String>();

        table.put(1, "Narga");
        table.put(2, "Tigerex");
        table.put(3, "Xenojiva");
        table.put(4, "Zoramag");
        table.put(5, "Akantor");
        table.put(6, "Legiana");
        // Basics(table, table2);
        // AdvMethods(table);
        //Checker(table);
        displayValues(table);

    }
}