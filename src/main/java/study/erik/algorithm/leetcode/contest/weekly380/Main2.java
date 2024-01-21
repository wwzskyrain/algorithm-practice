package study.erik.algorithm.leetcode.contest.weekly380;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/1/14 10:38
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main2 {

    @LetCodeCommit(title = "")
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        int sL = s.length();
        int aL = a.length();
        int bL = b.length();
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i <= sL - aL; i++) {
            if (s.startsWith(a, i)) {
                int start = Math.max(i - k, 0);
                int end = Math.min(i + k, sL - bL);
                for (int j = start; j <= end; j++) {
                    if (s.startsWith(b, j)) {
                        ret.add(i);
                        break;
                    }
                }
            }
        }
        return ret;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildList("0"), "bavgoc", "ba", "c", 6},
                {ArrayUtils.buildList("16,33"), "isawsquirrelnearmysquirrelhouseohmy", "my", "squirrel", 15},
                {ArrayUtils.buildList("0"), "abcd", "a", "a", 4},

        });
    }

    @Parameterized.Parameter
    public List<Integer> expect;
    @Parameterized.Parameter(1)
    public String s;
    @Parameterized.Parameter(2)
    public String a;
    @Parameterized.Parameter(3)
    public String b;
    @Parameterized.Parameter(4)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, beautifulIndices(s, a, b, k));
    }

}
