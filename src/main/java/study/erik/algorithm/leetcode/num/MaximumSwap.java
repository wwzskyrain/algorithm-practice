/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.num;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumSwap.java, v 0.1 2022年04月17日 11:41 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumSwap {

    @LetCodeCommit(title = "670. Maximum Swap")
    public int maximumSwap(int num) {

        char[] chars = Integer.toString(num).toCharArray();
        int x = chars.length - 1;
        int y = chars.length - 1;
        int maxIndex = x;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] > chars[maxIndex]) {
                // 只更新maxIndex，不着急用
                maxIndex = i;
            } else if (chars[i] < chars[maxIndex]) {
                // 这里才用。如果没有这个case，也就不需要发生真的交换.
                x = maxIndex;
                y = i;
            } // 如果 chars[i] > chars[maxIndex] ，则不更新maxIndex，这样就保持maxIndex是最右边那个.
        }
        int temp = chars[x];
        chars[x] = chars[y];
        chars[y] = ((char) temp);
        return Integer.parseInt(String.valueOf(chars));
    }

    @Parameter
    public int num;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2736, 7236},
                {9973, 9973},
                {7635, 7653},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maximumSwap(num));
    }

}