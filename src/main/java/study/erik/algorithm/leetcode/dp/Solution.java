package study.erik.algorithm.leetcode.dp;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author erik.wang
 * @date 2019/04/12
 **/
public class Solution {

    @Test
    public void test_min_mum_total() {

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        Assert.assertEquals(11, minimumTotal(triangle));
    }

    /**
     * title = DynamicProgramming
     * 没有通过的是'DFS深度遍历'，其写法优美，令自己折服，然而，超时了。
     * 通过的解法是：备忘录法，其中用输入数据做了'使用空间'，即空间复杂度为O(1)，要比要求的O(n)要厉害的多
     * 相关题目：
     * Partition to K Equal Sum Subsets
     * Image Overlap
     * Odd Even Jump
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        if (triangle.size() == 0) {
            return 0;
        }
        if (triangle.get(0).size() == 0) {
            return 0;
        }
        //从最后一行开始
        for (int i = triangle.size() - 1; i >= 0; i--) {

            for (int j = 0; j < triangle.get(i).size(); j++) {

                Integer value = triangle.get(i).get(j);
                if (i == triangle.size() - 1) {
                    continue;
                } else {
                    int minPath = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                    triangle.get(i).set(j, value + minPath);
                }
            }
        }
        return triangle.get(0).get(0);
    }

    /**
     * 返回以结点(level,column)为子树的小路径和
     * 算法没问题，用层次遍历，写法简单扼要，但是会超时。要改
     * 未通过的解法，超时了。
     *
     * @param triangle
     * @param level    当前层数，从0开始计数
     * @param column   当前index，索引嘛，当然从0开始计数了
     * @return
     */
    private int findMinPathSum(List<List<Integer>> triangle, int level, int column) {

        if (level == triangle.size() - 1) {
            return triangle.get(level).get(column);
        }

        return triangle.get(level).get(column) +
                Math.min(findMinPathSum(triangle, level + 1, column),
                        findMinPathSum(triangle, level + 1, column + 1));

    }

    /**
     * 讲一个故事：清朝有位举人，他有K个儿子，分别是老大、老二、老三、、、、老k；
     * 在儿子长大的过程中，举人常常儿子银子，并嘱咐他们攒着以后娶媳妇用。举人算了一下，
     * 他每次拿出的银子不一定都一样，他还不想偏袒哪位而想着，一次一次的拿出银子，每次给
     * 一个儿子，到了最后一期，给所有儿子的银子都一样的多。
     * 于是他就先计算出来target，每次给银子都从老大开始，如果这个儿子当前已经够了，就给下一个，一次类推。
     *
     * @param groups length = k，group[i]表示第i个子集的和
     * @param row    nums的当前访问下标
     * @param nums   输入数据，正序排列，都是正整数
     * @param target k个子集的和
     * @return 是否存在，对于 i 属于 (0...k-1) group[i] =  target
     */
    public boolean search(int[] groups, int row, int[] nums, int target) {

        // 所有的nums[row]都进入了正确的组。
        if (row < 0) {
            return true;
        }
        int v = nums[row--];

        /**
         * 只有全部元素都用上了，即每一个nums[row]都分了组，而且每一个进入一个组的nums[i]，其所在的组必须能成组，
         * 才返回true；
         */
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] + v <= target) {
                groups[i] += v;
                if (search(groups, row, nums, target)) return true;
                groups[i] -= v;
            }
            //加速失败，经过了上的一个if后，却group[i]中却没有能留住一个元素，太失败了。
            // 当然，经过了上一个if，group[i]却没有'和为target'，这也是只用失败，单不是太失败，还有希望
            if (groups[i] == 0) break;
        }
        return false;
    }

    /**
     * title = Partition to K Equal Sum Subsets
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsetsI(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int target = sum / k;

        Arrays.sort(nums);
        int row = nums.length - 1;
        if (nums[row] > target) return false;
        while (row >= 0 && nums[row] == target) {
            row--;
            k--;
        }
        return search(new int[k], row, nums, target);
    }

    @Test
    public void test_can_partion_k_subsets() {

        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;

        System.out.println(canPartitionKSubsets(nums, k));
    }

    enum Result {TRUE, FALSE}

    boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {
            memo[used] = Result.FALSE;
            int targ = (todo - 1) % target + 1;
            for (int i = 0; i < nums.length; i++) {
                if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                    if (search(used | (1 << i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
                }
            }
        }
        return memo[used] == Result.TRUE;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;

        Result[] memo = new Result[1 << nums.length];
        memo[(1 << nums.length) - 1] = Result.TRUE;
        return search(0, sum, memo, nums, sum / k);
    }


    /**
     * title = Maximum Subarray
     * 经典问题：最大子串和
     * 依稀记得一个算法
     * 设置m[i]是以nums[i]结尾的字符串中最大字符串的和
     * 则m[i]=max( m[i-1] + num[i] , num[i])
     * 即在m[i-1]的基础上，m[i]要么就是把num[i]续接上以num[i-1]为结尾的子串，要么就单干
     * 而"最大子串和"=max(m[0...length-1])。
     * 在写程序是m数组可以不需要
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        int r = nums[0];
        int m = nums[0];
        for (int i = 1; i < nums.length; i++) {
            m = Math.max(m + nums[i], nums[i]);
            r = Math.max(r, m);
        }
        return r;
        // TODO: 2019/4/17 next challenge
//         1 degree-of-an-array
//         2 longest-turbulent-subarray
    }

    @Test
    public void test_max_sub_array() {

        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Assert.assertEquals(6, maxSubArray(nums));
    }


}
