/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.palindromic.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : MaximumProductOfTheLengthOfTwoPalindromicSubstrings.java, v 0.1 2023年07月27日 16:02 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumProductOfTheLengthOfTwoPalindromicSubstrings {

    @LetCodeCommit(title = "1960. Maximum Product of the Length of Two Palindromic Substrings",
    selfRemark = "还是不错的。"
            + "1.马拉车的算法复习了一下。"
            + "2.求两个数的乘机最大，没多少技术含量。一般都是遍历两个因子")
    public long maxProduct(String s) {
        long ret = 1;
        int n = s.length();
        int[] l2r = manachers(s, n);
        int[] r2l = manachers(reverse(s), n);

        for (int i = 0, j = n - 2; i < n - 1; i++, j--) {
            ret = Math.max(ret, (long) l2r[i] * (long) r2l[j]);
        }
        return ret;
    }

    public String reverse(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] ret = new char[s.length()];
        while (l <= r) {
            ret[r] = s.charAt(l);
            ret[l] = s.charAt(r);
            l++;
            r--;
        }
        return new String(ret);
    }

    //返回int[], int[i]表示从0到i中，最长的回文子串
    public int[] manachers(String s, int n) {
        int[] m = new int[n];
        int[] l2r = new int[n];
        Arrays.fill(l2r, 1);
        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            //这是马拉松算法：找奇数长度的回文子串
            int k = (i > r) ? 1 : Math.min(m[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < n && s.charAt(i - k) == s.charAt(i + k)) {
                l2r[i + k] = Math.max(l2r[i + k], 2 * k + 1); //这个不属于马拉车算法。记录从0到i+k位置时最长回文子串
                k++;
            }
            m[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }

        for (int i = 1; i < n; i++) {l2r[i] = Math.max(l2r[i], l2r[i - 1]);}
        return l2r;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ababbb", 9},
                {"zaaaxbbby", 9},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxProduct(s));
    }

}