/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CheckIfItIsAGoodArray.java, v 0.1 2023年04月09日 17:11 yueyi Exp $
 */
public class CheckIfItIsAGoodArray {

    @LetCodeCommit(title = "1250. Check If It Is a Good Array",
            selfRemark = "这是一个数学题。"
                    + "只要这些nums中，有一对数据的最大公约数为1，则为good。"
                    + "如何找出那一对呢？可是有n(n-1)个组合的。"
                    + "不用，全局找即可——如果有一对最大公约数是1，则全部的最大公约数也是1，反之亦然。好像没有说清楚，也算了。"
                    + "学习点2：辗转相除法，神奇的是不用实现区分a和b的大小，如果a<b，则第一次之后就会调换的。")
    public boolean isGoodArray(int[] nums) {
        int a = nums[0];
        int y;
        for (int i = 1; i < nums.length; i++) {
            int b = nums[i];
            while (b > 0) {
                y = a % b;
                a = b;
                b = y;
            }
        }
        return a == 1;
    }

}