package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/28 10:38
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Collecting_Chocolates {

    @LetCodeCommit(title = "2735. Collecting Chocolates",
            selfRemark = "这个题意比较别扭。")
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[] f = new int[n];
        System.arraycopy(nums, 0, f, 0, n);
        long ans = getSum(f);
        //这个解法就是二层遍历
        for (int k = 1; k < n; k++) {
            //转它k次（最多n-1次）
            for (int i = 0; i < n; i++) {
                //多每一个i都要求k次的最小值（不一定恰恰就在k次）
                f[i] = Math.min(f[i], nums[(i + k) % n]);
            }
            ans = Math.min(ans, (long) k * x + getSum(f));
        }

        return ans;
    }

    public long getSum(int[] f) {
        long sum = 0;
        for (int num : f) {
            sum += num;
        }
        return sum;
    }



}
