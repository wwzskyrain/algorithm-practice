/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.rangsum;

import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : NumArrayMutable.java, v 0.1 2022年06月04日 08:37 yueyi Exp $
 */
public class NumArrayMutable {

    @LetCodeCommit(title = "307. Range Sum Query - Mutable")
    public static class NumArray {
        Tree  tree;
        int[] nums;

        public NumArray(int[] nums) {
            tree = new Tree(nums);
            this.nums = nums;
            for (int i = 0; i < nums.length; i++) {
                tree.update(i + 1, nums[i]);
            }
        }

        public void update(int index, int val) {
            tree.update(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return tree.queryPreSum(right + 1) - tree.queryPreSum(left);
        }

        private static class Tree {
            private int[] s;

            public Tree(int[] nums) {
                s = new int[nums.length + 1];
            }

            /**
             * index = (nums的index)+1
             *
             * @param index
             * @param delta
             */
            public void update(int index, int delta) {
                while (index < s.length) {
                    s[index] += delta;
                    index += lowBit(index);
                }
            }

            /**
             * 查询前缀和，相对于原数组nums，不包含i；
             *
             * @param i
             * @return
             */
            public int queryPreSum(int i) {
                int preSum = 0;
                while (i > 0) {
                    preSum += s[i];
                    i -= lowBit(i);
                }
                return preSum;
            }

            private int lowBit(int x) {
                return x & (-x);
            }
        }
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(ArrayUtils.buildArray("[1, 3, 5]"));
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }
}