package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-20 12:31
 */
public class ClimbingStairs {

    /**
     * title = Climbing Stairs
     * url = https://leetcode.com/problems/climbing-stairs/
     * 这是一个非常简单的一维动态规划，或者说一维递归
     * 成绩：100 和 5
     * difficulty=easy
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        return solution(n);
    }

    /**
     * d[n] = d[n-1] + d[n-2]
     *
     * @param n
     * @return
     */
    public int solution(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int one = 1;
        int two = 2;
        for (int i = 3; i <= n; i++) {
            int temp = one + two;
            one = two;
            two = temp;
        }
        return two;
    }

    @Test
    public void test_() {
        Assert.assertEquals(2, climbStairs(2));
        Assert.assertEquals(3, climbStairs(3));
    }

}
