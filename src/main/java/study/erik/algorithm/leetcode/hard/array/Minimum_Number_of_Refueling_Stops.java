package study.erik.algorithm.leetcode.hard.array;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.PriorityQueue;

/**
 * 日期：2023/10/7 ，时间：14:17
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Minimum_Number_of_Refueling_Stops {

    @LetCodeCommit(title = "871. Minimum Number of Refueling Stops",
            selfRemark = "lee215的解法，很巧妙。思路很清晰，就是有一个细节不好理解。" +
                    "如果这个解法的具体解法不好理解，可以看一下下面的谈心算法")
    public int minRefuelStops(int target, int startFuel, int[][] s) {
        // dp[t] means the furthest distance that we can get with t times of refueling.
        long[] dp = new long[s.length + 1];
        dp[0] = startFuel;
        for (int i = 0; i < s.length; ++i) {
            // t = i ? 为什么。
            for (int t = i; t >= 0 && dp[t] >= s[i][0]; --t) {
                //更新能够驾驶到第i个加油站的dp[t]们？不对不对，感觉上奇怪的就在于这了。
                //不是更新他们，而是他们要贡献给下一个最大距离dp[t+1]————
                //dp[t]已经覆盖i站点了，该站点的加入，是否能让那么dp[t+1]更远呢？下一跳能否更远呢？
                long newLong = dp[t] + s[i][1];
                dp[t + 1] = Math.max(dp[t + 1], newLong);
            }
        }
        for (int t = 0; t <= s.length; ++t) {
            if (dp[t] >= target) return t;
        }
        return -1;
    }

    /**
     * 这是一个贪心算法，但是思路很直观很清晰：
     * 一辆汽车将路上遇到的汽油，都放在自己的后备箱中，没油的时候，取最大的一桶加上，直到到达终点，问用了几桶油。
     *
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public int minRefuelStopsSolutionWithGreedy(int target, int startFuel, int[][] stations) {

        if (startFuel >= target) {
            return 0;
        }

        int n = stations.length, prev = 0;
        int fuel = startFuel, ans = 0;
        // fuel是汽车当前的油量
        PriorityQueue<Integer> p = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < n; i++) {
            int cost = stations[i][0] - prev;     //从上一位置走到当前位置需要的油量
            fuel = fuel - cost;

            //如果油量不够，需要加油，完成这段路
            while (fuel < 0 && !p.isEmpty()) {
                fuel += p.poll();
                ans++;
            }
            if (fuel < 0) return -1;   //加完之后还是不够
            //将新的加油站的油放在车上
            p.offer(stations[i][1]);
            //更新prev的值
            prev = stations[i][0];
        }
        //计算最后一个加油站到达target的油的用量
        fuel = fuel - (target - prev);
        while (fuel < 0 && !p.isEmpty()) {
            fuel += p.poll();
            ans++;
        }
        if (fuel < 0) return -1;


        return ans;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//                {0, 1, 1, ArrayUtils.buildArray2Dimension("[]")},
{2, 100, 10, ArrayUtils.buildArray2Dimension("[[10,60],[20,30],[30,30],[60,40]]")},
{-1, 100, 1, ArrayUtils.buildArray2Dimension("[[10,100]]")},
});
    }

    // target = 1, startFuel = 1, stations = []
    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int target;
    @Parameterized.Parameter(2)
    public int startFuel;
    @Parameterized.Parameter(3)
    public int[][] stations;

    @Test
    public void test() {
        Assert.assertEquals(expect, minRefuelStops(target, startFuel, stations));
    }

}
