package study.erik.algorithm.leetcode.hard.string;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/8/3 ，时间：12:37
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class LongestSubsequenceRepeatedKTimes {

    @LetCodeCommit(title = "2014. Longest Subsequence Repeated k Times",
            diff = "h",
            selfRemark = "这个题目，竟然是一个暴力破解的题目。" +
                    "谁都没有好办法。" +
                    "不过这里的暴力破解还是有点东西可以学习的。" +
                    "1.从无到有来构造，而不是从大到小区构造。")
    public String longestSubsequenceRepeatedK(String s, int k) {
        String res = "";
        //queue用于存储解，并且是从小打到的解。
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    //不用担心要放到cur的哪一位置，直接挂后面就可以了，因为咱这是无死角构造
                    String next = cur + (char) (i + 'a');
                    if (isSub(s, next, k)) {
                        res = next;
                        queue.offer(res);
                    }
                }
            }
        }
        return res;
    }

    boolean isSub(String s, String sub, int k) {
        //sub在s中重复k次
        int repeat = 0;
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == sub.charAt(j)) {
                j++;
                if (j == sub.length()) {
                    repeat++;
                    if (repeat == k) {
                        return true;
                    }
                    j = 0;
                }
            }
        }
        return false;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {"let", "letsleetcode", 2},
                {"b", "bb", 2},
                {"", "ab", 2},
        });
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String s;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, longestSubsequenceRepeatedK(s, k));
    }

}
