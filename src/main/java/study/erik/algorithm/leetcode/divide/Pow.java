package study.erik.algorithm.leetcode.divide;

import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-11 08:45
 * @description
 */
public class Pow {

    /**
     * title = Pow(x, n)
     * url = https://leetcode.com/problems/powx-n/submissions/
     * 这是分治法的第一题
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        return solution1(x, n);
    }

    /**
     * 这是递归解法；超时了。优化方案
     * 1.用备忘录记录一下子问题的解
     * 2.直接从下向上来解答吧
     *
     * @param x
     * @param n
     * @return
     */
    public double solution1(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 1) {
            return 1;
        }
        if (x == -1) {
            if (n % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        if (n < 0) {
            return divide(1 / x, -n);
        } else {
            return divide(x, n);
        }
    }

    public double divide(double x, int n) {
        if (n == 1) {
            return x;
        }
        double result = divide(x, n / 2);
        if (n % 2 == 0) {
            return result * result;
        }
        return result * result * x;
    }


    @Test
    public void test_myPow() {
        System.out.println(myPow(2, 10));
        System.out.println(myPow(2.1, 3));
        System.out.println(myPow(2.00000, -2));
        System.out.println(myPow(1, -2147483648));
        System.out.println(myPow(-1, -2147483648));
    }

}
