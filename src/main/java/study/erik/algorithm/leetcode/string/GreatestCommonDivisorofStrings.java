/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;
import study.erik.algorithm.util.QuestionType;

/**
 * @author yueyi
 * @version : GreatestCommonDivisorofStrings.java, v 0.1 2021年03月21日 11:11 上午 yueyi Exp $
 */
public class GreatestCommonDivisorofStrings {

    @LetCodeCommit(title = "Greatest Common Divisor of Strings",
            types = QuestionType.String_,
            selfRemark = "这是一个简单地题目，其普通解法就不写了，浪费时间；这里记录的是"
                    + "它的递归解法，有点意思的；因为其本质是最大公约数"
                    + "或者说是除法的特性：如果两个数都能被整除x，那么他们"
                    + "的差也能整除x")
    public String gcdOfStrings(String str1, String str2) {
        if (str1.length() < str2.length()) {
            return gcdOfStrings(str2, str1);
        }
        if (str2.length() == 0) {
            return str1;
        }
        if (!str1.startsWith(str2)) {
            return "";
        }
        return gcdOfStrings(str1.substring(str2.length()), str2);
    }

    @Test
    public void test_solution1() {
        String str1 = "ABCABC", str2 = "ABC";
        String expect = "ABC";
        Assert.assertEquals(expect, gcdOfStrings(str1, str2));
    }

    @Test
    public void test_solution2() {
        String str1 = "ABABAB", str2 = "ABAB";
        String expect = "AB";
        Assert.assertEquals(expect, gcdOfStrings(str1, str2));
    }

    @Test
    public void test_solution3() {
        String str1 = "LEET", str2 = "CODE";
        String expect = "";
        Assert.assertEquals(expect, gcdOfStrings(str1, str2));
    }

    @Test
    public void test_solution4() {
        String str1 = "ABCDEF", str2 = "ABC";
        String expect = "";
        Assert.assertEquals(expect, gcdOfStrings(str1, str2));
    }

}