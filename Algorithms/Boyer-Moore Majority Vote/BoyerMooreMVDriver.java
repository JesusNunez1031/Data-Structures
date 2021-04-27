import java.util.Arrays;

public class BoyerMooreMVDriver {
    public static void main(String[] args) {
        int[] array = {2, 2, 11, 2, 5, 2, 1, 2, 17};
        BoyerMooreMV solver = new BoyerMooreMV(array);

        System.out.printf("The majority element in %s is %d\n", Arrays.toString(array), solver.getMajority());

    }
}
