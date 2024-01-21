package study.erik.algorithm.leetcode.contest.weekly380;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/1/14 10:34
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main1 {

    @LetCodeCommit(title = "")
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> c = new HashMap<>();
        int maxSe = 0;
        for (int num : nums) {
            int s = c.getOrDefault(num, 0);
            s++;
            c.put(num, s);
            maxSe = Math.max(maxSe, s);
        }
        int t = 0;
        for (Map.Entry<Integer, Integer> entry : c.entrySet()) {
            if (entry.getValue().equals(maxSe)) {
                t += maxSe;
            }
        }
        return t;
    }


}
