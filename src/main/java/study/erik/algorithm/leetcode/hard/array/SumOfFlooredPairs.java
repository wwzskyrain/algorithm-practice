/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.array;

import javafx.util.Pair;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : SumOfFlooredPairs.java, v 0.1 2023年07月08日 13:05 yueyi Exp $
 */
public class SumOfFlooredPairs {

    @LetCodeCommit(title = "1862. Sum of Floored Pairs")
    public int sumOfFlooredPairs(int[] nums) {
        long MOD = 1_000_000_000 + 7;
        int[] ps = new int[100001];
        long res = 0, max_n = Arrays.stream(nums).max().orElse(1);
        for (int n : nums) {
            ++ps[n];
        }
        List<Pair<Integer, Integer>> list = new LinkedList<>();
        for (int i = 1; i <= max_n; ++i) {
            if (ps[i] != 0) {
                //优化：处理相同的n
                list.add(new Pair<>(i, ps[i]));
            }
            ps[i] += ps[i - 1];
        }
        for (Pair<Integer, Integer> pair : list) {
            //这里不用nums来迭代，而用处理过相同num的list来。
            long num = pair.getKey();
            long cnt = pair.getValue(); // 相同n的个数
            for (long f = max_n / num; f > 0; --f) {
                //cnt是基数
                res = (res + cnt * f * (ps[((int) Math.min(max_n, (f + 1) * num - 1))] - ps[(int) (f * num - 1)])) % MOD;
            }
        }
        return ((int) res);
    }

}