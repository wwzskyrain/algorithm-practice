package study.erik.algorithm.leetcode.hard.array;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/8/4 ，时间：10:23
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class PartitionArrayIntoTwoArraysToMinimizeSumDifference {

    @LetCodeCommit(title = "2035. Partition Array Into Two Arrays to Minimize Sum Difference",
            diff = "h",
            selfRemark = "又是一个综合hard题目——没有单独的考点，甚至没有单独的难点。" +
                    "剩下的就是对题意的见招拆招并不断优化")
    public int minimumDifference(int[] nums) {
        int N = nums.length;
        int nhalf = N / 2;

        int[] left = Arrays.copyOfRange(nums, 0, nhalf);
        int[] right = Arrays.copyOfRange(nums, nhalf, N);

        Map<Integer, Set<Integer>> leftSums = new HashMap<>();
        Map<Integer, Set<Integer>> rightSums = new HashMap<>();
        dfs(left, 0, 0, 0, leftSums);
        dfs(right, 0, 0, 0, rightSums);

        int ans = Math.abs(sum(left) - sum(right));
        int total = sum(nums);
        int half = total / 2;

        for (int k = 1; k < nhalf; k++) {
            //左边选出k个
            Set<Integer> leftSet = leftSums.get(k);
            //右边选出 nhalf-k 个
            Set<Integer> rightSet = rightSums.get(nhalf - k);

            int[] rightSorted = new int[rightSet.size()];
            int i = 0;
            for (Integer r : rightSet) rightSorted[i++] = r;
            Arrays.sort(rightSorted);

            for (Integer x : leftSet) {
                int remain = half - x;
                //相对于与rightSet中一个一个的比对，排序+二分查找会提高一些效率
                int idx2 = binarySearchLeft(rightSorted, remain);
                for (int q = 0; q < 2; q++) {
                    if (0 <= idx2 - q && idx2 - q < rightSorted.length) {
                        int leftAnsSum = x + rightSorted[idx2 - q];
                        int rightAnsSum = total - leftAnsSum;
                        int diff = Math.abs(leftAnsSum - rightAnsSum);
                        ans = Math.min(ans, diff);
                    }
                }
            }
        }
        return ans;
    }

    public static void dfs(int[] nums, int i, int currSum, int elemCount, Map<Integer, Set<Integer>> kSums) {
        if (i == nums.length) {
            kSums.putIfAbsent(elemCount, new HashSet<>());
            kSums.get(elemCount).add(currSum);
            return;
        }
        dfs(nums, i + 1, currSum, elemCount, kSums);
        dfs(nums, i + 1, currSum + nums[i], elemCount + 1, kSums);
    }

    public static int binarySearchLeft(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }


    public int sum(int[] nums) {
        return Arrays.stream(nums).sum();
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray("[3,9,7,3]")},
                {72, ArrayUtils.buildArray("[-36,36]")},
                {0, ArrayUtils.buildArray("[2,-1,0,4,-2,-9]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, minimumDifference(nums));
    }

}
