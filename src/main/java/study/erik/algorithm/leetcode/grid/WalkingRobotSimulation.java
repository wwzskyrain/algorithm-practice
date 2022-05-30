/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : WalkingRobotSimulation.java, v 0.1 2022年05月29日 18:51 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class WalkingRobotSimulation {

    @LetCodeCommit(title = "874. Walking Robot Simulation",
            diff = "m",
            selfRemark = "啊，这么菜的吗？9%的成绩。"
                    + "就干按照题意走呀。")
    public int robotSim(int[] commands, int[][] obstacles) {
        Pair<Integer, Integer> e = new Pair<>(0, 1);
        Pair<Integer, Integer> w = new Pair<>(0, -1);
        Pair<Integer, Integer> n = new Pair<>(-1, 0);
        Pair<Integer, Integer> s = new Pair<>(1, 0);
        Pair<Integer, Integer>[] dirs = new Pair[] {e, n, w, s};

        Set<String> obsSet = new HashSet<>();
        for (int[] obstacle : obstacles) {
            obsSet.add(obstacle[0] + "-" + obstacle[1]);
        }

        int curIdx = 0;
        int x = 0, y = 0;
        int max = 0;
        for (int i = 0; i < commands.length; i++) {
            int command = commands[i];
            if (command < 0) {
                curIdx = (curIdx + command * 2 - 3 + 8) % 4;
            } else {
                Pair<Integer, Integer> dir = dirs[curIdx];
                while (command > 0) {
                    int xx = x + dir.getKey();
                    int yy = y + dir.getValue();
                    if (obsSet.contains(xx + "-" + yy)) {
                        break;
                    } else {
                        x = xx;
                        y = yy;
                        max = Math.max(max, x * x + y * y);
                        command--;
                    }
                }
            }
        }
        return max;
    }

    @Parameter
    public int[]   commands;
    @Parameter(1)
    public int[][] obstacles;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,-1,3]"), ArrayUtils.buildArray2Dimension("[]"), 25},
                {ArrayUtils.buildArray("[4,-1,4,-2,4]"), ArrayUtils.buildArray2Dimension("[[2,4]]"), 65},
                {ArrayUtils.buildArray("[6,-1,-1,6]"), ArrayUtils.buildArray2Dimension("[]"), 36},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, robotSim(commands, obstacles));
    }
}
