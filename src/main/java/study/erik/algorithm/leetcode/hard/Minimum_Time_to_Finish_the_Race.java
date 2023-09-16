package study.erik.algorithm.leetcode.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.nowcoder.zijie.Main;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/**
 * 日期：2023/9/16 ，时间：09:20
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Minimum_Time_to_Finish_the_Race {

    @LetCodeCommit(title = "2188. Minimum Time to Finish the Race", selfRemark = "我们")
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        /*
        在自己尝试失败之后，来一个主流解法吧：
        dp[i]表示完成第i圈的最小时间花费。很明显这个定义就是题设——dp[numLaps-1]就表示问题的解。
        那么，dp[i] = min(
            dp[i-1] + minTime[1], //其中minTime[1]表示用所有种类的轮胎跑1圈的最小时间花费
            dp[i-2] + minTime[2], //其中minTime[2]表示用所有种类的轮胎跑2圈的最小时间花费
            ...,
            dp[1] + minTime[i-1], //其中minTime[3]表示用所有种类的轮胎跑i-1圈的最小时间花费
            )
            其中，minTime[j]是一个预处理的数组。定义为使用所有种类的轮胎连续跑j圈的最小时间花费。
            这个dp[i]的定义式，应该不证自明了吧。
        * */

        //minTimes[i], 用一个轮胎连续跑，跑i圈的最小花费。最值比较的集合就是（所有种类的轮胎）
        int[] minTimes = new int[20]; //最多只能跑20圈，其实准确的是17
        Arrays.fill(minTimes, (int) 1e9 + 7);
        for (int i = 0; i < tires.length; i++) {
            int curTime = tires[i][0];
            int span = tires[i][1];
            int firstLap = changeTime + curTime;
            for (int j = 1, sum = firstLap; curTime <= firstLap && j < minTimes.length; j++) {
                //终止条件：curTime <= firstLap，如果当前这一圈比firstLap大，那就没必要跑了，直接换轮胎了。
                //那这样会不会导致较大的j的minTimes[j]没有更新到更小的值，导致在后面计算dp[i]时会有问题。
                //放心，在计算dp[i]式，对于较大j1的minTimes[j1]会被较小的j2的minTimes[j2]给优化掉，表示的物理含义为
                //多换几次轮胎，比用一个轮胎跑j1次更有——这个物理含义好像不用解释。
                minTimes[j] = Math.min(minTimes[j], sum);
                curTime = curTime * span;
                sum += curTime;
            }
        }

        int[] dp = new int[numLaps + 1];
        Arrays.fill(dp, (int) 1e9 + 7);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= Math.min(19, i); j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + minTimes[j]);
            }
        }
        return dp[numLaps] - changeTime;
    }

    /**
     * 第一次尝试，失败了。失败在哪儿呢？
     *
     * @param tires
     * @param changeTime
     * @param numLaps
     * @return
     */
    public int minimumFinishTimeWithFirstTry(int[][] tires, int changeTime, int numLaps) {
        int n = tires.length;
        int[] used = new int[n];
        int[][] dp = new int[numLaps][n];
        int curMin = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            dp[0][j] = tires[j][0];
            curMin = Math.min(curMin, dp[0][j]);
            used[j] = 1;
        }
        for (int i = 1; i < numLaps; i++) {
            int tempMin = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                //换胎，用j
                int cost1 = curMin + changeTime + tires[j][0];

                //不换胎，继续用j
                int time = tires[j][0] * (int) Math.pow(tires[j][1], used[j]);
                int cost2 = dp[i - 1][j] + time;

                if (cost1 > cost2) {
                    //不换胎
                    dp[i][j] = cost2;
                    used[j]++;
                } else {
                    //换胎
                    dp[i][j] = cost1;
                    used[j] = 1;
                }
                tempMin = Math.min(tempMin, dp[i][j]);
            }
            curMin = tempMin;
        }
        return curMin;
    }

    /*
    dp[i][j] 第i圈是在第j个tire完成的最小值。
    dp[i][j] = min( min(dp[i-1][0...n]) + changeTime + 第一次用j, dp[i-1][j] + 继续用j)
    需要有一个辅助数组，记录使用j的次数
    dp[i][j] = //一下n+1项的最小值
        (
        dp[i-1][0] + changeTime + 第一次用j
        dp[i-1][1] + changeTime + 第一次用j
        ...    //不包含 dp[i-1][j]
        dp[i-1][n] + changeTime + 第一次用j

        dp[i-1][j] + changeTime + 换一个j胎  //这一个项可以和上面n-1项合起来计算。

        dp[i-1][j] + 继续用j胎
        )
        前n项可以用 min(dp[i-1][0...n-1])来表示，我们用一个变量来记录上一圈的最小时间花费.
        所以这时候就只有两个数字需要比较：min(dp[i-1][0...n-1]) + changeTime + 第一次用j 、第n+1项

       结论：失败了。是摆在那里呢？
       大概在dp[i]的最有值，不是只来自于dp[i-1]，还有可能来自dp[i-2]/dp[i-3]...吧
    * */

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{21, ArrayUtils.buildArray2Dimension("[[2,3],[3,4]]"), 5, 4}, {25, ArrayUtils.buildArray2Dimension("[[1,10],[2,2],[3,4]]"), 6, 5}, {318, ArrayUtils.buildArray2Dimension("[[36,5],[32,5],[88,8],[11,4],[52,2],[2,2],[90,5],[49,6],[68,9],[77,3],[42,7],[17,3],[73,7],[89,2],[92,9],[40,7],[71,8],[79,3],[55,6],[77,9],[14,3],[87,10],[4,2],[63,7],[79,8],[3,9],[44,2],[49,9],[91,3],[58,6],[62,3],[72,7],[97,6],[29,5],[88,9],[40,8],[36,4],[82,8],[53,8],[26,2],[26,6],[92,2],[46,2],[75,6],[85,2],[6,10],[12,4],[15,4]]"), 24, 27}});
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] tires;
    @Parameterized.Parameter(2)
    public int changeTime;
    @Parameterized.Parameter(3)
    public int numLaps;

    @Test
    public void test() {
        Assert.assertEquals(expect, minimumFinishTime(tires, changeTime, numLaps));
    }

}
