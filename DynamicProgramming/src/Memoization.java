/*
Memoization ensures that a method doesnt run for the same inputs more than once by keeping a
record of the results for the given inputs (usually in a hash map)

This code does memoization for fibonacci series and also for factorials
 */

import java.util.*;

public class Memoization {

    // Hashmap to save all of the values and check if we've seen them before
    public static Map<Integer, Integer> memo = new HashMap<>();

     static int fib( int n ) {
        if( n < 0)
            throw new IllegalArgumentException("No such thing as a negative index");

        //Base case
        else if (n == 0 || n == 1)
            return n;

        //Check if we have previously calculated that value
        if(memo.containsKey(n)) {
            System.out.printf("Fetching memo[%d]\n", n);
            return memo.get(n);
        }

        System.out.printf("computing fib(%d)\n", n);
        int result = fib(n - 1) + fib(n - 2);

        //memoize[store the new value for future reference
        memo.put(n, result);

        return result;
    }

    public static int fac( int n ) {
         // Base case
        if(n <= 2){
            return n;
        }

        //Check if the value has been previously calculated
        if(memo.containsKey(n)) {
            System.out.printf("Fetching memo[%d]\n", n);
            return memo.get(n);
        }

        System.out.printf("Computing factorial(%d)\n", n);
        int result = n * fac(n-1);

        //Save the value for future reference
        memo.put(n, result);

        return result;
    }

    public static void print(String text){
         System.out.println(text);
    }

    public static void main(String[] args) {
        //print("Result: " + fib(10));
        print("Result: " + fac(5));
        print("Result: " + fac(6));
        print("Result: " + fac(8));
        print("Result: " + fac(12));
        print("Result: " + fac(16));
    }
}
