package study.erik.algorithm.job.zijie.simulation06;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 17:47
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public String boldWords(String[] words, String s) {
        boolean[] bord = new boolean[s.length()];
        for (String word : words) {
            int from = 0;
            int idx;
            while ((idx = s.indexOf(word, from)) >= 0) {
                int l = word.length();
                int i = 0;
                while (i < l) {
                    bord[idx + i] = true;
                    i++;
                }
                from = idx + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            if (bord[i]) {
                if (i == 0 || !bord[i - 1]) {
                    sb.append("<b>");
                }
                sb.append(s.charAt(i));
            } else {
                if (i > 0 && bord[i - 1]) {
                    sb.append("</b>");
                }
                sb.append(s.charAt(i));
            }
            i++;
        }
        if(bord[s.length() - 1]){
            sb.append("</b>");
        }
        return sb.toString();
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
//                {"a<b>abc</b>d", new String[]{"ab", "bc"}, "aabcd"},
                {"eeaa<b>d</b>a<b>d</b>a<b>dc</b>", new String[]{"ccb","b","d","cba","dc"}, "eeaadadadc"},
                {"a<b>abc</b>d", new String[]{"ab", "bc"}, "aabcd"},

        });
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String[] words;
    @Parameterized.Parameter(2)
    public String s;

    @Test
    public void test() {
        Assert.assertEquals(expect, boldWords(words, s));
    }

}
