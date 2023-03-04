/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid.medium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : AlphabetBoardPath.java, v 0.1 2023年03月04日 18:42 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class AlphabetBoardPath {

    @LetCodeCommit(title = "1138. Alphabet Board Path",
            selfRemark = "这是一个不错的题目，是个细节题，虽然细节题，但是条理清楚")
    public String alphabetBoardPath(String target) {

        int l = target.length();
        int curX = 0;
        int curY = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            char targetC = target.charAt(i);
            int targetX = calX(targetC);
            int targetY = calY(targetC);
            while (curX != targetX || curY != targetY) {
                if (curX != targetX) {
                    int disX = targetX - curX;
                    if (curY == 5) {
                        int disY = targetY - curY;
                        int nextY = curY + (disY > 0 ? 1 : -1);
                        // 走起
                        sb.append(disY > 0 ? "D" : "U");
                        curY = nextY;
                    } else {
                        // 走起
                        sb.append(disX > 0 ? "R" : "L");
                        int nextX = curX + (disX > 0 ? 1 : -1);
                        curX = nextX;
                    }
                    continue;
                }
                if (curY != targetY) {
                    int disY = targetY - curY;
                    int nextY = curY + (disY > 0 ? 1 : -1);
                    if (nextY == 5 && curX != 0) {
                        int disX = targetX - curX;
                        int nextX = curX + (disX > 0 ? 1 : -1);
                        curX = nextX;
                        sb.append(disX > 0 ? "R" : "L");
                    } else {
                        sb.append(disY > 0 ? "D" : "U");
                        curY = nextY;
                    }
                }
            }
            sb.append("!");
        }
        return sb.toString();
    }

    public int calX(char c) {
        return (c - 'a') % 5;
    }

    public int calY(char c) {
        return (c - 'a') / 5;
    }

    @Parameter
    public String target;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"z", "ddd"},
                {"leet", "DDR!UURRR!!DDD!"},
                {"code", "RR!DDRR!UUL!R!"},
        };
    }

    @Test
    public void test_() {
        System.out.println(alphabetBoardPath(target));
    }

}