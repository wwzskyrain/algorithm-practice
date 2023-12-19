package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/18 14:36
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Roman_to_Integer {

    @LetCodeCommit(title = "13. Roman to Integer")
    public int romanToInt1(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int t = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1) {
                String str = s.substring(i, i + 2);
                if (map.containsKey(str)) {
                    t += map.get(str);
                    i++;
                } else {
                    t += map.get(s.substring(i, i + 1));
                }
            } else {
                t += map.get(s.substring(i, i + 1));
            }
        }
        return t;
    }

    public int romanToInt(String s) {
        int t = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                if (i == s.length() - 1) {
                    t++;
                } else if (s.charAt(i + 1) == 'V') {
                    t += 4;
                    i++;
                } else if (s.charAt(i + 1) == 'X') {
                    t += 9;
                    i++;
                } else {
                    t++;
                }
            } else if (c == 'V') {
                t += 5;
            } else if (c == 'X') {
                if (i == s.length() - 1) {
                    t += 10;
                } else if (s.charAt(i + 1) == 'L') {
                    t += 40;
                    i++;
                } else if (s.charAt(i + 1) == 'C') {
                    t += 90;
                    i++;
                } else {
                    t += 10;
                }
            } else if (c == 'L') {
                t += 50;
            } else if (c == 'C') {
                if (i == s.length() - 1) {
                    t += 100;
                } else if (s.charAt(i + 1) == 'D') {
                    t += 400;
                    i++;
                } else if (s.charAt(i + 1) == 'M') {
                    t += 900;
                    i++;
                } else {
                    t += 100;
                }
            } else if (c == 'D') {
                t += 500;
            } else if (c == 'M') {
                t += 1000;
            }
        }
        return t;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {621, "DCXXI"},
                {1994, "MCMXCIV"},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String s;

    @Test
    public void test() {
        Assert.assertEquals(expect, romanToInt1(s));
    }

}
