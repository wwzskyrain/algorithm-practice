package study.erik.algorithm.job.zijie.simulation10;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/13 14:16
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Longest_Binary_Subsequence_Less_Than_or_Equal_to_K {

    @LetCodeCommit(title = "2311. Longest Binary Subsequence Less Than or Equal to K")
    public int longestSubsequence(String s, int k) {
        // 贪心
        // 找最大的后缀，因为可以补充前导0。如果还有一个更加后缀的小于k的子串出现，则肯定不劣与第一个后缀
        int m = 32 - Integer.numberOfLeadingZeros(k); //前导0
        if (s.length() < m) {
            return s.length();
        }
        int post = Integer.valueOf(s.substring(s.length() - m), 2);
        int lastLength = (post <= k ? m : m - 1);
        int c = 0;
        int l = s.length() - m - 1;
        while (l >= 0) {
            c += s.charAt(l--) == '0' ? 1 : 0;
        }
        return c + lastLength;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {6, "00101001", 1},
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
        Assert.assertEquals(expect, longestSubsequence(s, k));
    }

}
