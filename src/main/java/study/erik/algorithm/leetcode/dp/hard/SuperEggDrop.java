/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : SuperEggDrop.java, v 0.1 2022年06月05日 17:22 yueyi Exp $
 */
public class SuperEggDrop {
    @LetCodeCommit(title = "887. Super Egg Drop", diff = "h", selfRemark = "这真是tm的一个好题，当然是看到了三个解法之后的盛赞. —— 看到光秃秃的代码，我真想揍你两顿，没办法，重新思考吧" + "1.不担心K个几点不够用吗？" + "2.题意是什么？确定最坏情况下的最有解？" + "这个解法是花花酱这个的解法。下面对它进行注释" + "整体思路：这里需要明白题意首先。" + "比如第一个问题，鸡蛋不会不够的，因为鸡蛋至少有一个，那么就一次从1、2、3...、n来扔鸡蛋，假设到4层碎了，那么f就是3层。" + "当然这只是f的一个特例，我们需要考虑最坏的情况，比如f是n，那么只有一个鸡蛋的我们就需要扔20次。" + "继续分析，见demo")
    public int superEggDrop(int K, int N) {
        /*
        k = 2, n = 6
        第一个鸡蛋，分别可以从1，2，3，4，5，6扔下，既分6中情况讨论。取六个结果的最小值
        假设从i层扔下，这里有份两种情况：
        case1：i层碎了，则问题就变成（k=1，n=[1,2,3...,i-1]）
        case2：i层没有碎，则第二个几点需要测试从i到6，而测试i->6与测试1->6-i是同质的，则问题变成（k=1，n=6-i])
        这两种情况都可能，我们需要考虑这两种情况的最坏值，也就是最大值。
        至此，递归公式已经出来了。
        设：dp[k,n]表示解
        dp(k,n) = 1 + min{max{dp(k-1,i), dp[k,n-i] | 1<=i<=n}
        除此之外，即使再使用备忘录，该解法也是超时。
        还需要一个优化方法，那就是快速找到 min{max{dp(k-1,i), dp(k,n-i)} | 1<=i<=n}
        优化思路：这里面有n中情况需要考虑，所以优化很必要。
        这里面 max{dp(k-1,i), dp(k,n-i)} 的两个值关于i分别是增函烁和减函数，所以，用二分查找找到他们相交的地方，
        就找到了他们两个值最大值的最小值。
        * */
        return dp(K, N);
    }

    Map<Integer, Integer> memo = new HashMap();

    public int dp(int K, int N) {
        //dp[i][j]表示，i个鸡蛋，j层楼的解。
        if (!memo.containsKey(N * 100 + K)) {
            int ans;
            if (N == 0) {
                ans = 0;
            } else if (K == 1) {
                ans = N;
            } else {
                int lo = 1, hi = N;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    //t1是增函数，开始比较小
                    int t1 = dp(K - 1, x - 1);
                    //t2是减函数，开始比较大
                    int t2 = dp(K, N - x);

                    if (t1 < t2) {
                        //符合预期，继续增加测试值
                        lo = x;
                    } else if (t1 > t2) {
                        hi = x;
                    } else {
                        lo = hi = x;
                    }
                }
                //在lo和hi两者之间试探
                ans = 1 + Math.min(Math.max(dp(K - 1, lo - 1), dp(K, N - lo)), Math.max(dp(K - 1, hi - 1), dp(K, N - hi)));
            }
            memo.put(N * 100 + K, ans);
        }

        return memo.get(N * 100 + K);
    }

    /**
     * lee215的解法。哎，用单田芳大师的话说：不服高人有罪
     *
     * @param K
     * @param N
     * @return
     */
    public int superEggDropWithLee215(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        int m = 0;
        /*
        定义dp[m][k]是k个鸡蛋扔m次，最大可以支持多少个楼

        dp[m][k] = dp[m-1][k-1] + dp[m-1][k] + 1
        表示：一个"扔"，如果鸡蛋碎了，则我们可以确定dp[m-1][k-1]层楼。
        如果鸡蛋没有碎，则可以确定dp[m-1][k]层楼；
        最后加上当前层。
        * */
        while (dp[m][K] < N) {
            ++m;
            for (int k = 1; k <= K; ++k)
                dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1;
        }
        return m;
    }


}