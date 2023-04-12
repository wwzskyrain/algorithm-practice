/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bfs.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix.java, v 0.1 2023年04月09日 16:16 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {

    @LetCodeCommit(title = "1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix",
            selfRemark = "没什么男的，学到了两点"
                    + "1.位操作——简单"
                    + "2.关于step的for循环写法，关于size的for循环写法，都比while优美")
    public int minFlips(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int v = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                v |= (mat[i][j] << (i * n + j));
            }
        }
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> set = new TreeSet<>();
        q.add(v);
        set.add(v);
        int[][] directions = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}, {0, 0}};

        for (int step = 0; !q.isEmpty(); step++) {
            for (int size = q.size(); size > 0; size--) {
                v = q.poll();
                if (v == 0) {
                    return step;
                }
                for (int x = 0; x < m; x++) {
                    for (int y = 0; y < n; y++) {
                        int vv = v;
                        for (int[] direction : directions) {
                            int xx = x + direction[0];
                            int yy = y + direction[1];
                            if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
                                vv ^= 1 << (xx * n + yy);
                            }
                        }
                        if (set.add(vv)) {
                            q.add(vv);
                        }
                    }
                }
            }
        }

        return -1;
    }

    @Parameter
    public int[][] mat;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[0,0],[0,1]]"), 3},
                {ArrayUtils.buildArray2Dimension("[[0]]"), 0},
                {ArrayUtils.buildArray2Dimension("[[1,0,0],[1,0,0]]"), -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minFlips(mat));
    }

}