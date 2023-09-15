package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author erik.wang
 * @date 2020-07-13 16:17
 * title = Sliding Window Maximum
 */
public class SlidingWindowMaximum {

    @Test
    public void test_() {

        int[] nums2 = {1, -1};
        System.out.println(Arrays.toString(maxSlidingWindow1(nums2, 1)));

        int[] nums1 = {1};
        System.out.println(Arrays.toString(maxSlidingWindow1(nums1, 1)));

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow1(nums, 3)));


    }

    /**
     * 成绩：17和12 ，还能再低吗？
     * 成绩：42和24， Deque的话，用LinkedList
     * 成绩：89和87， 这下心安了-见解法2
     * 整理之后，逻辑就清楚了。
     * 这里维护了一个有序队列，并且以当前元素为最小元素，即小于当前元素的都会被poll。
     *
     * @param nums
     * @param k
     * @return
     */
    @LetCodeCommit(no = 239, title = "Sliding Window Maximum",
            selfRemark = "这个题目是我们的拿手好题，因为完全是自己coding，并且优化的。" +
                    "单点递减队列：入队从队尾，需要维护单调递减特性；出队去队头即可。")
    public int[] maxSlidingWindow1(int[] nums, int k) {

        Deque<Integer> deque = new LinkedList<>();

        int l = 0;
        int[] maxWindow = new int[nums.length - k + 1];
        int maxIndex = 0;
        for (int r = 0; r < nums.length; r++) {
            int num = nums[r];
            //简单点写，直接从右往左，找到该插入的位置，并且一路poll掉哪些太小的元素
            while (!deque.isEmpty() && deque.getLast() < num) {
                deque.pollLast();
            }
            deque.addLast(num);
            //加入之后，刚好是一个窗口——长度为k，于是就要计算窗口的最值了。
            if (r - l + 1 == k) {
                maxWindow[maxIndex++] = deque.getFirst();
                if (deque.getFirst() == nums[l]) {
                    deque.pollFirst();
                }
                l++;
            }
        }
        return maxWindow;
    }


    /**
     * 成绩：89 和 87， 心安了
     * 思路和解法1一样的，而且这个思路我们也是很认可的。
     * 对比解法1，这里就是自己实现了Deque，用数组的方式，而且是循环数组的方式。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {

        //循环递减数组只需要窗口的大小
        int[] deque = new int[k];
        int tail = 0;
        int head = 0;

        int l = 0;
        int[] maxWindow = new int[nums.length - k + 1];
        int maxIndex = 0;
        for (int r = 0; r < nums.length; r++) {
            int num = nums[r];
            //简单点写，直接从右往左，找到该插入的位置，并且一路poll掉哪些太小的元素
            //tail == head  is empty
            //(tail + 1)%k == head  is full.
            while (tail != head && deque[(tail - 1 + k) % k] < num) {
                tail = (tail - 1 + k) % k;
            }
            deque[tail] = num;
            tail = (tail + 1) % k;
            //加入之后，刚好是一个窗口——长度为k，于是就要计算窗口的最值了。
            if (r - l + 1 == k) {
                maxWindow[maxIndex++] = deque[head];
                if (deque[head] == nums[l]) {
                    head = (head + 1) % k;
                }
                l++;

            }
        }
        return maxWindow;
    }

}
