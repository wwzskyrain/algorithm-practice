package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-13 08:39
 */
public class MultiplyStrings {


    @LetCodeCommit(no = 43, title = "Multiply Strings", time = 32, space = 59,
            selfRemark = "这道题目之前做过的。直接用字符来计算，不如转化成各位的'int'数组来计算。难点：进位计算",
            diff = "m",
            related = {"Plus One", "Add Strings"})
    public String multiply(String num1, String num2) {

        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }
        int[] numInt1 = new int[num1.length()];
        int[] numInt2 = new int[num2.length()];
        int[] result = new int[numInt1.length + numInt2.length];


        for (int i = 0; i < numInt1.length; i++) {
            numInt1[i] = num1.charAt(numInt1.length - 1 - i) - '0';
        }

        for (int i = 0; i < numInt2.length; i++) {
            numInt2[i] = num2.charAt(numInt2.length - 1 - i) - '0';
        }

        for (int j = 0; j < numInt2.length; j++) {
            for (int i = 0; i < numInt1.length; i++) {
                int r = numInt2[j] * numInt1[i];
                int curIndex = i + j;
                do {
                    //注意进位计算
                    int overflow = (result[curIndex] + r % 10) > 9 ? 1 : 0;
                    result[curIndex] = (result[curIndex] + r % 10) % 10;
                    r = r / 10;
                    r += overflow;
                    curIndex++;
                } while (r > 0);
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = result.length - 1;
        while (result[i] == 0) {
            i--;
        }
        while (i >= 0) {
            sb.append(result[i--]);
        }
        return sb.toString();
    }

    @Test
    public void test_case_1() {
        String num1 = "2", num2 = "3";
        Assert.assertEquals("6", multiply(num1, num2));
    }

    @Test
    public void test_case_2() {
        String num1 = "123", num2 = "456";
        Assert.assertEquals("56088", multiply(num1, num2));
    }

    @Test
    public void test_case_3() {
        String num1 = "9", num2 = "9";
        Assert.assertEquals("81", multiply(num1, num2));
    }

}
