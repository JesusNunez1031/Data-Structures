public class FenwickApp {
    public static void main(String[] args) {
        //data starts at 1 since 00000 has no updatable LSB so begin values from index 1
        int[] array = {0, 5, 2, 9, -3, 5, 20, 10, -7, 2, 3, -4, 0, -2, 15, 5};
        FenwickTree fenwick = new FenwickTree(array.length);

        fenwick.populate(array);
        System.out.println(fenwick.sumRange(6, 8));
        fenwick.print();

    }
}
