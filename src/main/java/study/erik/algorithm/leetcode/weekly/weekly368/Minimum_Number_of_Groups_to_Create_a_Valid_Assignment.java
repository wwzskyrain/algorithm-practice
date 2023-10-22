package study.erik.algorithm.leetcode.weekly.weekly368;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期：2023/10/22 ，时间：10:55
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Minimum_Number_of_Groups_to_Create_a_Valid_Assignment {

    @LetCodeCommit(title = "2910. Minimum Number of Groups to Create a Valid Assignment",
            diff = "m",
            selfRemark = "哎呦，终于做出来了。" +
                    "我错在了使用binary-search了。" +
                    "这个题目不能保证解的左右连续性。")
    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            counter.merge(num, 1, Integer::sum);
        }
        Integer maxSize = counter.values().stream().max(Integer::compare).get();
        for (int i = maxSize; i > 0; i--) {
            int group = canGroup(counter, i);
            if (group > 0) {
                return group;
            }
        }
        return -1;
    }

    public int canGroup(Map<Integer, Integer> counter, int size) {
        int size_1 = size - 1;
        int n1 = 0;
        int n2 = 0;
        for (Integer c : counter.values()) {
            if (size == 1) {
                n1 += c;
                continue;
            }
            boolean found = false;
            int i = 0;
            int maxI = c / size_1 + (c % size_1 == 0 ? 0 : 1);
            int j = c / size + (c % size == 0 ? 0 : 1);
            while (i <= maxI && j >= 0) {
                if (i * size_1 + j * size == c) {
                    found = true;
                    break;
                }
                if (i * size_1 + j * size > c) {
                    j--;
                }
                if (i * size_1 + j * size < c) {
                    i++;
                }
            }
            if (!found) {
                return -1;
            }
            n1 += i;
            n2 += j;
        }
        return n1 + n2;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildArray("[1,1,1,1,1]")},
                {5, ArrayUtils.buildArray("[1,1,3,3,1,1,2,2,3,1,3,2]")},
                {2, ArrayUtils.buildArray("[3,2,3,2,3]")},
                {4, ArrayUtils.buildArray("[10,10,10,3,1,1]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        Assert.assertEquals(expect, minGroupsForValidAssignment(nums));
    }

}
