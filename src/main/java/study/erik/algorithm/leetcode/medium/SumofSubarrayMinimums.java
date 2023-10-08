package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-04-14 17:12
 */
@LetCodeCommit(title = "907. Sum of Subarray Minimums")
public class SumofSubarrayMinimums {

    /**
     * title = https://leetcode.com/problems/sum-of-subarray-minimums
     *
     * @param A
     * @return
     */
    public int sumSubarrayMins(int[] A) {
        return solution(A);
    }


    /**
     * 成绩：98和55
     * 分析：很容易就能找到规律，因为这个题目和'最小柱状图面积'很相似。
     * 遍历数组，找到a[i]的左右边界，边界是和a[i]距离最近的小于a[i]的元素；
     * 这一看就想到了'递增栈'。
     * 但是这个问题如果没有这个背景输入，就麻烦了。
     * 首先，不能囿于找子数组，因为子数组是n*n的复杂度，所以必须要能转化。
     * 以a[i]为结尾并a[i]为最小值的个数为    left
     * 以a[i]为开头并a[i]为最小值的个数为    right
     *
     * @param A
     * @return
     */
    public int solution(int[] A) {
        if (A.length == 1) {
            return A[0];
        }

        int Mod = 1000000007;
        A = Arrays.copyOf(A, A.length + 1);
        A[A.length - 1] = Integer.MIN_VALUE;
        int[] stack = new int[A.length];
        int index = 0;
        long count = 0L;
        int i = 0;
        while (i < A.length) {
            if (index == 0) {
                stack[index++] = i;
                i++;
                continue;
            }

            int peekA = A[stack[index - 1]];
            if (A[i] >= peekA) {
                stack[index++] = i;
                i++;
                continue;
            }
            // 出栈呀
            int peek = stack[index - 1];
            int left = index == 1 ? -1 : stack[index - 2];
            int right = i;
            //注意要转化成long，不然会溢出的。
            count = (count + (long) (peek - left) * (right - peek) * peekA % Mod) % Mod;
            index--;

        }

        return ((int) count);


    }

    @Test
    public void test_() {
//        Assert.assertEquals(209, sumSubarrayMins(new int[]{59, 91}));
        Assert.assertEquals(17, sumSubarrayMins(new int[]{3, 1, 2, 4}));
    }

}
