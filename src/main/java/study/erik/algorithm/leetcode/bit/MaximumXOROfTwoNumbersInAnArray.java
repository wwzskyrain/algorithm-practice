/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumXOROfTwoNumbersInAnArray.java, v 0.1 2021年05月16日 9:00 上午 yueyi Exp $
 */
public class MaximumXOROfTwoNumbersInAnArray {

    @LetCodeCommit(title = "Maximum XOR of Two Numbers in an Array", no = 421,
            selfRemark = "本题的难点在于把一个O(n*n)的题目化成O(n)的题目。当然，肯定要在计算步骤上花功夫。"
                    + "这里就是把计算复合在一块。",
            related = "1707. Maximum XOR With an Element From Array")
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            add(num);
            max = Math.max(max, check(nums[i + 1]));
        }
        return max;
    }

    public Trie root          = new Trie();
    public int  MOST_HIGH_BIT = 31;

    public int check(int num) {
        Trie cur = root;
        int max = 0;
        for (int k = MOST_HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.right == null) {
                    //匹配失败，不计数只左移
                    cur = cur.left;
                    max = max * 2;
                } else {
                    //匹配成功
                    max = max * 2 + 1;
                    cur = cur.right;
                }
            } else {
                if (cur.left == null) {
                    //匹配失败，不计数只左移
                    cur = cur.right;
                    max = max * 2;
                } else { //匹配成功
                    max = max * 2 + 1;
                    cur = cur.left;
                }
            }
        }
        return max;
    }

    public void add(int num) {
        Trie cur = root;
        for (int k = MOST_HIGH_BIT; k >= 0; k--) {
            int bit = (num >> k) & 1;
            if (bit == 0) {
                if (cur.left == null) {
                    cur.left = new Trie();
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Trie();
                }
                cur = cur.right;
            }
        }
    }

    public class Trie {
        public Trie left;   //  表示0
        public Trie right;  //  表示1
    }

    @Test
    public void test_1() {
        int[] nums = {3, 10, 5, 25, 2, 8};
        Assert.assertEquals(28, findMaximumXOR(nums));
    }

    @Test
    public void test_2() {
        int[] nums = {0};
        Assert.assertEquals(0, findMaximumXOR(nums));
    }

    @Test
    public void test_3() {
        int[] nums = {2, 4};
        Assert.assertEquals(6, findMaximumXOR(nums));
    }

}