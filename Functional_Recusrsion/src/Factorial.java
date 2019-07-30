import java.math.BigInteger;
import java.util.stream.LongStream;


public class Factorial {

    //Find factorial using for loop
    public long factorialUsingForLoop(int n){
        long fact = 1;
        for(int i = 2; i <= n;i++){
            fact = fact * i;
        }
        return fact;
    }

    //Calculate factorial using streams
    public long factorialUsingStreams(int n){
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }

    //Factorial using recursion
    public long factorialUsingRecursion(int n){
        if(n <= 2){
            return n;
        }
        return n * factorialUsingRecursion(n-1);
    }

    // Factorials using Memoize
    private Long[] factorials = new Long[20];

    public long factorialUsingMemoize(int n){
        if(factorials[n] != null){
            return factorials[n];
        }

        if(n <= 2){
            return n;
        }

        long nthValue = n * factorialUsingMemoize(n - 1);
        factorials[n] = nthValue;
        return nthValue;
    }

    public BigInteger factorialHavingLargeResult(int n){
        BigInteger result = BigInteger.ONE;

        for(int i = 2;i <= n;i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
