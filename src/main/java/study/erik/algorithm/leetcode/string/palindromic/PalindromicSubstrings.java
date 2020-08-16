package study.erik.algorithm.leetcode.string.palindromic;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-16 17:49
 */
public class PalindromicSubstrings {

    @LetCodeCommit(no = 647, title = "Palindromic Substrings", time = 82, space = 84,
            selfRemark = "回文数，直接去找，不要先找所有的substring，再去判断，太慢")
    public int countSubstrings(String s) {

        int count = s.length();
        int i = 1;
        while (i < s.length()) {
            int il = i - 1;
            int ir = i + 1;
            while (il >= 0 && ir < s.length() && s.charAt(il) == s.charAt(ir)) {
                count++;
                il--;
                ir++;
            }
            i++;
        }
        i = 1;
        while (i < s.length()) {
            int ir = i;
            int il = i - 1;
            while (il >= 0 && ir < s.length() && s.charAt(il) == s.charAt(ir)) {
                count++;
                il--;
                ir++;
            }
            i++;
        }

        return count;
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals(3, countSubstrings("abc"));
    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals(6, countSubstrings("aaa"));
    }

    @Test
    public void test_solution_3() {
        Assert.assertEquals(15, countSubstrings("aaaaa"));
    }
}
