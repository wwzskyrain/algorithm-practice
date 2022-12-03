/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.union.find;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SatisfiabilityOfEqualityEquations.java, v 0.1 2022年12月03日 14:19 yueyi Exp $
 */
@LetCodeCommit(title = "990. Satisfiability of Equality Equations",
        diff = "m",
        selfRemark = "联通集合")
public class SatisfiabilityOfEqualityEquations {

    private int[] parent = new int[26];

    public boolean equationsPossible(String[] equations) {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                parent[find(a)] = find(b);
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                if (find(a) == find(b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

}