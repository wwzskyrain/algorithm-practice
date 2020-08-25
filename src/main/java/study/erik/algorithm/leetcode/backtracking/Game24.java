package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2020-03-29 12:27
 */
public class Game24 {

    public boolean judgePoint24(int[] nums) {
        return solution(nums);
    }

    /**
     * 成绩：67% 和 25%
     * 解法分析：这是一个典型的dfs题，在递归的过程要结合'四则运算'。
     * 任意拿出当前数组中的两个，进行六种四则运算，并把结果重新放入数组；如此往复直到数组中只有一个元素，比较它和target就可以了。
     * 先前也写了一个实现，但是没有考虑到圆括号的场景，就漏掉了很多。
     * 看了下力扣上大家的写法，有直接四重循环+六种组合的，这种四重循环是不能扩展的。我的这个写法是能扩展的。但是有大量的数组复制，也不太好
     * 扩展：
     * 1.  找出所有的解 todo done
     * 2.  构造所有的四则运算 todo
     * 3.  计算带圆括号的四则运算表达式 todo - 查看帖子： https://blog.csdn.net/u012507347/article/details/52245233
     *
     * @param nums
     * @return
     */
    public boolean solution(int[] nums) {
        double[] numsDouble = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsDouble[i] = nums[i];
        }
        return dfs(numsDouble, 24);
    }

    public boolean dfs(double[] nums, int target) {
        if (nums.length == 1) {
            return Math.abs(nums[0] - target) < 0.01;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {

                double[] copyNums = new double[nums.length - 1];
                int index = 0;
                for (int k = 0; k < nums.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    copyNums[index++] = nums[k];
                }

                copyNums[copyNums.length - 1] = nums[i] + nums[j];
                if (dfs(copyNums, target)) {
                    return true;
                }

                copyNums[copyNums.length - 1] = nums[i] - nums[j];
                if (dfs(copyNums, target)) {
                    return true;
                }

                copyNums[copyNums.length - 1] = nums[j] - nums[i];
                if (dfs(copyNums, target)) {
                    return true;
                }

                copyNums[copyNums.length - 1] = nums[i] * nums[j];
                if (dfs(copyNums, target)) {
                    return true;
                }

                if (!(new Double(0).equals(nums[i]))) {
                    copyNums[copyNums.length - 1] = nums[j] / nums[i];
                    if (dfs(copyNums, target)) {
                        return true;
                    }
                }

                if (!(new Double(0).equals(nums[j]))) {
                    copyNums[copyNums.length - 1] = nums[i] / nums[j];
                    if (dfs(copyNums, target)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<List> allResult = new ArrayList<>();

    public void dfs(double[] nums, int target, Deque<String> results) {
        if (nums.length == 1) {
            if (Math.abs(nums[0] - target) < 0.01) {
                allResult.add(new ArrayList<>(results));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {

                double[] copyNums = new double[nums.length - 1];
                int index = 0;
                for (int k = 0; k < nums.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    copyNums[index++] = nums[k];
                }

                copyNums[copyNums.length - 1] = nums[i] + nums[j];
                results.push(String.format("(%f + %f) = %f", nums[i], nums[j], copyNums[copyNums.length - 1]));
                dfs(copyNums, target, results);
                results.pop();


                copyNums[copyNums.length - 1] = nums[i] - nums[j];
                results.push(String.format("(%f - %f) = %f", nums[i], nums[j], copyNums[copyNums.length - 1]));
                dfs(copyNums, target, results);
                results.pop();


                copyNums[copyNums.length - 1] = nums[j] - nums[i];

                results.push(String.format("(%f - %f) = %f", nums[j], nums[i], copyNums[copyNums.length - 1]));
                dfs(copyNums, target, results);
                results.pop();

                copyNums[copyNums.length - 1] = nums[i] * nums[j];
                results.push(String.format("(%f * %f) = %f", nums[j], nums[i], copyNums[copyNums.length - 1]));
                dfs(copyNums, target, results);
                results.pop();


                if (!(new Double(0).equals(nums[i]))) {
                    copyNums[copyNums.length - 1] = nums[j] / nums[i];
                    results.push(String.format("(%f / %f) = %f", nums[j], nums[i], copyNums[copyNums.length - 1]));
                    dfs(copyNums, target, results);
                    results.pop();
                }

                if (!(new Double(0).equals(nums[j]))) {
                    copyNums[copyNums.length - 1] = nums[i] / nums[j];
                    results.push(String.format("(%f * %f) = %f", nums[i], nums[j], copyNums[copyNums.length - 1]));
                    dfs(copyNums, target, results);
                    results.pop();
                }
            }
        }
    }

    @Test
    public void test_dfs_to_find_solution() {


        int[] nums3 = {8, 1, 6, 6};
        double[] nums3Copy = new double[nums3.length];
        for (int i = 0; i < nums3.length; i++) {
            nums3Copy[i] = nums3[i];
        }
        dfs(nums3Copy, 24, new LinkedList<>());
        for (List list : allResult) {
            Collections.reverse(list);
            System.out.println(list);
        }

    }

    @Test
    public void test() {
        int[] nums1 = {1, 2, 1, 2};
        Assert.assertFalse(solution(nums1));

        int[] nums = {8, 4, 6};
        Assert.assertTrue(solution(nums));

        int[] nums2 = {7, 8, 9};
        Assert.assertTrue(solution(nums2));

        int[] nums3 = {3, 5, 1, 1};
        Assert.assertTrue(solution(nums3));
    }
}
