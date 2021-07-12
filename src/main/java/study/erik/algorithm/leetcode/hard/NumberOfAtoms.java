/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
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
 * @version : NumberOfAtoms.java, v 0.1 2021年07月05日 7:13 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfAtoms {

    /**
     * 半成品，不做了，没有太多意思，因为比较是一个细节题——字符串的题目一般就这样
     * @param formula
     * @return
     */
    @LetCodeCommit(title = "Number of Atoms")
    public String countOfAtoms(String formula) {
        return resolve(formula);
    }

    public String resolve(String formula) {
        return "";
    }

    public Map<String, Integer> countOfStr(String subFormula) {
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        int j = 1;
        while (j < subFormula.length() &&
                subFormula.charAt(j) >= 'a' &&
                subFormula.charAt(j) <= 'z') {
            j++;
        }
        String key = subFormula.substring(i, j);
        i = j;
        while (j < subFormula.length() &&
                subFormula.charAt(j) >= '0' &&
                subFormula.charAt(j) <= '9') {
            j++;
        }
        Integer count = 1;
        if (i != j) {
            count = Integer.valueOf(subFormula.substring(i, j));
        }
        // 半成品，这才解析了一个正常的元素及其数字，比如 H2
        return map;

    }

    @Parameter
    public String formula;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"H2O", "H2O"},
                {"Mg(OH)2", "H2MgO2"},
                {"K4(ON(SO3)2)2", "K4N2O14S4"},
                {"Be32", "Be32"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countOfAtoms(formula));
    }

}
