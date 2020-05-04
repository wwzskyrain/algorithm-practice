package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-03 22:26
 */
public class RemoveBoxes {

    @Test
    public void test_solution() {

        int[] boxes1 = new int[]{1, 2};
        Assert.assertEquals(2, removeBoxes(boxes1));

        int[] boxes = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
        Assert.assertEquals(23, removeBoxes(boxes));

    }

    /**
     * title = Remove Boxes
     *
     * @param boxes
     * @return
     */
    public int removeBoxes(int[] boxes) {
        return solution(boxes);
    }

    public int solution(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }
        int l = boxes.length;
        int[][][] dp = new int[l][l][l];
        return helpWithDfs(dp, 0, l - 1, 0, boxes);
    }

    public int helpWithDfs(int[][][] dp, int i, int j, int k, int[] box) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return (k + 1) * (k + 1);
        }
        int result = 0;
        if ((result = dp[i][j][k]) != 0) {
            return result;
        }
        result = Math.max(result, helpWithDfs(dp, i + 1, j, 0, box) + (k + 1) * (k + 1));
        for (int m = i + 1; m <= j; m++) {
            if (box[i] == box[m]) {
                result = Math.max(result,
                        helpWithDfs(dp, i + 1, m - 1, 0, box) +
                                helpWithDfs(dp, m, j, k + 1, box));
            }
        }
        dp[i][j][k] = result;
        return result;
    }

}
