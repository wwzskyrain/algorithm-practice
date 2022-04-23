/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.heap;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.PriorityQueue;

/**
 * @author yueyi
 * @version : KthLargestElementInAStream.java, v 0.1 2022年04月23日 11:09 上午 yueyi Exp $
 */
@LetCodeCommit(title = "703. Kth Largest Element in a Stream",
        diff = "e",
        selfRemark = "堆排序的应用。如果真让我写对排序，我写的出来吗？")
public class KthLargestElementInAStream {

    class KthLargest {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int                    k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            for (int num : nums) {
                minHeap.add(num);
                while (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }

        public int add(int val) {
            minHeap.add(val);
            while (minHeap.size() > k) {
                minHeap.poll();
            }
            return minHeap.peek();
        }
    }

}