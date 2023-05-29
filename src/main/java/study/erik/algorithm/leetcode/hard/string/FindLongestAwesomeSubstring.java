/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : FindLongestAwesomeSubstring.java, v 0.1 2023年05月27日 15:19 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FindLongestAwesomeSubstring {

    @LetCodeCommit(title = "1542. Find Longest Awesome Substring",
            diff = "h",
            selfRemark = "真的很牛这个一个。"
                    + "又是子串，又是presum，不过sum是异或操作什么呢？")
    public int longestAwesome(String s) {
        int res = 0;
        int cur = 0;
        int n = s.length();
        int[] seen = new int[1024];
        Arrays.fill(seen, n);
        seen[0] = -1;
        for (int i = 0; i < n; ++i) {
            cur ^= 1 << (s.charAt(i) - '0');
            // 加一个char能找到delta=0，说明delta-char就是一个奇数形式的回文数
            for (int a = 0; a < 10; ++a) {
                res = Math.max(res, i - seen[cur ^ (1 << a)]);
            }
            // delta=0是一个偶数形式的回文数。
            res = Math.max(res, i - seen[cur]);
            seen[cur] = Math.min(seen[cur], i);
        }
        return res;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"4121442", 7},
                {"12345678", 1},
                {"213123", 6},
                {"3242415", 5},

        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestAwesome(s));
    }

}