package study.erik.algorithm.job.zijie.simulation05;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 11:25
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_1 {

    @LetCodeCommit(title = "")
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] code1 = code(ransomNote);
        int[] code2 = code(magazine);
        for (int i = 0; i < 26; i++) {
            if(code1[i]>code2[i]){
                return false;
            }
        }
        return true;
    }

    public int[] code(String s) {
        int[] charArr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charArr[s.charAt(i) - 'a']++;
        }
        return charArr;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, 2, 3},
                {5, 5, 8},
                {6, 11, 13},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int m;
    @Parameterized.Parameter(3)
    public int o;
    @Parameterized.Parameter(4)
    public int p;



}
