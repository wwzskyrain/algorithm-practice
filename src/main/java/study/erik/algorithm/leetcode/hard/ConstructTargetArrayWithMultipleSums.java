/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : ConstructTargetArrayWithMultipleSums.java, v 0.1 2023年05月03日 17:19 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ConstructTargetArrayWithMultipleSums {

    @LetCodeCommit(title = "1354. Construct Target Array With Multiple Sums",
            selfRemark = "这个题目并不很难，其构造思路很明显。"
                    + "但是巧妙的点是什么呢，是lee这里用的%，见代码行注解。"
                    + "我自己写了一个，我用了堆，但是用优先级队列就可以了，因为这里不需要顾忌target的次序。"
                    + "我的卡点在于对于这样的[1,10000001]这种，就需要很多次的while循环处理，因为没有%这个操作。")
    public boolean isPossible(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        long total = 0;
        for (int a : A) {
            total += a;
            pq.add(a);
        }
        while (true) {
            int a = pq.poll();
            total -= a;
            if (a == 1 || total == 1) {return true;}
            if (a < total || total == 0 || a % total == 0) {return false;}
            a %= total; // 这里:顺着来的话 total = total + a的。所以这里逆着来时，完全可用a对上一个total取余。
            total += a;
            pq.add(a);
        }
    }

    @Parameter
    public int[]   target;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[9,3,5]"), true},
                {ArrayUtils.buildArray("[1,1,1,2]"), false},
                {ArrayUtils.buildArray("[8,5]"), true},
                {ArrayUtils.buildArray("[1,1000000000]"), true},
                {ArrayUtils.buildArray("[8,5]"), true},
                {ArrayUtils.buildArray("[8,5]"), true},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, isPossible(target));
    }
}