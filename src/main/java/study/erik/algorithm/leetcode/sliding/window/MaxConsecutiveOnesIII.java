package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-06-14 22:06
 */
public class MaxConsecutiveOnesIII {

    @Test
    public void test_() {
        Assert.assertEquals(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2), 6);
        Assert.assertEquals(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3), 10);
    }

    /**
     * ok,一遍通过，成绩99和65，相当不错啦
     *
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        if (A.length < K) {
            return K;
        }
        int start = 0;
        int maxLength = 0;
        int count0 = 0;
        for (int end = 0; end < A.length; end++) {
            if (A[end] == 0) {
                count0++;
            }
            while (count0 > K) {
                if (A[start++] == 0) {
                    count0--;
                }
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

}
