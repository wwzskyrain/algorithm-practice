/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : MinimumOneBitOperationsToMakeIntegersZero.java, v 0.1 2023年06月04日 09:38 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumOneBitOperationsToMakeIntegersZero {

    @LetCodeCommit(title = "1611. Minimum One Bit Operations to Make Integers Zero",
            diff = "h",
            selfRemark = "这个题目真的很不友好。我看答案都看了2个小时，真是难受的。"
                    + "首先，这是一个反格雷码的题目。太偏了。"
                    + "其次，按递归解法的话，效率就低了。不过，递归解法是正解。")
    public int minimumOneBitOperations(int n) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("0", 0);
        map1.put("1", 1);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("0", 1);
        map2.put("1", 0);
        return dfs(Integer.toBinaryString(n), map1, map2);
    }

    private int dfs(String n, Map<String, Integer> map1, Map<String, Integer> map2) {
        if (map1.containsKey(n)) {
            return map1.get(n);
        }
        int ret = 0;
        if (n.charAt(0) == '0') {
            ret += dfs(n.substring(1), map1, map2);
            map1.put(n, ret);
            return ret;
        }
        String sub = n.substring(1);
        ret += helper(n.substring(1), map1, map2);
        ret += 1;
        int subLength = sub.length();
        String s = Integer.toBinaryString(1 << (subLength - 1));
        ret += dfs(s, map1, map2);
        map1.put(n, ret);
        return ret;
    }

    private int helper(String n, Map<String, Integer> map1, Map<String, Integer> map2) {
        if (map2.containsKey(n)) {
            return map2.get(n);
        }
        int ret;
        String m = n.substring(1);
        if (n.charAt(0) == '1') {
            ret = dfs(m, map1, map2);
        } else {
            String subDfs = Integer.toBinaryString(1 << (m.length() - 1));
            ret = helper(m, map1, map2) + 1 + dfs(subDfs, map1, map2);
        }
        map2.put(n, ret);
        return ret;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {9, 14},
                {3, 2},
                {6, 4},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minimumOneBitOperations(n));
    }

    //public static void main(String[] args) {
    //    System.out.println(Integer.toBinaryString(1 << (4 - 1)));
    //}

}