/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : SplitArrayIntoConsecutiveSubsequences.java, v 0.1 2022年04月15日 10:42 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SplitArrayIntoConsecutiveSubsequences {

    @LetCodeCommit(title = "659. Split Array into Consecutive Subsequences",
            selfRemark = "不错的题目。三连改成五莲呢？")
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> end = new HashMap<>();
        for (int num : nums) {
            if (counter.get(num) < 1) {continue;}
            //这里优先接上。why优先街上.
            if (end.get(num - 1) != null &&
                    end.get(num - 1) > 0) {
                end.put(num - 1, end.get(num - 1) - 1);
                end.put(num, end.getOrDefault(num, 0) + 1);
                counter.put(num, counter.get(num) - 1);
                continue;
            }
            //其次在开一个
            if (counter.get(num + 1) != null && counter.get(num + 1) > 0 &&
                    counter.get(num + 2) != null && counter.get(num + 2) > 0) {
                counter.put(num + 1, counter.get(num + 1) - 1);
                counter.put(num + 2, counter.get(num + 2) - 1);
                counter.put(num, counter.get(num) - 1);
                end.put(num + 2, end.getOrDefault(num + 2, 0) + 1);
                continue;
            }
            return false;
        }
        return true;
    }

    @Parameter
    public int[]   nums;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3,4,6,7,8,9,10,11]"), true},
                {ArrayUtils.buildArray("[1,2,3,3,4,5]"), true},
                {ArrayUtils.buildArray("[1,2,3,3,4,4,5,5]"), true},
                {ArrayUtils.buildArray("[1,2,3,4,4,5]"), false}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, isPossible(nums));
    }
}