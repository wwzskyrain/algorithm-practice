package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author erik.wang
 * @date 2019/05/30
 **/
public class JumpGameI {

    /**
     * 不做总结，等于白做
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        return solution3(nums);
    }

    /**
     * 成绩：98% 和 52%
     * 解法分析：从左边向右走，不断的更新'最大达到下标'。
     * 这是新想出来的做法。
     * 相比较solution1，该解法跟省空间，速度更快。
     * @param nums
     * @return
     */
    public boolean solution2(int[] nums) {

        int maxArrivedIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxArrivedIndex) {
                // 只有是在maxArrivedIndex之内的才是有效的。
                int arriveIndex = nums[i] + i;
                maxArrivedIndex = Math.max(maxArrivedIndex, arriveIndex);
                if (maxArrivedIndex >= nums.length - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 有一种解法：递推式的自底向上推研法的入队出队实现。
     * 效果没有solution2好，因为解法二是只需要一次迭代就可以了。
     * 等等，这个题好奇怪呀，如果没有0的话，那么必定是会到的。
     * 所以，要是不能到的话，就是到了只能到坏点。
     * @param nums
     * @return
     */
    public boolean solution3(int[] nums) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            Integer index = queue.poll();
            if (index >= nums.length - 1) {
                return true;
            }
            for (int i = 1; i <= nums[index]; i++) {
                queue.add(index + i);
            }
        }
        return false;
    }

    @Test
    public void test_solution() {

        int[] array1 = new int[] {2, 3, 1, 1, 4};
        int[] array2 = new int[] {3, 2, 1, 0, 4};
        int[] array3 = new int[] {0};

        Assert.assertTrue(canJump(array1));
        Assert.assertFalse(canJump(array2));
        Assert.assertTrue(canJump(array3));
    }

}
