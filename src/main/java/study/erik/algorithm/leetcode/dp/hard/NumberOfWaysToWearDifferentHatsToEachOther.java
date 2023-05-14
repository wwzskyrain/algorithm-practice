/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.hard;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : NumberOfWaysToWearDifferentHatsToEachOther.java, v 0.1 2023年05月11日 08:32 yueyi Exp $
 */
public class NumberOfWaysToWearDifferentHatsToEachOther {

    @LetCodeCommit(title = "1434. Number of Ways to Wear Different Hats to Each Other",
            diff = "h",
            selfRemark = "这个题就是带不同的帽子，别'撞衫'了。"
                    + "这个题目的本质就是排列(而不是组合)。"
                    + "这里的解法不是按照人来分帽子，"
                    + "而是按照帽子来带给人——当前帽子，不戴在任何人头上或者分别戴在分别第i个人头上。"
                    + "还是dfs加备忘录的解法")
    public int numberWays(List<List<Integer>> hats) {
        int personNum = hats.size();
        int allPerson = (1 << personNum) - 1;
        List<Integer>[] hatToPerson = new List[41];
        for (int hat = 0; hat < hatToPerson.length; hat++) {
            hatToPerson[hat] = new ArrayList<>();
        }
        for (int i = 0; i < hats.size(); i++) {
            for (Integer hat : hats.get(i)) {
                hatToPerson[hat].add(i);
            }
        }
        return dfs(hatToPerson, allPerson, 1, 0, new Integer[41][allPerson]);
    }

    public int dfs(List<Integer>[] hatToPerson, int allPerson, int hat, int assignedPeople, Integer[][] dp) {
        if (assignedPeople == allPerson) {
            return 1;
        }
        if (hat > 40) {
            return 0;
        }
        if (dp[hat][assignedPeople] != null) {
            return dp[hat][assignedPeople];
        }
        int ans = dfs(hatToPerson, allPerson, hat + 1, assignedPeople, dp); // Don't wear this hat
        for (int p : hatToPerson[hat]) {
            if (((assignedPeople >> p) & 1) == 1) {
                continue; // Skip if person `p` was assigned hat
            }
            ans += dfs(hatToPerson, allPerson, hat + 1, assignedPeople | (1 << p), dp); // Wear this hat for p_th person
            ans %= 1_000_000_007;
        }
        dp[hat][assignedPeople] = ans;
        return ans;
    }
}