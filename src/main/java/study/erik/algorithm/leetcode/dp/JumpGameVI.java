/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yueyi
 * @version : JumpGameVI.java, v 0.1 2021年08月05日 9:11 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class JumpGameVI {

    @LetCodeCommit(title = "Jump Game VI")
    public int maxResult(int[] nums, int k) {
        return resolveWithDp(nums, k);
    }

    /**
     * 代码很好写，但是TLE——单靠dp是不行的，里面有太多的无价值的重复运算。
     * 这时候就要靠我们的单调队列了。
     * 下面我们自己实现了单调队列，成绩还不错。77.41%, 77.51%
     * 注意1：实现自己的单调队列——用deQueue来实现
     * 注意2：还可以把空间在优化下——把dp数据删掉，融合到队列中即可——测试了，成绩 75.99%， 81.86%
     * 可见时间成绩下降了，而相应空间成绩也没有升太多
     *
     * @param nums
     * @param k
     * @return
     */
    public int resolveWithDp(int[] nums, int k) {
        int[] dp = new int[nums.length];
        // dp[i] = max(dp[i-1],dp[i-2],...,dp[i-k]);
        dp[0] = nums[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(0);
        // deque的head是当前窗口k中的最大dp[i]值
        for (int i = 1; i < dp.length; i++) {

            dp[i] = dp[deque.peekFirst()] + nums[i];

            Integer tailIndex = deque.peekLast();
            while (tailIndex != null && dp[tailIndex] < dp[i]) {
                deque.removeLast();
                tailIndex = deque.peekLast();
            }
            deque.addLast(i);

            Integer headIndex = deque.peekFirst();
            while (headIndex <= i - k) {
                deque.removeFirst();
                headIndex = deque.peekFirst();
            }
        }
        return dp[nums.length - 1];
    }

    public int resolveWithDpWithOptimal(int[] nums, int k) {

        Deque<Pair<Integer, Integer>> deque = new LinkedList<>();
        // pair{ key = index, value = dp[index] }
        deque.addFirst(new Pair<>(0, nums[0]));
        // deque的head是当前窗口k中的最大dp[i]值
        for (int i = 1; i < nums.length; i++) {

            int curMaxScore = deque.peekFirst().getValue() + nums[i];

            Pair<Integer, Integer> last = deque.peekLast();
            while (last != null && last.getValue() < curMaxScore) {
                deque.removeLast();
                last = deque.peekLast();
            }
            deque.addLast(new Pair<>(i, curMaxScore));

            Pair<Integer, Integer> head = deque.peekFirst();
            while (head.getKey() <= i - k) {
                deque.removeFirst();
                head = deque.peekFirst();
            }
        }
        return deque.peekLast().getValue();
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   k;
    @Parameter(2)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[1,-1,-2,4,-7,3]"), 2, 7},
                {ArrayUtils.buildArray("[10,-5,-2,4,0,3]"), 3, 17},
                {ArrayUtils.buildArray("[1,-5,-20,4,-1,3,-6,-3]"), 2, 0}
        };
    }

    @Test
    public void test_nums() {
        Assert.assertEquals(expect, maxResult(nums, k));
    }
}