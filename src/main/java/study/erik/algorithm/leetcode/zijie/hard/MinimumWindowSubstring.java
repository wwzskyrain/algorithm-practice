package study.erik.algorithm.leetcode.zijie.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/12 ，时间：11:53
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class MinimumWindowSubstring {

    @LetCodeCommit(title = "76. Minimum Window Substring",
            selfRemark = "这个题目没太多意思。" +
                    "首先，思路很清晰——会基本的滑动窗口的都能做。" +
                    "其次，它并不是滑动窗口类型的好题——好题我见过的。" +
                    "最后，大家的解法也都大致相同，思路清晰，最多有一点小技巧而已。")
    public String minWindow(String s, String t) {
        //抄一份答案过来：
        //这也算一种滑动窗口了，没想到今天遇到了两次。
        int n = s.length();
        int m = t.length();
        int[] cnts = new int[128];
        int cnt = m;// 表示每个滑动窗口中需要保证t中的所有字符都出现
        for (int i = 0; i < m; i++) {
            cnts[t.charAt(i)]++;// 表示此轮遍历中该字符必须出现的次数
        }
        String res = "";
        int mins = Integer.MAX_VALUE;
        // 左闭右闭区间
        int left = 0, right = 0;
        while (right < n) {
            char ch = s.charAt(right);
            if (cnts[ch] > 0) {
                cnt--;// 找到了t中的一个字符
            }
            cnts[ch]--;
            // 找到了所有t中的字符
            if (cnt == 0) {
                while (left < right && cnts[s.charAt(left)] < 0) {
                    // 不断缩小窗口，筛出无关字符
                    cnts[s.charAt(left)]++;
                    left++;
                }
                if (right - left + 1 < mins) {
                    mins = right - left + 1;
                    res = s.substring(left, right + 1);
                }

                // 左边界往右收缩一位
                cnts[s.charAt(left)]++;
                cnt++;
                left++;
            }
            right++;
        }
        return res;
    }


}
