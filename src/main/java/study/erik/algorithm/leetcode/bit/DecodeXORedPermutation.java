/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : DecodeXORedPermutation.java, v 0.1 2021年05月11日 7:34 上午 yueyi Exp $
 */
public class DecodeXORedPermutation {

    @LetCodeCommit(title = "Decode XORed Permutation", no = 1734)
    public int[] decode(int[] encoded) {

        int[] perm = new int[encoded.length + 1];

        int p1 = 0;
        for (int i = 1; i < encoded.length; ) {
            p1 ^= encoded[i];
            i = i + 2;
        }
        for (int i = 1; i <= perm.length; i++) {
            p1 ^= i;
        }
        perm[0] = p1;
        for (int i = 1; i < perm.length; i++) {
            perm[i] = perm[i - 1] ^ encoded[i - 1];
        }

        return perm;
    }

    @Test
    public void test_1() {
        int[] encoded = new int[] {3, 1};
        int[] except = new int[] {1, 2, 3};
        Assert.assertArrayEquals(except, decode(encoded));
    }

    @Test
    public void test_2() {
        int[] encoded = new int[] {6, 5, 4, 6};
        int[] except = new int[] {2, 4, 1, 5, 3};
        Assert.assertArrayEquals(except, decode(encoded));
    }

    @Test
    public void test_3() {
        int[] encoded = new int[] {7, 5, 6, 11, 14, 15, 11, 6};
        int[] except = new int[] {6, 1, 4, 2, 9, 7, 8, 3, 5};
        // [1,6,3,2,9,7,8,3,5]
        int[] output = decode(encoded);
        System.out.println(Arrays.toString(output));
        Assert.assertArrayEquals(except, output);
    }

    @Test
    public void test_4() {
        int[] encoded = new int[] {3, 12, 1, 15, 5, 2, 3, 7};
        int[] except = new int[] {7, 4, 8, 9, 6, 3, 1, 2, 5};
        // [6,5,9,8,7,2,0,3,4]
        int[] output = decode(encoded);
        System.out.println(Arrays.toString(output));
        Assert.assertArrayEquals(except, output);
    }

    @Test
    public void check() {
        int[] encoded = new int[] {7, 5, 6, 11, 14, 15, 11, 6};
        int[] except = new int[] {6, 1, 4, 2, 9, 7, 8, 3, 5};

        int[] output = new int[] {1, 6, 3, 2, 9, 7, 8, 3, 5};
        for (int i = 0; i < output.length - 1; i++) {
            if (encoded[i] != (output[i] ^ output[i + 1])) {
                System.out.printf("error, %d != (%d ^ %d)=%d", encoded[i], output[i], output[i + 1], (output[i] ^ output[i + 1]));
            }
        }
    }
}