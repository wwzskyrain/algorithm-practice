package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-20 12:25
 */
public class ZigZagConversion {

    /**
     * 题意：把s按照'锯齿形'来排列，然后按照从左到右从上到下读出来
     * 没什么意思，不做；
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        return solution(s, numRows);
    }

    /**
     * @param s
     * @param numRows
     * @return
     */
    public String solution(String s, int numRows) {
        // TODO: 2020-04-20
        return "";
    }

    @Test
    public void test_() {

        Assert.assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
        Assert.assertEquals("PINALSIGYAHRPI", convert("PAYPALISHIRING", 4));
    }

}
