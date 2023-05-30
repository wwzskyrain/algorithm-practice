/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.bitmask;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumNumberOfAchievableTransferRequests.java, v 0.1 2023年05月29日 20:27 yueyi Exp $
 */
public class MaximumNumberOfAchievableTransferRequests {

    @LetCodeCommit(title = "1601. Maximum Number of Achievable Transfer Requests",
            diff = "h",
            selfRemark = "这也算是一个常见类型了，其实就是求组合。用bitmask求组合。"
                    + "这里待组合的元素不能太多哈。"
                    + "如果不用bitmask来求组合，其实就是求组合了，那就是级数级别的复杂度，在10+的元素就没得玩。")
    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length;
        int ret = 0;
        for (int s = (1 << m) - 1; s > 0; s--) {
            if (check(s, requests, n)) {
                ret = Math.max(ret, Integer.bitCount(s));
            }
        }
        return ret;
    }

    public boolean check(Integer state, int[][] requests, int n) {
        int[] b = new int[n];

        int m = requests.length;
        for (int i = 0; i < m; i++) {
            if ((state & (1 << i)) != 0) {
                b[requests[i][0]]--;
                b[requests[i][1]]++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (b[i] != 0) {
                return false;
            }
        }
        return true;
    }
}