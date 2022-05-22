/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : PositionsOfLargeGroups.java, v 0.1 2022年05月21日 09:55 yueyi Exp $
 */
public class PositionsOfLargeGroups {

    @LetCodeCommit(title = "830. Positions of Large Groups")
    public List<List<Integer>> largeGroupPositions(String s) {
        char[] chars = s.toCharArray();
        List<List<Integer>> res = new ArrayList<>();
        int t = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                t++;
                continue;
            }
            if (t >= 3) {
                res.add(Arrays.asList(i - t, i - 1));
            }
            t = 1;
        }
        if (t >= 3) {
            res.add(Arrays.asList(s.length() - t, s.length() - 1));
        }
        return res;
    }

}