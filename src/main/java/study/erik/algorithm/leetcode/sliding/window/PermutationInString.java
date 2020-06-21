package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-06-15 00:28
 * title = Permutation in String
 */
public class PermutationInString {

    @Test
    public void test_() {
        Assert.assertTrue(checkInclusion("ab", "eidbaooo"));
        Assert.assertFalse(checkInclusion("ab", "eidboaoo"));
    }

    /**
     * 一遍通过，很简单的
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() == 0) {
            return true;
        }
        if (s2.length() < s1.length()) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s1.length(); i++) {
            count[s2.charAt(i) - 'a']--;
        }
        if (isEmpty(count)) {
            return true;
        }

        int start = 0;
        for (int end = s1.length(); end < s2.length(); ) {
            int c1 = --count[s2.charAt(end++) - 'a'];
            int c2 = ++count[s2.charAt(start++) - 'a'];
            if (c1 == 0 && c2 == 0 && isEmpty(count)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
