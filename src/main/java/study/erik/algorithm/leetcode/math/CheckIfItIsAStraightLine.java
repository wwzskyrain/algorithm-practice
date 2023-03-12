/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.math;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : CheckIfItIsAStraightLine.java, v 0.1 2023年03月12日 14:31 yueyi Exp $
 */
public class CheckIfItIsAStraightLine {

    @LetCodeCommit(title = "1232. Check If It Is a Straight Line",
            selfRemark = "很不错的数学题目")
    public boolean checkStraightLine(int[][] coordinates) {
        int x1 = coordinates[0][0], y1 = coordinates[0][1];
        int x2 = coordinates[1][0], y2 = coordinates[1][1];
        int dx = x2 - x1;
        int dy = y2 - y1;
        for (int[] coordinate : coordinates) {
            int x = coordinate[0], y = coordinate[1];
            if (dx * (y - y2) != dy * (x - x2)) {
                return false;
            }
        }
        return true;
    }

}