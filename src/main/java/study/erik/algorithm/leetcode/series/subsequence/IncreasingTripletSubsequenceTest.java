package study.erik.algorithm.leetcode.series.subsequence;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-04-03 09:35
 */
public class IncreasingTripletSubsequenceTest {

    public boolean increasingTriplet(int[] nums) {
        return solution2(nums);
    }

    /**
     * 成绩：Memory Limit Exceeded
     * 双set法导致空间复杂度超限
     *
     * @param nums
     * @return
     */
    public boolean solution(int[] nums) {
        List<List> list1 = new ArrayList<>();
        List<List> list2 = new ArrayList<>();

        for (int num : nums) {
            list2.add(new ArrayList(Arrays.asList(num)));
            for (List<Integer> list : list1) {
                list2.add(new ArrayList(list));
                if (list.get(list.size() - 1) < num) {
                    list.add(num);
                    if (list.size() >= 3) {
                        return true;
                    }
                }
                list2.add(list);
            }
            list1 = list2;
            list2 = new ArrayList<>();
        }
        return false;
    }

    /**
     * 成绩：5% 和 67% 可怜的成绩呀，所以这个算法很low；就当操练了这个方法吧。
     * 操练：栈实现深度优先访问。
     *
     * @param nums
     * @return
     */
    public boolean solution1(int[] nums) {
        int[] stack = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            stack[0] = i;
            if (dfs(nums, stack, 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 成绩：5% 和 67% 可怜的成绩呀，所以这个算法很low；就当操练了这个方法吧。
     * 操练：栈实现深度优先访问。
     *
     * @param nums
     * @param stack
     * @param index
     * @return
     */
    private boolean dfs(int[] nums, int[] stack, int index) {

        if (index >= 3) {
            return true;
        }
        int pop = stack[index - 1];
        for (int j = pop + 1; j < nums.length; j++) {
            if (nums[j] > nums[pop]) {
                stack[index] = j;
                if (dfs(nums, stack, index + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 这个代码来自于讨论区 https://leetcode.com/problems/increasing-triplet-subsequence/discuss/560336/Java-code-Beats-100
     * 这个解法是在很快的也很简单。说明这个问题真的可以巧姐。
     * 方法：num1是最小值，num2是找到num1之后的最小值；找到num1和num2后，如果在能找到一个这样的num3，就返回true了。
     * 扩展：当前找到是三个自增序列，也可以扩展到四个、五个、。。。这时候可以用4个、5个局部变量等等。扩展性不好。
     * 而stack+dfs的方法，其扩展性就很好了。
     *
     * @param nums
     * @return
     */
    public boolean solution2(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            if (min1 >= num) {
                min1 = num;
                continue;
            } else if (min2 >= num && min2 > min1) {
                min2 = num;
                continue;
            } else if (num > min2) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test_solution() {

        int[] nums4 = {5, 1, 5, 5, 2, 5, 4};
        Assert.assertTrue(increasingTriplet(nums4));

        int[] nums3 = {2, 1, 5, 0, 4, 6};
        Assert.assertTrue(increasingTriplet(nums3));

        int[] nums2 = {0, 4, 2, 1, 0, -1, -3};
        Assert.assertFalse(increasingTriplet(nums2));

        int[] nums1 = {5, 4, 3, 2, 1};
        Assert.assertFalse(increasingTriplet(nums1));

        int[] nums = {1, 2, 3, 4, 5};
        Assert.assertTrue(increasingTriplet(nums));


    }
}
