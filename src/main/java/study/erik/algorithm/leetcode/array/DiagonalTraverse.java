/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : DiagonalTraverse.java, v 0.1 2022年03月02日 9:47 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class DiagonalTraverse {

    @LetCodeCommit(title = "498. Diagonal Traverse",
            selfRemark = "竟然没有怎么调就通过了。"
                    + "确实不是一个好题目，一个细节题。"
                    + "而我也是写了好久，才写成，虽然没有调的麻烦，毕竟写了很久的。")
    public int[] findDiagonalOrder(int[][] mat) {

        int[] ret = new int[mat.length * mat[0].length];
        int i = 0;
        int maxRow = mat.length - 1;
        int maxCol = mat[0].length - 1;

        int[] down = new int[] {1, -1};
        int[] up = new int[] {-1, 1};
        int x = 0;
        int y = 0;
        boolean isUp = true;
        int[] direction;
        do {
            ret[i++] = mat[x][y];
            direction = isUp ? up : down;
            while (x + direction[0] <= maxRow && x + direction[0] >= 0
                    && y + direction[1] <= maxCol && y + direction[1] >= 0) {
                x += direction[0];
                y += direction[1];
                ret[i++] = mat[x][y];
                continue;
            }
            int[] nextPoint = nextPoint(x, y, isUp, maxRow, maxCol);
            if (nextPoint == null) {
                break;
            }
            x = nextPoint[0];
            y = nextPoint[1];
            isUp = !isUp;
        } while (true);
        return ret;
    }

    public int[] nextPoint(int x, int y, boolean clockwise, int maxRow, int maxCol) {
        if (clockwise) {
            if (y < maxCol) {
                return new int[] {x, y + 1};
            }
            if (x < maxRow) {
                return new int[] {x + 1, y};
            }
            return null;
        }
        if (x < maxRow) {
            return new int[] {x + 1, y};
        }
        if (y < maxCol) {
            return new int[] {x, y + 1};
        }
        return null;

    }

    @Parameter
    public int[][] mat;
    @Parameter(1)
    public int[]   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2,3],[4,5,6],[7,8,9]]"),
                        ArrayUtils.buildArray("[1,2,4,7,5,3,6,8,9]")},
                {ArrayUtils.buildArray2Dimension("[[1,2],[3,4]]"),
                        ArrayUtils.buildArray("[1,2,3,4]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }

}