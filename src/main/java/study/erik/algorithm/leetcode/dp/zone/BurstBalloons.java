package study.erik.algorithm.leetcode.dp.zone;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-05-13 17:20
 */
public class BurstBalloons {

    @Test
    public void test_solution_1() {
        Assert.assertEquals(167, maxCoins(new int[]{3, 1, 5, 8}));
    }

    @LetCodeCommit(no = 312, title = "Burst Balloons", postLink = "https://leetcode.com/problems/burst-balloons/discuss/76228/share-some-analysis-and-explanations",
            selfRemark = "虽然花了我很久的时间，但是这个题目不难了，因为它不需要三维，它在合并之后，中间元素就消失了，不像那个Remove-Boxes，合并之后还有参与。但是那个问题用三维dp和记忆术，定义好dp[i][j][k]也不难了。",
            related = {"Max Sum of Rectangle No Larger Than K",
                    "Valid Permutations for DI Sequence"})
    public int maxCoins(int[] nums) {

        int[] numsC = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            numsC[i + 1] = nums[i];
        }

        numsC[0] = numsC[numsC.length - 1] = 1;

        //dp[i][j]表示把(i,j)的元素都点爆，只剩box[i],box[j]之后的最高分
        int[][] dp = new int[numsC.length][numsC.length];

        for (int l = 2; l < numsC.length; l++) {
            for (int i = 0; i < numsC.length; i++) {
                int j = i + l;
                if (j < numsC.length) {
                    for (int m = i + 1; m < j; m++) {
                        dp[i][j] = Math.max(dp[i][j],
                                numsC[i] * numsC[m] * numsC[j] + dp[i][m] + dp[m][j]);
                    }
                }
            }
        }

        return dp[0][numsC.length - 1];


    }


}
