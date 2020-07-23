package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-06-18 21:50
 * <p>
 * title =  Longest Turbulent Subarray
 */
public class LongestTurbulentSubarray {


    @Test
    public void test_() {


        int[] A1 = new int[]{4, 8, 12, 16};
        Assert.assertEquals(2, maxTurbulenceSize(A1));

        int[] A = new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9};
        Assert.assertEquals(5, maxTurbulenceSize(A));
    }

    /**
     * 成绩：100 和 78
     * 一遍通过，这本来是一个'滑动窗口'的题目，结果我用简单的dp做了解答，而且还这么高效，不得不让我怀疑，这个题目的类型。
     * up和down表示以当前元素A[i]结尾的最大长度子数组，up和down分别表示两个趋势
     * @param A
     * @return
     */
    public int maxTurbulenceSize(int[] A) {

        if (A.length <= 1) {
            return A.length;
        }

        int maxLength = 1;
        int up = 1;
        int down = 1;

        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) {
                up = down + 1;
                maxLength = Math.max(maxLength, up);
                down = 1;
            } else if (A[i - 1] > A[i]) {
                down = up + 1;
                maxLength = Math.max(maxLength, down);
                up = 1;
            } else {
                up = 1;
                down = 1;
            }
        }
        return maxLength;

    }


}
