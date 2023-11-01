package study.erik.algorithm.leetcode.weekly.weekly369;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/1 12:56
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public long minSum(int[] nums1, int[] nums2) {
        boolean hasZero1 = false;
        boolean hasZero2 = false;
        long sum1 = 0;
        long sum2 = 0;
        for (int i : nums1) {
            if (i == 0) {
                hasZero1 = true;
                sum1++;
            } else {
                sum1 += i;
            }

        }
        for (int i : nums2) {
            if (i == 0) {
                hasZero2 = true;
                sum2++;
            } else {
                sum2 += i;
            }

        }
        if (hasZero1 && hasZero2) {
            return Math.max(sum1, sum2);
        }
        if (hasZero1) {
            return sum1 > sum2 ? -1 : sum2;
        }
        if (hasZero2) {
            return sum2 > sum1 ? -1 : sum1;
        }
        return sum1 == sum2 ? sum1 : -1;
    }




}
