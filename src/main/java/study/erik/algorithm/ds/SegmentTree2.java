/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.ds;

/**
 * @author yueyi
 * @version : SegmentTree2.java, v 0.1 2022年06月05日 09:46 yueyi Exp $
 * 来一个支持区间更新的线段树，
 * 而且是用满二叉树结构实现的哟
 */
public class SegmentTree2 {

    private int[] sum;
    private int[] add;
    private int   n;

    /**
     * @param arr
     */
    public SegmentTree2(int[] arr) {
        this.n = arr.length;
        sum = new int[this.n << 2];
        add = new int[this.n << 2];
        build(1, arr.length, 1, arr);
    }

    private void pushUp(int idx) {
        // 这里只是计算本层idx的sum值. 也无安全可以
        sum[idx] = sum[idx << 1] + sum[idx << 1 | 1];
    }

    private void build(int l, int r, int idx, int[] arr) {
        add[idx] = 0;
        if (l == r) {
            sum[idx] = arr[l - 1];
            return;
        }
        int m = (l + r) >> 1;
        build(l, m, idx << 1, arr);
        build(m + 1, r, idx << 1 | 1, arr);
        // pushUp里面也就一句话，其实可以直接写到这里看着更明白.
        pushUp(idx);
    }

    /**
     * 这是个好方法，就是把当前节点的add标志清理掉。
     * 该方法可以单独调用，不用担心当前的sum会有影响，
     * 因为sum在之前做update的时候，已经更新了。
     * 也就是说，在更新区间值的时候，sum会先更新。
     * 而这里的pushDown其实也是更新区间值，是细粒度的更新区间值，
     * 所以，这里也会直接算好区间的sum的，比如tag1和tag2.
     *
     * @param idx
     * @param m
     */
    private void pushDown(int idx, int m) {
        if (add[idx] > 0) {
            add[idx << 1] = add[idx];
            // tag1.
            sum[idx << 1] += (m - m / 2) * add[idx];

            add[idx << 1 | 1] = add[idx];
            // tag2
            sum[idx << 1 | 1] += (m / 2) * add[idx];
            add[idx] = 0;
        }
    }

    public void update(int a, int b, int delta) {
        update(a, b, delta, 1, n, 1);
    }

    private void update(int a, int b, int delta, int l, int r, int idx) {
        if (a <= l && b >= r) {
            add[idx] += delta;
            sum[idx] += (r - l + 1) * delta;
            return;
        }
        pushDown(idx, r - l + 1);
        int m = (l + r) >> 1;
        if (a <= m) {
            update(a, b, delta, l, m, idx << 1);
        }
        if (b > m) {
            update(a, b, delta, m + 1, r, idx << 1 | 1);
        }
        pushUp(idx);
    }

    public int queryRangeSum(int a, int b) {
        return queryRangeSum(a, b, 1, n, 1);
    }

    private int queryRangeSum(int a, int b, int l, int r, int idx) {
        if (a <= l && b >= r) {
            return sum[idx];
        }
        pushDown(idx, r - l + 1);
        int m = (l + r) >> 1;
        int res = 0;
        if (a <= m) {
            res += queryRangeSum(a, b, l, m, idx << 1);
        }
        if (b > m) {
            res += queryRangeSum(a, b, m + 1, r, idx << 1 | 1);
        }
        return res;
    }

}