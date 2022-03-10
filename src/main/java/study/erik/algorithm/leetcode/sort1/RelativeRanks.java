/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.sort1;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author yueyi
 * @version : RelativeRanks.java, v 0.1 2022年03月10日 7:35 上午 yueyi Exp $
 */
public class RelativeRanks {

    @LetCodeCommit(title = "506. Relative Ranks",
            diff = "e",
            selfRemark = ""
                    + "1.kv分离排序"
                    + "2.平行数组")
    public String[] findRelativeRanks(int[] score) {
        Integer[] sort = new Integer[score.length];
        for (int i = 0; i < sort.length; i++) {
            sort[i] = i;
        }
        Arrays.sort(sort, (a, b) -> score[b] - score[a]);
        String[] res = new String[sort.length];
        for (int i = 0; i < sort.length; i++) {
            if (i == 0) {
                res[sort[i]] = "Gold Medal";
            } else if (i == 1) {
                res[sort[i]] = "Silver Medal";
            } else if (i == 2) {
                res[sort[i]] = "Bronze Medal";
            } else {
                res[sort[i]] = i + 1 + "";
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(String.format("%s", "taskId", "taskType"));
    }
}