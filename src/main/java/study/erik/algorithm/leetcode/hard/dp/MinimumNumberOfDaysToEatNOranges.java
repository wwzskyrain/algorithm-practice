/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

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
 * @version : MinimumNumberOfDaysToEatNOranges.java, v 0.1 2023年05月28日 23:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumNumberOfDaysToEatNOranges {

    @LetCodeCommit(title = "1553. Minimum Number of Days to Eat N Oranges",
            diff = "h",
            selfRemark = "题目不难，难在空间复杂度。"
                    + "我们写了一个int[]数组的，直接空间复杂度超载了。"
                    + "然后用这个记事本有什么好处呢？就是可以只计算真正需要计算的。应该大大省略了复杂度的。")
    public int minDays(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(0, 0);
        return dfs(n, map);
    }

    public int dfs(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int v = Math.min(n % 2 + dfs(n / 2, map) + 1, n % 3 + dfs(n / 3, map) + 1);
        map.put(n, v);
        return v;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {10, 4},
                {1, 1},
                {6, 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minDays(n));
    }
}
