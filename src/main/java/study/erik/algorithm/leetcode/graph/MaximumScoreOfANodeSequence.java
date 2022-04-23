/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.graph;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : MaximumScoreOfANodeSequence.java, v 0.1 2022年04月23日 4:13 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MaximumScoreOfANodeSequence {

    @LetCodeCommit(title = "2242. Maximum Score of a Node Sequence",
            selfRemark = "一个很不错的hard题。"
                    + "因为是三条边的遍历。如果是更多边，就不太好用遍历法了。"
                    + "其实，这个题的本质，如果是n跳变，这个题是np的."
                    + "有点陆行推销员的意思。",
            diff = "h")
    public int maximumScore(int[] scores, int[][] edges) {
        List<Integer>[] top3PerVertex = new List[scores.length];
        for (int i = 0; i < top3PerVertex.length; i++) {
            top3PerVertex[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int vX = edge[0];
            int vY = edge[1];
            //修改了排序算法之后，时间从220ms减小到90ms了.
            this.add(top3PerVertex[vX], vY, scores);
            this.add(top3PerVertex[vY], vX, scores);
        }
        int max = -1;
        // a -- x -- y -- b
        for (int[] edge : edges) {
            // 枚举所有的中间变，即x--y这条边.
            int x = edge[0];
            int y = edge[1];
            for (int a : top3PerVertex[x]) {
                for (int b : top3PerVertex[y]) {
                    // a==y表示重边；同理b==x
                    // a==b表示回路，也表示少了一条边： a--x--y--a
                    if (a != y && a != b && b != x) {
                        max = Math.max(max, scores[a] + scores[x] + scores[y] + scores[b]);
                    }
                }
            }
        }
        return max;
    }

    // 只要前三个.
    private void add(List<Integer> list, Integer vertex, int[] scores) {
        if (list.size() == 0) {
            list.add(vertex);
        } else if (list.size() == 1) {
            if (scores[list.get(0)] >= scores[vertex]) {
                list.add(vertex);
            } else {
                list.add(list.get(0));
                list.set(0, vertex);
            }
        } else if (list.size() == 2) {
            if (scores[list.get(1)] >= scores[vertex]) {
                list.add(vertex);
            } else if (scores[list.get(0)] >= scores[vertex]) {
                list.add(list.get(1));
                list.set(1, vertex);
            } else {
                list.add(list.get(1));
                list.set(1, list.get(0));
                list.set(0, vertex);
            }
        } else {
            if (scores[list.get(2)] >= scores[vertex]) {
                return;
            } else if (scores[list.get(1)] >= scores[vertex]) {
                list.set(2, vertex);
            } else if (scores[list.get(0)] >= scores[vertex]) {
                list.set(2, list.get(1));
                list.set(1, vertex);
            } else {
                list.set(2, list.get(1));
                list.set(1, list.get(0));
                list.set(0, vertex);
            }
        }
    }

    @Parameter
    public int[]   scores;
    @Parameter(1)
    public int[][] edges;
    @Parameter(2)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[5,2,9,8,4]"), ArrayUtils.buildArray2Dimension("[[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]"), 24},
                {ArrayUtils.buildArray("[9,20,6,4,11,12]"), ArrayUtils.buildArray2Dimension("[[0,3],[5,3],[2,4],[1,3]]"), -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, maximumScore(scores, edges));
    }
}