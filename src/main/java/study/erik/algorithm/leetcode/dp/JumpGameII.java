package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @author erik.wang
 * @date 2020-03-11 15:01
 * @description
 */
public class JumpGameII {


    /**
     * title = jump game II
     * url = https://leetcode.com/problems/jump-game-ii/
     * 问题分析：找到最小跳跃次数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        return solution4(nums);
    }


    public int solution1(int[] nums) {
        int length = nums.length;
        int[] minStep = new int[length];

        for (int i = 0; i < length - 1; i++) {
            minStep[i] = Integer.MAX_VALUE;
        }

        minStep[length - 1] = 0;

        for (int i = length - 2; i >= 0; i--) {

            int min = length;
            for (int j = 1; j <= nums[i] && j + i < length; j++) {
                min = Math.min(min, minStep[j + i]);
            }
            minStep[i] = min + 1;
        }

        return minStep[0];
    }

    /**
     * 成绩：15% 和30%
     * 成绩不好。解法分析:和JumpGameI的solution2一样，
     * 采用span覆盖法
     * @param nums
     * @return
     */
    public int solution2(int[] nums) {

        int[] minSteps = new int[nums.length];
        minSteps[0] = 0;
        for (int i = 1; i < minSteps.length; i++) {
            minSteps[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < nums.length; i++) {
            int span = nums[i];
            for (int j = 1; j <= span; j++) {
                int index = i + j;
                if (index < nums.length) {
                    minSteps[index] = Math.min(minSteps[index], minSteps[i] + 1);
                }
            }
        }
        return minSteps[nums.length - 1];
    }

    /**
     * 队列法，还是不行的。内存超过。放弃
     *
     * @param nums
     * @return
     */
    public int solution3(int[] nums) {

        PriorityQueue<Node> queue =
                new PriorityQueue<>(
                        (o1, o2) -> o1.time != o2.time ? o1.time - o2.time : o2.maxPosition - o1.maxPosition);
        int minTime = 10000;
        queue.offer(new Node(0, 0, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.index >= nums.length - 1) {
                minTime = Math.min(minTime, node.time);
            } else if (node.maxPosition >= nums.length - 1) {
                minTime = Math.min(minTime, node.time + 1);
            }
            if (node.time < minTime - 1) {
                for (int i = 1; i <= nums[node.index]; i++) {
                    if (i + node.index >= nums.length) {
                        break;
                    }
                    Node newNode = new Node(node.index + i, node.time + 1, node.index + i + nums[node.index + i]);
                    queue.add(newNode);
                }
            }
        }
        return minTime;
    }

    public int solution4(int[] nums) {

        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 10000;
        }
        // dp[i]表示最小跳数
        dp[0] = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (j + i >= nums.length) {
                    break;
                }
                dp[j + i] = Math.min(dp[j + i], dp[i] + 1);
            }
        }
        return dp[nums.length - 1];
    }

    public static class Node {
        public int index;
        public int time;
        public int maxPosition;

        public Node(int index, int time, int maxPosition) {
            this.index = index;
            this.time = time;
            this.maxPosition = maxPosition;
        }
    }

    @Test
    public void test_jump() {
        int[] array1 = new int[] {2, 3, 1, 1, 4};
        int[] array2 = new int[] {2, 3, 0, 1, 4};
        int[] array3 = new int[] {0};
        int[] array4 = new int[] {2, 1};
        int[] array5 = new int[] {8, 2, 4, 4, 4, 9, 5, 2, 5, 8, 8, 0, 8, 6, 9, 1, 1, 6, 3, 5, 1, 2, 6, 6, 0, 4, 8, 6, 0, 3, 2, 8, 7, 6, 5,
                1, 7, 0, 3, 4, 8, 3, 5, 9, 0, 4, 0, 1, 0, 5, 9, 2, 0, 7, 0, 2, 1, 0, 8, 2, 5, 1, 2, 3, 9, 7, 4, 7, 0, 0, 1, 8, 5, 6, 7, 5,
                1, 9, 9, 3, 5, 0, 7, 5};
        Assert.assertEquals(2, jump(array1));
        Assert.assertEquals(2, jump(array2));
        Assert.assertEquals(0, jump(array3));
        Assert.assertEquals(1, jump(array4));
        Assert.assertEquals(13, jump(array5));
    }


}
