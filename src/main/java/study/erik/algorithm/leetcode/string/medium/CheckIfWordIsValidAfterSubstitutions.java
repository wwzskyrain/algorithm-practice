/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CheckIfWordIsValidAfterSubstitutions.java, v 0.1 2022年12月04日 11:58 yueyi Exp $
 */
public class CheckIfWordIsValidAfterSubstitutions {

    @LetCodeCommit(title = "1003. Check If Word Is Valid After Substitutions"
            , diff = "m"
            , selfRemark = "简单的字符串判断用stack很妙的")
    public boolean isValid(String s) {

        char[] stack = new char[s.length()];
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (c != 'c') {
                stack[idx++] = c;
            } else {
                if (idx < 1 || stack[--idx] != 'b') {
                    return false;
                }
                if (idx < 1 || stack[--idx] != 'a') {
                    return false;
                }
            }
        }
        return idx == 0;
    }

    public boolean solutionWithABC(String s) {
        while (s.length() > 2) {
            boolean find = false;
            int l = s.length();
            for (int i = 0; i < l - 2; i++) {
                if (s.charAt(i) == 'a'
                        && s.charAt(i + 1) == 'b'
                        && s.charAt(i + 2) == 'c') {
                    find = true;
                    String newS = s.substring(0, i);
                    if (i + 3 < l) {
                        newS = newS + s.substring(i + 3);
                    }
                    s = newS;
                    break;
                }
            }
            if (!find) {
                return false;
            }
        }
        return s.length() == 0;
    }

}