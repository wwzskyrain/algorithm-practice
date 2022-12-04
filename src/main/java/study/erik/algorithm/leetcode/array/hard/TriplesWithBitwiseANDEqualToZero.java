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

/**
 * @author yueyi
 * @version : TriplesWithBitwiseANDEqualToZero.java, v 0.1 2022年12月04日 09:23 yueyi Exp $
 */
@LetCodeCommit(title = "982. Triples with Bitwise AND Equal To Zero")
@RunWith(Parameterized.class)
public class TriplesWithBitwiseANDEqualToZero {

    public int countTriplets(int[] nums) {
        int m = 3;
        int[][] map = new int[m + 1][1 << 16];
        // 这里再把map简化成map[2]来相互累加就是下面那种解法的拓展形式了。
        map[0][(1 << 16) - 1] = 1;
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[i].length; j++) {
                for (int num : nums) {
                    map[i + 1][num & j] += map[i][j];
                }
            }
        }
        return map[m][0];
    }

    /**
     * map计数法。只能解决3元素。
     * 当然可以扩展为N元素。
     * 扩展到n元素，即用继续用map做计数，由于在一层遍历中不能更新map，那样会到值重复计算，所以要用一个temp临时做本轮循环的计数。
     * 然而这种扩展后的形式却有人以dp的形式写出来。其本质是一样的。我本想写一下这个扩展形式，但是没有dp的写法间接，就不写了。
     * 可以直接见本题的另外一种解法，如上
     *
     * @param nums
     * @return
     */
    public int solutionWithMapCount(int[] nums) {

        int[] map = new int[1 << 16];
        for (int num : nums) {
            for (int i : nums) {
                map[num & i]++;
            }
        }
        int count = 0;
        for (int num : nums) {
            for (int i = 0; i < map.length; i++) {
                if ((i & num) == 0) {
                    // 这里map只能用一次，即不能是在map上做二次更新，因为会重复计算
                    count += map[i];
                }
            }
        }
        return count;
    }

    @Parameter
    public int[] nums;
    @Parameter(1)
    public int   expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[2,1,3]"), 12},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, countTriplets(nums));
    }
}