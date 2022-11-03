/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.sort1;

import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : SortArrayByParityII.java, v 0.1 2022年11月03日 23:54 yueyi Exp $
 */
public class SortArrayByParityII {

    @LetCodeCommit(title = "922. Sort Array By Parity II")
    public int[] sortArrayByParityII(int[] nums) {
        for (int o = 1; o < nums.length; o += 2) {
            if (nums[o] % 2 == 0) {
                for (int e = 0; e < nums.length; e += 2) {
                    if (nums[e] % 2 == 1) {
                        int t = nums[e];
                        nums[e] = nums[o];
                        nums[o] = t;
                        break;
                    }
                }
            }
        }
        return nums;
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(sortArrayByParityII(ArrayUtils.buildArray("[2,3,1,1,4,0,0,4,3,3]"))));
    }
}