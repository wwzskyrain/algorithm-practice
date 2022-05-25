/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LemonadeChange.java, v 0.1 2022年05月25日 08:05 yueyi Exp $
 */
public class LemonadeChange {

    @LetCodeCommit(title = "860. Lemonade Change")
    public boolean lemonadeChange(int[] bills) {
        int c5 = 0, c10 = 0;
        for (int b : bills) {
            if (b == 5) {
                c5++;
                continue;
            }
            if (b == 10) {
                if (c5 == 0) {
                    return false;
                }
                c5--;
                c10++;
                continue;
            }
            if (c10 > 0 && c5 > 0) {
                c10--;
                c5--;
            } else if (c5 > 2) {
                c5 -= 3;
            } else {
                return false;
            }
        }
        return true;
    }
}