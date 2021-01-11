/*
    A Fenwick Tree also known as a Binary Index Tree is a data structure used to efficiently perform range queries, such
    as addition, multiplication, and XOR. When compared with a flat array of numbers, the Fenwick tree achieves a much
    better balance between two operations: element update and prefix sum calculation.  In a flat array of n numbers, you
    can either store the elements, or the prefix sums. In the first case, computing prefix sums requires linear time; in
    the second case, updating the array elements requires linear time (in both cases, the other operation can be performed
     in constant time). Fenwick trees allow both operations to be performed in O(log n) time. This is achieved by
     representing the numbers as a tree, where the value of each node is the sum of the numbers in that subtree. The tree
     structure allows operations to be performed using only O(log n) node accesses.

    Ex: the array being used in the Fenwick Tree can be visualized as a Tree if we consider each array index as a node in
    the tree by using the index number itself

           Array  index (binary index)                       Fenwick Tree
          |  5  |   1        00001 ] ┐ ┐ ┐                      |  5  |
          |  2  |   2        00010   ┘ | |              2 + 5 = |  7  |
          |  9  |   3        00011 ]   | |                      |  9  |
          | -3  |   4        00100     ┘ |     -3 + 9 + 2 + 5 = | 13  |
          |  5  |   5        00101 ] ┐   |                      |  5  |
          | 20  |   6        00110   ┘   |             20 + 5 = | 25  |
          | 10  |   7        00111 ]     |                      | 10  |
          | -7  |   8        01000       ┘ -7+10+20+5-3+9+2+5 = | 41  |
          |  2  |   9        01001 ] ┐ ┐                        |  2  |
          |  3  |  10        01010   ┘ |                3 + 2 = |  5  |
          | -4  |  11        01011 ]   |                        | -4  |
          |  0  |  12        01100     ┘     0 + (-4) + 3 + 2 = |  1  |
          | -2  |  13        01101 ] ┐                          | -2  |
          | 15  |  14        01110   ┘              15 + (-2) = | 13  |
          |  5  |  15        01111 ]                            |  5  |

   The tree nodes are constructed by looking at the last bit of the binary index values, we don't use 0 in the tree so
   out start index it 1

           ↓
 ]  1. 00001, if the bit is one, then the fenwick tree will store a copy of the value from the original array, half of all
       index values have their right-most bit set to 1. This can be imaged as the bottom most layer of a tree.

          ↓
 ┐  2. 00010, if its the second right-most bit that is 1, then we sum up two values, the current value in the index and its
 ┘     previous.

 ┐       ↓
 |  3. 00100, if its the third right-most bit that's one, the value in the fenwick tree is the sum of the current value
 |     in the index of array, plus its previous 3 values.
 ┘
 ┐      ↓
 |  4. 01000, the same steps are repeated in terms of looking for the next right most bit and the number of summed
 |     values for a specific index increases exponentially,
 |     first step, summed values = 1
 |     second step, values = 2
 |     third step, values = 4
 |     fourth step, values = 8
 ┘

        Time complexities:
                    Method      |       Time Complexity
                     sum()      |         O(log(n))
                   sumRange()   |         O(log(n))
                   update()     |         O(log(n))
                   populate()   |         O(m log(n))
                    print()     |           O(n)

      where n is the number of 1 bits in the index
      where m is the number of values to be added to the tree and n are the number of bits for each number m
 */
public class FenwickTree {
    //Fenwick Tree array
    public int[] FT;

    /** Initialize a Fenwick Tree of size {@code length}
     * @param length length of the workspace array
     */
    public FenwickTree(int length) {
        FT = new int[length];
    }

    /**
     * Returns the sum {@code [0, i]}
     * @param i the index up to where the sum will be calculated
     * @return the sum from start of the array up to {@code i} inclusive
     */
    /*
        Ex: sum(7) = sum(00111)
                   = FT[00111] + FT[00110] + FT[00100]
                   = FT[7] + FT[6] + FT[4]
                   = range(7, 7) + range(5, 6) + range(1, 4)
                   = 10 + 25 + 13 = 48

            sum(8) = sum(01000)
                   = FT[01000]
                   = FT[8]
                   = range(1, 8)
                   = 41
           flipping the 1 bit in 01000, results in 00000, so index 8 encompasses the entire sum up to 8 in array
     */
    public int sum(int i) {
        int sum = 0;
        while (i > 0) {
            sum += FT[i];
            /*
                Ex: 6 = (00110)
                   -6 = (11010)
                   00110 & 11010 = 00010 (2s comp)
                   00110 - 00010 = (4)
             */
            i = i - (i & -i); //flip the last set bit using 2's compliment
        }
        return sum;
    }

    /**
     * Returns the sum of values in the range {@code start} to {@code end}, {@code [start, end]}
     * @param start start index
     * @param end end index
     * @return the sum of values from index {@code start} to {@code end} inclusive
     */
    /*
        a normal Fenwick tree can only calculate the sum from [0, i] where i is any index in the array. To compute a sum
        given a range, we compute the sum from [0, end] and [0, start - 1] and subtract the values.
     */
    public int sumRange(int start, int end) {
        return sum(end) - sum(start - 1);
    }

    /**
     * Updates the value at index {@code i} to new value {@code k}
     * @param i the index to update
     * @param k the new index value
     */
    /*
        if we were to call update as (4, 10)

            we add 10 to the index value of 4 and we also need to make sure the new addition propagates through the rest
            of the array. We don't need to touch every index, rather, only the indexes at exponentially increasing intervals

       init: array[4] = -3
       add 10: array[4] = -3 + 10 = 7
       update Tree Value: FT[00100] = 13, 13 + 10 = 23
       next index: adding the least significant bit => FT[01000] => 41 + 10 = 51
       next index: adding the LSB => 16 which is not in the range of our FT array so we're done

     */
    public void update(int i, int k) {
        while(i < FT.length) {
            FT[i] += k;
            i = i + (i & -i);   //add last set bit
        }
    }

    /**
     * Creates the Fenwick Tree by adding all values from {@code array}
     * @param array array of values
     */
    public void populate(int[] array){
        for(int i = 1; i < array.length;i++) {
            update(i, array[i]);
        }
    }

    /**
     * Prints the Fenwick Tree Array
     */
    public void print() {
        for(int i = 0; i < FT.length;i++) {
            if(i == 0) {
                //first value FT[0] is omitted since FT array is 1 indexed
                System.out.print("[");
            } else if(i == FT.length - 1) {
                System.out.printf("%d]", FT[i]);
            } else {
                System.out.printf("%d, ", FT[i]);
            }
        }
    }
}
