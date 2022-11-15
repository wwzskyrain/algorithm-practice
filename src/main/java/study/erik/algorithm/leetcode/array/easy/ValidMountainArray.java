/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ValidMountainArray.java, v 0.1 2022年11月09日 09:30 yueyi Exp $
 */
public class ValidMountainArray {

    @LetCodeCommit(title = "941. Valid Mountain Array")
    public boolean validMountainArray(int[] arr) {
        boolean up = true;
        if (arr.length < 3) {
            return false;
        }
        // 先保证上升
        if (arr[1] <= arr[0]) {
            return false;
        }
        for (int i = 2; i < arr.length; i++) {
            if (up) {
                if (arr[i] > arr[i - 1]) {
                    continue;
                } else if (arr[i] == arr[i - 1]) {
                    return false;
                } else {
                    up = false;
                }
            } else {
                if (arr[i] > arr[i - 1]) {
                    return false;
                } else if (arr[i] == arr[i - 1]) {
                    return false;
                } else {
                    continue;
                }
            }
        }
        return !up;
    }
}