package study.erik.algorithm.leetcode.medium;


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
 * 日期：2023/10/19 ，时间：08:30
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Tuple_with_Same_Product {

    @LetCodeCommit(title = "1726. Tuple with Same Product",
            selfRemark = "题目太简单了。")
    public int tupleSameProduct(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int m = nums[i] * nums[j];
                count.merge(m, 1, Integer::sum);
            }
        }
        int total = 0;
        for (Integer c : count.values()) {
            // 计算有多少对儿
            total += (c - 1) * c / 2;
        }
        //每一对有8种
        return total * 8;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {8, ArrayUtils.buildArray("[2,3,4,6]")},
                {16, ArrayUtils.buildArray("[1,2,4,5,10]")},
                {40, ArrayUtils.buildArray("[2,12,4,6,3,8]")},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, tupleSameProduct(nums));
    }

}
