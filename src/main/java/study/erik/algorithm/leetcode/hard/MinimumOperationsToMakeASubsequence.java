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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : MinimumOperationsToMakeASubsequence.java, v 0.1 2023年06月21日 08:36 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumOperationsToMakeASubsequence {

    @LetCodeCommit(title = "1713. Minimum Operations to Make a Subsequence",
            diff = "h",
            selfRemark = "子序列的题，lis题目。")
    public int minOperations(int[] target, int[] A) {

        Map<Integer, Integer> h = new HashMap<>();
        for (int i = 0; i < target.length; ++i) {h.put(target[i], i);}

        ArrayList<Integer> stack = new ArrayList<>();
        for (int a : A) {
            if (!h.containsKey(a)) {continue;}
            if (stack.size() == 0 || h.get(a) > stack.get(stack.size() - 1)) {
                //这里做了一个优化，比较尾巴元素，直接追加。也是把
                stack.add(h.get(a));
                continue;
            }
            int left = 0, right = stack.size() - 1, mid;
            while (left < right) {
                mid = (left + right) / 2;
                if (stack.get(mid) < h.get(a)) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            stack.set(left, h.get(a));
        }
        return target.length - stack.size();
    }

    @Parameter
    public int[] target;
    @Parameter(1)
    public int[] arr;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{ArrayUtils.buildArray("[5,1,3]"), ArrayUtils.buildArray("[9,4,2,3,4]"), 2},
                {ArrayUtils.buildArray("[6,4,8,1,3,2]"), ArrayUtils.buildArray("[4,7,2,3,8,6,1]"), 3},
                {ArrayUtils.buildArray("[5,1,3]"), ArrayUtils.buildArray("[9,4,2,3,4]"), 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minOperations(target, arr));
    }
}