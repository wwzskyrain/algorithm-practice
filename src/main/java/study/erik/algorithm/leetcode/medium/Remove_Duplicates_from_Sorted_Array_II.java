package study.erik.algorithm.leetcode.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/21 ，时间：12:14
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Remove_Duplicates_from_Sorted_Array_II {

    @LetCodeCommit(title = "80. Remove Duplicates from Sorted Array II")
    public int removeDuplicates(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }
        //first和last分别表示当前的元素的首位位置
        int first = 0;
        int index = first;
        int last = first;
        int l = nums.length;
        while (last < l) {
            while (last + 1 < l && nums[last + 1] == nums[first]) {
                last++;
            }
            int count = 2;
            while (count-- > 0 && first <= last) {
                nums[index++] = nums[first++];
            }
            last++;
            first = last;
        }
        return index;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {5, ArrayUtils.buildArray("[1,1,1,2,2,3]")},
                {7, ArrayUtils.buildArray("[0,0,1,1,1,1,2,3,3]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, removeDuplicates(nums));
    }

}
