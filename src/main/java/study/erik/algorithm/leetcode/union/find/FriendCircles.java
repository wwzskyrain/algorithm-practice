package study.erik.algorithm.leetcode.union.find;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-22 09:55
 */
public class FriendCircles {

    @LetCodeCommit(title = "547 Friend Circles", time = 84, space = 94, diff = "m",
            selfRemark = "这是我们用并查集做的第一个题目；效果还挺好呢。" +
                    "这个题还可以用dfs来做。" +
                    "一个有意思的现象：find函数换成迭代实现之后，time和space都降下来了。可见递归有时候还是很快的，而且递归时，space算os的不算我们的." +
                    "发现两一个现象：同样的代码，多次执行，其time和space都是不一样的")
    public int findCircleNum(int[][] M) {

        int l = M.length;
        int[] parent = new int[l];
        int[] height = new int[l];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if ((i != j) && M[i][j] == 1) {
                    union(parent, height, i, j);
                }
            }
        }

        int circles = 0;
        int[] count = new int[l];
        for (int i = 0; i < parent.length; i++) {
            int root = find(i, parent);
            if (count[root] == 0) {
                circles++;
            }
            count[root]++;
        }
        return circles;
    }

    public int find(int i, int[] parent) {
        if (i != parent[i]) {
            parent[i] = find(parent[i], parent);
        }
        return parent[i];
    }

    public void union(int[] parent, int[] height, int x, int y) {
        x = find(x, parent);
        y = find(y, parent);
        if (x == y) {
            return;
        }
        int hx = height[x];
        int hy = height[y];
        if (hx == hy) {
            height[x]++;
            parent[x] = y;
        } else if (hx < hy) {
            height[x] = y;
        } else {
            height[y] = x;
        }
    }


    @Test
    public void test_solution_1() {
        int[][] M = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}};
        Assert.assertEquals(2, findCircleNum(M));
    }

//    [[1,0,0,1],[0,1,1,0],[0,1,1,1],[1,0,1,1]]

    @Test
    public void test_solution_2() {
        int[][] M = {{1, 0, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}};
        Assert.assertEquals(1, findCircleNum(M));
    }


}
