package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-21 17:42
 */
public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
       return 0;
    }

    /**
     * 这是我第一个版本，也是一个很失败的解法
     * 1.   为甚么我会思考双值发？双值中的局部最值就是全局最值吗，傻蛋
     * 2.   路径方向怎么会弄反呢？傻蛋，有时候正向麻烦就反向吗
     * 3.   我这是dfs的想法，即遍历解空间的做法，明显不是dp的解法
     * 4.   看diss区吧，前两个都是好解法
     *      1.  从底向上的dp
     *      2.  一个是从上往下的dp + 备忘录
     *      3.  他们两个都是dp，只是实现方式不同而已
     * @param dungeon
     * @return
     */
    public int solution(int[][] dungeon) {

        if (dungeon.length == 1 && dungeon[0].length == 1) {
            return dungeon[0][0] > 0 ? 1 : 1 - dungeon[0][0];
        }
        int[][][] dp = new int[dungeon.length][dungeon[0].length][2];
        int topLeft = dungeon[0][0];
        if (topLeft > 0) {
            dp[0][0] = new int[]{1, topLeft + 1};
        } else {
            dp[0][0] = new int[]{1 - topLeft, 1};
        }

        for (int i = 1; i < dp.length; i++) {
            int remain = dp[i - 1][0][1] + dungeon[i][0];
            if (remain > 0) {
                dp[i][0] = new int[]{dp[i - 1][0][0], remain};
            } else {
                dp[i][0] = new int[]{dp[i - 1][0][0] + 1 - remain, 1};
            }
        }

        for (int j = 1; j < dp[0].length; j++) {
            int remain = dp[0][j - 1][1] + dungeon[0][j];
            if (remain > 0) {
                dp[0][j] = new int[]{dp[0][j - 1][0], remain};
            } else {
                dp[0][j] = new int[]{dp[0][j - 1][0] + 1 - remain, 1};
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                int remain = dp[i - 1][j][1] + dungeon[i][j];
                int init1, remain1;
                if (remain > 0) {
                    init1 = dp[i - 1][j][0];
                    remain1 = remain;
                } else {
                    init1 = dp[i - 1][j][0] + 1 - remain;
                    remain1 = 1;
                }

                int init2, remain2;
                remain = dp[i][j - 1][1] + dungeon[i][j];
                if (remain > 0) {
                    init2 = dp[i][j - 1][0];
                    remain2 = remain;
                } else {
                    init2 = dp[i][j - 1][0] + 1 - remain;
                    remain2 = 1;
                }
                dp[i][j] = init1 < init2 ? new int[]{init1, remain1} : new int[]{init2, remain2};
            }
        }

        return dp[dungeon.length - 1][dungeon[dungeon.length - 1].length - 1][0];
    }

    @Test
    public void test_() {

        int[][] dungeon4 = {{1, -3, 3}, {0, -2, 0}, {-3, -3, -3}};
        Assert.assertEquals(3, calculateMinimumHP(dungeon4));

        int[][] dungeon3 = {{2, 1}};
        Assert.assertEquals(1, calculateMinimumHP(dungeon3));

        int[][] dungeon2 = {{0, 0}};
        Assert.assertEquals(1, calculateMinimumHP(dungeon2));

        int[][] dungeon1 = {{100}};
        Assert.assertEquals(1, calculateMinimumHP(dungeon1));

        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        Assert.assertEquals(7, calculateMinimumHP(dungeon));
    }

//    public static int[][] convert(String input){
//        input = input.substring(1,input.length() - 1);
//        for (String strArr : input.split("],\\[")) {
//            strArr.substring(1, strArr.length() - 1).split(",");
//        }
//    }

}
