package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/11 10:23
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Path_In_Zigzag_Labelled_Binary_Tree {

    @LetCodeCommit(title = "1104. Path In Zigzag Labelled Binary Tree",
            selfRemark = "模拟时，没有完全AC。原来是因为初始化min数组没有真的扩大到30" +
                    "所以写代码一定要小心啊，心无旁骛。")
    public List<Integer> pathInZigZagTree(int label) {
        int maxLevel = 30;
        int[] min = new int[maxLevel];
        for (int i = 1; i < maxLevel; i++) {
            min[i] = 1 << (i - 1);
        }
        int level = findLevel(label, min);
        List<Integer> list = new ArrayList<>();
        list.add(label);
        int p = getPosition(label, level);
        while (p > 1) {
            int pP = p / 2;
            level--;
            list.add(0, getLabel(pP, level));
            p = pP;
        }
        return list;
    }

    public int getPosition(int label, int level) {
        if (level % 2 == 0) {
            int min = 1 << (level - 1);
            int max = (1 << level) - 1;
            return min + max - label;
        }
        return label;
    }

    public int getLabel(int position, int level) {
        if (level % 2 == 0) {
            int min = 1 << (level - 1);
            int max = (1 << level) - 1;
            return min + max - position;
        }
        return position;
    }

    public int findLevel(int label, int[] min) {
        int n = min.length;
        int i = 1;
        while (i < n) {
            if (min[i] > label) {
                return i - 1;
            }
            i++;
        }
        return -1;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildList("[1,3,5,12,22,51,89,205,357,821,1429,3284,5719,13136,22878,52546,91515,210184,366062]"), 366062},
        });
    }

    @Parameterized.Parameter
    public List<Integer> expect;
    @Parameterized.Parameter(1)
    public int label;

    @Test
    public void test() {
        Assert.assertEquals(expect, pathInZigZagTree(label));
    }

}
