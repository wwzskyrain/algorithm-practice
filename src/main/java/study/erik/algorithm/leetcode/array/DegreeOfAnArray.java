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
 * @version : DegreeOfAnArray.java, v 0.1 2022年04月22日 8:36 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class DegreeOfAnArray {

    @LetCodeCommit(title = "697. Degree of an Array",
            diff = "e",
            selfRemark = "成绩不好，34%和68%")
    public int findShortestSubArray(int[] nums) {
        int maxCount = 0;
        int min = Integer.MAX_VALUE;
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> firstIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer c = count.get(num);
            if (c == null) {
                count.put(num, 1);
                firstIndexMap.put(num, i);
                c = 1;
            }
            count.put(num, c + 1);
            if (c + 1 > maxCount) {
                maxCount = c + 1;
                Integer firstIndex = firstIndexMap.get(num);
                min = i - firstIndex + 1;
            } else if (c + 1 == maxCount) {
                Integer firstIndex = firstIndexMap.get(num);
                min = Math.min(min, i - firstIndex + 1);
            }
        }
        return min;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1]"), 1},
                {ArrayUtils.buildArray("[1,2,2,3,1]"), 2},
                {ArrayUtils.buildArray("[1,2,2,3,1,4,2]"), 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, findShortestSubArray(nums));
    }

}