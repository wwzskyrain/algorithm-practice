package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import static study.erik.algorithm.util.QuestionType.Array;

/**
 * @author erik.wang
 * @date 2020-08-13 23:55
 */
public class MissingNumber {

    @LetCodeCommit(no = 268, title = "Missing Number", time = 59, space = 60,
            diff = "e", types = Array,
            selfRemark = "这种占位题，做了好几个了；我承认，这个题目我做了半小时呢。思路对了，但是细节不少得处理",
            extend = "dis中，有两个绝妙的解法；我要写下来",
            related = "Couples Holding Hands")
    public int missingNumber(int[] nums) {

        int n = nums.length;
        if (n == 1) {
            return 1 - nums[0];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n || nums[i] == i) {
                continue;
            }

            int curNum = nums[i];
            while (curNum != i && curNum != n) {
                int temp = nums[curNum];
                nums[curNum] = curNum;
                curNum = temp;
            }
            nums[i] = (curNum == i ? i : n);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n) {
                return i;
            }
        }
        return n;
    }

    /**
     * 用异或
     *
     * @param nums
     * @return
     */
    public int missingNumber1(int[] nums) {
        int miss = nums.length;
        for (int i = 0; i < nums.length; i++) {
            miss = miss ^ i ^ nums[i];
        }
        return miss;
    }

    /**
     * 不多说
     *
     * @param nums
     * @return
     */
    public int missingNumber2(int[] nums) {

        int miss = nums.length;
        for (int i = 0; i < nums.length; i++) {
            miss = miss + (i - nums[i]);
        }
        return miss;
    }

    //
    @Test
    public void test_case_1() {
        int[] nums = new int[]{3, 0, 1};
        Assert.assertEquals(2, missingNumber2(nums));
    }

    @Test
    public void test_case_2() {
        int[] nums = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        Assert.assertEquals(8, missingNumber2(nums));
    }


    @Test
    public void test_case_3() {
        int[] nums = new int[]{0};
        Assert.assertEquals(1, missingNumber2(nums));
    }

    @Test
    public void test_case_4() {
        int[] nums = new int[]{0, 1};
        Assert.assertEquals(2, missingNumber2(nums));
    }

}
