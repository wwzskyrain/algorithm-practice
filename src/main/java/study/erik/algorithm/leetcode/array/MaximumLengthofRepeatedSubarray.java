package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-04 14:32
 */
public class MaximumLengthofRepeatedSubarray {


    @LetCodeCommit(no = 718, title = "Maximum Length of Repeated Subarray",
            time = 58, space = 5, diff = "m",
            related = "Minimum Size Subarray Sum",
            selfRemark = "第一次用了一个brute，代码很简洁，但是耗时太多，虽然也通过了；然后用了dp，耗时少了些，空间有多了")
    public int findLength(int[] A, int[] B) {

        int[][] dp = new int[A.length][B.length];

//      dp[i][j] 以A[i]和B[j]为尾元素的subArray的最长length;
//      dp[i][j] =  dp[i-1][j-1] + 1   --- A[i]==B[j]
//      dp[i][j] =  0   --- A[i]!=B[j]
        int maxLength = 0;
        for (int j = 0; j < B.length; j++) {
            dp[0][j] = (A[0] == B[j] ? 1 : 0);
        }
        for (int i = 0; i < A.length; i++) {
            dp[i][0] = (B[0] == A[i] ? 1 : 0);
        }

        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                dp[i][j] = (A[i] != B[j] ? 0 : (dp[i - 1][j - 1] + 1));
                maxLength = Math.max(dp[i][j], maxLength);
            }
        }
        return maxLength;
    }

    @Test
    public void test_case_1() {
        int[] A = {1, 2, 3, 2, 1};
        int[] B = {3, 2, 1, 4, 7};
        Assert.assertEquals(3, findLength(A, B));
    }
}
