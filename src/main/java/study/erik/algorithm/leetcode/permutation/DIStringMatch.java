/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.permutation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : DIStringMatch.java, v 0.1 2022年11月12日 19:29 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class DIStringMatch {

    @LetCodeCommit(title = "942. DI String Match")
    public int[] diStringMatch(String s) {
        int n = s.length();
        int low = 0, hi = n;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                res[i] = low++;
            } else {
                res[i] = hi--;
            }
        }
        res[n] = low;
        return res;
    }

    @Parameter
    public String s;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"IDID"},
                {"III"},
                {"DDI"}
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(diStringMatch(s)));
    }
}