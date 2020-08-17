package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-17 19:16
 */
public class MajorityElement {

    @LetCodeCommit(no = 169, title = "Majority Element", time = 99, space = 87,
            selfRemark = "这是剑指offer上的题目")
    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count = 1;
        int i = 1;
        while (i < nums.length) {
            int num = nums[i++];
            if (count == 0) {
                result = num;
                count++;
                continue;
            }
            if (num == result) {
                count++;
            } else {
                count--;
            }
        }
        return result;
    }

    @Test
    public void test_case_1() {
        Assert.assertEquals(9, majorityElement(new int[]{10, 9, 9, 9, 10}));
    }

    @Test
    public void test_case_2() {
        Assert.assertEquals(2, majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

}
