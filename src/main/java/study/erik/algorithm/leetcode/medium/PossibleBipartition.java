/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : PossibleBipartition.java, v 0.1 2022年05月30日 17:33 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PossibleBipartition {

    @LetCodeCommit(title = "886. Possible Bipartition",
            diff = "m",
            selfRemark = "还不错，ac了，做过类似的.")
    public boolean possibleBipartition(int n, int[][] dislikes) {

        int[] color = new int[n + 1];
        Map<Integer, List<Integer>> disMap = new HashMap<>();
        for (int[] dislike : dislikes) {
            int a = dislike[0];
            int b = dislike[1];
            List<Integer> aList = disMap.getOrDefault(a, new ArrayList<>());
            aList.add(b);
            disMap.put(a, aList);

            List<Integer> bList = disMap.getOrDefault(b, new ArrayList<>());
            bList.add(a);
            disMap.put(b, bList);
        }
        Deque<Integer> q = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                q.add(i);
                color[i] = 1;
                while (!q.isEmpty()) {
                    Integer head = q.removeFirst();
                    List<Integer> dislike = disMap.getOrDefault(head, Collections.emptyList());
                    for (Integer d : dislike) {
                        if (color[d] == color[head]) {
                            return false;
                        }
                        if (color[d] == 0) {
                            color[d] = -color[head];
                            q.add(d);
                        }
                    }
                }
            }
        }
        return true;
    }

    @Parameter
    public int     n;
    @Parameter(1)
    public int[][] dislikes;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {1, new int[0][0], true},
                {4, ArrayUtils.buildArray2Dimension("[[1,2],[1,3],[2,4]]"), true},
                {3, ArrayUtils.buildArray2Dimension("[[1,2],[1,3],[2,3]]"), false},
                {5, ArrayUtils.buildArray2Dimension("[[1,2],[2,3],[3,4],[4,5],[1,5]]"), false},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, possibleBipartition(n, dislikes));
    }
}