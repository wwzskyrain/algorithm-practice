package study.erik.algorithm.leetcode.string.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/4/2 07:35
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Faulty_KeyBoard {

    @LetCodeCommit(title = "")
    public String finalString(String s) {
        int l = s.length();
        char[] arr = s.toCharArray();
        int i = l - 1;
        while (i > 0) {
            while (i >= 0 && arr[i--] != 'i') {
            }
            int i1 = i;
            while (i >= 0 && arr[i] != 'i') {
                i--;
            }
            int i2 = i + 1;
            while (i1 > i2) {
                char t = arr[i1];
                arr[i1] = arr[i2];
                arr[i2] = t;
            }
        }
        return String.valueOf(arr);
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {"string", "weee"},
                {"ponter1", "poiinter2"}
        });
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, finalString(nums));
    }

}
