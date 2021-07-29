/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;

/**
 * @author yueyi
 * @version : BeautifulArray.java, v 0.1 2021年07月29日 12:18 上午 yueyi Exp $
 */
public class BeautifulArray {

    @LetCodeCommit(title = "Beautiful Array")
    public int[] beautifulArray(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        while (res.size() < n) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i : res) {
                if (i * 2 - 1 <= n) {
                    tmp.add(i * 2 - 1);
                }
            }
            for (int i : res) {
                if (i * 2 <= n) {
                    tmp.add(i * 2);
                }
            }
            res = tmp;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public int[] resolveWithIteration(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        while (res.size() < n) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i : res) {
                if (i * 2 - 1 <= n) {
                    tmp.add(i * 2 - 1);
                }
            }
            for (int i : res) {
                if (i * 2 <= n) {
                    tmp.add(i * 2);
                }
            }
            res = tmp;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    public int[] resolveWithRecursive(int n) {
        switch (n) {
            case 1:
                return new int[] {1};
        }
        int halfN = n / 2 + (n % 2 == 0 ? 0 : 1);
        int[] right = resolveWithRecursive(halfN);
        int[] left = new int[right.length];
        for (int i = 0; i < right.length; i++) {
            right[i] += right[i];
            left[i] = right[i] - 1;
        }
        // 左右合并，左边是奇数，右边是偶数
        int[] result = new int[n];
        int x = 0, y = 0, z = 0;
        while (x < left.length) {
            if (left[x] <= n) {
                result[z++] = left[x];
            }
            x++;
        }
        while (y < right.length) {
            if (right[y] <= n) {
                result[z++] = right[y];
            }
            y++;
        }
        return result;
    }

    @Test
    public void test_() {
        for (int i = 4; i < 100; i++) {
            Assert.assertArrayEquals(resolveWithIteration(i), resolveWithRecursive(i));
        }
    }

}