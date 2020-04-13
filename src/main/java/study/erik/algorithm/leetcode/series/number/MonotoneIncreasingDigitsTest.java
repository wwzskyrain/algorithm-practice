package study.erik.algorithm.leetcode.series.number;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-03 21:09
 */
public class MonotoneIncreasingDigitsTest {

    /**
     * title = monotone increasing digits
     * url = https://leetcode.com/problems/monotone-increasing-digits/
     *
     * @param N
     * @return
     */
    public int monotoneIncreasingDigits(int N) {
        return solution(N);
    }

    /**
     * 成绩：100% 和 20% ，可喜
     * 考点：这是一个细节题，或者说是一个多状态题目。
     *      考察了'把数字拆成数组'及其逆过程。
     *      还有，有点单调递增栈的意思
     * 收获：对栈的操作更熟练了。
     *
     *
     * @param N
     * @return
     */
    public int solution(int N) {
        int[] digits = new int[9];

        int high = 0;
        while (N > 0) {
            digits[high++] = N % 10;
            N = N / 10;
        }
        high--;
        int[] stack = new int[9];
        int stackIndex = 0;
        while (high >= 0) {
            if (stackIndex == 0) {
                stack[stackIndex++] = digits[high--];
                continue;
            }

            if (stack[stackIndex - 1] <= digits[high]) {
                stack[stackIndex++] = digits[high--];
                continue;
            } else {
                // 要借位了，注意可能出现连续借位
                int value = digits[high];
                while (stackIndex > 0 && stack[stackIndex - 1] > value) {
                    value = stack[stackIndex - 1] - 1;
                    stackIndex--;
                    high++;
                }
                stack[stackIndex++] = value;
                high--;
                while (high >= 0) {
                    stack[stackIndex++] = 9;
                    high--;
                }
                break;
            }
        }
        int result = 0;
        int i = 0;
        while (i < stackIndex) {
            result = result * 10 + stack[i++];
        }

        return result;
    }

    @Test
    public void test_solution() {
        Assert.assertEquals(667999, monotoneIncreasingDigits(668841));
        Assert.assertEquals(9, monotoneIncreasingDigits(10));
        Assert.assertEquals(1234, monotoneIncreasingDigits(1234));
        Assert.assertEquals(299, monotoneIncreasingDigits(332));
    }
}
