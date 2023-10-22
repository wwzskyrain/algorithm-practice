package study.erik.algorithm.leetcode.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/10/20 ，时间：13:00
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Sum3 {

    @LetCodeCommit(title = "15. 3Sum")
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int first = 0; first < nums.length; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                //重复元素，只需要第一个作为枚举first即可
                continue;
            }
            //惯用的排序+双指针
            int third = nums.length - 1;
            int target = -nums[first];
            for (int second = first + 1; second < nums.length; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    //重复元素，只需要第一个作为枚举first即可
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target) {
                    third--;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ret.add(list);
                }
                // 执行到这里，nums[second] + nums[third] < target了，也就是second要往前挪一步了。
            }
        }
        return ret;
    }

}
