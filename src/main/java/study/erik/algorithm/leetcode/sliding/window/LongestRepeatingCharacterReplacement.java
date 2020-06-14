package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-06-08 15:41
 * title = Longest Repeating Character Replacement
 * url = https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {


    @Test
    public void test_characterReplacement() {
        Assert.assertEquals(4, characterReplacement("AABABBA", 1));
        Assert.assertEquals(4, characterReplacement("ABAB", 2));
    }

    /**
     * 这是'滑动窗口'的第一题，直接看的答案；
     * 思路很清晰，不过我还是理解了很久；
     * 因为求最大值，所以，不需要缩小窗口：要么右移窗口，要么扩大窗口。
     * 其中，右移会保证最大值不变小、扩大窗口以寻求更大的值
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {

        int[] count = new int[26];
        int maxCount = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            if (end - start + 1 - maxCount > k) {
                count[s.charAt(start++) - 'A']--;
            }
        }
        return s.length() - start;
    }
}

