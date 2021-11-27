/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
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

/**
 * @author yueyi
 * @version : ProductofArrayExceptSelf.java, v 0.1 2021年11月27日 1:22 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ProductofArrayExceptSelf {

    @LetCodeCommit(title = "Product of Array Except Self")
    public int[] productExceptSelf(int[] nums) {
        // 对比下面的两个版本
        //return solution1(nums);
        //return solution2(nums);
        return nums;
    }

    public int[] solution1(int[] nums) {
        int[] preProduct = new int[nums.length];
        int[] sufProduct = new int[nums.length];
        preProduct[0] = 1;
        for (int i = 1; i < preProduct.length; i++) {
            preProduct[i] = preProduct[i - 1] * nums[i - 1];
        }
        sufProduct[nums.length - 1] = 1;
        for (int j = nums.length - 2; j >= 0; j--) {
            sufProduct[j] = (j + 2 < nums.length ? sufProduct[j + 1] : 1) * nums[j + 1];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = preProduct[i] * sufProduct[i];
        }
        return result;
    }

    public int[] solution2(int[] nums) {
        // 比solution1更精简了，不需要preProduct和suffixProduct了
        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int suffixProduct = 1;
        for (int j = nums.length - 2; j >= 0; j--) {
            suffixProduct *= nums[j + 1];
            result[j] *= suffixProduct;
        }
        return result;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,2,3,4]"), ArrayUtils.buildArray("[24,12,8,6]")},
                {ArrayUtils.buildArray("[-1,1,0,-3,3]"), ArrayUtils.buildArray("[0,0,9,0,0]")},
        };
    }

    @Test
    public void test_() {
        Assert.assertArrayEquals(expect, productExceptSelf(nums));
    }

}