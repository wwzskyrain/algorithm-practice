package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-10 19:09
 */
public class FirstMissingPositiveTest {


    @LetCodeCommit(no = 41, title = "First Missing Positive",
            selfRemark = "完全是看答案，所以就不计space和time了。" + "其实这一道题我做过了，而且思想很简单的，为什么还是没想出来呢？" + "这个题目有一次没有想起来。")
    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int temp;
            while ((temp = nums[i]) > 0 && temp <= nums.length && nums[temp - 1] != temp) {
                nums[i] = nums[temp - 1];
                //归位，temp其实是nums[i]
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    @Test
    public void test_case_1() {
        Assert.assertEquals(3, firstMissingPositive(new int[]{1, 2, 0}));
    }

    @Test
    public void test_case_2() {
        Assert.assertEquals(2, firstMissingPositive(new int[]{3, 4, -1, 1}));
    }


    @Test
    public void test_case_3() {
        Assert.assertEquals(1, firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }


}
