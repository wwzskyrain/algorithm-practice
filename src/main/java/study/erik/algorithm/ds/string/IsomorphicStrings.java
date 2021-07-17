/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.ds.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : IsomorphicStrings.java, v 0.1 2021年07月13日 7:51 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class IsomorphicStrings {

    @LetCodeCommit(title = " Isomorphic Strings",
            selfRemark = "题意很重要，同构，首先得长度一样，其次，结构一样，何为结构一样？"
                    + "就是把s中的一个字符x第一次映射到t中的y之后，那么s中剩下的x都要在它的index处"
                    + "和t中的y相对应。反着也是的")
    public boolean isIsomorphic(String s, String t) {
        return resolve(s, t);
    }

    public boolean resolve(String s, String t) {
        // 一个映射表，key=index=x，value=s2t[s]=y
        int[] s2t = new int[256];
        int[] t2s = new int[256];
        if (s.length() != t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s2t[s.charAt(i)] == 0 && t2s[t.charAt(i)] == 0) {
                //第一次出现，记录映射关系
                s2t[s.charAt(i)] = t.charAt(i);
                t2s[t.charAt(i)] = s.charAt(i);
            } else if (s2t[s.charAt(i)] != 0 && t2s[t.charAt(i)] != 0) {
                //非第一次出现，即已经有了映射关系，则检查当前的x，y是否符合这个映射关系
                if (t.charAt(i) != s2t[s.charAt(i)] || s.charAt(i) != t2s[t.charAt(i)]) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Parameter
    public String  s;
    @Parameter(1)
    public String  t;
    @Parameter(2)
    public Boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{"egg", "add", true},
                //{"foo", "bar", false},
                //{"paper", "title", true},
                {"badc", "baba", false}
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, isIsomorphic(s, t));
    }

}