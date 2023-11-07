package study.erik.algorithm.job.zijie.simulation04;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/7 16:37
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        int step = 2;
        while (step <= n) {
            for (int i = 0; i < n / step; i++) {
                int first = i * step;
                int second = first + step / 2;
                if (i % 2 == 0) {
                    nums[first] = Math.min(nums[first], nums[second]);
                } else {
                    nums[first] = Math.max(nums[first], nums[second]);
                }
            }
            step *= 2;
        }
        return nums[0];
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {850, ArrayUtils.buildArray("[999,939,892,175,114,464,850,107]")},
                {1, ArrayUtils.buildArray("[1,3,5,2,4,8,2,2]")},
                {3, ArrayUtils.buildArray("[3]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, minMaxGame(nums));
    }

}
