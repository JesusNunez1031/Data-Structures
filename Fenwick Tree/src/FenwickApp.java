public class FenwickApp {
    public static void main(String[] args) {
        //data starts at 1 since 00000 has no updatable LSB so queries should be int the range of 1 to array.length inclusive
        int[] array = {5, 2, 9, -3, 5, 20, 10, -7, 2, 3, -4, 0, -2, 15, 5};
        FenwickTree fenwick = new FenwickTree(array);

        System.out.println(fenwick.sumRange(1, array.length));
        System.out.println(fenwick.sum(7));
        fenwick.print();

    }
}
