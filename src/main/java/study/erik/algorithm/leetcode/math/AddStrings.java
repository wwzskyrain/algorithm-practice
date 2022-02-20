/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : AddStrings.java, v 0.1 2022年02月20日 6:14 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class AddStrings {

    @LetCodeCommit(title = "415. Add Strings")
    public String addStrings(String num1, String num2) {
        int maxLength = Math.max(num1.length(), num2.length()) + 1;
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();
        StringBuilder sb = new StringBuilder();
        int over = 0;
        for (int i = 0; i < maxLength; i++) {
            int add1 = i < num1.length() ? num1.charAt(i) - '0' : 0;
            int add2 = i < num2.length() ? num2.charAt(i) - '0' : 0;
            int r = add1 + add2 + over;
            if (r >= 10) {
                over = 1;
                r -= 10;
            } else {
                over = 0;
            }
            sb.append((char) (r + '0'));
        }
        sb.reverse();
        return sb.charAt(0) == '0' ? sb.substring(1) : sb.toString();
    }

    @Parameter
    public String nums1;
    @Parameter(1)
    public String nums2;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{"11", "123", "134"},
                {"456", "77", "533"},
                {"0", "0", "0"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, addStrings(nums1, nums2));
    }

}