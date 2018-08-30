package com.phicomm.interview;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        int[] nums = new int[] {1,3};
        searchInsert(nums, 2);
    }

    public static int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int low = 0, high = nums.length;
        int middle;
        while(low <= high) {
            middle = (low + high) / 2;
            if(nums[middle] == target) {
                return middle;
            } else if(nums[middle] < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return low;
    }
}