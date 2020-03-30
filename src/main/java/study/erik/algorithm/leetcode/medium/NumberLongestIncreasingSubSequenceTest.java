package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-03-30 17:50
 */
public class NumberLongestIncreasingSubSequenceTest {

    /**
     * url = https://leetcode.com/problems/number-of-longest-increasing-subsequence/
     * title = Number of Longest Increasing SubSequence
     * 分析：这个题目不仅要找最长子序列的长度，还要找到这些最长子序列的个数。
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        return solution(nums);
    }

    /**
     * 哎呦，终于通过了，成绩：92% 和 11%
     * 这个题目很好，虽然不是很难。
     * 首先，这个题目就是就"最长自增子序列"的另一种问法。是
     * 一般最值问题都有三种问法：
     * 第一种：问最值，比如，求最长子序列的长度
     * 第二问：问最值的那个解，比如，求出一个最长子序列
     * 第三问：问最值的个数，比如，请有多少个这样的最值。就是本题。
     * 第四问：问所有的最值的解，比如，求出所有做长子序列
     * 对于第一问：
     *      我们是非常熟悉这个题和其解答的，因为它的解法即完美的使用了递归的思想，有在计算时从下向上，没有辅助数组，没有递归调用——只有两个辅助变量，堪称完美。
     * 对于第二问：
     *      既然要求最值的一个解，那就要用到递归调用比较合适了。
     * 对于第三问：
     *      也是求最值，不过求最值的个数。那么就可以在第一问的解答的同时，变形一下来求解。当然，最初我们想到了递归，然而却超时的厉害。
     * 对于第四问：
     *      求所有的子序列。最清晰的解题思路还是用递归。如果非要用第三问的解法，感觉也可以的，就是不太好理解吧。这个以后可以写一写。
     * 该题的解法参考出处： https://www.cnblogs.com/grandyang/p/7603903.html
     * @param nums
     * @return
     */
    public int solution(int[] nums) {
        if (nums.length < 1) {
            return nums.length;
        }
        //以元素nums[i]结尾的最长自增序列的长度
        int[] length = new int[nums.length];
        //在length[i]的基础上，该最长自增序列的个数
        int[] count = new int[nums.length];
        Arrays.fill(length, 1);
        Arrays.fill(count, 1);

        int maxLength = 0;
        int maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            //遍历i之前的所有最长子序列，以得到length[i]和count[i]
            for (int j = 0; j < i; j++) {

                if (nums[i] <= nums[j]) {
                    continue;
                }
                if (length[i] == length[j] + 1) {
                    count[i] += count[j];
                } else if (length[i] < length[j] + 1) {
                    length[i] = length[j] + 1;
                    count[i] = count[j];
                }
            }

            //更新最大值
            if (maxLength == length[i]) {
                maxCount += count[i];
            } else if (maxLength < length[i]) {
                maxLength = length[i];
                maxCount = count[i];
            }
        }
        return maxCount;
    }


    @Test
    public void test_solution() {

        int[] nums3 = {3, 1, 2};
        Assert.assertEquals(1, solution(nums3));

        int[] nums2 = {43, 13, 38, 85, 19, 28, 15, 100, -83, -62, 8, 45, 55, -44, 44, -57, 5, 15, -46, 89, 15, 93, 6, 11, 80, 14, 6, -71, 13, 52, 77, 31, 61, 70, -98, 65, 62, 65, 96, 42, 59, 79, 74, -78, 65, 54, 4, 65, 81, 21, 9, 18, 4, 17, 72, 75, 55, 54, 85, -93, 44, 16, 43, -14, 5, 48, 70, 26, 41, 60, 52, 98, 55, -21, 77, 71, 43, 0, 61, 9, 8, -66, -55, -64, 10, -86, -5, 14, 26, 72, 17, 94, 86, 91, 26, 73, 9, 7, 2, 9, 70, 100, 64, 74, 31, 77, 12, 90, -49, 14, 10, -33, 13, 42, 2, 25, 0, 41, 98, 54, 1, 26, 19, -72, 61, -23, 35, 40, 47, 53, 44, 38, 80, 63, 64, 80, 67, 58};
        Assert.assertEquals(24, solution(nums2));

        int[] nums1 = {2, 2, 2, 2, 2};
        Assert.assertEquals(5, solution(nums1));

        int[] nums = {1, 3, 5, 4, 7};
        Assert.assertEquals(2, solution(nums));
    }

}
