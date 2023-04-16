/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ReplaceElementsWithGreatestElementOnRightSide.java, v 0.1 2023年04月13日 22:13 yueyi Exp $
 */
public class ReplaceElementsWithGreatestElementOnRightSide {

    @LetCodeCommit(title = "1299. Replace Elements with Greatest Element on Right Side")
    public int[] replaceElements(int[] arr) {
        int i = arr.length - 1;
        int max = -1;
        int curI;
        while (i >= 0) {
            curI = arr[i];
            arr[i--] = max;
            max = Math.max(max, curI);
        }
        return arr;
    }

}