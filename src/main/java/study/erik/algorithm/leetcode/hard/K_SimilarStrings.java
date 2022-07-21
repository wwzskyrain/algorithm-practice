/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author yueyi
 * @version : K_SimilarStrings.java, v 0.1 2022年07月21日 09:27 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class K_SimilarStrings {

    @LetCodeCommit(title = "854. K-Similar Strings")
    public int kSimilarity(String s1, String s2) {

        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(s1);
        visited.add(s1);
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                String curS = q.poll();
                if (curS.equals(s2)) {
                    return step;
                }
                List<String> nextSs = nextS(curS, s2);
                for (String next : nextSs) {
                    // 无论next是不是s2，都先进去在说，这是细节。
                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.add(next);
                    }
                }
                size--;
            }
            step++;
        }
        return -1;
    }

    public List<String> nextS(String curS, String targetS) {
        // 这里不用计较curS==targetS的情况，因为预先有判断
        char[] curSCharArr = curS.toCharArray();
        int i = 0;
        while (i < curSCharArr.length && curSCharArr[i] == targetS.charAt(i)) {
            i++;
        }
        char firstDiffChar = targetS.charAt(i);
        List<String> ans = new ArrayList<>();
        for (int j = i + 1; j < curSCharArr.length; j++) {
            if (curSCharArr[j] == firstDiffChar) {
                swap(curSCharArr, i, j);
                ans.add(new String(curSCharArr));
                swap(curSCharArr, i, j);
            }
        }
        return ans;
    }

    public void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    @Parameter
    public String s1;
    @Parameter(1)
    public String s2;
    @Parameter(2)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"bccaba", "abacbc", 3},
                {"ab", "ba", 1},
                {"abc", "bca", 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, kSimilarity(s1, s2));
    }

}