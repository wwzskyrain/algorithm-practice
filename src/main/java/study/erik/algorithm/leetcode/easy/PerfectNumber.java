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
 * @version : PerfectNumber.java, v 0.1 2022年03月10日 9:37 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PerfectNumber {

    @LetCodeCommit(title = "507. Perfect Number",
            diff = "e",
            selfRemark = "这个题被dis了，它活该。"
                    + "题目表述不清"
                    + "case还偏冷。"
                    + "虽然我ac了，我还是也dis它了")
    public boolean checkPerfectNumber(int num) {

        if (num == 1) {
            return false;
        }
        int sum = 0;
        double sqrt = Math.sqrt(num);
        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                int d = num / i;
                if (d != i && d != num) {
                    sum += d;
                }
            }
        }
        return sum == num;
    }

    @Parameter
    public int     num;
    @Parameter(1)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {99999999, false},
                {1, false},
                {28, true},
                {6, true},
                {14, false},
                {12, false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, checkPerfectNumber(num));
    }

}