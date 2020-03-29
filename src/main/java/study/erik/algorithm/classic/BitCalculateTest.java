package study.erik.algorithm.classic;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-24 08:59
 */
public class BitCalculateTest {


    /**
     * 来自于：https://blog.csdn.net/qq_41209741/article/details/90741997
     * 用位运算(非、与、或、异或、移位)来实现加法运算
     *
     * @param A
     * @param B
     * @return
     */
    int add(int A, int B) {
        int sum = 0, carry;
        while (B != 0) {
            //对应位的和
            sum = A ^ B;
            //对应位和的进位，既然是进位，就要整体左移一位
            carry = (A & B) << 1;
            A = sum;
            B = carry;
        }
        return sum;
    }

    @Test
    public void test_add() {
        Assert.assertEquals(13, add(11, 2));
        Assert.assertEquals(99, add(98, 1));
    }

    /**
     * 位运算实现 A-B， 代码来自： https://www.cnblogs.com/xiaomoxian/p/5169471.html
     * 注意：-B = (~B+1) ，这是计算机中的只是
     *
     * @param A
     * @param B
     * @return
     */
    public int subtract(int A, int B) {
        return add(A, add(~B, 1));
    }

    @Test
    public void test_subtract() {
        Assert.assertEquals(23, subtract(47, 24));
        Assert.assertEquals(1, subtract(100, 99));
    }

    /**
     * 乘法，不考虑溢出先。
     * 因为是完全的位运算
     *
     * @param A
     * @param B
     * @return
     */
    public int multi(int A, int B) {
        // TODO: 2020-03-24
        int result = 0;
        while (B != 0) {
            if ((B & 1) != 0) {
                //累加所有倍数的A
                result = add(result, A);
            }
            A = A << 1;
            B = B >>> 1;
        }
        return result;
    }

    @Test
    public void test_multi() {
        Assert.assertEquals(24, multi(4, 6));
        Assert.assertEquals(81, multi(9, 9));
    }

    /**
     * 位运算实现除法
     *
     * @param A
     * @param B
     * @return
     */
    public int divide(int A, int B){
        // TODO: 2020-03-29  
        return 0;
    }

}
