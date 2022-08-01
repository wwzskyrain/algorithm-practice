package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;
import study.erik.algorithm.util.QuestionType;

/**
 * @author erik.wang
 * @date 2020-08-06 22:24
 */
public class MinimumCostToMergeStonesTest {


    @LetCodeCommit(no = 1000, title = "Minimum Cost to Merge Stones", time = 94, space = 72,
            related = {"Burst Balloons"},
            types = QuestionType.DP,
            selfRemark = "这是区间dp的例子，还可以用三维dp来解答，但是我不太会")
    public int mergeStones(int[] stones, int K) {

        int l = stones.length;
        if ((l - 1) % (K - 1) > 0) {
            return -1;
        }

        int[] prefixSum = new int[l];
        for (int i = 0; i < l; i++) {
            prefixSum[i] = (i == 0 ? 0 : prefixSum[i - 1]) + stones[i];
        }
        //dp[i][j]表示把stone[i]...stone[j]尽可能的合并到小于K堆时的最小花费
        int[][] dp = new int[l][l];
        for (int m = K; m <= l; ++m) {
            for (int i = 0; i + m <= l; ++i) {
                int j = i + m - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int mid = i; mid < j; mid += K - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][mid] + dp[mid + 1][j]);
                }
                //真正的合并
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += prefixSum[j] - prefixSum[i] + stones[i];
                }
            }
        }
        return dp[0][l - 1];

    }

    /**
     * 这是个三维dp的题目，有点难做。我暂时不做哈
     * @param stones
     * @param K
     * @return
     */
    public int mergeStonesI(int[] stones, int K) {

        int length = stones.length;
        if ((length - 1) % (K - 1) != 0) {
            return -1;
        }

//      dp[i][j][k] 表示把stones[i]...stones[j]以每次K个邻接元素合并的节奏合并成k堆的最小花费
//      dp[i][j][k] = min(dp[i][s][t]+dp[s+1][j][k-t], 其中s属于[i,j), t属于[1,k-1]}),
//            可见，dp[i][j][k]本身就是一个二维计算，所以一共五层循环？不
//      dp[i][j][1] = dp[i][j][K] + sum(i,j)
//      result = dp[0][length-1][1]


        int[] prefixSum = new int[length];
        for (int i = 0; i < prefixSum.length; i++) {
            prefixSum[i] = stones[i] + (i == 0 ? 0 : prefixSum[i - 1]);
        }

        int[][][] dp = new int[length][length][K + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                for (int k = 0; k < dp[i][j].length; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        for (int l = K; l < length - K; l++) {
            for (int i = 0; i < length; i++) {

                int j = i + l - 1;

                for (int k = 2; k <= K; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                    for (int s = i; s < j; s++) {
                        if (dp[i][s][k - 1] == Integer.MAX_VALUE || dp[s + 1][j][1] == Integer.MAX_VALUE) {
                            continue;
                        }
                        dp[i][j][k] = Math.min(dp[i][j][k], dp[i][s][k - 1] + dp[s + 1][j][1]);
                    }
                }
                if (dp[i][j][K] == Integer.MAX_VALUE) {
                    continue;
                }
                dp[i][j][1] = dp[i][j][K] + prefixSum[j] - prefixSum[i] + stones[i];
            }
        }
        return dp[0][length - 1][1];
    }


    @Test
    public void test_case_1() {
        Assert.assertEquals(20, mergeStonesI(new int[]{3, 2, 4, 1}, 2));
    }

    @Test
    public void test_case_2() {
        Assert.assertEquals(-1, mergeStonesI(new int[]{3, 2, 4, 1}, 3));
    }


    @Test
    public void test_case_3() {
        Assert.assertEquals(25, mergeStonesI(new int[]{3, 5, 1, 2, 6}, 3));
    }

}
