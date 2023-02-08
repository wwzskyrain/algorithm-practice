/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : MinimumAbsoluteDifference.java, v 0.1 2023年02月08日 08:01 yueyi Exp $
 */
public class MinimumAbsoluteDifference {

    @LetCodeCommit(title = "1200. Minimum Absolute Difference")
    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);
        int curDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            int a = arr[i];
            int b = arr[i + 1];
            if (Math.abs(a - b) > curDiff) {
                continue;
            }
            if (Math.abs(a - b) < curDiff) {
                list = new ArrayList<>();
                curDiff = Math.abs(a - b);
            }
            list.add(Arrays.asList(a, b));
        }
        return list;
    }
}