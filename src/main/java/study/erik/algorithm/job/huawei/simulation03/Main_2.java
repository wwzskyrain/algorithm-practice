package study.erik.algorithm.job.huawei.simulation03;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/3 10:19
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public boolean checkInclusion(String s1, String s2) {
        int[] count = getCounter(s1);
        int[] coumt2 = new int[26];
        int l1 = s1.length();
        int l2 = s2.length();
        if(l1 > l2){
            return false;
        }
        int i = 0;
        while (i < l1) {
            coumt2[s2.charAt(i) - 'a']++;
            i++;
        }
        if (checkIntEqual(count, coumt2)) {
            return true;
        }
        while (i < l2) {
            coumt2[s2.charAt(i - l1) - 'a']--;
            coumt2[s2.charAt(i) - 'a']++;
            i++;
            if (checkIntEqual(count, coumt2)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIntEqual(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] getCounter(String s) {
        int l = s.length() - 1;
        int[] count = new int[26];
        while (l >= 0) {
            count[s.charAt(l--) - 'a']++;
        }
        return count;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {true, "adc", "dcda"},
                {true, "a", "ab"},
                {true, "ab", "eidbaooo"},
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
