/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yueyi
 * @version : SwapForLongestRepeatedCharacterSubstring.java, v 0.1 2023年03月04日 16:52 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SwapForLongestRepeatedCharacterSubstring {

    @LetCodeCommit(title = "1156. Swap For Longest Repeated Character Substring",
            selfRemark = "不算一个好题")
    public int maxRepOpt1(String text) {
        int l = text.length();
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        char curC = text.charAt(0);
        int curCount = 1;
        int maxL = 0;
        int[] count = new int[26];
        count[text.charAt(0) - 'a']++;
        for (int i = 1; i < l; i++) {
            char c = text.charAt(i);
            count[c - 'a']++;
            if (c == curC) {
                curCount++;
            } else {
                maxL = Math.max(maxL, curCount);
                list.add(new Pair<>(curC - 'a', curCount));
                curC = c;
                curCount = 1;
            }
        }
        list.add(new Pair<>(curC - 'a', curCount));
        maxL = Math.max(maxL, curCount);

        for (int i = 0; i < list.size(); i++) {
            // 加上一个
            Pair<Integer, Integer> p = list.get(i);
            Integer length = p.getValue();
            length += (length < count[p.getKey()] ? 1 : 0);
            maxL = Math.max(maxL, length);
        }

        for (int i = 2; i < list.size(); i++) {
            // 合并
            Pair<Integer, Integer> pi = list.get(i);
            Pair<Integer, Integer> pi_1 = list.get(i - 1);
            Pair<Integer, Integer> pi_2 = list.get(i - 2);
            if (pi_1.getValue() == 1 && Objects.equals(pi_2.getKey(), pi.getKey())) {
                int length = pi.getValue() + pi_2.getValue();
                length += (length < count[pi.getKey()] ? 1 : 0);
                maxL = Math.max(maxL, length);
            }
        }

        return maxL;
    }

    @Parameter
    public String text;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"aabba", 3},
                {"ababa", 3},
                {"aaabaaa", 6},
                {"aaaaa", 5},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maxRepOpt1(text));
    }

}