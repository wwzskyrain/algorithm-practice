/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Stack;

/**
 * @author yueyi
 * @version : MinimumCostTreeFromLeafValues.java, v 0.1 2023年01月19日 19:57 yueyi Exp $
 */
public class MinimumCostTreeFromLeafValues {

    @LetCodeCommit(title = "1130. Minimum Cost Tree From Leaf Values",
            diff = "m",
            selfRemark = "https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/solutions/339959/one-pass-o-n-time-and-space/"
                    + "这是lee大神的帖子，讲的很好。")
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> s = new Stack<>();
        s.push(Integer.MAX_VALUE);
        for (int i : arr) {
            while (s.peek() <= i) {
                int p = s.pop();
                res += Math.min(s.peek(), i) * p;
            }
            s.push(i);
        }
        while (s.size() > 2) {
            res += s.pop() * s.peek();
        }
        return res;
    }
}