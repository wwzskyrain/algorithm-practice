/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ThreeSumWithMultiplicity.java, v 0.1 2022年04月06日 10:08 下午 yueyi Exp $
 */
public class ThreeSumWithMultiplicity {

    @LetCodeCommit(title = "923. 3Sum With Multiplicity",
            selfRemark = "这个解法让这个题目很没意思了。"
                    + "这个题的条件太多了：三个数的和，数字有都是100以内.")
    public int threeSumMulti(int[] arr, int target) {
        int mod = 1000000007;
        // 注意这里一定要用long，int是有问题的，在*时会溢出
        long[] c = new long[101];
        for (int i : arr) {
            c[i]++;
        }
        long ret = 0;
        for (int i = 0; i < c.length; i++) {
            for (int j = i; j < c.length; j++) {
                int k = target - i - j;
                if (k < 0 || k > 100) {
                    continue;
                }
                if (i == j && j == k) {
                    ret = ret + c[i] * (c[i] - 1) * (c[i] - 2) / 6;
                } else if (i == j && j != k) {
                    ret = ret + c[i] * (c[i] - 1) / 2 * c[k];
                } else if (j < k) {
                    ret = ret + c[i] * c[j] * c[k];
                }
            }
        }
        return (int) ret % mod;
    }

}