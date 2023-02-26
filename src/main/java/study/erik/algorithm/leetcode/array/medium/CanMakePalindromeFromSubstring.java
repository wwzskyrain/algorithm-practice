/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : CanMakePalindromeFromSubstring.java, v 0.1 2023年02月26日 16:35 yueyi Exp $
 */
public class CanMakePalindromeFromSubstring {

    @LetCodeCommit(title = "1177. Can Make Palindrome from Substring",
            selfRemark = "这个题意有点绕，所以我判断它不是好题。"
                    + "但是在分析之后，这个解法还算可以。"
                    + "当然还能优化，优化点就是用true和false来表示，即没有必要计算出每个char出现的次数，儿只需要知道其奇偶即可。")
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        int[][] count = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
            count[i + 1] = count[i].clone();
            count[i + 1][s.charAt(i) - 'a']++;
        }
        List<Boolean> ret = new ArrayList<>();
        for (int[] query : queries) {
            int sum = 0;
            for (int i = 0; i < 26; i++) {
                sum += (count[query[1] + 1][i] - count[query[0]][i]) % 2;
            }
            ret.add(sum / 2 <= query[2]);
        }
        return ret;
    }

}