package study.erik.algorithm.leetcode.array.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/1 12:24
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Move_Zeroes {

    @LetCodeCommit(title = "")
    public void moveZeroes(int[] nums) {
        int c = 0;
        for (int num : nums) {
            c += (num == 0 ? 0 : 1);
        }
        int i = 0;
        int j = 0;
        int n = nums.length;
        while (j < n){
            int num = nums[j++];
            if(num == 0){
                continue;
            }
            nums[i++] = num;
            if(i == c){
                break;
            }
        }
        while (i < n){
            nums[i++] = 0;
        }
    }

// [0,1,0,3,12]
    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[1,3,12,0,0]"), ArrayUtils.buildArray("[0,1,0,3,12]")},
        });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        moveZeroes(nums);
        Assert.assertArrayEquals(expect, nums);
    }

}
