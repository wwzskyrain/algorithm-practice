/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.ds;

/**
 * @author yueyi
 * @version : SegmentTree2Test.java, v 0.1 2022年06月05日 10:18 yueyi Exp $
 */
public class SegmentTree2Test {

    public static void main(String[] args) {
        int[] arr = new int[] {3, 5, 8, 9, 10};
        SegmentTree2 tree2 = new SegmentTree2(arr);
        System.out.println(tree2.queryRangeSum(1, 3));
        tree2.update(1, 3, 2);
        System.out.println(tree2.queryRangeSum(1, 3));
        System.out.println(tree2.queryRangeSum(1, 2));
        System.out.println(tree2.queryRangeSum(2, 4));
    }

}