/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : IntegerBreak.java, v 0.1 2021年12月05日 8:52 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class IntegerBreak {

    @LetCodeCommit(title = "343. Integer Break",
            selfRemark = "之前做过还写过备注，这里再写一次。"
                    + "很显然用递归思想，然后具体用了备忘录，当然可以写成自底向上的，就不写了")
    public int integerBreak(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 0);
        return solution(n, map);
    }

    private int solution(int n, Map<Integer, Integer> map) {
        Integer ret = map.get(n);
        if (ret != null) {
            return ret;
        }
        int max = 0;
        for (int i = 1; i <= n / 2; i++) {
            max = Math.max(max, i * (n - i));
            max = Math.max(max, i * solution(n - i, map));
        }
        map.put(n, max);
        return max;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2, 1},
                {10, 36},
                {5, 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, integerBreak(n));
    }

}