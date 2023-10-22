package study.erik.algorithm.leetcode.array.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 日期：2023/10/18 ，时间：10:17
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximal_Score_After_Applying_K_Operations {

    @LetCodeCommit(title = "2530. Maximal Score After Applying K Operations",
            selfRemark = "medium，太简单了，就是一个堆啊")
    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            queue.add(num);
        }
        long total = 0L;
        while (k-- > 0) {
            if (queue.isEmpty()) {
                return total;
            }
            Integer score = queue.poll();
            total += score;
            score = score / 3 + (score % 3 == 0 ? 0 : 1);
            queue.add(score);
        }
        return total;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {50, ArrayUtils.buildArray("[10,10,10,10,10]"), 5},
                {17, ArrayUtils.buildArray("[1,10,3,3,3]"), 3},
                {50, ArrayUtils.buildArray("[10,10,10,10,10]"), 5},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxKelements(nums, k));
    }

}
