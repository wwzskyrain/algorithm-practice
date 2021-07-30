/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : ZoreOneMatrix.java, v 0.1 2021年07月30日 1:41 上午 yueyi Exp $
 */
public class ZoreOneMatrix {

    @LetCodeCommit(title = "01 Matrix")
    public int[][] updateMatrix(int[][] mat) {
        return resolveWithDfs(mat);
    }

    /**
     * bfs而不是dfs才是正解。
     * 为甚么最先想到的是dfb，其实最想想到的是dp，写着写着就变成了bfs了。
     * bfs有什么不好，首先是无限循环的问题因为在向下层递归的时候，会有反向递归，导致无穷递归的问题。
     * 这个问题可以加一个访问标志来解决。
     * 其次是就是存在错误——逻辑漏洞，因为递归是有方向的，在相互依赖的时候，
     * 就不考虑来自对方依赖的这条路径(因为已经标识为visited了)，就漏掉了答案。
     *
     * bfs的解法是多源bfs辐射，其中一个节点会被多源所辐射，取其最小值即可。
     * 入队需谨慎：只有能给后续节点带来更小之的时候，才让该后续节点入队。
     * 多次入队：一个节点是可以被多次入队的，每次入队代表着一次更优的路径。
     * 优化：那么，如果尽早让更优的路径入队，岂不是就少一些入队了。是的，而更优的路径如何找呢？
     * 路径都是从队列出来的，让所以更优的路径先出来，就是让队列中的节点其路径更小的先出来，所以就可以考虑用优先级队列来优化。
     * 好了，至此就看出来了，这就是一个迪杰斯塔拉算法的队列版本了。
     *
     * @param mat
     * @return
     */
    public int[][] resolveWithDfs(int[][] mat) {

        int[][] result = new int[mat.length][mat[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                    result[i][j] = 0;
                } else {
                    result[i][j] = mat.length * mat[0].length;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] head = queue.poll();
            int x, y;
            for (int i = 0; i < direct.length; i++) {
                x = head[0] + direct[i][0];
                y = head[1] + direct[i][1];
                if (x >= 0 && x < result.length
                        && y >= 0 && y < result[x].length
                        && result[x][y] > result[head[0]][head[1]] + 1) {
                    queue.add(new int[] {x, y});
                    result[x][y] = result[head[0]][head[1]] + 1;
                }
            }
        }
        return result;
    }

    public int[][] direct = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 失败的解法， DFS is a failure
     *
     * @param mat
     * @param result
     * @param i
     * @param j
     * @return
     */
    public int resolveWithRecursive(int[][] mat, int[][] result, int i, int j) {
        if (mat[i][j] == 0) {
            return result[i][j] = 0;
        }
        if (result[i][j] >= 0) {
            return result[i][j];
        }
        int min = Math.max(mat.length, mat[0].length);
        result[i][j] = -2;
        for (int k = 0; k < direct.length; k++) {
            int x = i + direct[k][0];
            int y = j + direct[k][1];
            if (x >= 0 && x < mat.length && y >= 0 && y < mat[x].length && result[x][y] != -2) {
                min = Math.min(min, resolveWithRecursive(mat, result, x, y) + 1);
            }
        }
        return result[i][j] = min;
    }

    @Test
    public void test1() {
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] except = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        //System.out.println(Arrays.deepToString(updateMatrix(mat)));
        Assert.assertArrayEquals(except, updateMatrix(mat));
    }

    @Test
    public void test2() {
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        int[][] except = {{0, 0, 0}, {0, 1, 0}, {1, 2, 1}};
        //System.out.println(Arrays.deepToString(updateMatrix(mat)));
        Assert.assertArrayEquals(except, updateMatrix(mat));
    }

    @Test
    public void test3() {
        int[][] mat = {{1, 0, 1, 1, 0, 0, 1, 0, 0, 1}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}};
        int[][] except = {{1, 0, 1, 1, 0, 0, 1, 0, 0, 1}, {0, 1, 1, 0, 1, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {0, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 2, 1, 1, 0, 1}, {2, 1, 1, 1, 1, 2, 1, 0, 1, 0},
                {3, 2, 2, 1, 0, 1, 0, 0, 1, 1}};

        ArrayUtils.printMatrix(mat);
        //System.out.println(Arrays.deepToString(updateMatrix(mat)));
        Assert.assertArrayEquals(except, updateMatrix(mat));
    }

}