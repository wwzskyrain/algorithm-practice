package study.erik.algorithm.leetcode.matrix;


import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/10/5 ，时间：10:52
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Maximum_Strictly_Increasing_Cells_in_a_Matrix {

    @LetCodeCommit(title = "2713. Maximum Strictly Increasing Cells in a Matrix")
    public int maxIncreasingCells(int[][] mat) {
        return maxIncreasingCellsSolution1(mat);
    }

    /**
     * 用dfs+memo 一样超时了
     */
    public int maxIncreasingCellsSolution2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Map<String, Integer> memo = new HashMap<>();
        int[] ans = new int[]{0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(mat, m, n, i, j, memo, ans);
            }
        }
        return ans[0];
    }

    public int dfs(int[][] mat, int m, int n, int x, int y, Map<String, Integer> memo, int[] ans) {
        String key = String.format("%d-%d", x, y);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int max = 1;
        int v = mat[x][y];
        for (int j = 0; j < n; j++) {
            if (mat[x][j] > v) {
                max = Math.max(max, dfs(mat, m, n, x, j, memo, ans) + 1);
            }
        }
        for (int i = 0; i < m; i++) {
            if (mat[i][y] > v) {
                max = Math.max(max, dfs(mat, m, n, i, y, memo, ans) + 1);
            }
        }
        ans[0] = Math.max(ans[0], max);
        memo.put(key, max);
        return max;
    }


    /**
     * 第一个版不超时了。
     * 超时原因：用一个二维数组dp来记录每一个[i][j]的解，从大到小来遍历这个mat，对于每一个[i][j]都读其遍历当列、当行，从而找出最大值。
     * 超时原因也就不言而喻了，因为相当于每一个都要访问m+n个元素。
     * 算法本身没有问题哈，问题在于不高效。
     * 好了，优化吧。
     * 首先，我们是从大到小访问的，所以，当访问到[i][j]时，已经存在的都是可行的解，都是问题递归的解，只要加一就行啊。
     * 所以，不用遍历当前行和当前列，只需要知道当前行和当前列的最大值，即可。
     * OK，思路很清晰。为了更快的找到当前行的最大值和当前列的最大值，需要借助两个辅助数组。
     * 好了。问题得到解决。
     * 不过，从大到小访问和从小到大访问都是一样的。本例中就是从小达到访问。
     *
     * @param mat
     * @return
     */
    public int maxIncreasingCellsSolution1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        TreeMap<Integer, List<Pair<Integer, Integer>>> map = new TreeMap<>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                int v = mat[i][j];
                map.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(i, j));
            }
        }
        int[] rowMax = new int[m];
        int[] columnMax = new int[n];
        int retMax = 0;
        for (List<Pair<Integer, Integer>> list : map.values()) {
            int size = list.size();
            int[] maxArr = new int[size];
            for (int i = 0; i < list.size(); i++) {
                //
                Pair<Integer, Integer> point = list.get(i);
                maxArr[i] = Math.max(rowMax[point.getKey()], columnMax[point.getValue()]) + 1;
                retMax = Math.max(retMax, maxArr[i]);
            }//在找到所有maxArr后，再更新行列的最大值。注意，相同的值一定要在同一层做处理。
            for (int i = 0; i < maxArr.length; i++) {
                Pair<Integer, Integer> point = list.get(i);
                Integer x = point.getKey();
                Integer y = point.getValue();
                int newMax = maxArr[i];
                rowMax[x] = Math.max(rowMax[x], newMax);
                columnMax[y] = Math.max(columnMax[y], newMax);
            }

        }
        return retMax;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {2, ArrayUtils.buildArray2Dimension("[[3,1],[3,4]]")},
                {1, ArrayUtils.buildArray2Dimension("[[1,1],[1,1]]")},
                {4, ArrayUtils.buildArray2Dimension("[[3,1,6],[-9,5,7]]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[][] mat;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxIncreasingCells(mat));
    }

}
