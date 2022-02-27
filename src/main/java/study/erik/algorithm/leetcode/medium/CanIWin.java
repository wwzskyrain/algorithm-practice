/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

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
 * @version : CanIWin.java, v 0.1 2022年02月27日 8:33 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CanIWin {

    @LetCodeCommit(title = "464. Can I Win",
            selfRemark = "这是个回溯法，而不是什么dp啦")
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        if ((maxChoosableInteger + 1) * maxChoosableInteger / 2 < desiredTotal) {return false;}

        boolean[] used = new boolean[maxChoosableInteger + 1];
        return backtrack(desiredTotal, used, new HashMap<>());
    }

    public boolean backtrack(int desiredTotal, boolean[] used, Map<Integer, Boolean> map) {
        if (desiredTotal <= 0) {
            return false;
        }
        Integer key = format(used);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        for (int i = 1; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                boolean canWin = backtrack(desiredTotal - i, used, map);
                if (!canWin) {
                    map.put(key, true);
                    // 回溯
                    used[i] = false;
                    return true;
                }
                used[i] = false;
            }
        }
        map.put(key, false);
        return false;
    }

    public Integer format(boolean[] used) {
        int ret = 0;
        for (int i = 0; i < used.length; i++) {
            ret <<= 1;
            ret |= (used[i] ? 1 : 0);
        }
        return ret;
    }

    @Parameter
    public int     maxChoosableInteger;
    @Parameter(1)
    public int     desiredTotal;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {10, 11, false},
                {10, 0, true},
                {10, 1, true},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, canIWin(maxChoosableInteger, desiredTotal));
    }
}