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
 * 日期：2023/9/22 ，时间：10:02
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Digital_Dp_2 {

    /**
     * 求在区间[0,num]中不包含4的数的个数。
     * 看不太懂，先搁置吧。
     * 等等，有看懂了
     *
     * @param n
     * @return
     */
    public int testMethod(int n) {
        int len = 0;
        Arrays.fill(dp, -1);
        while (n > 0) {
            digit[++len] = n % 10;
            n /= 10;
        }
        return dfs(len, true);
    }

    int LEN = 12;
    int[] dp = new int[LEN];
    int[] digit = new int[LEN];

    /**
     * 首先解释最难得点isMax。
     * 但是在解释isMax之前，再次声明下，这是dfs+备忘录的套路。
     * 然后，再重申dp[i]表示i位数上符合定义的(这里就是不包含4)数字的个数，比如i=2时，表示在[00-99]这100个数字中会有多少个符合定义的数字。
     * OK，isMax表示什么呢？解释不明白，直接举例吧。
     * 比如 计算 324 。
     * 第一次dfs(len=3, isMax=true)，这里表示计算的是三位，而且是最大值。
     * 好，在这个dfs调用中，需要调用dfs(len-1)，
     * 第一次调用时i=0，计算0(xx)，表示以0开头的两位数中有多少符合定义的，也就是dp[2]，dfs(2,isMax = false)
     * 因为isMax=false，所以在调用dfs(1)时就要从0计算到9，而且会设置dp[2]=（真实值）
     * 第二次调用是i=1，计算1(xx)，表示以1开头的两位数中有多少符合定义的，也就是dp[2]，dfs(2,isMax = false)
     * 因为isMax=false，而且dp[2]已经被计算出来了，所以直接返回
     * 第二次调用是i=2，计算2(xx)，表示以2开头的两位数中有多少符合定义的，也就是dp[2]，dfs(2,isMax = false)
     * 因为isMax=false，而且dp[2]已经被计算出来了，所以直接返回
     * 第二次调用是i=3，计算3(xx)，表示以3开头的两位数中有多少符合定义的，也就是dp[2]，dfs(2,isMax = true) ，注意这时候isMax是true了哈。
     * 因为isMax=true，所以不能用dp[2]，因为我们这里不会计算所有的二位数。
     *
     * @param len
     * @param isMax
     * @return
     */
    int dfs(int len, boolean isMax) {
        int ans = 0;
        int maxx;
        if (len == 0) {
            return 1;
        }
        if (!isMax && dp[len] != -1) {
            //可以用备忘录了。
            return dp[len];
        }
        maxx = (isMax ? digit[len] : 9);
        for (int i = 0; i <= maxx; i++) {
            if (i == 4) {
                continue;
            }
            ans += dfs(len - 1, (isMax) && i == maxx);
        }
        if (!isMax) {
            //计算满了，dp[len]成立了。
            dp[len] = ans;
        }
        return ans;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {19, 120},
                {19, 20},
                {4, 4},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;

    @Test
    public void test() {
        Assert.assertEquals(expect, testMethod(n));
    }

}
