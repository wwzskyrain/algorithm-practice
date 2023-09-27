package study.erik.algorithm.leetcode.advance.segmenttree;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 日期：2023/9/27 ，时间：10:09
 * 作者：yueyi
 * 描述：这里主要介绍树状数组的解法。
 * 该题目有四种解法：
 * 1. 从后遍历+插入法
 * 2. 归并排序法
 * 3. 树状数组
 * 4. 线段树（本类重点）
 */
@RunWith(Parameterized.class)
public class Count_of_Smaller_Numbers_After_Self {

    @LetCodeCommit(title = "315. Count of Smaller Numbers After Self",
            selfRemark = "也是利用了区间(线段)操作的特性。" +
                    "当然，本本题目没有直接用到区间操作，而只是定点操作，但是其查询时区间界别的。" +
                    "线段树的解法和树状数组的解法，在本题的本质上是一致的，但是树状数组更优秀。" +
                    "在线段树之前，也可以先对元数据进行散列紧凑操作，把给他们rank一下，然后用rank来做构造区间。" +
                    "这一点在树状数组的解法中已经使用了，请去参照。")
    public List<Integer> countSmaller(int[] nums) {
        int l = nums.length;
        int[] ret = new int[l];
        int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
        for (int num : nums) {
            start = Math.min(start, num);
            end = Math.max(end, num);
        }
        SegmentTreeNode root = SegmentTreeNode.build(start, end);
        for (int i = nums.length - 1; i >= 0; i--) {
            ret[i] = SegmentTreeNode.count(root, root.start, nums[i] - 1);
            SegmentTreeNode.insert(root, nums[i], 1);
        }
        return Arrays.stream(ret).boxed().collect(Collectors.toList());
    }

    public static class SegmentTreeNode {
        private int start, end, value;
        private SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, int value, SegmentTreeNode left, SegmentTreeNode right) {
            this.start = start;
            this.end = end;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public static SegmentTreeNode build(int s, int e) {
            if (s == e) {
                return new SegmentTreeNode(s, e, 0, null, null);
            }
            SegmentTreeNode root = new SegmentTreeNode(s, e, 0, null, null);
            int m = s + (e - s) / 2;
            root.left = build(s, m);
            root.right = build(m + 1, e);
            return root;
        }

        public static void insert(SegmentTreeNode root, int index, int value) {
            if (index < root.start || index > root.end) {
                //越界了，不应该在这里
                return;
            }
            if (root.start == root.end) {
                //叶子节点了
                if (root.start == index) {
                    root.value += value;
                }
            } else {
                int m = root.start + (root.end - root.start) / 2;
                root.value += value;
                if (index <= m) {
                    insert(root.left, index, value);
                } else {
                    insert(root.right, index, value);
                }
            }
        }

        public static int count(SegmentTreeNode root, int s, int e) {
            if (e < root.start || s > root.end) {
                return 0;
            }
            if (root.start == s && root.end == e) {
                return root.value;
            }
            int mid = root.start + (root.end - root.start) / 2;
            if (e <= mid) {
                return count(root.left, s, e);
            } else if (s > mid) {
                return count(root.right, s, e);
            } else {
                return count(root.left, s, mid) + count(root.right, mid + 1, e);
            }
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[1,0]"), ArrayUtils.buildArray("[-1,-2]")},
                {ArrayUtils.buildArray("[2,1,1,0]"), ArrayUtils.buildArray("[5,2,6,1]")},
                {ArrayUtils.buildArray("[0]"), ArrayUtils.buildArray("[-1]")},
                {ArrayUtils.buildArray("[0,0]"), ArrayUtils.buildArray("[-1,-1]")},
                });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        Assert.assertArrayEquals(expect, ArrayUtils.convertListTo(countSmaller(nums)));
    }

}
