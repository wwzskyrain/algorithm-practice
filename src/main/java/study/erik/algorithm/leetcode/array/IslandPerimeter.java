/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : IslandPerimeter.java, v 0.1 2022年02月27日 4:57 下午 yueyi Exp $
 */
public class IslandPerimeter {

    @LetCodeCommit(title = "463. Island Perimeter",
            diff = "e",
            selfRemark = "easy题目，无话可说")
    public int islandPerimeter(int[][] grid) {
        int[][] directions = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int row = grid.length;
        int col = grid[0].length;
        int ret = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    for (int[] direction : directions) {
                        int x = i + direction[0];
                        int y = j + direction[1];
                        if (x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == 1) {
                            continue;
                        }
                        ret++;
                    }
                }
            }
        }
        return ret;

    }

}