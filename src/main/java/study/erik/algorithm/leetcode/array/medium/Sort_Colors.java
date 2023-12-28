package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/21 09:44
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Sort_Colors {

    @LetCodeCommit(title = "75. Sort Colors")
    public void sortColors(int[] nums) {
        int[] counters = new int[3];
        for (int num : nums) {
            counters[num]++;
        }
        int j = 0;
        for (int i = 0; i < counters.length; i++) {
            while (counters[i] > 0) {
                nums[j++] = i;
                counters[i]--;
            }
        }
    }


}
