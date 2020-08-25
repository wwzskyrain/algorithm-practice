package study.erik.algorithm.leetcode.backtracking;

import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-08-25 09:21
 */
public class IncreasingSubsequences {

    @LetCodeCommit(title = "491. Increasing Subsequences")
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> allSubsequence = new HashSet<>();
        Deque<Integer> subSequcence = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            subSequcence.addLast(nums[i]);
            backTrack(nums, i, allSubsequence, subSequcence);
            subSequcence.removeLast();
        }
        return new ArrayList<>(allSubsequence);
    }

    public void backTrack(int[] nums, int index, Set<List<Integer>> allSubsequences, Deque<Integer> subsequence) {

        if (subsequence.size() > 1) {
            allSubsequences.add(new ArrayList<>(subsequence));
        }
        int lastNum = subsequence.getLast();
        for (int i = index + 1; i < nums.length; i++) {
            int n = nums[i];
            if (lastNum <= n) {
                subsequence.addLast(n);
                backTrack(nums, i, allSubsequences, subsequence);
                subsequence.removeLast();
            }
        }
    }

    @Test
    public void test_solution() {
        int[] nums = {4, 6, 7, 7};
        List<List<Integer>> subsequences = findSubsequences(nums);
        for (List<Integer> subsequence : subsequences) {
            System.out.println(subsequence);
        }
    }

}
