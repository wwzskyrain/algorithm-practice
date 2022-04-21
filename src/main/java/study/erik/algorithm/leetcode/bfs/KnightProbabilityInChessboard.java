/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : KnightProbabilityInChessboard.java, v 0.1 2022年04月21日 8:05 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class KnightProbabilityInChessboard {

    @LetCodeCommit(title = "688. Knight Probability in Chessboard",
            diff = "m",
            selfRemark = "问题很简单，代码也很简单，就是为一个case调了一个多小时，原因竟然是long溢出了。我咋看出来的？"
                    + "在total处打个断点，一看total是负值便知道了。"
                    + ""
                    + "还有dp解法。技术变换的不错的。https://leetcode.com/problems/knight-probability-in-chessboard/discuss/113954/Evolve-from-recursive-to-dpbeats-94"
                    + "看第一个评论区：Tabulation Version")
    public double knightProbability(int n, int k, int row, int column) {
        double total = bfs(n, k, row, column, new HashMap<>());
        return total / (Math.pow(8, k));
    }

    private int[][] directions = new int[][] {{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    public double bfs(int n, int k, int x, int y, Map<String, Double> map) {
        if (k == 0) {
            return 1;
        }
        String key = String.format("%d_%d_%d", k, x, y);
        Double value = map.get(key);
        if (value != null) {
            return value;
        }
        Double ret = 0.0;
        for (int[] direction : directions) {
            int xx = x + direction[0];
            int yy = y + direction[1];
            if (xx >= 0 && xx < n && yy >= 0 && yy < n) {
                ret += bfs(n, k - 1, xx, yy, map);
            }
        }
        map.put(key, ret);
        return ret;
    }

    @Parameter
    public int    n;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public int    row;
    @Parameter(3)
    public int    column;
    @Parameter(4)
    public double expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {8, 30, 6, 4, 0.00019},
                {3, 3, 0, 0, 0.01562},
                {3, 2, 0, 0, 0.0625},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, knightProbability(n, k, row, column), 0.00001);
    }

}