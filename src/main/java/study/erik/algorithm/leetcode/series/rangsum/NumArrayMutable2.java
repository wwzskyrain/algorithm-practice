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
public class NumArrayMutable2 {

    @LetCodeCommit(title = "307. Range Sum Query - Mutable",
            diff = "m",
            selfRemark = "线段树的解法.")
    public static class NumArray {
        SegmentTree tree = new SegmentTree();
        int[]       nums;

        public NumArray(int[] nums) {
            tree.build(nums);
            this.nums = nums;
        }

        public void update(int index, int val) {
            tree.update(index, val - this.nums[index]);
            this.nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return tree.query(left, right);
        }

        public static class SegmentTree {
            private SegmentTreeNode root;

            public void build(int[] arr) {
                root = build(0, arr.length - 1, arr);
            }

            private SegmentTreeNode build(int l, int r, int[] arr) {
                if (l == r) {
                    return new SegmentTreeNode(l, r, arr[l]);
                }
                int m = (l + r) >> 1;
                SegmentTreeNode lChild = build(l, m, arr);
                SegmentTreeNode rChild = build(m + 1, r, arr);
                return new SegmentTreeNode(l, r, lChild.sum + rChild.sum, lChild, rChild);
            }

            public void update(int idx, int delta) {
                update(idx, delta, root);
            }

            private void update(int idx, int delta, SegmentTreeNode root) {
                if (root.l == root.r && root.l == idx) {
                    root.sum += delta;
                    return;
                }
                root.sum += delta;
                int m = (root.l + root.r) >> 1;
                if (idx <= m) {
                    update(idx, delta, root.lChild);
                } else {
                    update(idx, delta, root.rChild);
                }
            }

            public int query(int l, int r) {
                return query(l, r, root);
            }

            private int query(int l, int r, SegmentTreeNode root) {
                if (root.l == l && root.r == r) {
                    return root.sum;
                }
                int m = (root.l + root.r) >> 1;
                if (r <= m) {
                    return query(l, r, root.lChild);
                }
                if (l > m) {
                    return query(l, r, root.rChild);
                }
                return query(l, m, root.lChild) + query(m + 1, r, root.rChild);
            }
        }

        private static class SegmentTreeNode {
            private int             l;
            private int             r;
            private int             sum;
            private SegmentTreeNode lChild;
            private SegmentTreeNode rChild;

            public SegmentTreeNode(int l, int r, int sum) {
                this.l = l;
                this.r = r;
                this.sum = sum;
            }

            public SegmentTreeNode(int l, int r, int sum, SegmentTreeNode lChild,
                                   SegmentTreeNode rChild) {
                this.l = l;
                this.r = r;
                this.sum = sum;
                this.lChild = lChild;
                this.rChild = rChild;
            }
        }
    }

    public static void main(String[] args) {
        //test_1();
        test_2();
    }

    public static void test_1() {
        NumArray numArray = new NumArray(ArrayUtils.buildArray("[1, 3, 5]"));
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

    public static void test_2() {
        //["NumArray","update","sumRange","sumRange","update","sumRange"]
        //        [[[9,-8]],[0,3],[1,1],[0,1],[1,-3],[0,1]]
        NumArray numArray = new NumArray(ArrayUtils.buildArray("[9,-8]"));
        numArray.update(0, 3);
        System.out.println(numArray.sumRange(1, 1));
        System.out.println(numArray.sumRange(0, 1));
        numArray.update(0, -3);
        System.out.println(numArray.sumRange(0, 1));
    }

}