package study.erik.algorithm.leetcode.weekly001;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/10/18 ，时间：10:46
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class GetWordsInLongestSubsequence {

    @LetCodeCommit(title = "")
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        //dp0[i]表示最长的且以0结尾的子序列。dp[i] = 0 or max(dp1[0...i-1]) + 1;
        //dp1[i]表示最长的且以1结尾的子序列。dp[i] = 0 or max(dp0[0...i-1) + 1;
        int[] dp0 = new int[n];
        int[] dp1 = new int[n];
        Arrays.fill(dp0, -1);
        Arrays.fill(dp1, -1);
        int max0 = 0;
        int max0Idx = -1;
        int max1 = 0;
        int max1Idx = -1;

        int max = 0;
        int maxIndex = 0;
        int maxMode = -1;
        for (int i = 0; i < groups.length; i++) {
            int g = groups[i];
            if (g == 0) {
                dp1[i] = -1;
                int m = max1 + 1;
                if (max < m) {
                    max = m;
                    maxMode = 0;
                    maxIndex = i;
                    dp0[i] = max1Idx;
                }
                if (m > max0) {
                    max0 = m;
                    max0Idx = i;
                }
            } else {
                dp0[i] = -1;
                int m = max0 + 1;
                if (m > max) {
                    max = m;
                    maxMode = 1;
                    maxIndex = i;
                    dp1[i] = max0Idx;
                }
                if (m > max1) {
                    max1 = m;
                    max1Idx = i;
                }
            }
        }
        List<String> ret = new LinkedList<>();
        while (maxIndex >= 0) {
            ret.add(0, words[maxIndex]);
            if (maxMode == 0) {
                maxIndex = dp0[maxIndex];
                maxMode = 1;
            } else {
                maxIndex = dp1[maxIndex];
                maxMode = 0;
            }
        }
        return ret;
    }

    /*
    * 4
["d","a","v","b"]
[1,0,0,1]
    * */

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList("d", "a", "b"), 4, new String[]{"d", "a", "v", "b"}, ArrayUtils.buildArray("[1,0,0,1]")},
                {Arrays.asList("e", "b"), 3, new String[]{"e", "a", "b"}, ArrayUtils.buildArray("[0,0,1]")},
                {Arrays.asList("a", "b", "c"), 4, new String[]{"a", "b", "c", "d"}, ArrayUtils.buildArray("[1,0,1,1]")},
                });
    }

    @Parameterized.Parameter
    public List<String> expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public String[] words;
    @Parameterized.Parameter(3)
    public int[] groups;

    @Test
    public void test() {
        Assert.assertEquals(expect, getWordsInLongestSubsequence(n, words, groups));
    }

}
