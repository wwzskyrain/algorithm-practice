/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.greedy.hard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : MaximumNumberOfNonOverlappingSubstrings.java, v 0.1 2023年05月23日 07:49 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumNumberOfNonOverlappingSubstrings {

    @LetCodeCommit(title = "1520. Maximum Number of Non-Overlapping Substrings",
            diff = "hard",
            selfRemark = "这样的题目，没有固定的套路，才真的见思考能力。这个解法真的很赞。"
                    + "1.通过两个数组l、r轻易找到有效substring。"
                    + "2.这个按照顺序找到解的做法——给res按照index从小到大的顺序设置substring，也是绝了。")
    public List<String> maxNumOfSubstrings(String s) {
        int l[] = new int[26], r[] = new int[26];
        Arrays.fill(l, s.length());
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < s.length(); ++i) {
            int ch = s.charAt(i) - 'a';
            l[ch] = Math.min(l[ch], i);
            r[ch] = i;
        }
        int right = -1;
        for (int i = 0; i < s.length(); ++i) {
            if (i == l[s.charAt(i) - 'a']) {
                int new_right = checkSubstr(s, i, l, r);
                if (new_right != -1) {
                    if (i > right) {
                        res.add("");
                    }
                    right = new_right;
                    res.set(res.size() - 1, s.substring(i, right + 1));
                }
            }
        }
        return res;
    }

    int checkSubstr(String s, int i, int l[], int r[]) {
        int right = r[s.charAt(i) - 'a'];
        for (int j = i; j <= right; ++j) {
            if (l[s.charAt(j) - 'a'] < i) {
                return -1;
            }
            right = Math.max(right, r[s.charAt(j) - 'a']);
        }
        return right;
    }

    @Parameter
    public String s;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"adefaddaccc"},
                {"abbaccd"},
                {"adefaddaccc"},
        };
    }

    @Test
    public void test_() {
        System.out.println(maxNumOfSubstrings(s));
    }

}