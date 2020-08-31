package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-31 17:29
 */
public class FindAllAnagramsInAString {


    @LetCodeCommit(title = "438. Find All Anagrams in a String", diff = "m",
            selfRemark = "固定窗口，然后每右移就更新窗口状态")
    public List<Integer> findAnagrams(String s, String p) {

        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        List<Integer> indices = new LinkedList<>();
        int base = 20200;
        int[] count = new int[26];

        for (int i = 0; i < p.length(); i++) {
            int i1 = p.charAt(i) - 'a';
            if (count[i1] == 0) {
                count[i1] = base;
            }
            count[i1]--;
        }
        for (int i = 0; i < p.length(); i++) {
            int i1 = s.charAt(i) - 'a';
            if (count[i1] > 0) {
                count[i1]++;
            }
        }
        if (check(count, base)) {
            indices.add(0);
        }
        int l = 0;
        int h = p.length() - 1;
        while (h < s.length() - 1) {
            int i1 = s.charAt(l++) - 'a';
            if (count[i1] > 0) {
                count[i1]--;
            }

            i1 = s.charAt(++h) - 'a';
            if (count[i1] > 0) {
                count[i1]++;
                if (count[i1] == base && check(count, base)) {
                    indices.add(l);
                }
            }
        }
        return indices;
    }

    public boolean check(int[] count, int base) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0 && count[i] != base) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test_solution_1() {
        String s = "cbaebabacd", p = "abc";
        Assert.assertEquals(Arrays.asList(0, 6), findAnagrams(s, p));
    }

    @Test
    public void test_solution_2() {
        String s = "abab", p = "ab";
        Assert.assertEquals(Arrays.asList(0, 1, 2), findAnagrams(s, p));
    }

    @Test
    public void test_solution_3() {
        String s = "aa", p = "bb";
        Assert.assertEquals(Arrays.asList(), findAnagrams(s, p));
    }


}
