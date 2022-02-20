/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ConvertANumberToHexadecimal.java, v 0.1 2022年02月20日 5:23 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ConvertANumberToHexadecimal {

    @LetCodeCommit(title = "405. Convert a Number to Hexadecimal")
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        char[] map = new char[16];
        for (int i = 0; i < map.length; i++) {
            if (i < 10) {
                map[i] = (char) ('0' + i);
                continue;
            }
            map[i] = (char) ('a' + i - 10);
        }
        long longNum = num;
        if (num < 0) {
            num = -num;
            num = Integer.MAX_VALUE ^ num;
            longNum = ((long) num) + Integer.MAX_VALUE + 1 + 1;
        }
        return doSolution(longNum, map);
    }

    public String doSolution(Long num, char[] map) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int mod = (int) (num % 16);
            sb.append(map[mod]);
            num = num / 16;
        }
        return sb.reverse().toString();
    }

    @Parameter
    public int    num;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {26, "1a"},
                {-1, "ffffffff"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, toHex(num));
    }
}