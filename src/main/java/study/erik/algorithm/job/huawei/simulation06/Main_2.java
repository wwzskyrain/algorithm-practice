package study.erik.algorithm.job.huawei.simulation06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/5 15:57
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(selfRemark = "细节题目，一定要沉下心，别怕时间")
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int cell = board[i][j];
                int c = nums(board, i, j);
                if (cell == 0 ) {
                    if( c == 3){
                        //复活
                        board[i][j] = -2;
                    }
                    continue;
                } else if (cell == 1) {
                    if (c < 2 || c > 3) {
                        //死亡
                        board[i][j] = -1;
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int cell = board[i][j];
                if (cell < 0) {
                    if (cell == -2) {
                        board[i][j] = 1;
                    } else if (cell == -1) {
                        board[i][j] = 0;
                    }

                }
            }
        }
    }

    int[][] around = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int nums(int[][] board, int i, int j) {
        //-1 1  = 1
        //0 -2 = 0
        int t = 0;
        for (int k = 0; k < around.length; k++) {
            int nextI = i + around[k][0];
            int nextJ = j + around[k][1];
            if (nextI >= 0 && nextI < board.length && nextJ >= 0 && nextJ < board[nextI].length) {
                int cell = board[nextI][nextJ];
                if (cell == -1 || cell == 1) {
                    t++;
                }
            }
        }
        return t;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray2Dimension("[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]"), ArrayUtils.buildArray2Dimension("[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]"),},
        });
    }

    @Parameterized.Parameter
    public int[][] expect;
    @Parameterized.Parameter(1)
    public int[][] board;


    @Test
    public void test() {
        gameOfLife(board);
        Assert.assertEquals(expect, board);
    }

}
