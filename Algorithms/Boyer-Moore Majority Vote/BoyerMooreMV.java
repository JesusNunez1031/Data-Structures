import java.util.NoSuchElementException;

public class BoyerMooreMV {
    /*
        The Boyer-Moore majority vote algorithm is an algorithm for finding the majority element from a sequence of elements
        using linear time and constant space.
            - A majority element is not guaranteed in a sequence hence the algorithm looks for an element that occurs repeatedly
              for more than half of the elements of the input. To verify if the algorithms chosen majority is valid, a
              second pass of the data can be done checking if the frequency of the chosen element is greater than half
              the length of the total data.
            - It is not possible for a streaming algorithm to find the most frequent element in less than linear space,
              for sequences whose number of repetitions can be small.
     */
    private final int[] nums;
    private boolean solved;
    public int majorityElement;
    private final int UNDEFINED = -1;

    public BoyerMooreMV(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            throw new IllegalArgumentException("Sequence array of values cannot be empty or null");
        }
        nums = sequence;
        solved = false;
    }

    public int getMajority() {
        if (!solved) {
            solve();
        }
        if (majorityElement == UNDEFINED) {
            throw new NoSuchElementException("The provided sequence has no valid majority element");
        }
        return majorityElement;
    }

    public void solve() {
        //avoid extra work
        if (solved) {
            return;
        }
        //pick the first value in the sequence and set its counter to 1
        int counter = 1;
        int majority_elem = nums[0];

        //iterate through all the values in the sequence
        for (int value : nums) {
            //if we find another occurrence of majority_element, increase its count, otherwise decrease the count
            if (value == majority_elem) {
                counter++;
            } else {
                counter--;
            }

            /*
                when the counter for the current majority element reaches 0, we reset the counter and pick the current
                value as the new majority element
             */
            if (counter == 0) {
                counter = 1;
                majority_elem = value;
            }
        }
        //call validMajority method to verify if the chosen value is a valid majority element in the given sequence
        if (validMajority(majority_elem)) {
            majorityElement = majority_elem;
        } else {
            majorityElement = UNDEFINED;
        }
        solved = true;
    }

    private boolean validMajority(int majority_elem) {
        /*
            A valid majority element in a given sequence has a count that is greater than half the length of the current
            sequence, i.e. its occurrence is > ⌊n/2⌋, floor of n/2
         */
        int n = nums.length;
        int counter = 0;

        for (int value : nums) {
            if (value == majority_elem) {
                counter++;
            }
        }
        return counter > Math.floor(n / 2);
    }
}
