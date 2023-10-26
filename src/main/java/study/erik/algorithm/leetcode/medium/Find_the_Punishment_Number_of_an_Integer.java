package study.erik.algorithm.leetcode.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期：2023/10/25 ，时间：17:16
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Find_the_Punishment_Number_of_an_Integer {

    @LetCodeCommit(title = "2698. Find the Punishment Number of an Integer",
            diff = "m",
            selfRemark = "难点在于需要对字符串分割。")
    public int punishmentNumber(int n) {
        int total = 1;
        Map<String, Boolean> memo = new HashMap<>();
        for (int i = 2; i <= n; i++) {
            int i_i = i * i;
            if (canDivide(String.valueOf(i_i), i, memo)) {
                total += i_i;
            }
        }
        return total;
    }

    public boolean canDivide(String str, int target, Map<String, Boolean> memo) {
        if (target < 0) {
            return false;
        }
        if (str.length() == 0) {
            return target == 0;
        }
        String key = str + "-" + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (str.charAt(0) == '0') {
            boolean result = canDivide(str.substring(1), target, memo);
            memo.put(key, result);
            return result;
        }
        if (Integer.valueOf(str).equals(target)) {
            memo.put(key, true);
            return true;
        }
        int i = 1;
        while (i <= str.length()) {
            String ss = str.substring(0, i);
            int delta = Integer.parseInt(ss);
            if (canDivide(str.substring(i), target - delta, memo)) {
                memo.put(key, true);
                return true;
            }
            i++;
        }
        memo.put(key, false);
        return false;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {31334, 99},
                {182, 10},
                {1478, 37},
                {3503, 51},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;

    @Test
    public void test() {
        Assert.assertEquals(expect, punishmentNumber(n));
    }

}
