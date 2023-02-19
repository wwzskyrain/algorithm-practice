/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Stack;

/**
 * @author yueyi
 * @version : ReverseSubstringsBetweenEachPairOfParentheses.java, v 0.1 2023年02月19日 22:27 yueyi Exp $
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {

    @LetCodeCommit(title = "1190. Reverse Substrings Between Each Pair of Parentheses",
            selfRemark = "通过这道题，我对lee215佩服的五体投地。"
                    + "首先要有一个灵感，即虫洞。"
                    + "其次要把这个简单的灵感在复杂的场景也能正确的分析并且应用。"
                    + "而且应用起来还十分的简单而且正确。")
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            }
            if (c == ')') {
                Integer left = stack.pop();
                pair[left] = i;
                pair[i] = left;
            }
        }
        StringBuilder sb = new StringBuilder();
        int d = 1;
        for (int i = 0; i < n; i += d) {
            char c = s.charAt(i);
            if (c == '(' || c == ')') {
                d = -d;
                i = pair[i];
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}