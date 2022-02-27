/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search.binary;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ArrangingCoins.java, v 0.1 2022年02月24日 9:55 上午 yueyi Exp $
 */
public class ArrangingCoins {

    @LetCodeCommit(title = "441. Arranging Coins")
    public int arrangeCoins(int n) {
        int sum = 0;
        int i = 0;
        while (sum <= n) {
            i++;
            sum += i;
        }
        return i - 1;
    }

    public static void main(String[] args) {
        //2147483647
    }
}