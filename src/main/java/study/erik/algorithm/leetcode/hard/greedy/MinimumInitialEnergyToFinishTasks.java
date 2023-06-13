/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.greedy;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : MinimumInitialEnergyToFinishTasks.java, v 0.1 2023年06月13日 06:11 yueyi Exp $
 */
public class MinimumInitialEnergyToFinishTasks {

    @LetCodeCommit(title = "1665. Minimum Initial Energy to Finish Tasks")
    public int minimumEffort(int[][] tasks) {
        //剩下的能量-倒排
        Arrays.sort(tasks, Comparator.comparing(t -> t[0] - t[1]));
        //累计需要借用的能量
        int ans = 0;
        //当前的能量值
        int cur = 0;
        for (int[] task : tasks) {
            if (task[1] > cur) {
                //需要增加delta能量(task[1] - cur)才能启动。第一次，肯定要借(task[1])能量，因为cur是0
                ans += (task[1] - cur);
                //补齐当前能量
                cur = task[1];
            }
            //减去消耗的能量
            cur -= task[0];
        }
        return ans;
    }

}