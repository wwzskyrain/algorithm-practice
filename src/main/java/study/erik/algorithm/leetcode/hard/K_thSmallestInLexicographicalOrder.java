/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : K_thSmallestInLexicographicalOrder.java, v 0.1 2022年03月05日 9:29 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class K_thSmallestInLexicographicalOrder {

    @LetCodeCommit(title = "440. K-th Smallest in Lexicographical Order",
            selfRemark = "这是一个很不错的题目哟。"
                    + "当然可以先序遍历然后计数，但是肯定会超时的。"
                    + "这里直接用计算的方式了。"
                    + "处理计算方法本身有点复杂外，"
                    + "对于求k_th的也是要注意边界值的。")
    public int findKthNumber(int n, int k) {
        int cur = 1;
        k--;
        // 先减去1
        while (k > 0) {
            int nextStep = nextStep(n, cur);
            if (nextStep <= k) {
                // 这里要=，因为这里是k走前头，前了1，cur走后头。
                k -= nextStep;
                cur++;
                continue;
            }
            k--;
            cur *= 10;
        }
        return cur;
    }

    /**
     * 到cur的兄弟节点需要多少步(节点)
     *
     * @param max
     * @param cur
     * @return
     */
    private int nextStep(int max, int cur) {
        int steps = 0;
        long n1 = cur, n2 = cur + 1;
        while (n1 <= max) {
            if (max < n2) {
                // 这里要max+1，因为是要跳过的，所以要加一
                steps += (max + 1 - n1);
                break;
            } else {
                // 注意这里为什么是n2-n1，因为，n2和n1是同一层次的，比如10和20、100和200
                steps += n2 - n1;
                n1 *= 10;
                n2 *= 10;
            }
        }
        return steps;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int k;
    @Parameter(2)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {13, 5, 13},
                {1, 1, 1},
                {100, 10, 17}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findKthNumber(n, k));
    }
}