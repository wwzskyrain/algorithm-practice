/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PushDominoes.java, v 0.1 2021年07月22日 8:18 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PushDominoes {

    @LetCodeCommit(title = "Push Dominoes",
            selfRemark = "嵌套型，必须栈吗？不。这就就硬钢的，看我的if-else",
            related = "Maximum Score of a Good Subarray")
    public String pushDominoes(String dominoes) {
        return resolve(dominoes);
    }

    public String resolve(String dominoes) {
        char[] chars = dominoes.toCharArray();

        int follow = 0;
        for (int poneer = 0; poneer < chars.length; poneer++) {
            if (chars[poneer] != 'L') {
                continue;
            }
            int nearestR = poneer - 1;
            while (nearestR >= follow && chars[nearestR] != 'R') {
                nearestR--;
            }
            if (nearestR < follow) { // 没有找到最近的R
                while (follow <= poneer) {
                    chars[follow++] = 'L';
                }
            } else { //找到R
                int low = nearestR;
                int high = poneer;
                while (low < high) {
                    chars[low++] = 'R';
                    chars[high--] = 'L';
                }
                // 再处理 从j到k的，这里面只会有R，不会有L
                while (follow < nearestR && chars[follow++] != 'R') {
                    //在第一个R之前，都不推
                }
                while (follow < nearestR) {
                    chars[follow++] = 'R';
                }
            }
            follow = poneer + 1;
        }

        while (follow < chars.length && chars[follow++] != 'R') {
            //在第一个R之前
        }
        while (follow < chars.length) {
            chars[follow++] = 'R';
        }
        return String.valueOf(chars);
    }

    @Parameter
    public String dominoes;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {".L.R.", "LL.RR"},
                {"RR.L", "RR.L"},
                {".L.R...LR..L..", "LL.RR.LLRRLL.."}
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, pushDominoes(dominoes));
    }

}