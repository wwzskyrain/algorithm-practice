/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.union;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : CheckingExistenceOfEdgeLengthLimitedPaths.java, v 0.1 2023年06月18日 17:57 yueyi Exp $
 */
public class CheckingExistenceOfEdgeLengthLimitedPaths {

    @LetCodeCommit(title = "1697. Checking Existence of Edge Length Limited Paths")
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int queryLength = queries.length;
        Integer[] idx = new Integer[queryLength];
        for (int i = 0; i < idx.length; i++) {
            idx[i] = i;
        }
        Arrays.sort(edgeList, Comparator.comparing(e -> e[2]));
        Arrays.sort(idx, Comparator.comparingInt(o -> queries[o][2]));

        UF uf = new UF(n);
        boolean[] ans = new boolean[queryLength];
        int j = 0;
        for (int i = 0; i < queryLength; i++) {
            int index = idx[i];
            int limit = queries[index][2];
            while (j < edgeList.length && edgeList[j][2] < limit) {
                uf.union(edgeList[j][0], edgeList[j][1]);
                j++;
            }
            ans[index] = uf.find(queries[index][0]) == uf.find(queries[index][1]);
        }
        return ans;
    }

    public static class UF {
        int[] parent;
        int[] size;

        public UF(int n) {
            this.parent = new int[n + 1];
            this.size = new int[n + 1];
            for (int i = 0; i < this.parent.length; i++) {
                this.parent[i] = i;
            }
            Arrays.fill(this.size, 1);
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (size[xParent] < size[yParent]) {
                parent[xParent] = yParent;
                size[yParent] += size[xParent];
            } else {
                parent[yParent] = xParent;
                size[xParent] += size[yParent];
            }
        }
    }

}