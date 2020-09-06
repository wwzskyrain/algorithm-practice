package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-09-05 08:35
 */
public class PermutationSequence {

    @LetCodeCommit(title = "60. Permutation Sequence", diff = "m",
            selfRemark = "回溯法，不排序的，但是成绩不好。然后我们写一个排序的来")
    public String getPermutation(int n, int k) {
        return solution(new boolean[n], new int[]{k}, new char[n], 0);
    }

    private String solution(boolean[] nums, int[] k, char[] num, int curIndex) {

        if (curIndex == nums.length) {
            if (k[0] == 1) {
                return String.valueOf(num);
            } else {
                k[0]--;
                return "";
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (!nums[i]) {
                num[curIndex] = (char) (i + '1');
                nums[i] = true;
                String result = solution(nums, k, num, curIndex + 1);
                if (!result.equals("")) {
                    return result;
                }
                nums[i] = false;
            }
        }
        return "";
    }

    @Test
    public void test_solution_1() {
        int n = 3, k = 3;
        Assert.assertEquals("213", getPermutation(n, k));
    }

    @Test
    public void test_solution_2() {
        int n = 4, k = 9;
        Assert.assertEquals("2314", getPermutation(n, k));
    }


}
