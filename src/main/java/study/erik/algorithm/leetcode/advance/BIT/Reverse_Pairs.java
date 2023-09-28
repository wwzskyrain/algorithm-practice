package study.erik.algorithm.leetcode.advance.BIT;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/9/28 ，时间：09:31
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Reverse_Pairs {

    @LetCodeCommit(title = "493. Reverse Pairs",
            selfRemark = "这个题目用merge-sort做过一版本了。" +
                    "现在用binary-index-tree写一遍。" +
                    "遇到的问题:" +
                    "1.lowbit的算法记错了。" +
                    "2.被负数给吓了一下。")
    public int reversePairs(int[] nums) {
        //long是必要的吗，是的，因为num*2很容易越界。
        Set<Long> set = new TreeSet<>();
        for (int num : nums) {
            set.add((long) num);
            set.add(num * 2L);
        }
        int rank = 1;
        Map<Long, Integer> map = new HashMap<>();
        for (Long n : set) {
            map.put(n, rank++);
        }
        BIT bit = new BIT(rank);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int targetRank = map.get(nums[i] * 2L);
            int total = bit.sum(rank);
            ans += (total - bit.sum(targetRank));
            bit.update(map.get((long) nums[i]), 1);
        }
        return ans;
    }

    public static class BIT {
        public int n;
        public int[] arr;

        public BIT(int n) {
            this.n = n;
            arr = new int[n + 1];
        }

        public void update(int index, int delta) {
            while (index < arr.length) {
                arr[index] += delta;
                index += lowBit(index);
            }
        }

        public int sum(int index) {
            int s = 0;
            while (index > 0) {
                s += arr[index];
                index -= lowBit(index);
            }
            return s;
        }

        private int lowBit(int n) {
            return n & (-n);
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray("[1,3,2,3,1]")},
                {3, ArrayUtils.buildArray("[2,4,3,5,1]")},
                {1, ArrayUtils.buildArray("[-5, -3]")},
                {0, ArrayUtils.buildArray("[-7, -3]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, reversePairs(nums));
    }

}
