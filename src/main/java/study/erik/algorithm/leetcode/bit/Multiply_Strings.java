package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-04-13 18:31
 */
public class Multiply_Strings {

    /**
     * url = https://leetcode.com/problems/multiply-strings/
     * 成绩：36和16， 成绩不理想
     * 分析，这是一道十进制的位运算题目，也是一道大数相乘的题目。
     * 本来是想把这到题目当做一个'wonderful'来记录呢，因为看到了一个解法很干净，很惊讶。
     * 然后自己看来看去，就写出了自己的答案，整体思路还是一样的，细节不同。然后就又把这个题目放到
     * 位运算系列下了
     * <p>
     * <p>
     * 重点：为预算的重点是，要处理进位。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {

        int[] result = new int[num1.length() + num2.length()];

        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int start = num1.length() - i - 1 + num2.length() - j - 1;

                // 处理进位
                while (mul > 0) {
                    mul = mul + result[start];
                    result[start] = mul % 10;
                    start++;
                    mul = mul / 10;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = result.length - 1; i >= 0; i--) {
            if (builder.length() == 0 && result[i] == 0) {
                continue;
            }
            builder.append(result[i]);
        }

        return builder.length() == 0 ? "0" : builder.toString();
    }

    @Test
    public void test_multi() {

        Assert.assertEquals("0", multiply("1234", "0"));

        Assert.assertEquals("81", multiply("9", "9"));

        String num1 = "123";
        String num2 = "456";
        Assert.assertEquals("56088", multiply(num1, num2));


    }


}
