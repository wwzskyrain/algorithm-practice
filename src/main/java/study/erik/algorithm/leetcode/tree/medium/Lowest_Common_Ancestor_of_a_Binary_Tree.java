package study.erik.algorithm.leetcode.tree.medium;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/3 12:44
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Lowest_Common_Ancestor_of_a_Binary_Tree {

    @LetCodeCommit(title = "")
    public int testMethodName(int[] nums) {
        
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

    @Test
    public void test() {
        Assert.assertEquals(expect, testMethodName(nums));
    }

}
