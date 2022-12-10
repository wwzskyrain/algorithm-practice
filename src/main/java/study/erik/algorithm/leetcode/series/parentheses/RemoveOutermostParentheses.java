/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.parentheses;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RemoveOutermostParentheses.java, v 0.1 2022年12月10日 10:31 yueyi Exp $
 */
public class RemoveOutermostParentheses {

    @LetCodeCommit(title = "1021. Remove Outermost Parentheses")
    public String removeOuterParentheses(String s) {
        int i = 0;
        int j = 0;
        int b = 0;
        StringBuilder sb = new StringBuilder();
        while (j < s.length()) {
            if (s.charAt(j) == '(') {
                b++;
            } else {
                b--;
                if (b == 0) {
                    sb.append(s.substring(i + 1, j));
                    i = j + 1;
                }
            }
            j++;
        }
        return sb.toString();
    }

}