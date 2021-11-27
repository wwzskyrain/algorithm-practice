/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : PascalTriangleII.java, v 0.1 2021年11月25日 11:17 下午 yueyi Exp $
 */
public class PascalTriangleII {

    @LetCodeCommit(title = "Pascal's Triangle II")
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            result.add(1);
            for (int j = result.size() - 2; j > 0; j--) {
                result.set(j, result.get(j) + result.get(j - 1));
            }
        }
        return result;
    }

    @Test
    public void test_() {
        Assert.assertEquals(Arrays.asList(1, 3, 3, 1), getRow(3));
        Assert.assertEquals(Arrays.asList(1), getRow(0));
        Assert.assertEquals(Arrays.asList(1, 1), getRow(1));
    }

}