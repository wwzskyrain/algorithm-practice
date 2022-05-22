/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : SplitArrayIntoFibonacciSequence.java, v 0.1 2022年05月22日 08:49 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SplitArrayIntoFibonacciSequence {

    @LetCodeCommit(title = "842. Split Array into Fibonacci Sequence",
            diff = "m",
            selfRemark = "边界条件挺多的."
                    + "1.要整数计算，而且是int计算，不是long，更不是长字符运算."
                    + "2.暴力求解就可以，因为只需要确定a、b两个前缀.")
    public List<Integer> splitIntoFibonacci(String num) {

        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            String aStr = num.substring(0, i + 1);
            if (chars[0] == '0' && aStr.length() > 1) {
                break;
            }
            long a = Long.parseLong(aStr);
            if (a > Integer.MAX_VALUE) {
                break;
            }
            for (int j = i + 1; j < chars.length; j++) {
                String bStr = num.substring(i + 1, j + 1);
                if (bStr.charAt(0) == '0' && bStr.length() > 1) {
                    break;
                }
                long b = Long.parseLong(bStr);
                if (b > Integer.MAX_VALUE) {
                    break;
                }
                List<Long> split = doSplit(num.substring(j + 1), a, b);
                if (split.size() != 0) {
                    return split.stream().map(Long::intValue).collect(Collectors.toList());
                }
            }
        }
        return Collections.emptyList();
    }

    public List<Long> doSplit(String num, long a, long b) {
        if (num.length() == 0) {
            return Collections.emptyList();
        }
        List<Long> ret = new ArrayList<>();
        ret.add(a);
        ret.add(b);
        long c = a + b;
        while (num.startsWith(String.valueOf(c))) {
            if (c > Integer.MAX_VALUE) {
                break;
            }
            ret.add(c);
            a = b;
            b = c;
            c = a + b;
            num = num.substring(String.valueOf(b).length());
        }
        if (num.length() == 0) {
            return ret;
        } else {
            return Collections.emptyList();
        }
    }

    @Parameter
    public String num;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"1101111"},
                {"112358130"},
                {"0123"},
                {"5511816597"},
                {"214748364721474836422147483641"},
                {"539834657215398346785398346991079669377161950407626991734534318677529701785098211336528511"}
        };
    }

    @Test
    public void test_() {
        System.out.println(splitIntoFibonacci(num));
    }

}