package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/3/31 08:44
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Minimum_Number_of_Coins_to_be_Added {

    @LetCodeCommit(title = "2952. Minimum Number of Coins to be Added")
    public int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int n = coins.length;
        int c = 0;
        int next = 1;
        int i = 0;
        while (next <= target) {
            if (i < n && coins[i] <= next) {
                next += coins[i];
                i++;
            } else {
                next *= 2; // 加了一个next
                c++;
            }
        }
        return c;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray("1,4,10"), 19},
                {1, ArrayUtils.buildArray("1,4,10,5,7,19"), 19},
                {3, ArrayUtils.buildArray("1,1,1"), 20}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] coins;
    @Parameterized.Parameter(2)
    public int target;

    @Test
    public void test() {
        Assert.assertEquals(expect, minimumAddedCoins(coins, target));
    }

}
