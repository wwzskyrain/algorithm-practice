package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-24 09:44
 */
public class RepeatedSubstringPattern {

    @LetCodeCommit(title = "459. Repeated Substring Pattern", time = 64,
            selfRemark = "这是easy题目？不像。还有用kmp算法的呢")
    public boolean repeatedSubstringPattern(String s) {

        if (s.length() < 2) {
            return false;
        }

        if (s.length() == 2) {
            return s.charAt(0) == s.charAt(1);
        }

        if (verify(s, 1, s.length())) {
            return true;
        }
        int seg = 2;
        int L = s.length();
        int sqrt = (int) Math.sqrt(L);
        while (seg <= sqrt) {

            if (L % seg == 0) {
                int l = L / seg;
                if (verify(s, l, seg) || verify(s, seg, l)) {
                    return true;
                }
            }
            seg++;
        }
        return false;
    }

    public boolean verify(String s, int l, int segment) {
        for (int i = 0; i < l; i++) {
            char c = s.charAt(i);
            for (int j = 1; j < segment; j++) {
                if (c != s.charAt(j * l + i)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test_solution_1() {
        Assert.assertTrue(repeatedSubstringPattern("abab"));
    }

    @Test
    public void test_solution_2() {
        Assert.assertFalse(repeatedSubstringPattern("aba"));
    }

    @Test
    public void test_solution_3() {
        Assert.assertTrue(repeatedSubstringPattern("abcabcabcabc"));
    }

    @Test
    public void test_solution_4() {
        Assert.assertTrue(repeatedSubstringPattern("1234512345123451234512345"));
    }

    @Test
    public void test_solution_5() {
        Assert.assertFalse(repeatedSubstringPattern("12345123451234512345123452"));
    }

    @Test
    public void test_solution_6() {
        Assert.assertFalse(repeatedSubstringPattern("a"));
    }

    @Test
    public void test_solution_7() {
        Assert.assertFalse(repeatedSubstringPattern("ab"));
    }

    @Test
    public void test_solution_8() {
        Assert.assertFalse(repeatedSubstringPattern("abc"));
    }

    @Test
    public void test_solution_9() {
        Assert.assertFalse(repeatedSubstringPattern("abcde"));
    }

    @Test
    public void test_solution_10() {
        Assert.assertTrue(repeatedSubstringPattern("ababab"));
    }


    @Test
    public void test_solution_11() {
        Assert.assertTrue(repeatedSubstringPattern("abcdabcdabcdabcdabcd"));
    }


    @Test
    public void test_solution_12() {
        Assert.assertTrue(repeatedSubstringPattern("zzz"));
    }

}
