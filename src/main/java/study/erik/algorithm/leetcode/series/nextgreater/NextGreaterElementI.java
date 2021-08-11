/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.nextgreater;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : NextGreaterElementI.java, v 0.1 2021年08月10日 2:16 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NextGreaterElementI {

    @LetCodeCommit(title = "Next Greater Element I")
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //return resolveWithSort(nums1, nums2);
        return resolveMap(nums1,nums2);
    }

    public int[] resolveMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> value2IndexMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            value2IndexMap.put(nums2[i], i);
        }
        int[] greater = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int index = value2IndexMap.get(nums1[i]);
            while (index < nums2.length && nums2[index] <= nums1[i]) {
                index++;
            }
            greater[i] = index < nums2.length ? nums2[index] : -1;
        }
        return greater;
    }

    public int[] resolveWithSort(int[] nums1, int[] nums2) {

        Pair<Integer, Integer>[] pairs = new Pair[nums2.length];
        for (int i = 0; i < pairs.length; i++) {
            pairs[i] = new Pair<>(nums2[i], i);
        }
        Arrays.sort(pairs, (p1, p2) -> p1.getKey().compareTo(p2.getKey()));
        int[] greater = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int index = Arrays.binarySearch(pairs, new Pair<>(nums1[i], i), (p1, p2) -> p1.getKey().compareTo(p2.getKey()));
            if (index < 0) {
                greater[i] = -1;
                continue;
            }
            int j = pairs[index].getValue();
            while (j < nums2.length && nums2[j] <= nums1[i]) {
                j++;
            }
            greater[i] = j < nums2.length ? nums2[j] : -1;
        }
        return greater;
    }

    @Parameter
    public int[] nums1;
    @Parameter(1)
    public int[] nums2;
    @Parameter(2)
    public int[] expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[4,1,2]"), ArrayUtils.buildArray("[1,3,4,2]"), ArrayUtils.buildArray("[-1,3,-1]")},
                {ArrayUtils.buildArray("[2,4]"), ArrayUtils.buildArray("[1,2,3,4]"), ArrayUtils.buildArray("[3,-1]")}
        };
    }

    @Test
    public void test_nums1() {
        Assert.assertArrayEquals(expect, nextGreaterElement(nums1, nums2));
    }
}