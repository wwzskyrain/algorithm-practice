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
 * @version : MaximumBuildingHeight.java, v 0.1 2023年07月08日 09:07 yueyi Exp $
 */
public class MaximumBuildingHeight {

    @LetCodeCommit(title = "1840. Maximum Building Height",
            diff = "h",
            selfRemark = "这个题目还不错，很直观，所以并不难。代码写起来和理解起来都简单。适合做面试题，但是略显简单。")
    public int maxBuilding(int n, int[][] restrictions) {
        int m = restrictions.length;
        int[][] res = new int[m + 2][2];
        System.arraycopy(restrictions, 0, res, 0, restrictions.length);
        res[m] = new int[] {1, 0};
        res[m + 1] = new int[] {n, n - 1};
        Arrays.sort(res, Comparator.comparing(a -> a[0]));

        // loop from i to l-1
        for (int i = 0; i < res.length - 1; i++) {
            res[i + 1][1] = Math.min(res[i + 1][1], res[i][1] + (res[i + 1][0] - res[i][0]));
        }

        // loop from i to l-1
        for (int i = res.length - 1; i > 0; i--) {
            res[i - 1][1] = Math.min(res[i - 1][1], res[i][1] + (res[i][0] - res[i - 1][0]));
        }

        int ans = 0;
        for (int i = 0; i < res.length - 1; i++) {
            int l = res[i][0];
            int r = res[i + 1][0];
            int h1 = res[i][1];
            int h2 = res[i + 1][1];
            ans = Math.max(ans, Math.max(h1, h2) + (r - l - Math.abs(h2 - h1)) / 2);
        }
        return ans;
    }

}