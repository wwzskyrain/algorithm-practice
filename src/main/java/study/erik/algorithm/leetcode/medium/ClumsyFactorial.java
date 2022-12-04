/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ClumsyFactorial.java, v 0.1 2022年12月04日 12:49 yueyi Exp $
 */
public class ClumsyFactorial {

    @LetCodeCommit(title = "1006. Clumsy Factorial",
            diff = "m",
            selfRemark = "真有意思"
                    + "1.首先这是一个递归题，直接递归解法"
                    + "2.其次这还可以是一个找规律的题目")
    public int clumsy(int N) {
        if (N == 1) {return 1;}
        if (N == 2) {return 2;}
        if (N == 3) {return 6;}
        if (N == 4) {return 7;}
        if (N % 4 == 1) {return N + 2;}
        if (N % 4 == 2) {return N + 2;}
        if (N % 4 == 3) {return N - 1;}
        return N + 1;
    }

}