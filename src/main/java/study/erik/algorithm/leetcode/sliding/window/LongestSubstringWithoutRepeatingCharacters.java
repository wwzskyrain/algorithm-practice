package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author erik.wang
 * @date 2020-07-12 22:51
 * title = Longest Substring Without Repeating Characters
 */
public class LongestSubstringWithoutRepeatingCharacters {


    @Test
    public void test_() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring("bbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 这写的啥，一点注释都没有
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int l, h, maxLength, length;
        l = h = maxLength = 0;
        length = s.length();
        Set<Character> set = new HashSet<>();
        while (h < length) {
            char c = s.charAt(h);
            if (set.contains(c)) {
                char cc;
                do {
                    cc = s.charAt(l++);
                    set.remove(cc);
                } while (cc != c);
            } else {
                maxLength = Math.max(maxLength, h - l + 1);
            }
            h++;
            set.add(c);

        }
        return maxLength;
    }


}
