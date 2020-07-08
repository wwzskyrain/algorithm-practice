package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-07-05 12:02
 * title = Maximum Points You Can Obtain from Cards
 */

public class MaximumPointsYouCanObtainfromCardsTest {


    @Test
    public void test_() {


        int[] cardPoints3 = {96, 90, 41, 82, 39, 74, 64, 50, 30};
        int k3 = 8;
        Assert.assertEquals(maxScore(cardPoints3, k3), 536);

        int[] cardPoints2 = {9, 7, 7, 9, 7, 7, 9};
        int k2 = 7;
        Assert.assertEquals(maxScore(cardPoints2, k2), 55);


        int[] cardPoints1 = {1, 2, 3, 4, 5, 6, 1};
        int k1 = 3;
        Assert.assertEquals(12, maxScore(cardPoints1, k1));
    }


    /**
     * m题，调一调就能通过的(自信),但是成绩太低了，16和5
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {

        int length = cardPoints.length;
        int result = 0;
        int sum = 0;
        int right = Math.min(length, k) - 1;
        int left = 0;
        int p = right;
        while (p >= 0) {
            sum += cardPoints[p--];
        }
        result = sum;
        if (cardPoints.length <= k) {
            return result;
        }

        while (right != length - 1) {
            sum -= cardPoints[right];
            right = (right - 1 + length) % length;
            left = (left - 1 + length) % length;
            sum += cardPoints[left];
            result = Math.max(sum, result);
        }

        return result;
    }



}
