package study.erik.algorithm.leetcode.hard.array;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期：2023/8/3 ，时间：15:18
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class MaximumNumberOfWaysToPartitionAnArray {

    @LetCodeCommit(title = "2025. Maximum Number of Ways to Partition an Array",
            selfRemark = "这题目我不会，我想也想不起来。" +
                    "哎，刷的没有意思了。")
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        long[] pref = new long[n];
        long[] suff = new long[n];

        //store prefix and suffix sum
        pref[0] = nums[0];
        suff[n - 1] = nums[n - 1];
        for (int i = 1; i < n; ++i) {
            pref[i] = pref[i - 1] + nums[i];
            suff[n - 1 - i] = suff[n - i] + nums[n - 1 - i];
        }
        // for(int i=n-2; i>=0; --i) suff[i] = suff[i+1] + nums[i];

        long ans = 0;
        Map<Long, Long> left = new HashMap<>(), right = new HashMap<>();

        //intially store the differences in the hashmap right
        for (int i = 0; i < n - 1; ++i) {
            long key = pref[i] - suff[i + 1];
            right.put(key, right.getOrDefault(key, 0L) + 1);
        }


        if (right.containsKey(0L)) {
            ans = right.get(0L);
        }
        for (int i = 0; i < n; ++i) {

            //find the number of pivot indexes when nums[i] is changed to k
            long curr = 0, diff = k - nums[i];
            if (left.containsKey(diff)) curr += left.get(diff);
            if (right.containsKey(-diff)) curr += right.get(-diff);

            //update answer
            ans = Math.max(ans, curr);

            //transfer the current element from right to left
            if (i < n - 1) {
                long dd = pref[i] - suff[i + 1];
                left.put(dd, left.getOrDefault(dd, 0L) + 1);
                right.put(dd, right.getOrDefault(dd, 0L) - 1);
                if (right.get(dd) == 0) right.remove(dd);
            }
        }
        return (int) ans;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildArray("[2,-1,2]"), 3},
                {2, ArrayUtils.buildArray("[0,0,0]"), 1},
                {4, ArrayUtils.buildArray("[22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14]"), -33},
                {1, ArrayUtils.buildArray("[2,-1,2]"), 3},

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
        Assert.assertEquals(expect, waysToPartition(nums, k));
    }

}
