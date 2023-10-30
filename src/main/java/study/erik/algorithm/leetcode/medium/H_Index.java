/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

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
 * @version : H_Index.java, v 0.1 2021年07月11日 8:44 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class H_Index {

    @LetCodeCommit(title = " H-Index",
            selfRemark = "投向，这道题目题意不明，赞的是怼的1/3." +
                    "真的吗？我怎么觉得没问题呢。")
    public int hIndex(int[] citations) {
        return resolve3(citations);
    }

    public int resolve3(int[] citations) {

        Arrays.sort(citations);
        int n = citations.length;
        if (citations.length == 0) {
            return 0;
        } else if (citations.length == 1) {
            return citations[0] >= 1 ? 1 : 0;
        } else if (citations[0] >= n) {
            return n;
        }
        // 下面还可以用'binary-search'来优化吧
        int l = 1, h = n;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (citations[n - m] >= m) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return l - 1;
//        int h = 1;
//        while (h < n) {
//            if (citations[n - h] < h) {
//                break;
//            }
//            h++;
//        }
//        return h - 1;
    }

    public int resolve2(int[] citations) {

        Arrays.sort(citations);
        int n = citations.length;
        if (citations.length == 0) {
            return 0;
        } else if (citations.length == 1) {
            return citations[0] >= 1 ? 1 : 0;
        } else if (citations[0] >= n) {
            return n;
        }
        // 下面还可以用'binary-search'来优化吧
        int h = 1;
        while (h < n) {
            if (citations[n - h] < h) {
                break;
            }
            h++;
        }
        return h - 1;
    }

    public int resolve(int[] citations) {

        Arrays.sort(citations);
        // h个>=h
        // 0,1,3,5,6
        int h1_index = 0;
        for (int h = 1; h <= citations.length; h++) {
            while (h1_index < citations.length && citations[h1_index] <= h) {
                h1_index++;
            }
            if (citations.length - h1_index == h) {
                return h;
            }
        }
        return 0;
    }

    @Parameter
    public int[] citations;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{11, 15}, 2},
                {new int[]{1}, 1},
                {new int[]{3, 0, 6, 1, 5}, 3},
                {new int[]{1, 3, 1}, 1},
                };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, hIndex(citations));
    }

}