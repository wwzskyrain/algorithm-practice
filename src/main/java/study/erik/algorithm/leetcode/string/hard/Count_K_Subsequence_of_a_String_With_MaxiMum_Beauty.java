package study.erik.algorithm.leetcode.string.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * 日期：2023/10/5 ，时间：20:15
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Count_K_Subsequence_of_a_String_With_MaxiMum_Beauty {

    @LetCodeCommit(title = "2842. Count K-Subsequences of a String With Maximum Beauty",
            selfRemark = "这个题目怎么说呢？确实很难，但是又有为了答案而出题的痕迹，所以不能算一个好题。" +
                    "这个一看是需要取余的就说明不可能去模拟的，必定超时。" +
                    "但是这是一个求子序列的题目哎，而且对子序列有个计算值，求计算值的最大值的所有子序列的个数。" +
                    "按照正常思路，求出所有的子序列并且对他们都做计算后，记录最大值，然后再把统计这些具有" +
                    "最大值的子序列的个数。这思路没问题，但是实现必定超时。" +
                    "所以就有了一下的解法——计算法：" +
                    "首先这个统计结果是可以计算出来的，其次计算的必定是计算值为最大值的那部分子序列的个数。" +
                    "具体可以看代码的注释。" +
                    "所以这里的关键点在于这个计算函数，它是破绽")
    public int countKSubsequencesWithMaxBeauty(String s, int k) {
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }
        TreeMap<Integer, Integer> countToNumMap = new TreeMap<>();
        for (int i = 0; i < count.length; i++) {
            int c = count[i];
            if (c > 0) {
                countToNumMap.merge(c, 1, Integer::sum);
            }
        }
        //倒着来，反正计算值具有简单的单调性。
        long ans = 1;
        for (Map.Entry<Integer, Integer> entry : countToNumMap.descendingMap().entrySet()) {
            Integer c = entry.getKey();
            Integer num = entry.getValue();
            if (num >= k) {
                //有num种字符出现了c次。如果num>k，则
                //首先从num种选出k个，则是一个组合数，C(num, k);
                //其次，从其中的一种组合看，每一种字符都有c个，所以一共有 pow(c, k)中.
                return (int) (ans * pow(c, k) % MOD * comb(num, k) % MOD);
            }
            //num<k，则这num种字符，每一种都出一个吧，而每种都有c个，所以是pow(c, num)。
            ans = ans * pow(c, num) % MOD;
            k -= num;
        }
        return 0; //k还剩下，k太大了，无法选择k个不同的字符。
    }

    public long MOD = (long) 1e9 + 7;

    public long pow(long x, int n) {
        long ret = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                ret = ret * x % MOD;
            }
            x = x * x % MOD;
            n /= 2;
        }
        return ret;
    }

    public long comb(int n, int k) {
        long ret = 1;
        for (int i = 1; i <= k; i++) {
            ret = ret * (n--) % MOD / i;
        }
        return ret % MOD;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {4, "bcca", 2},
                {2, "abbcd", 4},
                {4, "bcca", 2},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String s;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, countKSubsequencesWithMaxBeauty(s, k));
    }

}
