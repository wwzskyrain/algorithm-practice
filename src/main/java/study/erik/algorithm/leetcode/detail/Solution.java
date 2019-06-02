package study.erik.algorithm.leetcode.detail;

import org.junit.Test;

import java.util.Map;

/**
 * @author erik.wang
 * @date 2019/04/21
 * 细节题
 **/
public class Solution {

    /**
     * title = Reverse Integer
     * <br>注意细节：
     * <ui>12000 ->  21</ui>
     * <li>-132 -> -231</li>
     * <li>溢出时返回0</li>
     *
     * @param x
     * @return
     */
    public int reverse(int x) {

        Long result = 0L;
        Long t = Long.valueOf(x);

        t = t > 0 ? t : -t;

        for (; t > 0; t /= 10) { //erik- 很正常的"降级打击"--降数量级打击
            result = result * 10 + t % 10;
        }

        boolean sing = x > 0 ? true : false;

        if (result > Integer.MAX_VALUE || (!sing && result >= Integer.MAX_VALUE)) {
            return 0;
        } else {
            return sing ? result.intValue() : -result.intValue();
        }

    }

    @Test
    public void test_int_flow() {

        int base = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            int result = base + i;
            System.out.println(result);
        }
    }

    /**
     * title = Reverse Bits
     * 一个小题，搞了一个小时；最终，黄天不负，faster than 100.00% 了。
     * 分析：该解法很简单，就是直接"翻转"输入n的二进制形式，具体分三步
     * 1.取出第i位；2.将结果左移一位，将最低位的位子腾出来 3.将第一步取出的第i位放到结果值的最低位。
     * 细节分析：1.在第三步的加法中，不需要考虑加法导致的溢出，是不可能溢出的
     * 2.第三步的左移可能造成溢出，无所谓。
     * 经验：这里可以总结出两个经验
     * 1.如何dump出无符号(包括正整数和负整数)的二进制
     * 2.如何根据'内存'数据镜像来还原(构造)该数据，当然反序构造也可以。
     * 3.移位+疑惑运算真的很好使，注意：没有无符号左移<<<，因为左移就足够了
     * 4.Integer.value(String,redix)是不能根据'数据内存镜像'来构造的，其输入参数是便于人识别的，比如'-111'，'3343';
     * 5.有符号负数与其无符号值的变化和内存表示举例：-3    11111111111111111111111111111101  ==  4294967293  =  1<<32 + (-3)
     * <p>
     * 相关题目:Number of 1 Bits
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {

        // 1.如何把有符号整数变成它的无符号数
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int temp = (n >>> i & 1);
            result = temp + (result << 1);
        }
        return result;
// 00000010100101000001111010011100  = 43261596


    }


    /**
     * 相比较Integer.toBinaryString，该函数不会省略正整数的前面的一串000.
     * 比如，对于123来说，前后两者分别输入：
     * <li>1111011</li>
     * <li>00000000000000000000000001111011</li>
     *
     * @param n
     * @return
     */
    public String toBinaryString(int n) {

        char[] chars = new char[32];
        for (int i = 0; i < 32; i++) {
            chars[31 - i] = (n >>> i & 1) == 1 ? '1' : '0';
        }
        return String.valueOf(chars);
    }

    /**
     * title = Number of 1 Bits
     * 有'Reverse Bits'题目的分析基础，这个题目就很easy了。
     * 提交结果非常的好，fast than 100%，less than 100%
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {

        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * title = Power of Two
     * 判断n是否是一个2的power。
     * 注意：n一定为正整数
     * 所以，需要它的二进制表示中有且只有一个1
     * 提交结果也很好
     * 相关题目：Power of Three
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {

        if (n < 0) //排除边界条件-2147483648，因为它的二进制表示是:10000000000000000000000000000000
            return false;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n >> i & 1) == 1) {
                count++;
            }
        }
        return count == 1;
    }

    //    Power of Three
    public boolean isPowerOfThree(int n) {

        if (n < 1) {
            return false;
        }

        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n = n / 3;
        }

        return true;
    }

    /**
     * title = Power of Four
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {

        if (num < 1) {
            return false;
        }

        while (num > 1) {
            if (num % 4 != 0) {
                return false;
            }
            num = num / 4;
        }

        return true;

    }


    @Test
    public void test_is_power_of_three() {
        System.out.println(isPowerOfThree(27));
    }

    @Test
    public void test_integer() {
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(0));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));
        System.out.println(Integer.toBinaryString(-4));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.MIN_VALUE);
    }


}
