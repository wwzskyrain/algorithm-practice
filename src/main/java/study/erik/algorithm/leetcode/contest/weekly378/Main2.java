package study.erik.algorithm.leetcode.contest.weekly378;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/1/1 15:51
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main2 {

    @LetCodeCommit(title = "2981. Find Longest Special Substring That Occurs Thrice I",
    selfRemark = "AC了")
    public int maximumLength(String s) {
        Map<Integer, Integer>[] maps = new Map[26];
        for (int i = 0; i < maps.length; i++) {
            maps[i] = new HashMap<>();
        }
        int max = -1;
        int l = s.length();
        for (int i = 0; i < l; i++) {
            int j = i;
            int c = s.charAt(i);
            Map<Integer, Integer> map = maps[c - 'a'];
            while (j >= 0 && s.charAt(j) == s.charAt(i)) {
                int length = i - j + 1;
                int counter = map.getOrDefault(length, 0);
                counter++;
                map.put(length, counter);
                if (counter >= 3) {
                    max = Math.max(max, length);
                }
                j--;
            }
        }
        return max;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, "aaaa"},
                {-1, "abcdef"},
                {1, "abcaba"},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String s;


    @Test
    public void test() {
        Assert.assertEquals(expect, maximumLength(s));
    }

}
