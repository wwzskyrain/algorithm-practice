/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.union;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yueyi
 * @version : RankTransformOfAMatrix.java, v 0.1 2023年06月11日 08:34 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RankTransformOfAMatrix {

    @LetCodeCommit(title = "1632. Rank Transform of a Matrix",
            diff = "h",
            selfRemark = "不能说太难，就是太不好理解了。"
                    + "这个解法中，太多精妙的点，与其说精妙，不如说对题意完美的理解。"
                    + "预期说对题意完美的理解，不如反过来用答案去反推题。")
    public int[][] matrixRankTransform(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        //这个rank记录着当前行+列[n+m]的最小值，整个题解的生命周期中都要有这个家伙。
        int[] rank = new int[n + m];
        Map<Integer, List<Pair<Integer, Integer>>> invMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                invMap.computeIfAbsent(matrix[i][j], l -> new ArrayList<>()).add(new Pair<>(i, j));
            }
        }
        for (int key : invMap.keySet()) {
            //invMap结果matrix了，作为数据的来源
            UF uf = new UF(n + m);
            //rank2是对总局rank的临时变态
            int[] rank2 = rank.clone();
            for (Pair<Integer, Integer> coord : invMap.get(key)) {
                //union返回了unionFind中的两科树<px,py>
                Pair<Integer, Integer> res = uf.union(coord.getKey(), coord.getValue() + n);
                //value是py，key是px。union中是parent[px]=py了，所以这里更新py即可。
                //这里就把上局的rank中的新的相关的行与列整合起来了，为下面的+1做准备。
                rank2[res.getValue()] = Math.max(rank2[res.getValue()], rank2[res.getKey()]);
            }
            for (Pair<Integer, Integer> coord : invMap.get(key)) {
                //一联赋值了三个元素，牛逼。
                rank[coord.getKey()] = rank[coord.getValue() + n]
                        = matrix[coord.getKey()][coord.getValue()]
                        = rank2[uf.find(coord.getKey())] + 1;
            }
        }
        return matrix;
    }

    public static class UF {
        int[] parent;

        public UF(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {parent[i] = i;}
        }

        public int find(int x) {
            return parent[x] = parent[x] == x ? x : find(parent[x]);
        }

        public Pair<Integer, Integer> union(int x, int y) {
            int px = find(x);
            int py = find(y);
            parent[px] = py;
            return new Pair<>(px, py);
        }
    }

    @Parameter
    public int[][] matrix;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[1,2],[3,4]]")},
                {ArrayUtils.buildArray2Dimension("[[7,7],[7,7]]")},
                {ArrayUtils.buildArray2Dimension("[[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]")},
        };
    }

    @Test
    public void test_() {
        matrixRankTransform(matrix);
    }

}



