package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-08-20 13:46
 */
@RunWith(Parameterized.class)
public class ShortestUnsortedContinuousSubarray {

    @LetCodeCommit(title = "581. Shortest Unsorted Continuous Subarray",
            diff = "m",
            selfRemark = "这是个好题目，虽然我没有做出来。"
                    + "因为我对题意理解不清，不应该只从操作层面。"
                    + "也应该从逻辑层面思考一下题意。"
                    + "下面的排序法，算是从逻辑层面思考——从题意出发。"
                    + "还有一个解法，O(n)的时间复杂度。https://leetcode.com/problems/shortest-unsorted-continuous-subarray/discuss/103057/Java-O(n)-Time-O(1)-Space")
    public int findUnsortedSubarray(int[] nums) {

        int[] temp = nums.clone();
        Arrays.sort(temp);
        int start = 0;
        while (start < nums.length && temp[start] == nums[start]) {
            start++;
        }
        if (start == nums.length) {
            return 0;
        }
        int end = nums.length - 1;
        while (end > start && temp[end] == nums[end]) {
            end--;
        }
        return end - start + 1;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("2, 6, 4, 8, 10, 9, 15"), 5},
                {ArrayUtils.buildArray("1, 2, 3, 4"), 0},
                {ArrayUtils.buildArray("1, 2, 3, 3, 3"), 0},
                {ArrayUtils.buildArray("1, 3, 2, 2, 2"), 4},
                {ArrayUtils.buildArray("[1,3,5,4,2]"), 4}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findUnsortedSubarray(nums));
    }

}
