/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumLengthOfSubarrayWithPositiveProduct.java, v 0.1 2021年11月19日 2:14 下午 yueyi Exp $
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {

    @LetCodeCommit(title = "Maximum Length of Subarray With Positive Product",
            selfRemark = "思路不错，dp的思路；思考起来好麻烦，感觉脑子不够用呀。")
    public int getMaxLen(int[] nums) {
        int l = nums.length;
        int i = 0;
        while (i < l) {
            if (nums[i] != 0) {
                break;
            }
            i++;
        }
        if (i == l) {
            return 0;
        }
        // f1(n)表示以n结尾的成绩为正数的长度
        // f2(n)表示以n结尾的成绩为负数的长度
        int f1 = nums[i] > 0 ? 1 : 0;
        int f2 = nums[i] > 0 ? 0 : 1;
        int max = f1;
        i++;
        while (i < l) {
            int n = nums[i];
            if (n == 0) {
                while (i < l) {
                    if (nums[i] != 0) {
                        break;
                    }
                    i++;
                }
                if (i == l) {
                    return max;
                }
                f1 = nums[i] > 0 ? 1 : 0;
                f2 = nums[i] > 0 ? 0 : 1;
            } else if (n > 0) {
                f1++;
                // 如果f2==0，表示前面没有负数乘机，而单独一个正整数n也构不成负数乘机
                f2 = f2 > 0 ? (f2 + 1) : 0;
            } else {
                int tempF1 = f1;
                f1 = (f2 > 0) ? (f2 + 1) : 0;
                f2 = tempF1 + 1;
            }
            max = Math.max(max, f1);
            i++;
        }

        return max;
    }

    @Test
    public void test_() {
        Assert.assertEquals(4, getMaxLen(new int[] {1, -2, -3, 4}));
        Assert.assertEquals(3, getMaxLen(new int[] {0, 1, -2, -3, -4}));
        Assert.assertEquals(4, getMaxLen(new int[] {1, 2, 3, 5, -6, 4, 0, 10}));
        Assert.assertEquals(6, getMaxLen(new int[] {-16, 0, -5, 2, 2, -13, 11, 8}));
        Assert.assertEquals(11, getMaxLen(new int[] {9, 10, 1, 0, 19, 20, -28, 30, -12, 20, 11, -8, 7, 21, -26}));
    }

}