/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : MinimumDeletionsMakeCharacterFrequenciesUnique.java, v 0.1 2021年08月07日 1:07 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumDeletionsMakeCharacterFrequenciesUnique {

    @LetCodeCommit(title = "Minimum Deletions to Make Character Frequencies Unique")
    public int minDeletions(String s) {
        return resolveWithSort(s);
    }

    /**
     * 这是一个计算题吧，虽然我们用代码操作来搞定了
     *
     * @param s
     * @return
     */
    public int resolveWithSort(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']--;
        }
        Arrays.sort(count);
        int deletionTime = 0;
        for (int i = 1; i < count.length; i++) {
            if (count[i] == count[i - 1]) {
                Integer targetCount = count[i];
                if (targetCount == 0) {
                    break;
                }
                int j = i;
                while (j < count.length && count[j] == targetCount) {
                    deletionTime++;
                    count[j++]++;
                }
            }
        }
        return deletionTime;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"aab", 0},
                {"aaabbbcc", 2},
                {"ceabaacb", 2},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, minDeletions(s));
    }
}