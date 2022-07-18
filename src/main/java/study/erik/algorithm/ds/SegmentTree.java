/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.ds;

/**
 * @author yueyi
 * @version : SegmentTree.java, v 0.1 2022年06月04日 18:42 yueyi Exp $
 */
public class SegmentTree {
    /**
     * 又写了一个线段树.
     * l、r是线段树的边界：其实是一维数组的idx
     * max则是该线段[l,r]的一个聚合值。
     * lChild、rChild则是其左右两个子线段，会把当前这个线段完全"瓜分"。
     */
    public static class SegmentTreeNode {
        private final int             l;
        private final int             r;
        private       int             max;
        private       SegmentTreeNode lChild;
        private       SegmentTreeNode rChild;

        public SegmentTreeNode(int l, int r, int max) {
            this.l = l;
            this.r = r;
            this.max = max;
        }

        public SegmentTreeNode(int l, int r, int max, SegmentTreeNode lChild, SegmentTreeNode rChild) {
            this.l = l;
            this.r = r;
            this.max = max;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }

    private SegmentTreeNode tree;

    /**
     * 把arr按照不同粒度来瓜分到不同层次的线段中.
     *
     * @param arr
     */
    public void build(int[] arr) {
        this.tree = build(0, arr.length - 1, arr);
    }

    private SegmentTreeNode build(int l, int r, int[] arr) {
        if (l == r) {
            return new SegmentTreeNode(l, r, arr[l]);
        }
        int mid = (l + r) >> 1;
        SegmentTreeNode lChild = build(l, mid, arr);
        SegmentTreeNode rChild = build(mid + 1, r, arr);
        int max = Math.max(lChild.max, rChild.max);
        return new SegmentTreeNode(l, r, max, lChild, rChild);
    }

    public int queryMax(int l, int r) {
        return queryMax(l, r, this.tree);
    }

    /**
     * 查询区间[l,r]的最值。
     * 入参的[l,r]不一定刚好对应线段树的某一个线段，但是一定可以有线段树中的线段组合而成.
     *
     * @param l
     * @param r
     * @param root
     * @return
     */
    private int queryMax(int l, int r, SegmentTreeNode root) {
        if (root.l == l && root.r == r) {
            return root.max;
        }
        int m = (root.l + root.r) >> 1;
        if (r <= m) {
            return queryMax(l, r, root.lChild);
        }
        if (l > m) {
            return queryMax(l, r, root.rChild);
        }
        return Math.max(queryMax(l, m, root.lChild), queryMax(m + 1, r, root.rChild));
    }

    public void update(int idx, int val) {
        update(idx, val, tree);
    }

    private void update(int idx, int val, SegmentTreeNode root) {
        if (root.l == root.r && root.l == idx) {
            root.max = Math.max(root.max, val);
            return;
        }
        int mid = (root.l + root.r) >> 1;
        if (idx <= mid) {
            update(idx, val, root.lChild);
        } else {
            update(idx, val, root.rChild);
        }
        root.max = Math.max(root.lChild.max, root.rChild.max);
    }

    public static void main(String[] args) {

        int[] arr = new int[] {1, 3, 5, 7, 8, 10};
        SegmentTree tree = new SegmentTree();
        tree.build(arr);
        System.out.println(tree.queryMax(0, 5));
        System.out.println(tree.queryMax(3, 5));
        tree.update(0, 11);

        System.out.println(tree.queryMax(0, 5));
        System.out.println(tree.queryMax(3, 5));

        tree.update(3, 12);
        System.out.println(tree.queryMax(0, 5));
        System.out.println(tree.queryMax(3, 5));
        System.out.println(tree.queryMax(4, 5));

    }

}