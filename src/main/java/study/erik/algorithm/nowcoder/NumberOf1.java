package study.erik.algorithm.nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/22
 **/
public class NumberOf1 {

    /**
     * 通过这一题，我们得出一些结论
     * 1.负数是用补码表示的，而且我们知道补码如何表示-1，-2等
     * 2.我们知道int的最小值和最大值
     * 最小值是 -2147483648，   它是2的31次幂的负数,     其计算机内为[01111111111111111111111111111111] 31个1
     * 最大值是 2147483648，    它是2的31次幂-1,        其计算机内为[10000000000000000000000000000000] 31个0
     * 3.计算二进制中1的个数（到现在才点题），用"无符号"右移方法
     * @param n
     * @return
     */
    public int doNumberOf1(int n) {

        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n = n >>> 1;
        }
        return count;
    }

    @Test
    public void test_doNumberOf1() {
        Assert.assertEquals(1, doNumberOf1(1));
        Assert.assertEquals(32, doNumberOf1(-1));
        Assert.assertEquals(1, doNumberOf1(-2147483648));

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2147483648));
        System.out.println("最大的int\t" + Integer.MAX_VALUE);
        System.out.println("最大的int的二进制表示\t" + Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println("最小的int\t" + Integer.MIN_VALUE);
        System.out.println("最小的int的二进制表示\t" + Integer.toBinaryString(Integer.MIN_VALUE));
    }


}
