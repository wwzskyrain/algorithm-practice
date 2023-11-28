package study.erik.algorithm.leetcode.hard.dp;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/8/2 ，时间：14:05
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class NumberOfUniqueGoodSubsequences {

    @LetCodeCommit(title = "1987. Number of Unique Good Subsequences",
            diff = "h",
            selfRemark = "这个题目不算难，虽然我没有独立做出来。" +
                    "我被卡在哪里呢？卡在了unique上。" +
                    "解惑看注释")
    public int numberOfUniqueGoodSubsequences(String binary) {
        int mod = (int) 1e9 + 7, ends0 = 0, ends1 = 0, has0 = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '1') {
                //如果当前char是1，则只需要计算上end0就可以，因为如果在之前end1的基础上加上1，则会重复end1个啊。所以，不加end1.
                ends1 += (ends0 + 1);
                ends1 = (ends1) % mod;
            } else {
                ends0 = (ends0 + ends1) % mod;
                has0 = 1;
            }
        }
        //请思考，这里为什么没有去重呢？
        //因为这里是追加之后成新的值ends0、ends1，而没有累加。
        return (ends0 + ends1 + has0) % mod;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, "001"},
                {2, "11"},
                {5, "101"},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String binary;

    @Test
    public void test() {
        Assert.assertEquals(expect, numberOfUniqueGoodSubsequences(binary));
    }

}
