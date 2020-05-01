package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-04-26 10:46
 */
public class LargestMultipleofThree {

    /**
     * title = Largest Multiple of Three
     * url = https://leetcode.com/problems/largest-multiple-of-three/submissions/
     *
     * @param digits
     * @return
     */
    public String largestMultipleOfThree(int[] digits) {
        return solution(digits);
    }

    /**
     * 成绩 74.67% 和 100
     * 这个题目其实很简单，不用dp，也不用高深的计算，只需要知道关于三整除的数据尝试就可以了。
     * 具体分析见代码注解
     * 小结：hard的题目也可能很简单，也有可能不是在高深的思想，就是解题就是了。
     * 这里虽然也是求最值，当然遍历解空间之后，一定能求出最值，但是恐怕没有人会这样做。
     * 求最值不一定要比遍历解空间，也可能有最值取向。
     *
     * @param digits
     * @return
     */
    public String solution(int[] digits) {

        int[] counter = new int[10];
        int sum = 0;

        for (int i = 0; i < digits.length; i++) {
            //两个作用：1.统计1-9字符出现的次数，最后要把他们从大到小串联起来；2.计算sum
            sum = (sum + digits[i]) % 3;
            counter[digits[i]]++;
        }

        switch (sum) {
            case 0:
                break;
            case 1:
                //余数为1：要么减去一个[1，4，7]；要么减去两个[2,5,8]
                if (!removeMod(counter, 1)) {
                    removeMod(counter, 2);
                    removeMod(counter, 2);
                }
                break;
            case 2:
                //余数为2：要么减去一个【2，5，8】，要么减去两个[1，4，7]
                if (!removeMod(counter, 2)) {
                    removeMod(counter, 1);
                    removeMod(counter, 1);
                }
                break;
        }
        return build(counter);
    }

    private boolean removeMod(int[] counter, int mod) {
        for (int i = 0; i < 3; i++) {
            if (counter[mod + i * 3] > 0) {
                counter[mod + i * 3]--;
                return true;
            }
        }
        return false;
    }

    private String build(int[] counter) {
        StringBuilder sb = new StringBuilder();
        int i = counter.length - 1;
        while (i >= 0) {
            int count = counter[i];
            while (count > 0) {
                sb.append(i);
                count--;
            }
            i--;
        }
        String result = sb.toString();
        return result.startsWith("0") ? "0" : result;
    }

    @Test
    public void test_() {

        int[] digits5 = {5, 8};
        Assert.assertEquals("", largestMultipleOfThree(digits5));

        int[] digits = {8, 1, 9, 9};
        Assert.assertEquals("9981", largestMultipleOfThree(digits));

        int[] digits2 = {8, 6, 7, 1, 0};
        Assert.assertEquals("8760", largestMultipleOfThree(digits2));


        int[] digits3 = {1};
        Assert.assertEquals("", largestMultipleOfThree(digits3));

        int[] digits4 = {0, 0, 0, 0, 0, 0};
        Assert.assertEquals("0", largestMultipleOfThree(digits4));
    }

}
