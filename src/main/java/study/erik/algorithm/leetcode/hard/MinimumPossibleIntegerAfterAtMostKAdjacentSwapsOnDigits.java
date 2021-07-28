/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits.java, v 0.1 2021年07月25日 2:13 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {

    @LetCodeCommit(title = "Minimum Possible Integer After at Most K Adjacent Swaps On Digits")
    public String minInteger(String num, int k) {
        // TODO: 2021/7/27 等我搞定之后
        return "0";
    }

    /**
     * 递归解法，会超时
     *
     * @param num
     * @param k
     * @return
     */
    public String resolveWithRecursion(String num, int k) {
        int length = num.length();
        if (k > length * (length - 1) / 2) {
            char[] chars = num.toCharArray();
            Arrays.sort(chars);
            return String.valueOf(chars);
        }
        for (int i = 0; i < 10; i++) {
            char ch = (char) (i + '0');
            int index = num.indexOf(ch);
            if (index >= 0 && index <= k) {
                return ch + resolveWithRecursion(num.substring(0, index) + num.substring(index + 1), k - index);
            }
        }
        return "";
    }

    /**
     * 一个失败的解法，花了一个多小时也是枉然。
     *
     * @param num
     * @param k
     * @return
     */
    public String resolveWithFail(String num, int k) {
        int[][] pairs = new int[num.length()][];
        for (int i = 0; i < num.length(); i++) {
            pairs[i] = new int[] {num.charAt(i) - '0', i};
        }

        int baseIndex = 0;
        Arrays.sort(pairs, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 0; i < pairs.length && k > 0; i++) {
            int[] pair = pairs[i];
            if (pair[1] - baseIndex <= k) {
                k -= pair[1] - baseIndex;
                for (int j = i + 1; j < pairs.length; j++) {
                    int[] p = pairs[j];
                    if (p[1] < pair[1]) {
                        p[1]++;
                    }
                }
                pair[1] = baseIndex;
                baseIndex++;
            }
        }

        Arrays.sort(pairs, Comparator.comparing(ints -> ints[1]));
        StringBuilder sb = new StringBuilder();
        for (int[] pair : pairs) {
            sb.append(pair[0]);
        }
        return sb.toString();
    }

    @Parameter
    public String num;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"294984148179", 11, "124498948179"},
                //{"4321", 4, "1342"},
                //{"100", 1, "010"},
                //{"36789", 1000, "36789"},
                //{"22", 22, "22"},
                //{"9438957234785635408", 23, "0345989723478563548"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minInteger(num, k));
    }

}