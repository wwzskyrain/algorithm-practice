/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree.segment;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : SumSegmentTree.java, v 0.1 2021年07月27日 6:54 下午 yueyi Exp $
 */
public class SumSegmentTree {

    public static class Node {
        public int left;
        public int right;
        public int sum;

        public Node(int left, int right, int sum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
        }
    }

    Node[] nodes;

    public void solution(int[] A) {
        if (A.length == 0) {
            return;
        }
        nodes = new Node[4 * A.length];
        build(nodes, A, 1, 0, A.length - 1);
    }

    public int build(Node[] nodes, int[] A, int node, int l, int r) {
        if (l == r) {
            nodes[node] = new Node(l, r, A[l]);
            return nodes[node].sum;
        }
        int m = l + (r - l) / 2;
        int sum = build(nodes, A, node * 2, l, m) +
                build(nodes, A, node * 2 + 1, m + 1, r);
        nodes[node] = new Node(l, r, sum);
        return nodes[node].sum;
    }

    public long query(int start, int end) {
        return sum(1, start, end);
    }

    public long sum(int node, int start, int end) {
        int left = nodes[node].left;
        int right = nodes[node].right;
        if (left == start && right == end) {
            return nodes[node].sum;
        }
        int mid = left + (right - left) / 2;
        if (end <= mid) {
            return sum(node * 2, start, end);
        } else if (start > mid) {
            return sum(node * 2 + 1, start, end);
        }
        return sum(node * 2, start, mid) +
                sum(node * 2 + 1, mid + 1, end);

    }

    public void modify(int index, int value) {
        update(1, index, value);
    }

    public void update(int node, int index, int value) {
        int left = nodes[node].left;
        int right = nodes[node].right;
        if (left == right && left == index) {
            nodes[node].sum = value;
            return;
        }
        int mid = left + (right - left) / 2;
        if (index <= mid) {
            update(node * 2, index, value);
        } else {
            update(node * 2 + 1, index, value);
        }
        nodes[node].sum = nodes[node * 2].sum + nodes[node * 2 + 1].sum;
    }

    @Test
    public void test() {
        solution(new int[] {1, 2, 7, 8, 5});
        Assert.assertEquals(10, query(0, 2));
        modify(0, 4);
        Assert.assertEquals(6, query(0, 1));
        modify(2, 1);
        Assert.assertEquals(14, query(2, 4));
    }

    @Test
    public void test1() {
        solution(new int[] {});
        Assert.assertEquals(10, query(0, 2));
        modify(0, 4);
        Assert.assertEquals(6, query(0, 1));
        modify(2, 1);
        Assert.assertEquals(14, query(2, 4));
    }

    public static class Interval {
        public int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 顺便做一个题：不带modify操作的求区间和
     *
     * @param A
     * @param queries
     * @return
     */
    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        // write your code here
        long[] sum = new long[A.length];
        for (int i = 0; i < A.length; i++) {
            sum[i] = i == 0 ? A[i] : sum[i - 1] + A[i];
        }
        List<Long> result = new LinkedList<>();
        for (Interval query : queries) {
            result.add(sum[query.end] - sum[query.start] + A[query.start]);
        }
        return result;
    }

}