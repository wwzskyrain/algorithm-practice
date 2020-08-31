package study.erik.algorithm.leetcode.string.palindromic;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-30 11:14
 */
public class ShortestPalindrome {

    @LetCodeCommit(title = "214. Shortest Palindrome", diff = "h",
            selfRemark = "这个题即使用了马拉松算法也会超时，而一年前的解法，是不超时的，如今copy过来再运行也超时了。" +
                    "我不太喜欢回文字符串这类题目，没太多意思；" +
                    "而对于字符串的题目我还是很感兴趣的")
    public String shortestPalindrome(String s) {

        if (s.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder().append(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            sb.append("#").append(s.charAt(i));
        }
        s = sb.toString();
        int[] len = new int[sb.length()];
        int maxLengthIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int maxRight = maxLengthIndex + len[maxLengthIndex];
            if (i < maxRight) {
                int j = 2 * maxLengthIndex - i;
                if (len[j] < maxRight - i) {
                    len[i] = len[j];
                    continue;
                } else if (len[j] > maxRight - i) {
                    len[i] = maxRight - i;
                    continue;
                } else {
                    len[i] = len[j];
                }
            }

            int l, r;
//          先判断在增长
            while ((l = i - len[i] - 1) >= 0 &&
                    (r = i + len[i] + 1) < s.length() &&
                    s.charAt(l) == s.charAt(r)) {
                len[i]++;
            }
            if (len[i] > len[maxLengthIndex]) {
                maxLengthIndex = i;
            }
        }
        int lastCoverIndex = 1;
        for (int j = 0; j < len.length; j++) {
            if (j - len[j] == 0) {
                lastCoverIndex = j;
            }
        }
        String postFix = s.substring(lastCoverIndex + len[lastCoverIndex] + 1);

        sb = new StringBuilder();
        for (int i = postFix.length() - 1; i >= 0; i--) {
            sb.append(postFix.charAt(i));
        }
        sb.append(s);
        return sb.toString().replaceAll("#", "");
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals("aaacecaaa", shortestPalindrome("aacecaaa"));

    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals("dcbabcd", shortestPalindrome("abcd"));
    }


}
