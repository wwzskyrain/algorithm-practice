/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Supplier;

/**
 * @author yueyi
 * @version : SlidingWindowMedian.java, v 0.1 2022年02月07日 11:40 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class SlidingWindowMedian {

    @LetCodeCommit(title = "480. Sliding Window Median",
            tag = "好题-堆",
            selfRemark = "这个题目真不容易"
                    + "1.treeSet的存储值和比较值是不同的。"
                    + "2.balance的过程和insert的过程是有点绕的。特别是insert的过程"
                    + "3.代码中使用了很有型的lambda表达式.")
    public double[] medianSlidingWindow(int[] nums, int k) {

        //这个比较器是神来之笔
        Comparator<Integer> c = (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : a - b;
        TreeSet<Integer> left = new TreeSet<>(c.reversed());//倒序，大顶堆，堆顶是左边值的最大的值
        TreeSet<Integer> right = new TreeSet<>(c);//正序，小顶堆，堆顶是右边值的最小的值
        Supplier<Double> median = (k % 2 == 0) ?
                () -> (((double) nums[left.first()]) + nums[right.first()]) / 2.0 :
                () -> ((double) nums[right.first()]);
        // 这种简短的函数，都可以用Runnable来实现，不用传参数，只需要起一个很好的变量名。
        Runnable balance = () -> {
            //左边的个数，小于等于右边的个数——当k为偶数时，左右个数相等；当k为奇数时，右边多一个，此时中位数只需要是右边的堆顶。
            while (left.size() > right.size()) {
                right.add(left.pollFirst());
            }
        };

        double[] result = new double[nums.length - k + 1];
        int resultIndex = 0;
        for (int i = 0; i < k; i++) {
            left.add(i);
        }
        balance.run();
        result[resultIndex++] = median.get();
        for (int i = k; i < nums.length; i++) {
            if (!left.remove(i - k)) {
                //删除i-k，可能存在于左边和右边。
                right.remove(i - k);
            }
            //insert时一定要先把i到right转一圈，挤出right中的最小值，挤到left中，然后再balance.
            right.add(i);
            left.add(right.pollFirst());
            balance.run();
            result[resultIndex++] = median.get();
        }
        return result;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int k;
    @Parameter(2)
    public double[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][]{
                {
                        ArrayUtils.buildArray("[1,3,-1,-3,5,3,6,7]"), 3,
                        ArrayUtils.buildDoubleArray("[1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]")
                },
                {
                        ArrayUtils.buildArray("[1,2,3,4,2,3,1,4,2]"), 3,
                        ArrayUtils.buildDoubleArray("[2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]")
                },
                {
                        ArrayUtils.buildArray("[1,4,2,3]"), 4,
                        ArrayUtils.buildDoubleArray("[2.5]")
                },

                };
    }

    @Test
    public void test_() {
        double[] actualDoubles = medianSlidingWindow(nums, k);
        Assert.assertArrayEquals(expect, actualDoubles, 0.001);
    }

}