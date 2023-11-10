package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/10 21:27
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Successful_Pairs_of_Spells_and_Potions {

    @LetCodeCommit(title = "2300. Successful Pairs of Spells and Potions")
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            long spell = spells[i];
            long target = (success / spell) + (success % spell == 0 ? 0 : 1);
            int idx = search(potions, (int) target);
            ret[i] = potions.length - idx;
        }
        return ret;
    }

    //返回第一个大于等于target的index，potions可能存在重复元素
    public int search(int[] potions, long target) {
        if(target > Integer.MAX_VALUE){
            return potions.length;
        }
        int l = 0;
        int h = potions.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (potions[m] >= target) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return h;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                // spells = [5,1,3], potions = [1,2,3,4,5], success = 7
                {ArrayUtils.buildArray("[4,0,3]"), ArrayUtils.buildArray("[5,1,3]"), ArrayUtils.buildArray("[1,2,3,4,5]"), 7},
                {ArrayUtils.buildArray("[2,0,2]"), ArrayUtils.buildArray("[3,1,2]"), ArrayUtils.buildArray("[8,5,8]"), 16},
        });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[] spells;
    @Parameterized.Parameter(2)
    public int[] potions;
    @Parameterized.Parameter(3)
    public int success;

    @Test
    public void test() {
        Assert.assertArrayEquals(expect, successfulPairs(spells, potions, success));
    }

}
