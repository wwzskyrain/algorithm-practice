package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-27 08:27
 */
public class ProfitableSchemes {


    @Test
    public void test_() {

        // 这个case会花上30s的时间，肯定超时了
        int G2 = 100, P2 = 10;
        int[] group2 = new int[]{66, 24, 53, 49, 86, 37, 4, 70, 99, 68, 14, 91, 70, 71, 70, 98, 48, 26, 13, 86, 4, 82, 1, 7, 51, 37, 27, 87, 2, 65, 93, 66, 99, 28, 17, 93, 83, 91, 45, 3, 59, 87, 92, 62, 77, 21, 9, 37, 11, 4, 69, 46, 70, 47, 28, 40, 74, 47, 12, 3, 85, 16, 91, 100, 39, 24, 52, 50, 40, 23, 64, 22, 2, 15, 18, 62, 26, 76, 3, 60, 64, 34, 45, 40, 49, 11, 5, 8, 40, 71, 12, 60, 3, 51, 31, 5, 42, 52, 15, 36};
        int[] profit2 = new int[]{8, 4, 8, 8, 9, 3, 1, 6, 7, 10, 1, 10, 4, 9, 7, 11, 5, 1, 7, 4, 11, 1, 5, 9, 9, 5, 1, 10, 0, 10, 4, 1, 1, 1, 6, 9, 3, 6, 2, 5, 4, 7, 8, 5, 2, 3, 0, 6, 4, 5, 9, 9, 10, 7, 1, 8, 9, 6, 0, 2, 9, 2, 2, 8, 6, 10, 3, 4, 6, 1, 10, 7, 5, 4, 8, 1, 8, 5, 5, 4, 1, 1, 10, 0, 8, 0, 1, 11, 5, 4, 7, 9, 1, 11, 1, 0, 1, 6, 8, 3};
        Assert.assertEquals(188883405, profitableSchemes(G2, P2, group2, profit2));

        int G1 = 10, P1 = 5;
        int[] group1 = {2, 3, 5}, profit1 = {6, 7, 8};
        Assert.assertEquals(7, profitableSchemes(G1, P1, group1, profit1));


        int G = 5, P = 3;
        int[] group = {2, 2}, profit = {2, 3};
        Assert.assertEquals(2, profitableSchemes(G, P, group, profit));


    }

    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        return solutionWithDp(G, P, group, profit);
    }

    /**
     * 解题思路来自于diss：https://leetcode.com/problems/profitable-schemes/discuss/154617/C%2B%2BJavaPython-DP
     * dp[i,j] 表示 j 个member时，收益为(恰好是)i的count数（在当前的{(p1,g1),(p2,g2)...}）时.
     * 改值会随着新的(p,g)结点的加入而重新刷新。
     * <p>
     * 当(p,g)加入时， dp[i+p, j+g] += dp[i,j] 这个状态转移再正常不过了。
     * 当我们完成了遍历的所有的(p,g)之后，也就是最终确定了dp这张表之后，我们只需要统计一下
     * dp[P,G], dp[P+1,G], dp[P+2,G],....,dp[sum(profit),G]；
     * <p>
     * 优化：最后的统计，是可以优化的——不需要在最后的阶段特意统计，而是把统计做到dp跟新的过程中：
     * 当i+p>P(题目要求的最小收益)的时候，没必要更新dp[i+p,j+g]，记录到dp[P,j+g]上就好了，
     * <p>
     * 总结：
     * <p>
     * 1.   0-1背包问题跟遍历解空间有什么关系呢？
     * 我感觉这0-1背包问题的dp解法和遍历解空间是相反的两个方向
     * 遍历解空间是，'挑选了哪几个'=>'花费了多少'=>'收益多少'。
     * 0-1背包呢，是'花费了多少'-->'挑选了哪几个'-->'收益多少'
     * <p>
     * 2.   这里有所谓的01背包的思路吗？
     * 有的，这里的题设就是01背包解题的模型，在有限代价的情况下，指定收益的组合个数
     * 01背包求的是最值，这里求的是指定目标的解的个数，从问法上是一左一右的。
     * 但是在解法上都是一样的，都是有把目标给泛化，而不是只盯着一个目标；泛化后的目标就会有很多的目标
     * t1，t2，t3等等，而这些目标是有状态关系的，有时多是确定关系(不是确定关系，我们也不会正眼瞧它)。
     * 与其说是把目标泛化，不如说是把其中的一个指标泛化。这里有三个关系实体：一个个的(p,g)，总的P，和总的G
     * 好像不只是一个关系泛化，是把三个关系都泛化，(p,g) + P + G ==> 个数(问题) 。哎呀，已经分不清楚
     * 哪个是自变量哪个是因变量了。就这样吧，不久刷题吗，搞这么复杂。
     *
     * @param G
     * @param P
     * @param group
     * @param profit
     * @return
     */
    public int solutionWithDp(int G, int P, int[] group, int[] profit) {

        int[][] dp = new int[P + 1][G + 1];
        dp[0][0] = 1;
        int mod = 1000000007;
        for (int k = 0; k < group.length; k++) {

            int g = group[k];
            int p = profit[k];
            // 记住必须倒着来遍历，这里买一个小关子啊
            for (int i = P; i >= 0; i--) {
                for (int j = G - g; j >= 0; j--) {
                    dp[Math.min(i + p, P)][j + g] =
                            (dp[Math.min(i + p, P)][j + g] + dp[i][j]) % mod;
                }
            }
        }
        int ret = 0;
        for (int c : dp[P]) {
            ret = (ret + c) % mod;
        }
        return ret;
    }


    /**
     * 返回可行解的个数
     * 成绩：超时
     * 这是一个dfs的解法，是遍历解空间的
     *
     * @param G      当前人数
     * @param P      当前差值
     * @param group
     * @param profit
     * @param index  当前待加入的节点
     * @return
     */
    public int solutionWithDfs(int G, int P, int[] group, int[] profit, int index) {
        int MOD = 1000000007;
        if (index == group.length) {
            return P < 1 ? 1 : 0;
        }
        int sum = solutionWithDfs(G, P, group, profit, index + 1) % MOD;
        int g = G - group[index];
        sum += (g < 0) ? 0 : (solutionWithDfs(g, P - profit[index], group, profit, index + 1)) % MOD;
        return sum;
    }
}
