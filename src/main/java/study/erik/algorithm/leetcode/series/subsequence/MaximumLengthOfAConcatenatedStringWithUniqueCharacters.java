/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.subsequence;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : MaximumLengthOfAConcatenatedStringWithUniqueCharacters.java, v 0.1 2023年03月16日 07:44 yueyi Exp $
 */
public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    @LetCodeCommit(title = "1239. Maximum Length of a Concatenated String with Unique Characters")
    public int maxLength(List<String> arr) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int max = 0;
        for (String s : arr) {
            int a = 0, dup = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                dup |= a & (1 << c - 'a');
                a |= 1 << (c - 'a');
            }
            if (dup > 0) {
                continue;
            }
            for (int i = dp.size() - 1; i >= 0; i--) {
                Integer d = dp.get(i);
                if ((d & a) > 0) {
                    continue;
                }
                d |= a;
                dp.add(d);
                max = Math.max(max, Integer.bitCount(d));
            }
        }
        return max;
    }

}