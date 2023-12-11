package study.erik.algorithm.job.duoduo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/10 08:51
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public int numTrees(int n) {
        /*
        f(n) = f(0) * f(n-1) + f(1)*f(n-2) + f(2)*f(n-3) + ... + f(n-1)*f(0)
        * */
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for(int j = 2; j <= n ; j++) {
            int r = 0;
            for (int i = 0; i < j; i++) {
                r += (f[i] * f[j - 1 - i]);
            }
            f[j] = r;
        }
        return f[n];
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {5, 3},
                {1, 1},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;

    @Test
    public void test() {
        Assert.assertEquals(expect, numTrees(n));
    }

}
