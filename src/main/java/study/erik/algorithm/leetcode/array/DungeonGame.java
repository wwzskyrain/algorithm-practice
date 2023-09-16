/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DungeonGame.java, v 0.1 2022年01月03日 8:31 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class DungeonGame {

    @LetCodeCommit(title = "174. Dungeon Game",
            diff = "h",
            selfRemark = "这个题目不难呀，为啥我之前竟然有6次提交失败。"
                    + "不过这个题目的原意就是我么小时候玩的小霸王游戏。" +
                    "洋洋洒洒写完了，一点注释都没有，搞的我这会不能一下子搞懂，真是很low。")
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 1 && dungeon[0].length == 1) {
            return Math.max(1, 1 - dungeon[0][0]);
        }
        int i = dungeon.length - 1;
        int j = dungeon[i].length - 1;
        dungeon[i][j] = dungeon[i][j] > 0 ? 1 : 1 - dungeon[i][j];
        /*
        定义dungeon[i][j]为到达从[i][j]房间到达右下角的最小生命值——和原问题定义一样。
        * */

        j--;
        while (j >= 0) {
            //最后一行
            dungeon[i][j] = Math.max(1, dungeon[i][j + 1] - dungeon[i][j]);
            j--;
        }
        i = dungeon.length - 2;
        j = dungeon[0].length - 1;
        while (i >= 0) {
            //最后一列
            dungeon[i][j] = Math.max(1, dungeon[i + 1][j] - dungeon[i][j]);
            i--;
        }
        i = dungeon.length - 2;
        while (i >= 0) {
            j = dungeon[i].length - 2;
            while (j >= 0) {
                int min = Math.min(dungeon[i + 1][j], dungeon[i][j + 1]);
                dungeon[i][j] = Math.max(1, min - dungeon[i][j]);
                j--;
            }
            i--;
        }

        return dungeon[0][0];
    }

    @Parameter
    public int[][] dungeon;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[100]]"), 1},
                {ArrayUtils.buildArray2Dimension("[[-2,-3,3],[-5,-10,1],[10,30,-5]]"), 7},
                {ArrayUtils.buildArray2Dimension("[[0]]"), 1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, calculateMinimumHP(dungeon));
    }

}