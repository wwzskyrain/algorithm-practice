/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MakeTheStringGreat.java, v 0.1 2022年11月08日 09:13 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MakeTheStringGreat {

    @LetCodeCommit(title = "1544. Make The String Great",
            diff = "e",
            selfRemark = "这是一个e的题，却有趣，就写了写，反复思考了三遍，没太多把握，就提交了，一遍过，而且成绩不赖，72%呢")
    public String makeGood(String s) {

        int i = 0;
        while (i < s.length()) {
            // 并没有反反复复的从i=0开始
            int j = i + 1;
            boolean found = false;
            while (i >= 0 && j < s.length() && Math.abs(s.charAt(i) - s.charAt(j)) == 'a' - 'A') {
                found = true;
                i--;
                //i倒退一下
                j++;
            }
            if (found) {
                s = s.substring(0, i + 1) + s.substring(j);
            } else {
                i++;
            }
        }
        return s;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"leEeetcode", "leetcode"},
                {"abBAcC", ""},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, makeGood(s));
    }
}