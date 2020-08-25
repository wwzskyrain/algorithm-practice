package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-20 13:46
 */
public class ShortestUnsortedContinuousSubarray {


    @LetCodeCommit(title = "581. Shortest Unsorted Continuous Subarray")
    public int findUnsortedSubarray(int[] nums) {

        if (nums.length == 1) {
            return 0;
        }
        int l = 0;
        while (l + 1 < nums.length && nums[l] <= nums[l + 1]) {
            l++;
        }
        int ll = l;
//        while (ll)

        return 0;
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals(5, findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals(0, findUnsortedSubarray(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void test_solution_3() {
        Assert.assertEquals(0, findUnsortedSubarray(new int[]{1, 2, 3, 3, 3}));
    }

    @Test
    public void test_solution_4() {
        Assert.assertEquals(4, findUnsortedSubarray(new int[]{1, 3, 2, 2, 2}));
    }


}
