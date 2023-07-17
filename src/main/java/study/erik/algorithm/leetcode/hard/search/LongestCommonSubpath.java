/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.search;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : LongestCommonSubpath.java, v 0.1 2023年07月13日 07:04 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1923. Longest Common Subpath")
public class LongestCommonSubpath {

    public int longestCommonSubpathWithCpp(int n, int[][] paths) {
        int minLength = Integer.MAX_VALUE;
        for (int[] path : paths) {
            minLength = Math.min(minLength, path.length);
        }
        int l = 0;
        int r = minLength; //不错的。
        long base = 100001, mod = 100000000019L;
        while (l < r) {
            // 注意：这里m要向上取，可以考虑l+1=r的情况，假设这个时候l是正解，这时候刚好返回l
            int m = (l + r + 1) / 2;
            Set<Long> hashSet = new HashSet<>();

            for (int i = 0; i < paths.length && (i == 0 || !hashSet.isEmpty()); ++i) {
                long hash = 0, d = 1;
                //保存path[i]中的m长度子串，这些子串需在前path[i]的m长度子串中出现过。
                Set<Long> hs1 = new HashSet<>();
                for (int j = 0; j < paths[i].length; ++j) {
                    //找长度为m的子串
                    hash = (hash * base + paths[i][j]) % mod;
                    if (j >= m) {
                        //注意：这里'-d*paths[i][j - m]'时，需要做好MOD计算
                        hash = (mod + hash - d * paths[i][j - m] % mod) % mod;
                    } else {
                        d = d * base % mod;
                    }
                    if (j >= m - 1 && (i == 0 || hashSet.contains(hash))) {
                        hs1.add(hash);
                    }
                }
                hashSet = hs1;
            }
            if (hashSet.isEmpty()) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        return l;
    }

    @Parameter
    public int     n;
    @Parameter(1)
    public int[][] paths;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {10, ArrayUtils.buildArray2Dimension(
                        "[[1,7,0,6,9,0,7,4,3,9,1,5,0,8,0,6,3,6,0,8,3,7,8,3,5,3,7,4,0,6,8,1,4],[1,7,0,6,9,0,7,4,3,9,1,5,0,8,0,6,3,6,0,8,3,"
                                + "7,8,3,5,3,7,4,0,6,8,1,5],[8,1,7,0,6,9,0,7,4,3,9,1,5,0,8,0,6,3,6,0,8,3,7,8,3,5,3,7,4,0,6,8,1]]"),
                        32},
                {10, ArrayUtils.buildArray2Dimension("[[2,1,4,0,3],[2,1,4,0,3]]"), 5},
                {5, ArrayUtils.buildArray2Dimension("[[0,1,2,3,4],\n"
                        + "                       [2,3,4],\n"
                        + "                       [4,0,1,2,3]]"), 2},
                {3, ArrayUtils.buildArray2Dimension("[[0],[1],[2]]"), 0},
                {5, ArrayUtils.buildArray2Dimension("[[0,1,2,3,4],\n"
                        + "                       [4,3,2,1,0]]"), 1},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestCommonSubpathWithCpp(n, paths));
    }

}