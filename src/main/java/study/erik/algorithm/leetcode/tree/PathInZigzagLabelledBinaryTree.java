/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yueyi
 * @version : PathInZigzagLabelledBinaryTree.java, v 0.1 2023年01月08日 15:26 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PathInZigzagLabelledBinaryTree {

    @LetCodeCommit(title = "1104. Path In Zigzag Labelled Binary Tree")
    public List<Integer> pathInZigZagTree(int label) {

        List<Integer> maxNo = new ArrayList<>();
        int n = 0;
        int i = 0;
        while (n <= 1000000) {
            n = ((int) Math.pow(2, i++)) - 1;
            maxNo.add(n);
        }
        int level = Collections.binarySearch(maxNo, label);
        if (level < 0) {
            level = -(level + 1);
        }
        List<Integer> res = new ArrayList<>();
        while (label > 0) {
            res.add(label);
            label = label / 2;
            if (label < 1) {
                break;
            }
            level--;
            int max = maxNo.get(level);
            int min = maxNo.get(level - 1) + 1;
            label = max + min - label;
        }
        int l = 0, h = res.size() - 1;
        while (l < h) {
            int t = res.get(l);
            res.set(l, res.get(h));
            res.set(h, t);
            l++;
            h--;
        }
        return res;
    }

    @Parameter
    public int label;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {14},
                {26},
                {196348},
        };
    }

    @Test
    public void test_() {
        System.out.println(pathInZigZagTree(label));
    }

}