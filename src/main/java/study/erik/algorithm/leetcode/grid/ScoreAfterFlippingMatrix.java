/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ScoreAfterFlippingMatrix.java, v 0.1 2022年05月26日 08:07 yueyi Exp $
 */
public class ScoreAfterFlippingMatrix {

    @LetCodeCommit(title = "861. Score After Flipping Matrix",
            diff = "m",
            selfRemark = "貌似最值题，其实计算题")
    public int matrixScore(int[][] grid) {

        int M = grid.length, N = grid[0].length;
        int res = (1 << N - 1) * M; // 第一列全部都是1
        for (int j = 1; j < N; j++) {
            //从第2列开始算.
            int cur = 0;
            for (int i = 0; i < M; i++) {
                cur += grid[i][j] == grid[i][0] ? 1 : 0;
            }
            res += Math.max(cur, M - cur) * (1 << (N - j - 1));
        }
        return res;
    }
}