package study.erik.algorithm.leetcode.weekly.weekly369;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/1 12:47
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_1 {

    @LetCodeCommit(title = "")
    public int findKOr(int[] nums, int k) {
        int[] c = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    c[i]++;
                }
            }
        }
        int t = 0;
        for (int i = 0; i < 32; i++) {
            if (c[i] >= k) {
                t += (1 << i);
            }
        }
        return t;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {9, ArrayUtils.buildArray("[7,12,9,8,9,15]"), 4},
                {0, ArrayUtils.buildArray("[2,12,1,11,4,5]"), 6},
                {15, ArrayUtils.buildArray("[10,8,5,9,11,6,8]"), 1},

        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, findKOr(nums, k));
    }

}
