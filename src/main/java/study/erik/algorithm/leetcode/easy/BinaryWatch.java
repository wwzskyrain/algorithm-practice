/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author yueyi
 * @version : BinaryWatch.java, v 0.1 2021年06月21日 1:01 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BinaryWatch {

    @LetCodeCommit(title = "Binary Watch",
            related = {"XOR Operation in an Array", "Decode XORed Permutation", "Verbal Arithmetic Puzzle"})
    public List<String> readBinaryWatch(int turnedOn) {
        return resolveWithEnum(turnedOn);
    }

    /**
     * 使用枚举的方法，其实有很多题使用枚举真的很好，特别是现实生活中的题目，让我想起一种题目——小学五年级做的应用题
     * 当然还可以用'回溯法'，有点小题大做
     *
     * @param turnedOn
     * @return
     */
    public List<String> resolveWithEnum(int turnedOn) {
        List<String> result = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            //bitCount的高效写法会吗？看看源码吧，吓死个人
            int hBitCount = Integer.bitCount(h);
            for (int m = 0; m < 60; m++) {
                int mBitCount = Integer.bitCount(m);
                if (hBitCount + mBitCount == turnedOn) {
                    result.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return result;
    }

    @Parameter
    public int      turnedOn;
    @Parameter(1)
    public String[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, new java.lang.String[] {"0:01", "0:02", "0:04", "0:08", "0:16", "0:32", "1:00", "2:00", "4:00", "8:00"}},
                {9, new java.lang.String[] {}},
        };
    }

    @Test
    public void test() {
        HashSet<String> expectSet = new HashSet<>();
        for (String string : expect) {
            expectSet.add(string);
        }
        Assert.assertTrue(expectSet.equals(new HashSet<>(readBinaryWatch(turnedOn))));
    }
}