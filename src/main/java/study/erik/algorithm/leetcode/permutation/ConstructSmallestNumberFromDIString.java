/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.permutation;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ConstructSmallestNumberFromDIString.java, v 0.1 2022年11月14日 09:13 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ConstructSmallestNumberFromDIString {

    @LetCodeCommit(title = "2375. Construct Smallest Number From DI String",
            postLink = "https://leetcode.com/problems/construct-smallest-number-from-di-string/",
            diff = "m",
            selfRemark = "就这，就92%的好成绩了。"
                    + "真的是构建呀，而不是去求最值")
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        int l = 0, h = 0;
        StringBuilder res = new StringBuilder();
        while (h < n) {
            while (h < n && pattern.charAt(h++) == 'I') {
                // 先找到'D*I'，构建，即倒排
                int tempH = h;
                while (tempH > l) {
                    res.append(tempH);
                    tempH--;
                }
                l = h;
            }
        }
        // 最后再处理一遍
        int tempH = h;
        while (tempH >= l) {
            res.append(tempH + 1);
            tempH--;
        }
        return res.toString();
    }

    @Parameter
    public String pattern;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"IIIDIDDD", "123549876"},
                {"DDD", "4321"},
                {"IDIDI", "132546"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, smallestNumber(pattern));
    }
}