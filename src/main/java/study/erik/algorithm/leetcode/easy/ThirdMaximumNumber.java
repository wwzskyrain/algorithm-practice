/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : ThirdMaximumNumber.java, v 0.1 2022年02月20日 9:45 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ThirdMaximumNumber {

    @LetCodeCommit(title = "414. Third Maximum Number")
    public int thirdMax(int[] nums) {
        int[] max = new int[3];
        int i = 0;
        for (int num : nums) {
            if (i == 0) {
                max[i++] = num;
                continue;
            }
            int foundIndex = Arrays.binarySearch(max, 0, i, num);
            if (foundIndex < 0) {// 需要插入元素
                // (-(insertion point) - 1)
                int indexToInsert = -(foundIndex + 1);
                if (i > 2) {
                    // 前面的前移
                    indexToInsert--;
                    while (indexToInsert >= 0) {
                        int t = max[indexToInsert];
                        max[indexToInsert] = num;
                        num = t;
                        indexToInsert--;
                    }
                    continue;
                }
                // 后面的后移
                while (indexToInsert <= i && indexToInsert < max.length) {
                    int temp = max[indexToInsert];
                    max[indexToInsert] = num;
                    num = temp;
                    indexToInsert++;
                }
                i = Math.min(i + 1, max.length);
            }
        }
        return i == max.length ? max[0] : max[i - 1];
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,2,5,3,5]"), 2},
                {ArrayUtils.buildArray("[3,2,1]"), 1},
                {ArrayUtils.buildArray("[1,2]"), 2},
                {ArrayUtils.buildArray("[2,2,3,1]"), 1}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, thirdMax(nums));
    }

}