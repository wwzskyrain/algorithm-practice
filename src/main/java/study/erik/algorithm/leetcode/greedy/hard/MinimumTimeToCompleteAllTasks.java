/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.greedy.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : MinimumTimeToCompleteAllTasks.java, v 0.1 2023年03月12日 20:26 yueyi Exp $
 */
public class MinimumTimeToCompleteAllTasks {

    @LetCodeCommit(title = "2589. Minimum Time to Complete All Tasks",
            selfRemark = "这个贪心题目，应该就是我们读研时的算法题目。"
                    + "可惜，我忘记如何证明这个贪心策略有效了。"
                    + "但是我很懂这个策略")
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (task1, task2) -> task1[1] == task2[1] ? task1[0] - task2[0] : task1[1] - task2[1]);

        Set<Integer> used = new TreeSet<>();
        int ret = 0;
        for (int[] task : tasks) {
            int timeUsed = 0;
            for (int i = task[0]; i <= task[1]; i++) {
                if (used.contains(i)) {
                    timeUsed++;
                }
            }
            int left = task[2] - timeUsed;
            for (int i = task[1]; left > 0; i--) {
                if (used.add(i)) {
                    ret++;
                    left--;
                }
            }
        }
        return ret;
    }

}