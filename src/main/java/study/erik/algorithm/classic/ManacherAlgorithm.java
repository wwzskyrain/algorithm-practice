/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.classic;

/**
 * @author yueyi
 * @version : ManacherAlgorithm.java, v 0.1 2023年07月27日 16:26 yueyi Exp $
 * 介绍马拉车算法求s的回文子串
 */
public class ManacherAlgorithm {

    public int maxPalindromicSubstring(String s) {
        return 0;
    }

    public int maxOddPalindromicSubstring(String s) {
        int n = s.length();
        s = "$" + s + "^";
        int[] p = new int[n + 2];
        int l = 1, r = 1;
        int max = 0;
        for (int i = 1; i <= n; i++) {
            p[i] = Math.max(0, Math.min(r - i, p[l + (r - i)]));
            while (s.charAt(i - p[i]) == s.charAt(i + p[i])) {
                p[i]++;
                int length = p[i];
                int finalLength = length * 2 - 1;
                max = Math.max(max, finalLength);
            }
            if (i + p[i] > r) {
                l = i - p[i];
                r = i + p[i];
            }
        }
        return max;
    }

}