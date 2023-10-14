package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @Date 2019-11-22
 */
public class MaximalRectangle {

    /**
     * title: 85. Maximal Rectangle
     * url = https://leetcode.com/problems/maximal-rectangle/
     * difficulty = hard
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        return solution1(matrix);
    }

    /**
     * 这个思路听好的。巧妙的点在于，计算左右边界
     *
     * @param matrix
     * @return
     */
    public int solution2(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] left = new int[n];    //当前高度的左边界
        int[] right = new int[n];   //当前高度的右边界
        int[] height = new int[n];  //当前高度
        Arrays.fill(left, 0);
        Arrays.fill(right, n);
        Arrays.fill(height, 0);
        int maxA = 0;
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; j++) {       // compute height (can do this from either side)
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            for (int j = 0; j < n; j++) {       // compute left (from left to right)
                if (matrix[i][j] == '1') {
                    //非常巧妙的点。计算left[j]其实要和计算height[j]同步思考。
                    //这里matrix[i][j] == '1'，height[j]加一的。
                    //那么left[i]是否可以保持不变呢？这是一个好问题。我还不能直接解答。
                    //当可以保持的时候，说明j的左边（j-1-left[j], j-1）没有塌方；
                    //当不能保持而被迫，说明j的左边（j-1-left[j], j-1）中有一个塌方了，可能就是左边邻居一，也可能是左边邻居二.
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    //cur_left-1，表示最近的一个是0的square，那么当前left[i]休想再往左边找了。
                    //又因为j是从0开始往右便利，当时square=0的时候，它就更新，所以可以进一步理解为
                    //cur_left是第一个不是square=0的位置。
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) {  // compute right (from right to left)
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            // compute the area of rectangle (can do this from either side)
            for (int j = 0; j < n; j++) {
                maxA = Math.max(maxA, (right[j] - left[j]) * height[j]);
            }
        }
        return maxA;
    }

    /*
    解法：来源于博客  https://www.cnblogs.com/grandyang/p/4322667.html
    分析：问题转化为'直方图中的最大矩阵'； 84. Largest Rectangle in Histogram
    关键点是：如果matrix[i][j]是'0'，则直方图中的第j个bar，其高度也断崖为0.

     */
    public int solution1(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int res = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                //关键点是：如果matrix[i][j]是'0'，则直方图中的第j个bar，其高度也断崖为0.
                height[j] = matrix[i][j] == '0' ? 0 : (1 + height[j]);
            }
            List<Integer> heightList = Arrays.stream(height).boxed().collect(Collectors.toList());
            res = Math.max(res, largestRectangleArea(heightList));
        }
        return res;
    }

    int largestRectangleArea(List<Integer> height) {
        int res = 0;
        Deque<Integer> s = new LinkedList<>();
        height.add(0);
        for (int i = 0; i < height.size(); ++i) {
            if (s.isEmpty() || height.get(s.peek()) <= height.get(i)) s.push(i);
            else {
                int tmp = s.peek();
                s.pop();
                res = Math.max(res, height.get(tmp) * (s.isEmpty() ? i : (i - s.peek() - 1)));
                --i;
            }
        }
        return res;
    }

    @Test
    public void test_solution() {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '1', '0'}, {'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}
        };

        Assert.assertEquals(6, solution2(matrix));
    }
}
