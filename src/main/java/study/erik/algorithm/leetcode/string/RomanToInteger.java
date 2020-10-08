package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;


public class RomanToInteger {

    /**
     * @param s
     * @return
     * @see IntegerToRoman#intToRoman(int)
     */
    @LetCodeCommit(title = "Roman to Integer", extend = "题目其实很简单了，不太明白为什么那么多人dis了这道题，大概是因为case太麻烦了，其实还好，" +
            "这里我用了一个映射表，然后代码就极其简单了，不好的就是效率不高，时间复杂度才24%，但是空间挺省的，已经86%了。" +
            "哈哈，神奇的事情发生了，我又不动任何代码的提交了一遍，时间复杂度就上升到了67，当然空间复杂度也下降到了62",
            related = "Integer to Roman")
    public int romanToInt(String s) {
        Pair[] pairs = new Pair[13];
        pairs[0] = new Pair("IV", 4);
        pairs[1] = new Pair("IX", 9);
        pairs[2] = new Pair("XL", 40);
        pairs[3] = new Pair("XC", 90);
        pairs[4] = new Pair("CD", 400);
        pairs[5] = new Pair("CM", 900);
        pairs[6] = new Pair("I", 1);
        pairs[7] = new Pair("V", 5);
        pairs[8] = new Pair("X", 10);
        pairs[9] = new Pair("L", 50);
        pairs[10] = new Pair("C", 100);
        pairs[11] = new Pair("D", 500);
        pairs[12] = new Pair("M", 1000);

        int sum = 0;
        while (s.length() > 0) {
            for (Pair pair : pairs) {
                if (s.startsWith(pair.getKey())) {
                    sum += pair.getValue();
                    s = s.substring(pair.getKey().length());
                    break;
                }
            }
        }
        return sum;
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
        Assert.assertEquals(romanToInt("III"), 3);
    }

    @Test
    public void test_roman_to_int_2() {
        Assert.assertEquals(romanToInt("IV"), 4);
    }

    @Test
    public void test_roman_to_int_3() {
        Assert.assertEquals(romanToInt("IX"), 9);
    }

    @Test
    public void test_roman_to_int_4() {
        Assert.assertEquals(romanToInt("LVIII"), 58);
    }

    @Test
    public void test_roman_to_int_5() {
        Assert.assertEquals(romanToInt("MCMXCIV"), 1994);
    }

    @Test
    public void test_roman_to_int() {
        Assert.assertEquals(romanToInt("III"), 3);
    }


}
