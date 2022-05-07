/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.permutation;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : LetterCasePermutation.java, v 0.1 2022年05月07日 07:46 yueyi Exp $
 */
public class LetterCasePermutation {

    @LetCodeCommit(title = "784. Letter Case Permutation",
            selfRemark = "好久没遇到这类题目了。"
                    + "很简单的全排列问题，早在我们本科算法分析课上就学到过。"
                    + "也是回溯法的典型题。"
                    + "一气呵成")
    public List<String> letterCasePermutation(String s) {
        List<String> allPermutation = new ArrayList<>();
        doPermutation(s.toCharArray(), 0, allPermutation);
        return allPermutation;
    }

    public void doPermutation(char[] chars, int index, List<String> per) {
        if (index == chars.length) {
            per.add(String.valueOf(chars));
            return;
        }
        char c = chars[index];
        if (c >= '0' && c <= '9') {
            doPermutation(chars, index + 1, per);
            return;
        }
        doPermutation(chars, index + 1, per);
        char temp = c;
        if (c >= 'a' && c <= 'z') {
            chars[index] = (char) (c - ('a' - 'A'));
        } else {
            chars[index] = (char) (c + ('a' - 'A'));
        }
        doPermutation(chars, index + 1, per);
        chars[index] = temp;
    }

    @Test
    public void test() {
        List<String> list = letterCasePermutation("a1b2");
        System.out.println(list);
        list = letterCasePermutation("3z4");
        System.out.println(list);
    }
}