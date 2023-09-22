package study.erik.algorithm.leetcode.array.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;

/**
 * 日期：2023/9/18 ，时间：15:16
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Shortest_Subarray_with_Sum_at_Least_K {

    @LetCodeCommit(title = "")
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            //更有优势
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int ans = Integer.MAX_VALUE;
        //  单调递增队列。对于待入队列元素j，如果队列尾巴元素i比j的preSum大，
        //  那么j更能符合区间和>k的条件，而且在取最值的时候，j肯定比i更优了。
        //  所以，需要把i顶出去，这就是维护单调递增队列的定义。
        //  没想到单调队列还能求最值。
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < preSum.length; i++) {
            while (!queue.isEmpty() && preSum[i] - preSum[queue.peekFirst()] >= k) {
                ans = Math.min(ans, i - queue.pollFirst());
            }
            while (!queue.isEmpty() && preSum[queue.peekLast()] >= preSum[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int shortestSubarraySolutionError(int[] nums, int k) {
        //错误case： {3, ArrayUtils.buildArray("[84,-37,32,40,95]"), 167}
        int n = nums.length;
        int left = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            if (sum >= k) {
                while (sum - nums[left] >= k) {
                    sum -= nums[left++];
                }
                min = Math.min(min, right - left + 1);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{{1, ArrayUtils.buildArray("[1]"), 1}, {-1, ArrayUtils.buildArray("[1,2]"), 4}, {3, ArrayUtils.buildArray("[2,-1,2]"), 3}, {2, ArrayUtils.buildArray("[17,85,93,-45,-21]"), 150}, {3, ArrayUtils.buildArray("[84,-37,32,40,95]"), 167}});
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, shortestSubarray(nums, k));
    }

}
