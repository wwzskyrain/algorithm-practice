package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;


public class IntegerToRoman {

    /**
     * @param num
     * @return
     * @see RomanToInteger#romanToInt(String)
     * @see
     */
    @LetCodeCommit(title = "Integer to Roman", diff = "m",
            extend = "反正第一次提交分数很高，91和87.我的算法也很简单，还可以优化，用二分查找，找到第一个比target小的数字，" +
                    "好的，去写一个这样的二分查找来。")
    public String intToRoman(int num) {
        Pair[] pairs = new Pair[13];
        pairs[0] = new Pair("I", 1);
        pairs[1] = new Pair("IV", 4);
        pairs[2] = new Pair("V", 5);
        pairs[3] = new Pair("IX", 9);
        pairs[4] = new Pair("X", 10);
        pairs[5] = new Pair("XL", 40);
        pairs[6] = new Pair("L", 50);
        pairs[7] = new Pair("XC", 90);
        pairs[8] = new Pair("C", 100);
        pairs[9] = new Pair("CD", 400);
        pairs[10] = new Pair("D", 500);
        pairs[11] = new Pair("CM", 900);
        pairs[12] = new Pair("M", 1000);

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            for (int i = pairs.length - 1; i >= 0; i--) {
                Pair pair = pairs[i];
                if (num >= pair.getValue()) {
                    num -= pair.getValue();
                    sb.append(pair.getKey());
                    break;
                }
            }
        }
        return sb.toString();
    }

    static class Pair {
        String key;
        int value;

        public Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }
    }


    @Test
    public void test_roman_to_int_1() {
        Assert.assertEquals(intToRoman(3), "III");
    }

    @Test
    public void test_roman_to_int_2() {
        Assert.assertEquals(intToRoman(4), "IV");
    }

    @Test
    public void test_roman_to_int_3() {
        Assert.assertEquals(intToRoman(9), "IX");
    }

    @Test
    public void test_roman_to_int_4() {
        Assert.assertEquals(intToRoman(58), "LVIII");
    }

    @Test
    public void test_roman_to_int_5() {
        Assert.assertEquals(intToRoman(1994), "MCMXCIV");
    }


}
