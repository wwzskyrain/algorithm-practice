/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.substring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : ReplaceTheSubstringForBalancedString.java, v 0.1 2022年11月06日 13:36 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1234. Replace the Substring for Balanced String")
public class ReplaceTheSubstringForBalancedString {

    Map<String, Integer> count = new HashMap<>();

    public int balancedString(String s) {
        count = new HashMap<>();
        int n = s.length(), res = n, i = 0, k = n / 4;
        for (int j = 0; j < n; ++j) {
            countOne(s.charAt(j));
        }
        for (int j = 0; j < n; ++j) {
            // 这个题目简单多了，求满足窗口的最小窗口长度
            subtractOne(s.charAt(j));
            while (i < n && get("Q") <= k && get("W") <= k && get("E") <= k && get("R") <= k) {
                res = Math.min(res, j - i + 1);
                countOne(s.charAt(i++));
            }
        }
        return res;
    }

    private void subtractOne(char c) {
        String key = c + "";
        count.put(key, count.getOrDefault(key, 0) - 1);
    }

    private void countOne(char c) {
        String key = c + "";
        count.put(key, count.getOrDefault(key, 0) + 1);
    }

    private int get(String c) {
        return count.getOrDefault(c, 0);
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"QQQW", 2},
                {"QQWE", 1},
                {"QWER", 0},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, balancedString(s));
    }
}