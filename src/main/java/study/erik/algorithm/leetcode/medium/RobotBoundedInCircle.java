/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RobotBoundedInCircle.java, v 0.1 2022年12月13日 07:58 yueyi Exp $
 */
public class RobotBoundedInCircle {

    @LetCodeCommit(title = "1041. Robot Bounded In Circle",
            diff = "m",
            selfRemark = "这个medium是很有点技巧性的。本题不考算法，考观察能力")
    public boolean isRobotBounded(String instructions) {

        int[][] directions = new int[][] {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int x = 0, y = 0, i = 3;
        char[] chars = instructions.toCharArray();
        for (char c : chars) {
            if (c == 'L') {
                i = (i + 1) % 4;
            } else if (c == 'R') {
                i = (i + 3) % 4;
            } else {
                x += directions[i][0];
                y += directions[i][1];
            }
        }
        return x == 0 && y == 0 || i != 3;
    }
}