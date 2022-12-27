/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math.medium;

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
 * @version : AddingTwoNegabinaryNumbers.java, v 0.1 2022年12月27日 12:47 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class AddingTwoNegabinaryNumbers {

    @LetCodeCommit(title = "1073. Adding Two Negabinary Numbers",
            selfRemark = "我先把两个数组变成整数，然后计算，然后把结果转化成-2的数组——数据溢出了。"
                    + "然后用这种数组计算法。这里需要注意carry需要取负。")
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int[] res = new int[Math.max(arr1.length, arr2.length) + 2];
        int k = res.length - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) {
                carry += arr1[i--];
            }
            if (j >= 0) {
                carry += arr2[j--];
            }
            res[k--] = carry & 1;
            carry = -(carry >> 1);
        }
        int idx = 0;
        while (idx < res.length && res[idx] == 0) {
            idx++;
        }
        return idx == res.length ? new int[] {0} : Arrays.copyOfRange(res, idx, res.length);
    }

    @Parameter
    public int[] arr1;
    @Parameter(1)
    public int[] arr2;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1]"), ArrayUtils.buildArray("[1]")},
                {ArrayUtils.buildArray("[0]"), ArrayUtils.buildArray("[1,1]")},
                {ArrayUtils.buildArray("[1,1,1,1,1]"), ArrayUtils.buildArray("[1,0,1]")},
                {ArrayUtils.buildArray("[0]"), ArrayUtils.buildArray("[0]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(addNegabinary(arr1, arr2)));
    }
}