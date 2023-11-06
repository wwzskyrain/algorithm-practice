package study.erik.algorithm.job.huawei.simulation07;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import javax.management.StringValueExp;
import java.util.*;

/**
 * 日期：2023/11/5 23:15
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_3 {

    @LetCodeCommit(title = "")
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int j = n - 2;
        while (j >= 0) {
            if (nums[j] > nums[j + 1]) {
                j--;
            } else {
                break;
            }
        }
        if (j >= 0) {
            int firstLarge = findFirstLarge(nums, j + 1, n, nums[j]);
            int t = nums[firstLarge];
            nums[firstLarge] = nums[j];
            nums[j] = t;
        }
        int a = j + 1;
        int b = n - 1;
        while (a < b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
            a++;
            b--;
        }
    }

    public int findFirstLarge(int[] nums, int i, int j, int target) {
        if (i == j) {
            return i;
        }
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] > target) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i - 1;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[11,3,8]"), ArrayUtils.buildArray("[8,11,3]")},
                {ArrayUtils.buildArray("[2,1,3]"), ArrayUtils.buildArray("[1,3,2]")},
                {ArrayUtils.buildArray("[3,1,2]"), ArrayUtils.buildArray("[2,3,1]")},
                {ArrayUtils.buildArray("[1,5,1]"), ArrayUtils.buildArray("[1,1,5]")},
                {ArrayUtils.buildArray("[1,3,2]"), ArrayUtils.buildArray("[1,2,3]")},
                {ArrayUtils.buildArray("[1,2,3]"), ArrayUtils.buildArray("[3,2,1]")},
        });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        nextPermutation(nums);
        Assert.assertArrayEquals(expect, nums);
    }

}
