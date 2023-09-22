package study.erik.algorithm.leetcode.tree.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/18 ，时间：17:17
 * 作者：yueyi
 * 描述：
 */
@LetCodeCommit(title = "1483. Kth Ancestor of a Tree Node")
@RunWith(Parameterized.class)
public class Kth_Ancestor_of_a_Tree_Node {
    public static class TreeAncestor {

        int[][] pa;

        public TreeAncestor(int n, int[] parent) {
            int maxLayer = 32 - Integer.numberOfLeadingZeros(n);
            this.pa = new int[n][maxLayer];
            for (int i = 0; i < n; i++) {
                //第一层
                this.pa[i][0] = parent[i];
            }
            for (int i = 1; i < maxLayer; i++) {
                //i表示第i曾父亲，比如i=1，表示第2层父亲；i=2，表示第四层父亲，i=3表示第8层父亲；
                //i与i+1的关系：在i层走两步就是i+1——i层的父亲的父亲
                for (int node = 0; node < n; node++) {
                    int p = this.pa[node][i - 1];
                    this.pa[node][i] = p < 0 ? -1 : this.pa[p][i - 1];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k);
            for (int i = 0; i < m; i++) {
                if (((k >> i) & 1) == 1) {
                    node = this.pa[node][i];
                    if (node < 0) {
                        return node;
                    }
                }
            }
            return node;
        }
    }

}
