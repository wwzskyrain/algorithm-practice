package study.erik.algorithm.leetcode.huawei.medium;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

//HJ43 迷宫问题
//https://www.nowcoder.com/practice/cf24906056f4488c9ddb132f317e03bc?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class MiGrid {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m * n; i++) {
            int g = in.nextInt();
            grid[i / n][i % n] = g;
        }
        Deque<int[]> deque = new LinkedList<>();
        deque.addLast(new int[]{0, 0});
        dfs(grid, -1, -1, 0, 0, deque);
        for (int[] point : deque) {
            System.out.printf("(%d,%d)\n", point[0], point[1]);
        }
    }

    public static int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static boolean dfs(int[][] grid, int xP, int yP, int x, int y, Deque<int[]> path) {
        int m = grid.length;
        int n = grid[0].length;
        if (x == m - 1 && y == n - 1) {
            return true;
        }
        for (int[] dir : directions) {
            int xNext = x + dir[0];
            int yNext = y + dir[1];
            if (xNext == xP && yNext == yP) {
                //父节点，不要回头了
                continue;
            }
            if (xNext < 0 || xNext >= m || yNext < 0 || yNext >= n) {
                //越界了
                continue;
            }
            if (grid[xNext][yNext] == 0) {
                // 向下走一步
                path.addLast(new int[]{xNext, yNext});
                boolean findPath = dfs(grid, x, y, xNext, yNext, path);
                if (findPath) {
                    // 快速返回；也即使找到了一条路径。对于树遍历，就是找到了一条道达叶子结点的路径。
                    return findPath;
                } else {
                    // 回退上面向下走的那一步；
                    path.removeLast();
                }
            }
        }
        // 走到这里，说明这条路不通        
        return false;
    }

}
