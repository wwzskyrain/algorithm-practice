package study.erik.algorithm.job.zijie.simulation03;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/7 15:59
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "字符串的排列")
    public boolean checkInclusion(String s1, String s2) {
        int[] c = count(s1);
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 > l2) {
            return false;
        }
        int[] window = new int[26];
        int i = 0;
        while (i < l1) {
            window[s2.charAt(i++) - 'a']++;
        }
        while (true) {
            if (check(c, window)) {
                return true;
            }
            if (i < l2) {
                window[s2.charAt(i - l1) - 'a']--;
                window[s2.charAt(i) - 'a']++;
                i++;
            } else {
                break;
            }
        }
        return false;
    }

    public boolean check(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] count(String s) {
        int[] c = new int[26];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i) - 'a']++;
        }
        return c;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {true, "ab", "eidbaooo"},
                {false, "ab", "eidboaoo"},
                {true, "adc", "dcda"}
        });
    }

    @Parameterized.Parameter
    public boolean expect;
    @Parameterized.Parameter(1)
    public String s1;
    @Parameterized.Parameter(2)
    public String s2;

    @Test
    public void test() {
        Assert.assertEquals(expect, checkInclusion(s1, s2));
    }

}
