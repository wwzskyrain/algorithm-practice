package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/22
 **/
public class IntegerBreaker {

    /**
     * title =  Integer Break
     * link = https://leetcode.com/problems/integer-break/submissions/
     * 题目描述：一个正整数n，拆分它，使拆分的个数字之积最大。
     * 解答：1.求最值问题。找到他的所有拆分，然后以此计算他们的积，求最值；
     * 这个想法很朴实。但是仔细分析，这个问题具有子问题特性，就是可以拆解该问题为小规模问题。
     * 因为连乘是具有结合律的。
     * 2.直接给出这个题的递归公式
     * P(n) = max{ P(n-i)*P(i) }，其中i属于集合{ 2,3,4,...,n-1 };
     * 3.具体解答时，使用"备忘录"方法来消除重复计算
     * 4.注意细节：这个问题有个恶心的地方，就是至少拆分出两个数字来，比如3，也要拆分充1+2，该拆分的积为2；还不如不拆分
     * @param n
     * @return
     */
    public int integerBreak(int n) {

        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else if (n == 4) {
            return 4;
        } else if (n == 5) {
            return 6;
        } else {
            int[] result = new int[n + 1];
            result[0] = 1;
            result[1] = 1;
            result[2] = 2;
            result[3] = 3;
            result[4] = 4;
            doIntegerBreak(n, result);
            return result[n];
        }
    }

    public int doIntegerBreak(int n, int[] result) {

        int max = 0;
        for (int i = 4; i < n; i++) {

            int first;
            if (result[n - i] > 0) {
                first = result[n - i];
            } else {
                first = doIntegerBreak(n - i, result);
                result[n - i] = first;
            }

            int second;
            if (result[i] > 0) {
                second = result[i];
            } else {
                second = doIntegerBreak(i, result);
                result[i] = second;
            }
            max = Math.max(max, first * second);
            result[n] = max;
        }
        return result[n];
    }

    @Test
    public void test_integerBreak() {
        Assert.assertEquals(36, integerBreak(10));
        Assert.assertEquals(1, integerBreak(2));
        System.out.println(integerBreak(41));
    }

//  add patch.

}
