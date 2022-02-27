/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : AssignCookies.java, v 0.1 2022年02月26日 9:36 下午 yueyi Exp $
 */
public class AssignCookies {

    @LetCodeCommit(title = "455. Assign Cookies")
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = g.length - 1;
        int sIndex = s.length - 1;
        int ret = 0;
        while (sIndex >= 0 && gIndex >= 0) {
            if (s[sIndex] >= g[gIndex]) {
                ret++;
                sIndex--;
            }
            gIndex--;
        }
        return ret;

    }

}