/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

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
 * @version : NumbersAtMostNGivenDigitSet.java, v 0.1 2022年10月09日 09:09 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumbersAtMostNGivenDigitSet {

    @LetCodeCommit(title = "902. Numbers At Most N Given Digit Set",
            time = 100,
            selfRemark = "")
    public int atMostNGivenDigitSet(String[] digits, int n) {
        char[] digitCharArr = new char[digits.length];
        for (int i = 0; i < digits.length; i++) {
            digitCharArr[i] = digits[i].charAt(0);
        }
        String s = Integer.toString(n);
        int sum = 0;
        for (int i = 1; i < s.length(); i++) {
            sum += pow(digits.length, i);
        }
        return sum + doRecursive(digitCharArr, s);
    }

    private int doRecursive(char[] digits, String s) {
        int sum = 0;
        char c = s.charAt(0);
        int foundIndex = Arrays.binarySearch(digits, c);
        if (foundIndex >= 0) {
            if (s.length() == 1) {
                return foundIndex + 1;
            }
            sum += doRecursive(digits, s.substring(1));
            sum += foundIndex * this.pow(digits.length, s.length() - 1);
            return sum;
        } else {
            int insertIndex = -(foundIndex + 1);
            // 如果没有找到，则问题变简单了。
            return insertIndex * this.pow(digits.length, s.length() - 1);
        }
    }

    private int pow(int base, int p) {
        if (p <= 0) {
            return 1;
        }
        return (int) Math.pow(base, p);
    }

    @Parameter
    public String[] digits;
    @Parameter(1)
    public int      n;
    @Parameter(2)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"1", "3", "5", "7"}, 100, 20},
                {new String[] {"1", "3", "5", "7"}, 200, 36},
                {new String[] {"1", "3", "5", "7"}, 300, 36},
                {new String[] {"1", "3", "5", "7"}, 310, 36},
                {new String[] {"1", "3", "5", "7"}, 320, 40},
                {new String[] {"1", "4", "9"}, 1000000000, 29523},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, atMostNGivenDigitSet(digits, n));
    }

}