/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yueyi
 * @version : RandomPickWithWeight.java, v 0.1 2022年03月24日 2:17 下午 yueyi Exp $
 */
@LetCodeCommit(title = "528. Random Pick with Weight",
        diff = "m",
        selfRemark = "这道题目还可以吧，拐了一个弯弯而已")
public class RandomPickWithWeight {

    private int[]  preSum;
    private int[]  w;
    private int    board = 0;
    private Random r     = new Random(System.currentTimeMillis());

    public RandomPickWithWeight(int[] w) {
        this.w = w;
        preSum = new int[w.length];
        for (int i = 0; i < preSum.length; i++) {
            preSum[i] = (i == 0) ? w[i] : w[i] + preSum[i - 1];
        }
        board = preSum[preSum.length - 1];
    }

    public int pickIndex() {
        int randomV = r.nextInt(board) + 1;
        int i = Arrays.binarySearch(preSum, randomV);
        return i >= 0 ? i : -(i + 1);
    }

}