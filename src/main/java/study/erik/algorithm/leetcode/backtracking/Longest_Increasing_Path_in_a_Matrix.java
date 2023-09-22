package study.erik.algorithm.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-30 16:34
 */
public class Longest_Increasing_Path_in_a_Matrix {

    public int longestIncreasingPath(int[][] matrix) {
        return solution(matrix);
    }

    /**
     * 如果不用lengthM来记录的话，就超时了。
     * 成绩：20% 和 97%
     * 小结：这类二维矩阵找路径的问题，我们已经遇到过不少了，所以直接就上手写代码了
     * 第一版也就20分钟就写出来了，虽然超时了。
     * 第二版十分钟也就有写好并且通过了。
     * 所以说我们做到了重复的题，这对刷题来讲是一个信号——你已经刷了不少题目了。
     *
     * @param matrix
     * @return
     */
    public int solution(int[][] matrix) {
        int longestLength = 0;
        boolean[][] visited = new boolean[matrix.length][];
        int[][] lengthM = new int[matrix.length][];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = new boolean[matrix[i].length];
            lengthM[i] = new int[matrix[i].length];
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                longestLength = Math.max(longestLength, dfs(matrix, visited, i, j, lengthM));
            }
        }
        return longestLength;
    }

    /**
     * 返回当前坐标(x,y)开头最长路径长度
     *
     * @param matrix
     * @param visited
     * @param x
     * @param y
     * @return
     */
    public int dfs(int[][] matrix, boolean[][] visited, int x, int y, int[][] lengthM) {

        if (lengthM[x][y] > 0) {
            return lengthM[x][y];
        }

        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int length = 0;
        int currentValue = matrix[x][y];
        for (int i = 0; i < direction.length; i++) {
            int X = x + direction[i][0];
            int Y = y + direction[i][1];
            if (inBoardAndUnvisited(visited, X, Y)) {
                if (matrix[X][Y] > currentValue) {
                    visited[X][Y] = true;
                    length = Math.max(dfs(matrix, visited, X, Y, lengthM), length);
                    visited[X][Y] = false;
                }
            }

        }
        lengthM[x][y] = length + 1;
        return length + 1;
    }

    private boolean inBoardAndUnvisited(boolean[][] visited, int X, int Y) {
        return X >= 0 && X < visited.length
                && Y >= 0 && Y < visited[X].length
                && (!visited[X][Y]);
    }

    /**
     * 返回当前(x,y)的最长路径的长度
     *
     * @param matrix
     * @param lengthM
     * @param x
     * @param y
     * @return
     */
    private int getLength(int[][] matrix, boolean[][] visited, int[][] lengthM, int x, int y) {
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        if (lengthM[x][y] > 0) {
            return lengthM[x][y];
        }
        int temp = 0;
        for (int i = 0; i < direction.length; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];
            if (inBoardAndUnvisited(visited, newX, newY)) {

            }
        }
        lengthM[x][y] = temp + 1;
        return temp + 1;
    }

    @Test
    public void test_solution() {

        int[][] nums2 = {{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, {19, 18, 17, 16, 15, 14, 13, 12, 11, 10}, {20, 21, 22, 23, 24, 25, 26, 27, 28, 29}, {39, 38, 37, 36, 35, 34, 33, 32, 31, 30}, {40, 41, 42, 43, 44, 45, 46, 47, 48, 49}, {59, 58, 57, 56, 55, 54, 53, 52, 51, 50}, {60, 61, 62, 63, 64, 65, 66, 67, 68, 69}, {79, 78, 77, 76, 75, 74, 73, 72, 71, 70}, {80, 81, 82, 83, 84, 85, 86, 87, 88, 89}, {99, 98, 97, 96, 95, 94, 93, 92, 91, 90}, {100, 101, 102, 103, 104, 105, 106, 107, 108, 109}, {119, 118, 117, 116, 115, 114, 113, 112, 111, 110}, {120, 121, 122, 123, 124, 125, 126, 127, 128, 129}, {139, 138, 137, 136, 135, 134, 133, 132, 131, 130}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        Assert.assertEquals(140, solution(nums2));


        int[][] nums1 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };

        Assert.assertEquals(4, solution(nums1));

        int[][] nums = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}};

        Assert.assertEquals(4, solution(nums));

    }
}
