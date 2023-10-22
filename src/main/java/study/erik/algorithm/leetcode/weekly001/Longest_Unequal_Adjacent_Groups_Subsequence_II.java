package study.erik.algorithm.leetcode.weekly001;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/10/18 ，时间：13:58
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Longest_Unequal_Adjacent_Groups_Subsequence_II {

    @LetCodeCommit(title = "2901. Longest Unequal Adjacent Groups Subsequence II",
            selfRemark = "这个题目我竟然因为超时而没有ac。" +
                    "我用dfs去解答了。其实没必要。而且我么会dp的写法的，比如下面的代码就是我们事后诸葛亮独自完成的。" +
                    "那么问题来了：赛场上为什么没有做出来呢？哪怕是变化思路呢？" +
                    "还是对dp的这个写法不太熟悉和自信。")
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        List<String>[] dp = new List[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
        }
        //dp[i]表示以words[i]结尾的最长的子序列。
        dp[0].add(words[0]);
        for (int i = 1; i < n; i++) {
            String wordI = words[i];
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                String wordJ = words[j];
                if (groups[i] == groups[j]) {
                    continue;
                }
                if (dp[j].size() <= list.size()) {
                    continue;
                }
                if (!checkDistance(wordI, wordJ)) {
                    continue;
                }
                list = new ArrayList<>(dp[j]);
            }
            list.add(wordI);
            dp[i] = list;
        }
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dp[i].size() > ret.size()) {
                ret = dp[i];
            }
        }
        return ret;
    }

    public boolean checkDistance(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        int l = word1.length();
        int diff = 0;
        for (int i = 0; i < l; i++) {
            diff += (word1.charAt(i) != word2.charAt(i) ? 1 : 0);
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {
                        Arrays.asList("cb", "ab", "aa", "ac", "bc"), 13, new String[]{
                        "cb", "dc", "ab", "aa", "ac", "bb", "ca", "bcc",
                        "cdd", "aad", "bba", "bc", "ddb"
                }, ArrayUtils.buildArray("[12,6,10,11,4,8,9,11,2,11,3,2,5]")
                },
                {Arrays.asList("aaa", "ada"), 3, new String[]{"bdb", "aaa", "ada"}, ArrayUtils.buildArray("[2,1,3]")},
                {Arrays.asList("bab", "cab"), 3, new String[]{"bab", "dab", "cab"}, ArrayUtils.buildArray("[1,2,2]")},
                {Arrays.asList("a", "b", "c", "d"), 4, new String[]{"a", "b", "c", "d"}, ArrayUtils.buildArray("[1,2,3,4]")},
                });
    }

    @Parameterized.Parameter
    public List<String> expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public String[] words;
    @Parameterized.Parameter(3)
    public int[] groups;

    @Test
    public void test() {
        Assert.assertEquals(expect, getWordsInLongestSubsequence(n, words, groups));
    }

}
