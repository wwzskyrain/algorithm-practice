/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.bitmask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : TheEarliestAndLatestRoundsWherePlayersCompete.java, v 0.1 2023年07月10日 07:40 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class TheEarliestAndLatestRoundsWherePlayersCompete {

    @LetCodeCommit(title = "1900. The Earliest and Latest Rounds Where Players Compete")
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        dfs((1 << n) - 1, 1, 0, 27, firstPlayer - 1, secondPlayer - 1);
        return new int[] {min_r, max_r};
    }

    int min_r = Integer.MAX_VALUE, max_r = Integer.MIN_VALUE;

    /**
     * 模拟玩家low(0-based)和玩家high(0-based)进行比赛。
     *
     * @param mask
     * @param round
     * @param low
     * @param high
     * @param targetFirstPlayer
     * @param targetSecondPlayer
     */
    void dfs(int mask, int round, int low, int high, int targetFirstPlayer, int targetSecondPlayer) {
        if (low >= high) {
            //进入下一轮
            dfs(mask, round + 1, 0, 27, targetFirstPlayer, targetSecondPlayer);
        } else if ((mask & (1 << low)) == 0) {
            dfs(mask, round, low + 1, high, targetFirstPlayer, targetSecondPlayer);
        } else if ((mask & (1 << high)) == 0) {
            dfs(mask, round, low, high - 1, targetFirstPlayer, targetSecondPlayer);
        } else if (low == targetFirstPlayer && high == targetSecondPlayer) {
            min_r = Math.min(min_r, round);
            max_r = Math.max(max_r, round);
        } else {
            if (low != targetFirstPlayer && low != targetSecondPlayer) {
                //玩家i(0-based)败北
                dfs(mask ^ (1 << low), round, low + 1, high - 1, targetFirstPlayer, targetSecondPlayer);
            }
            if (high != targetFirstPlayer && high != targetSecondPlayer) {
                //玩家j(0-based)败北
                dfs(mask ^ (1 << high), round, low + 1, high - 1, targetFirstPlayer, targetSecondPlayer);
            }
        }
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int firstPlayer;
    @Parameter(2)
    public int secondPlayer;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {11, 2, 4},
                //{5, 1, 5},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(earliestAndLatest(n, firstPlayer, secondPlayer)));
    }
}