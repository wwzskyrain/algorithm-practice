package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-05 08:45
 */
public class ReversePairs {

    @Test
    public void test_solution() {
        Assert.assertEquals(2, reversePairs(new int[]{1, 3, 2, 3, 1}));
        Assert.assertEquals(3, reversePairs(new int[]{2, 4, 3, 5, 1}));
    }


    /**
     * title = Reverse Pairs
     * 这道题目太经典了，我真的不会写。先放着吧
     * diss大神的'树状数组'的应用，真的看不懂呢？
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        return solution(nums);
    }

    /**
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        // TODO: 2020-05-05
        return 0;
    }


}
