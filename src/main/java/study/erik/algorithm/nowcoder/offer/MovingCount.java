package study.erik.algorithm.nowcoder.offer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.QuestionType;
import study.erik.algorithm.util.SwordOfferCommit;

/**
 * @author erik.wang
 * @date 2020-08-26 10:25
 */
public class MovingCount {

    @SwordOfferCommit(title = "可以移动的次数", review = "很明显个的一个dfs题目，不多说",
            type = {QuestionType.Back_Tracking, QuestionType.DFS})
    public int movingCount(int threshold, int rows, int cols) {

        if (threshold < 0) {
            return 0;
        }
        if (rows == 0 || cols == 0) {
            return 0;
        }

        boolean[][] visited = new boolean[rows][cols];
        int[] count = new int[1];
        count[0]++;
        visited[0][0] = true;
        visit(visited, 0, 0, count, threshold);
        return count[0];
    }

    private int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private void visit(boolean[][] v, int i, int j, int[] count, int threshold) {

        for (int k = 0; k < direction.length; k++) {
            int ii = i + direction[k][0];
            int jj = j + direction[k][1];
            if (ii >= 0 && ii < v.length && jj >= 0 && jj < v[ii].length
                    && !v[ii][jj]
                    && sum(ii) + sum(jj) <= threshold) {
                count[0]++;
                v[ii][jj] = true;
                visit(v, ii, jj, count, threshold);
            }
        }

    }

    private int sum(int num) {
        int s = 0;

        while (num > 0) {
            s += num % 10;
            num /= 10;
        }
        return s;
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals(1, movingCount(15, 1, 1));
    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals(0, movingCount(-10, 10, 10));
    }


}
