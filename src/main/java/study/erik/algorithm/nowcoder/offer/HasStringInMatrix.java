package study.erik.algorithm.nowcoder.offer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.QuestionType;
import study.erik.algorithm.util.SwordOfferCommit;


/**
 * @author erik.wang
 * @date 2020-08-26 11:12
 */
public class HasStringInMatrix {

    @SwordOfferCommit(title = "matrix中，有指定的string吗？", review = "还是回溯法",
            type = {QuestionType.Back_Tracking, QuestionType.DFS})
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (str.length > matrix.length) {
            return false;
        }

        if (str.length == 0) {
            return false;
        }
        char[][] m = new char[rows][cols];
        for (int i = 0; i < matrix.length; i++) {
            m[i / cols][i % cols] = matrix[i];
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == str[0]) {
                int x = i / cols;
                int y = i % cols;
                boolean[][] visited = new boolean[rows][cols];
                visited[x][y] = true;
                if (dfs(m, visited, x, y, str, 1)) {
                    return true;
                }
                visited[x][y] = true;
            }
        }
        return false;
    }

    private int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};


    private boolean dfs(char[][] matrix, boolean[][] visited, int x, int y, char[] str, int i) {

        if (i == str.length) {
            return true;
        }
        for (int j = 0; j < directions.length; j++) {
            int xx = x + directions[j][0];
            int yy = y + directions[j][1];
            if (xx >= 0 && xx < matrix.length && yy >= 0 && yy < matrix[xx].length
                    && !visited[xx][yy]
                    && matrix[xx][yy] == str[i]) {
                visited[xx][yy] = true;
                if (dfs(matrix, visited, xx, yy, str, i + 1)) {
                    return true;
                }
                visited[xx][yy] = false;
            }
        }
        return false;
    }

    @Test
    public void test_solution_1() {
        char[] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
        Assert.assertTrue(hasPath(matrix, 3, 4, "bcced".toCharArray()));

    }

    @Test
    public void test_solution_2() {
        char[] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
        Assert.assertFalse(hasPath(matrix, 3, 4, "abcb".toCharArray()));
    }

    @Test
    public void test_solution_3() {
        char[] matrix = "ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray();
        Assert.assertFalse(hasPath(matrix, 5, 8, "SGGFIECVAASABCEHJIGQEMS".toCharArray()));
    }


}
