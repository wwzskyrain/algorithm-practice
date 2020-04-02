package study.erik.algorithm.leetcode.series.subsequence;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author erik.wang
 * @date 2020-04-01 17:05
 */
public class IncreasingSubsequencesTest {

    /**
     * url = https://leetcode.com/problems/increasing-subsequences/
     * title = Increasing Subsequences
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        return solution(nums);
    }

    /**
     * 成绩：20% 和 100%
     * 这个题目，扣弄一天。
     * 先是用dfs，并且用栈来保存树的路径，栈还是我们喜欢的数组实现呢。可惜，这个思路不对，因为路径会计算重复。而计算重复也不是个大问题，
     * 用java的set就能解决，而且接下来我们提交AC的方法其中也用了这个方法。所以，一会我们还要再实现一下呢；
     * 我们还是先介绍一下这个被ac的方法。这是我自己想出来的——找规律.叫双集合法。
     * set保存着我们的递增序列。遍历nums数组，首先nums[i]是一个新子序列的开始，当然要加到集合中去。
     * 其次，把之前set中的子序列之后追加nums[i]还会形成新的递增子序列，除此之外，之前在set中的子序列还是要保留。
     * 为了新旧子序列的生成和保存的方便，我们用set1和set2来做。最后把set1中长度大于1的都倒到List<List>中，就可以了。
     * <p>
     * 太晚了，不屑stack方法实现了。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> solution(int[] nums) {

        Set<List<Integer>> set1 = new HashSet<>();
        Set<List<Integer>> set2 = new HashSet<>();


        for (int i = 0; i < nums.length; i++) {
            for (List<Integer> sequence : set1) {
                set2.add(sequence);
                if (sequence.get(sequence.size() - 1) <= nums[i]) {
                    ArrayList<Integer> newSequence = new ArrayList<>(sequence);
                    newSequence.add(nums[i]);
                    set2.add(newSequence);
                }

            }
            set2.add(Arrays.asList(nums[i]));

            set1 = set2;
            set2 = new HashSet<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> subSequence : set1) {
            if (subSequence.size() > 1) {
                result.add(subSequence);
            }
        }
        return result;
    }


    @Test
    public void test_solution() {

        int[] nums2 = {4, 3, 2, 1};
        for (List<Integer> subSequence : findSubsequences(nums2)) {
            System.out.println(subSequence);
        }
        System.out.println("---------");

        int[] nums1 = {4, 6, 7, 7};
        for (List<Integer> subSequence : findSubsequences(nums1)) {
            System.out.println(subSequence);
        }
        System.out.println("---------");
        int[] nums = {1, 3, 5, 7};
        for (List<Integer> subSequence : findSubsequences(nums)) {
            System.out.println(subSequence);
        }

    }

}
