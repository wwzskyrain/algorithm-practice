package study.erik.algorithm.job.huawei.simulation08;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/7 14:27
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public int maxScore(int[] nums) {
        backtrace(nums, 0);
        return max;
    }

    int max = 0;

    public void backtrace(int[] nums, int i) {
        if (i == nums.length - 1) {
            // cal
            int t = 0;
            for (int j = 0; j < nums.length / 2; j++) {
                t += ((j + 1) * gcd(nums[2 * j], nums[2 * j + 1]));
            }
            if (t > max) {
                max = t;
            }
            return;
        }
        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            backtrace(nums, i + 1);
            swap(nums, i, j);
        }
    }

    public int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        while (b > 0) {
            int c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildArray("[1,2]")},
                {11, ArrayUtils.buildArray("[3,4,6,8]")},
                {14, ArrayUtils.buildArray("[1,2,3,4,5,6]")},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxScore(nums));
    }

}
