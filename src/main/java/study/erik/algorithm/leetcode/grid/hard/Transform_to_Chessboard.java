package study.erik.algorithm.leetcode.grid.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/7 ，时间：09:49
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Transform_to_Chessboard {

    @LetCodeCommit(title = "782. Transform to Chessboard",
            selfRemark = "求最小变换操作次数，即'最短距离'，用bfs是最好的。但是这里肯定会超时，而且用一个二维数组当状态是很不容易表示的。" +
                    "所以，这个题目需要观察规律，且不容易观察出来，所以很不适合在面试中出现。" +
                    "但是它确实华为、google的题目，所以还是写一下吧。" +
                    "必须先明白两点" +
                    "1.只能存在两种行，也只能存在两种列；不然免谈。（从必要条件出发的）" +
                    "2.然后，继续再看这个两种行是否01参半（以n为偶数为例），否则免谈。" +
                    "3.最后该计算操作次数了。" +
                    "   首先：行和列是可以独立操作的。" +
                    "   其次：计算一下当前行与标准行（0101、1010）差多远——就是移动步数了。")
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int rowMask = 0, colMask = 0;

        /* 检查棋盘的第一行与第一列 */
        for (int i = 0; i < n; i++) {
            rowMask |= (board[0][i] << i);
            colMask |= (board[i][0] << i);
        }
        int reverseRowMask = ((1 << n) - 1) ^ rowMask;
        int reverseColMask = ((1 << n) - 1) ^ colMask;
        int rowCnt = 0, colCnt = 0;
        for (int i = 0; i < n; i++) {
            int currRowMask = 0;
            int currColMask = 0;
            for (int j = 0; j < n; j++) {
                currRowMask |= (board[i][j] << j);
                currColMask |= (board[j][i] << j);
            }
            /* 检测每一行的状态是否合法 */
            if (currRowMask != rowMask && currRowMask != reverseRowMask) {
                return -1;
            } else if (currRowMask == rowMask) {
                /* 记录与第一行相同的行数 */
                rowCnt++;
            }
            /* 检测每一列的状态是否合法 */
            if (currColMask != colMask && currColMask != reverseColMask) {
                return -1;
            } else if (currColMask == colMask) {
                /* 记录与第一列相同的列数 */
                colCnt++;
            }
        }
        int rowMoves = getMoves(rowMask, rowCnt, n);
        int colMoves = getMoves(colMask, colCnt, n);
        return (rowMoves == -1 || colMoves == -1) ? -1 : (rowMoves + colMoves);
    }

    public int getMoves(int mask, int count, int n) {
        int ones = Integer.bitCount(mask);
        if ((n & 1) == 1) {
            /* 如果 n 为奇数，则每一行中 1 与 0 的数目相差为 1，且满足相邻行交替 */
            if (Math.abs(n - 2 * ones) != 1 || Math.abs(n - 2 * count) != 1) {
                return -1;
            }
            if (ones == (n >> 1)) {
                /* 以 0 为开头的最小交换次数 */
                return n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            } else {
                return (n + 1) / 2 - Integer.bitCount(mask & 0x55555555);
            }
        } else {
            /* 如果 n 为偶数，则每一行中 1 与 0 的数目相等，且满足相邻行交替 */
            if (ones != (n >> 1) || count != (n >> 1)) {
                return -1;
            }
            /* 找到行的最小交换次数：一个1，就表示有两个位子不正，就需要一次移动操作 */
            int count0 = n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            int count1 = n / 2 - Integer.bitCount(mask & 0x55555555);
            return Math.min(count0, count1);
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray2Dimension("[[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]")},
                {0, ArrayUtils.buildArray2Dimension("[[0,1],[1,0]]")},
                {-1, ArrayUtils.buildArray2Dimension("[[1,0],[1,0]]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] board;

    @Test
    public void test() {
        Assert.assertEquals(expect, movesToChessboard(board));
    }

}
