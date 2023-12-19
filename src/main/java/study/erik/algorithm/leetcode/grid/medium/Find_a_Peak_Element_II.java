package study.erik.algorithm.leetcode.grid.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.FavoriteLevel;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/19 23:38
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Find_a_Peak_Element_II {

    @LetCodeCommit(title = "1901. Find a Peak Element II",
            favorite = FavoriteLevel.ONE_STAR,
            selfRemark = "没有意思，题意和解法太明显不过了。" +
                    "顺着高的往上爬就可以了。" +
                    "浪费时间")
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int i = 0;
        int j = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        while (true) {
            int largerNum = 0; //统计比[i,j]更大的数字。
            int nextI = -1;
            int nextJ = -1;
            for (int[] dir : dirs) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                if (ii < m && ii >= 0 && jj < n && jj >= 0) {
                    if (mat[ii][jj] > mat[i][j]) {
                        largerNum++;
                        nextI = ii;
                        nextJ = jj;
                    }
                }
            }
            if (largerNum == 0) {
                return new int[]{i, j};
            } else {
                i = nextI;
                j = nextJ;
            }
        }
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[0,1]"), ArrayUtils.buildArray2Dimension("[[1,4],[3,2]]")},
                {ArrayUtils.buildArray("[1,1]"), ArrayUtils.buildArray2Dimension("[[10,20,15],[21,30,14],[7,16,32]]")},
        });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[][] mat;

    @Test
    public void test() {
        Assert.assertArrayEquals(expect, findPeakGrid(mat));
    }

}
