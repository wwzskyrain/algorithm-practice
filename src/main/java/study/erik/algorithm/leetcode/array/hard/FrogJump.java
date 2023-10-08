/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : FrogJump.java, v 0.1 2022年02月12日 6:08 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FrogJump {

    @LetCodeCommit(title = "403. Frog Jump",
            selfRemark = "写了一个优先级队列的解法，超时，因为重复解太多了。"
                    + "最后还是用二位dp比较好。" +
                    "重新思考：首先用dfs搜索就能解答，然后加备忘录来提速，" +
                    "最后就是转化为动态规划。不过本例解法，dp的定义却并不是解，而是稍微变换了。")
    public boolean canCross(int[] stones) {
        int length = stones.length;
        boolean[][] dp = new boolean[length][length + 1];
        // dp[i][j] 表示i_stone可以跳跃j个单元。
        // 如果i_stone能条j个单元，根据题意，它也能条j+1、j-1个单元。
        // 那么，'如果'如何成立呢？就是存在至少一个i之前的石头pre_i，可以跳distance(pre_i, i).
        dp[0][1] = true;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < i; j++) {
                int diff = stones[i] - stones[j];
                if (diff < 0 || diff > length || !dp[j][diff]) {
                    continue;
                }
                //从j跳到了i，所以dp[i][diff]就是true了。
                dp[i][diff] = true;
                if (diff - 1 >= 0) {
                    dp[i][diff - 1] = true;
                }
                if (diff + 1 <= length) {
                    dp[i][diff + 1] = true;
                }
                if (i == length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 模拟跳跃，更直观。
     * @param stones
     * @return
     */
    public boolean canCrossSolution(int[] stones) {
        if (stones.length == 0) {
            return true;
        }

        //k=石头的坐标 v=其可以跳跃的步数的集合
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>(stones.length);
        map.put(0, new HashSet<>());
        map.get(0).add(1);

        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], new HashSet<>() );
        }

        for (int i = 0; i < stones.length - 1; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                // 在当前石头的基础上能跳跃到的点——落脚点
                int reach = step + stone;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                HashSet<Integer> set = map.get(reach);
                if (set != null) {//落脚点必须在石头上。
                    set.add(step);
                    if (step - 1 > 0) set.add(step - 1);
                    set.add(step + 1);
                }
            }
        }

        return false;
    }

    public boolean canCross1(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int unit : stones) {
            map.put(unit, new HashSet<>());
        }
        Set<Integer> zoneUnitJump = map.get(0);
        if (zoneUnitJump == null) {
            return false;
        }
        zoneUnitJump.add(1);
        for (int unit : stones) {
            Set<Integer> skipSet = map.get(unit);
            for (Integer skip : skipSet) {
                int nextUnit = unit + skip;
                if (nextUnit == stones[stones.length - 1]) {
                    return true;
                }
                Set<Integer> nextUnitSkip = map.get(unit + skip);
                if (nextUnitSkip != null) {
                    nextUnitSkip.add(skip);
                    if (skip - 1 >= 0) {
                        nextUnitSkip.add(skip - 1);
                    }
                    if (skip + 1 <= stones.length) {
                        nextUnitSkip.add(skip + 1);
                    }
                }// 【优点】else 说明该nextUnit处不是石头，落水了，就不管了
            }
        }
        return false;

    }

    @Parameter
    public int[] stones;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
//                {ArrayUtils.buildArray("[0,2]"), false},
//                {ArrayUtils.buildArray("[0,1,3,6,7]"), false},
{ArrayUtils.buildArray("[0,1,3,5,6,8,12,17]"), true},
{ArrayUtils.buildArray("[0,1,2,3,4,8,9,11]"), false},
};
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, canCross1(stones));
    }

}