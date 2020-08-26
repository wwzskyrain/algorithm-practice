package study.erik.algorithm.nowcoder.offer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.SwordOfferCommit;


/**
 * @author erik.wang
 * @date 2020-08-26 00:42
 */
public class CutRope {


    @SwordOfferCommit(title = "割绳子", review = "回溯法(dfs实现)会超时。对于这种直接求结果的，大量重复计算，那就是备忘录的战场。" +
            "既然备忘录可以，那么dp也可以了")
    public int cutRope(int target) {

        // dp解法
        int[] dp = new int[target + 1];
        if (target < 4) {
            return target;
        }
        for (int i = 0; i < 4; i++) {
            dp[i] = i;
        }

        for (int i = 4; i < dp.length; i++) {
            for (int j = 2; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[target];
    }

    int max = 0;

    /**
     * 这个dfs是可以用备忘录的，然后就催生出dp方法了
     *
     * @param target
     * @param concurrentV
     */
    public void dfs(int target, int concurrentV) {
        if (target < 2) {
            max = Math.max(max, concurrentV);
        }
        int m = 2;
        while (m <= target) {
            dfs(target - m, concurrentV * m);
            m++;
        }
    }

    @Test
    public void test_solution() {
        Assert.assertEquals(18, cutRope(8));
    }

}
