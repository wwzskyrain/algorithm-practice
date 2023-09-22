package study.erik.algorithm.ds.advance.dp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/22 ，时间：09:23
 * 作者：yueyi
 * 描述：
 */

/**
 * 数位dp1，用数组预处理法。还有一个是dfs+记忆。
 */
@RunWith(Parameterized.class)
public class Digital_Dp_1 {

    /**
     * 求在区间[0,num]中不包含4的数的个数
     *
     * @param num
     * @return
     */
    public int testMethod(int num) {
        init();
        return solve(num) + 1;
    }

    int solve(int num) {
        int len = 0;
        int[] digit = new int[LEN];
        while (num > 0) {
            digit[++len] = num % 10;
            num /= 10;
        }
        int ans = 0;
        for (int i = len; i > 0; i--) {
            //i位数，从最高位遍历
            for (int j = 0; j < digit[i]; j++) {
                //从0到digit[i]
                if (j != 4) {
                    //为啥要特殊处理呢？在初始化dp的时候已经处理过啦呀。
                    ans += dp[i][j];
                }
            }
            // 如果当前位的目标数字就是4，那就结束了。比如432，当计算3位数时，也就是
            // 百位数字需要计算1xx、2xx、3xx，不能计算4xx了，这时候也就结束了。整体都结束了。
            // 不过注意一点，ans要减1，为啥？
            if (digit[i] == 4) {
                ans--;
                break;
            }
        }
        return ans;
    }

    int LEN = 12; //只支持11位数字
    int[][] dp = new int[LEN][12];

    void init() {
        //dp[i][j]表示i位数，第一个数字式j时，符合条件的数字数量，
        // 则dp[i][j] = sum(dp[i-1][0],
        // dp[i-1][1],
        // dp[i-1][2],
        // dp[i-1][3],
        // dp[i-1][5],
        // dp[i-1][6],
        // dp[i-1][7],
        // dp[i-1][8],
        // dp[i-1][9],) 一共9项，因为不包函j=4

        dp[0][0] = 1;
        for (int i = 1; i <= dp.length; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (j != 4) {
                        dp[i][j] += dp[i - 1][k];
                    }
                }
            }
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, 2, ArrayUtils.buildArray2Dimension("[[1,1],[2,1],[1,2],[2,2]]"), 2},
                });
    }



}
