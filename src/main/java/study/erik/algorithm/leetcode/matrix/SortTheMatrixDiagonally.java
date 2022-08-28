/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.matrix;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : SortTheMatrixDiagonally.java, v 0.1 2022年08月28日 20:58 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SortTheMatrixDiagonally {

    @LetCodeCommit(title = "1329. Sort the Matrix Diagonally",
            selfRemark = "斜着的line用直线公式即可")
    public int[][] diagonalSort(int[][] mat) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(j - i, new PriorityQueue<>());
                map.get(j - i).add(mat[i][j]);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = map.get(j - i).poll();
            }
        }
        return mat;
    }

}