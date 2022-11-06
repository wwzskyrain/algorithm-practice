/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LongPressedName.java, v 0.1 2022年11月06日 21:19 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongPressedName {

    @LetCodeCommit(title = "925. Long Pressed Name",
            selfRemark = "模式匹配的简单题目，说实话，case挺多的。不过思路主体简单，而且成绩不错，93%")
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        int l = name.length();

        while (i < l) {
            if (j >= typed.length()) {
                return false;
            }
            if (name.charAt(i) == typed.charAt(j)) {
                j++;
                i++;
                continue;
            }
            if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
                continue;
            }
            return false;
        }
        while (j < typed.length()) {
            if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
                continue;
            }
            return false;
        }
        return true;
    }

    @Parameter
    public String  name;
    @Parameter(1)
    public String  typed;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"pyplrz", "ppyypllr", false},
                {"alexd", "ale", false},
                {"vtkgn", "vttkgnn", true},
                {"alex", "aaleex", true},
                {"saeed", "ssaaedd", false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, isLongPressedName(name, typed));
    }
}