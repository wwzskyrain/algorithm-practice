/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.dp;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : MaximumNumberOfGroupsGettingFreshDonuts.java, v 0.1 2023年07月04日 07:44 yueyi Exp $
 */
@LetCodeCommit(title = "1815. Maximum Number of Groups Getting Fresh Donuts")
public class MaximumNumberOfGroupsGettingFreshDonuts {

    static final int K_WIDTH      = 5;
    static final int K_WIDTH_MASK = (1 << K_WIDTH) - 1;

    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] cnt = new int[batchSize];
        for (int group : groups) {
            cnt[(group % batchSize)]++;
        }
        long start = 0;
        for (int i = batchSize - 1; i >= 1; --i) {
            //groups.length < 30, 所以cnt[i] < 30;
            start = (start << K_WIDTH) | cnt[i];
        }
        Map<Long, Integer> map = new HashMap<>();
        return dfs(map, batchSize, start) + cnt[0];
    }

    public int dfs(Map<Long, Integer> map, int batchSize, long mask) {
        if (mask == 0) {
            return 0;
        }
        if (map.containsKey(mask)) {
            return map.get(mask);
        }

        long total = 0;
        for (int i = 1; i < batchSize; i++) {
            long groupCount = (mask >> ((i - 1) * K_WIDTH)) & K_WIDTH_MASK;
            // groupCount ：人数为i的组的个数
            total += (i * groupCount);
        }
        int best = 0;
        for (int i = 1; i < batchSize; i++) {
            long groupCount = (mask >> ((i - 1) * K_WIDTH)) & K_WIDTH_MASK;
            if (groupCount > 0) {
                //为啥递归只取一个组，即total-i；因为我们的dp思路就是这样的，即
                //最后分配人数为i的这个组时的最优解。
                long newMask = mask - (1L << ((i - 1) * K_WIDTH));
                //其实这里是需要回溯的，只不过我们用值传递——每次传newMask，对mask无印象，从而避免了对mask的回溯。
                int result = dfs(map, batchSize, newMask);
                if ((total - i) % batchSize == 0) {
                    //如果已经分配的(total - i)是batchSize的倍数，则当前这次分配，该组是愉快的。
                    ++result;
                }
                best = Math.max(best, result);
            }
        }
        map.put(mask, best);
        return best;
    }

}