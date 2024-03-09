package study.erik.algorithm.leetcode.array.hard;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/3/9 11:46
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Find_the_K_Sum_of_an_Array {

    @LetCodeCommit(title = "2386. Find the K-Sum of an Array",
            selfRemark = "这题很多人都说它很难但是又很喜欢，因为它是真的很有深度。" +
                    "请参见：https://github.com/wisdompeak/LeetCode/tree/master/Priority_Queue/2386.Find-the-K-Sum-of-an-Array")
    public long kSum(int[] nums, int k) {
        // 1. 转化为元素绝对值后求第k-1小的序列和
        // 2. 接小顶堆和构造的特殊而又简单的遍历方式，找到第k-1个序列和
        // 3. 正元素的sum-(第 k-1 序列和)
        long maxSum = 0;
        long[] numsCopy = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxSum += nums[i];
            }
            numsCopy[i] = Math.abs(nums[i]);
        }
        if (k == 1) {
            return maxSum;
        }
        Arrays.sort(numsCopy);
        PriorityQueue<Pair<Long, Integer>> q = new PriorityQueue<>(Comparator.comparingLong(Pair::getKey));
        q.add(new Pair<>((long) numsCopy[0], 0));
        for (int i = 0; i < k - 1; i++) {
            Pair<Long, Integer> poll = q.poll();
            long curSum = poll.getKey();
            int tail = poll.getValue();
            if (i == k - 2) {
                return maxSum - curSum;
            }
            if (tail < nums.length - 1) {
                q.add(new Pair<>(curSum + numsCopy[tail + 1], tail + 1));
                q.add(new Pair<>(curSum + numsCopy[tail + 1] - numsCopy[tail], tail + 1));
            }
        }
        return -1;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray("[2,4,-2]"), 5},
                {10, ArrayUtils.buildArray("[1,-2,3,4,-10,12]"), 16},
                {322276537, ArrayUtils.buildArray("[-633923342,-751179047,217252742,-284828656,879227627,969412532,-349793954,-437921947,-924701863,216000860,648647721,731123751,744169330,419285940,-577841367,228657519,-483595977]"), 1164}
        });
    }

    @Parameterized.Parameter
    public long expect;
    @Parameterized.Parameter(1)
    public int[] nums;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, kSum(nums, k));
    }

}
