package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-01 09:18
 */
public class TargetSum {

    @Test
    public void test_solution() {

        int[] nums3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Assert.assertEquals(1048576, findTargetSumWays(nums3, 0));

        int[] nums2 = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        Assert.assertEquals(256, findTargetSumWays(nums2, 1));

        int[] nums1 = {1, 0};
        Assert.assertEquals(2, findTargetSumWays(nums1, 1));

        int[] nums = {1, 1, 1, 1, 1};
        Assert.assertEquals(5, findTargetSumWays(nums, 3));
    }

    /**
     * url = https://leetcode.com/problems/target-sum/
     * title = Target Sum
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        return solutionWithDp(nums, S);
    }

    /**
     * 成绩：72 和 100 ，成绩不错，给自己故障
     * 这个题目是01背包问题的的扩展题目，所以一看到题目我们就想到了这个思路，经过了几次处理包含0的case试错之后，就完成了。
     * 解法属于：把目标泛化的套路。
     * <p>
     * dp[i][j] 表示 前i个数时，在题意要求的作用下，其和是j的组合的个数，
     * 这里最好不要把j当做下边，而当做范围在max和min之间的具体值，即具体的每一个可能的+-组合结果
     * 当遍历到第i个数字是：
     * dp[i][j] = dp[i][j-num[i]] + dp[i][j+num[i]]
     * 而原问题则是当dp[nums.length][S].
     * <p>
     * 具体实现时注意的问题：
     * 1.   需要以max和min来作为目标泛化的界限
     * 2.   问：为什么不像01背包的解法中，对二位数组进行优化，优化成一位数组？
     * 3.   答：其实可以优化到行数为2的二位数组，或者用两个一位数组来实现，但是在想优化成一位数组，是不可能的，因为从状态转变公式看出，变化来自两个方向，不太好从一个方向算起的。
     * <p>
     * extension: diss区有一个解法，转化到了子集和问题，而子集和问题就是01背包问题，所以直接解答，很巧妙。
     * 难道转一下就很桥面了吗？当然不是，要看转过去之后，问题是变的多么简单，而01背包问题的解法，我们已经烂熟于心，所以这样的转化才是巧妙的
     * 链接：https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C%2B%2B-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
     *
     * @param nums
     * @param S
     * @return
     */
    public int solutionWithDp(int[] nums, int S) {

        int max = 0, min = 0;
        for (int i = 0; i < nums.length; i++) {
            max += nums[i];
            min -= nums[i];
        }

        if (S > max || S < min) {
            return 0;
        }

        int[][] dp = new int[nums.length][max - min + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0) {
                    //初始化第0行，需要对num[0]=0做特殊处理
                    int firstNum = nums[0];
                    if (firstNum == 0) {
                        dp[0][0 - min] = 2;
                    } else {
                        dp[0][firstNum - min] = 1;
                        dp[0][-firstNum - min] = 1;
                    }
                } else {
                    //注意，这里不需要做j到具体target的转换，因为什么呢，请思考
                    //就问你这个赋值骚不骚，哈哈
                    int k;
                    dp[i][j] = ((k = j + nums[i]) < dp[i].length ? dp[i - 1][k] : 0)
                            + ((k = j - nums[i]) >= 0 ? dp[i - 1][k] : 0);
                }
            }
        }
        return dp[nums.length - 1][S - min];
    }

}
