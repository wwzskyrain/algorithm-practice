package study.erik.algorithm.think.recursive;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/20
 **/
public class TailRecursive {

    @Test
    public void test_fac() {

        Assert.assertEquals(120, factorial(5));

        Assert.assertEquals(120, factorialTailRecursive(5, 1));
    }

    /**
     * 阶乘函数本来就可以写成'尾递归'，干嘛还需要变换到'尾递归'呢? <br/>
     * 哈哈，这其实不是'尾递归'的。因为局部变量n还是在当前调用栈中的。<br/>
     * 变形为'尾递归'的样子{@link TailRecursive#factorialTailRecursive(int, int)}
     *
     * @param n
     * @return
     */
    public int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }

    /**
     * 这里是'阶乘函数'的'尾递归'实现方式。
     * 所谓尾递归，就是递归调用发生在最后return语句中，而且return语句不掺杂任何局部变量；
     * 这就要将局部变量想方设法传入的递归调用的参数中。
     *
     * @param n
     * @param cur
     * @return
     */
    public int factorialTailRecursive(int n, int cur) {
        if (n == 1) {
            return cur;
        }

        return factorialTailRecursive(n - 1, n * cur);
    }

    /**
     * 递归形式的fib；这是一个中尾递归吗？递归调用的位置在return中，算尾递归；但是他有两个调用出，还算尾递归吗？
     * 如果是一维递归，是一个尾递归；二维就不好说了。
     * 二维应该不是了。尾递归的价值在于，当执行到下一个递归调用时，是不用在保留当前调用的上下文的；
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 尾递归形式的'斐波那契函数'实现
     * @param n 其实只是一个计数器
     * @param first 前一个斐波那契数，也是前一个函数的值
     * @param second 当前的斐波那契额数，也就是当前的函数值
     * @return
     */
    public int fibTailRecursive(int n, int first, int second) {

        if (n == 2) {
            return second;
        }
        return fibTailRecursive(n - 1, second, first + second);
    }

    @Test
    public void test_fib() {
        Assert.assertEquals(5, fib(5));
        Assert.assertEquals(5, fibTailRecursive(5, 1, 1));
    }

}
