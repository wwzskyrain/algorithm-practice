/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.backtracking.hard;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : VerbalArithmeticPuzzle.java, v 0.1 2023年04月26日 08:13 yueyi Exp $
 */
@LetCodeCommit(title = "1307. Verbal Arithmetic Puzzle",
        selfRemark = "已经确定是backtracking了")
public class VerbalArithmeticPuzzle {

    String[] words;
    String   result;

    public boolean isSolvable(String[] words, String result) {
        this.words = words;
        this.result = result;
        return true;
    }

}