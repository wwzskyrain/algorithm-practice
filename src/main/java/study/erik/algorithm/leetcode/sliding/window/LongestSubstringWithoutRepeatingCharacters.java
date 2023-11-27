package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author erik.wang
 * @date 2020-07-12 22:51
 * title = Longest Substring Without Repeating Characters
 */
public class LongestSubstringWithoutRepeatingCharacters {


    @Test
    public void test_() {
        Assert.assertEquals(2, lengthOfLongestSubstring2("cdd"));
        Assert.assertEquals(2, lengthOfLongestSubstring2("aab"));
        Assert.assertEquals(3, lengthOfLongestSubstring2("abcabcbb"));
        Assert.assertEquals(1, lengthOfLongestSubstring2("bbb"));
        Assert.assertEquals(3, lengthOfLongestSubstring2("pwwkew"));
    }

    /**
     * 这写的啥，一点注释都没有
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        Set<Character> set = new HashSet();
        int i = 0, j = 0;
        int n = s.length();
        int max = 0;
        while (j < n) {
            char c = s.charAt(j++);
            if (!set.contains(c)) {
                set.add(c);
                max = Math.max(max, j - i);
                continue;
            }
            while (i < j) {
                char cI = s.charAt(i++);
                set.remove(cI);
                if (cI == c) {
                    break;
                }
            }
            set.add(c);
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        //半成品
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (map.containsKey(c)) {
                Integer idx = map.get(c);
                int l = j - Math.min(i, idx);
                max = Math.max(max, l);
                i = j;
            }
            map.put(c, j);
        }
        max = Math.max(max, s.length() - i);
        return max;
    }


}
