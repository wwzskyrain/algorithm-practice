/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.bit;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : CreateSortedArrayThroughInstructions.java, v 0.1 2023年06月14日 07:18 yueyi Exp $
 */
public class CreateSortedArrayThroughInstructions {

    @LetCodeCommit(title = "1649. Create Sorted Array through Instructions",
            diff = "h",
            selfRemark = "这个题意太明显了，就是要用bit或者线段树。而且还是一阶bit。"
                    + "所以，如lee215大神所说，这个题目没有意思，就是考数据接口")
    public int createSortedArray(int[] instructions) {
        int max = Arrays.stream(instructions).max().getAsInt();
        BIT bit = new BIT(max);
        int cost = 0;
        for (int instruction : instructions) {
            int left = bit.getRangeSum(0, instruction - 1);
            int right = bit.getRangeSum(instruction + 1, max);
            cost = (cost + Math.min(left, right)) % 1_000_000_007;
            bit.add(instruction, 1);
        }
        return cost;
    }

    public static class BIT {
        int tree[];

        public BIT(int size) {
            this.tree = new int[size + 1];
        }

        public void add(int index, int delta) {
            int n = tree.length;
            while (index < n) {
                tree[index] += delta;
                index = index + (index & -index);
            }
        }

        public int getPreSum(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index = index - (index & (-index));
            }
            return sum;
        }

        public int getRangeSum(int left, int right) {
            return getPreSum(right) - getPreSum(left - 1);
        }
    }

}