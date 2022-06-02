/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : BitwiseORsOfSubarrays.java, v 0.1 2022年06月02日 13:08 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BitwiseORsOfSubarrays {

    @LetCodeCommit(title = "898. Bitwise ORs of Subarrays",
            diff = "m",
            selfRemark = "我先写了一个二重循环的，访问上三角.结果超时。"
                    + "然后看了lee大神的解法，他用set来维护每次迭代的结果，这样可以"
                    + "避免下一次迭代的很多重复计算，所以优化了时间。")
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> all = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        for (int i : arr) {
            Set<Integer> temp = new HashSet<>();
            for (Integer r : cur) {
                temp.add(r | i);
            }
            temp.add(i);
            cur = temp;
            all.addAll(cur);
        }
        return all.size();
    }

    @Parameter
    public int[] arr;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,1,2]"), 3},
                {ArrayUtils.buildArray("[1,2,4]"), 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, subarrayBitwiseORs(arr));
    }

}