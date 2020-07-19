package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-07-19 16:57
 * title = Maximum Number of Vowels in a Substring of Given Length
 */
public class MaximumNumberofVowelsinaSubstringofGivenLength {


    @Test
    public void test_() {

        Assert.assertEquals(2, maxVowels("aeiou", 2));
        Assert.assertEquals(1, maxVowels("tryhard", 4));
        Assert.assertEquals(0, maxVowels("rhythms", 4));
        Assert.assertEquals(2, maxVowels("leetcode", 3));
        Assert.assertEquals(3, maxVowels("abciiidef", 3));

    }

    /**
     * 成绩：81 和 39
     * 这是最简单的一个题目了吧，不应该是medium，应该是easy
     * 我用一个数组来做c是否是'元音字母'，这有点浪费空间了，不过还好，时间复杂度还好的。
     *
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {


        int[] map = new int[26];
        map[0] = 1;
        map['e' - 'a'] = 1;
        map['i' - 'a'] = 1;
        map['o' - 'a'] = 1;
        map['u' - 'a'] = 1;
        int count = 0;
        int maxCount = 0;

        int l, r;
        l = r = 0;
        while (r < s.length()) {
            while (r - l + 1 <= k) {
                count += map[s.charAt(r++) - 'a'];
                maxCount = Math.max(maxCount, count);
            }
            count -= map[s.charAt(l++) - 'a'];
        }
        return maxCount;
    }


}
