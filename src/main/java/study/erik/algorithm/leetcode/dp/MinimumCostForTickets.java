package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-25 22:59
 */
public class MinimumCostForTickets {

    /**
     * title = Minimum Cost For Tickets
     * url = https://leetcode.com/problems/minimum-cost-for-tickets/
     *
     * @param days
     * @param costs
     * @return
     */
    public int mincostTickets(int[] days, int[] costs) {
        return solution(days, costs);
    }

    /**
     * 成绩：100 和 14
     * 总结：这个题目难道我们了；其实很简单的，那么是为什么难倒我们呢
     * 我们首先用解空间的思想分析题目；
     * 思路分析：首先这可以理解成一个操作题：d1可以买各种价位的票各一张，这样有3中情况（假设只有三种票），
     *  而d2则可以在d1的基础上，有4中情况：买三种票+不买票(如果d2可以被d1的某一种情况覆盖，比如7天票，或者30天的票)
     *  d3，d4 就这样遍历下去，同时保留当前遍历的最值，最总的问题的解。
     * 思路评价：诚然，这是一个方法，但是小题大做，太麻烦，对于medium级别的题目最好不用dfs这种遍历思想
     *
     * 那么用dp思想来分析呢：假设dp[i]表示问题的解，即最小花费；
     * 那么dp[i]是如何得来的呢？这里还需要讨论一个问题，那就是'解空间'中的解
     * 解：能够在满足days都有票的一种买票方法就是一个解。
     *  比如，每天都买一张一天的票，
     *  比如每天都买一张7天的票，而且再接下来的6天都不在买票，
     *  比如。。。。
     * 那么，dp[i]实际上就是一个最优解；最优解是由解空间推出的；而最优解也可以有更小规模的最优解加上在最小估摸最优解的基础上的基本操作——每一种操作就构成一个解。
     * 这些最优解+具体的操作构成的解的最优值就是更大规模的最优解。所以
     * dp[i] = 最优(小规模1最优解+基本操作1，小规模2最优解+基本操作2，小规模3最优解+基本操作3，....，)
     *
     * 具体来说：
     *  基本操作就是三个，买1天票、买7天票、买30天票。但是具体在哪一天买，却又有变化的。
     *  注意一点：dp[i]的那个最优解，有可能刚刚覆盖了第i天，还有可能覆盖过了第i天，无所谓。
     *  太具体了，不写了，要睡觉啊。
     *  其实这写分析也是看到diss区域的，那里还有基于此算法的优化。
     *
     *
     *
     * @param days
     * @param costs
     * @return
     */
    public int solution(int[] days, int[] costs) {

        if (days.length == 0) {
            return 0;
        }

        int[] allDays = new int[days[days.length - 1] + 1];
        boolean[] travel = new boolean[days[days.length - 1] + 1];
        for (int i = 0; i < days.length; i++) {
            travel[days[i]] = true;
        }

        for (int i = 1; i < allDays.length; i++) {
            if (!travel[i]) {
                allDays[i] = allDays[i - 1];
                continue;
            }

            int minCost = allDays[i - 1] + costs[0];
            minCost = Math.min(minCost, (i - 7 > 0 ? allDays[i - 7] : 0) + costs[1]);
            minCost = Math.min(minCost, (i - 30 > 0 ? allDays[i - 30] : 0) + costs[2]);
            allDays[i] = minCost;
        }

        return allDays[days[days.length - 1]];
    }

    @Test
    public void test_() {

        int[] days2={1,3,7}, costs2 ={1,4,20};
        Assert.assertEquals(3, mincostTickets(days2, costs2));

        int[] days1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, costs1 = {2, 7, 15};
        Assert.assertEquals(17, mincostTickets(days1, costs1));
        int[] days = {1, 4, 6, 7, 8, 20}, costs = {2, 7, 15};
        Assert.assertEquals(11, mincostTickets(days, costs));

    }

}
