package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-16 08:22
 */
public class FloodFill {

    @LetCodeCommit(no = 733, title = "Flood Fill")
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        dfs(image, image[sr][sc], sr, sc, -1, directions);
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                int v = image[i][j];
                image[i][j] = v == -1 ? newColor : v;
            }
        }
        return image;

    }

    public void dfs(int[][] image, int initColor, int sr, int sc, int newColor, int[][] directions) {
        image[sr][sc] = newColor;
        for (int[] dir : directions) {
            int newSr = sr + dir[0];
            int newSc = sc + dir[1];
            if (newSr >= 0 && newSr < image.length && newSc >= 0 && newSc < image[sr].length && image[newSr][newSc] == initColor) {
                dfs(image, initColor, newSr, newSc, newColor, directions);
            }
        }
    }

    @Test
    public void test_solution_1() {
        String input = "[[1,1,1],[1,1,0],[1,0,1]]";
        int[][] expect = buildImage("[[2,2,2],[2,2,0],[2,0,1]]");
        int[][] actual = floodFill(buildImage(input), 1, 1, 2);
        Assert.assertArrayEquals(expect, actual);
    }

    @Test
    public void test_solution_2() {
        String input = "[[0,0,0],[0,1,1]]";
        int[][] expect = buildImage("[[0,0,0],[0,1,1]]");
        int[][] actual = floodFill(buildImage(input), 1, 1, 1);
        Assert.assertArrayEquals(expect, actual);
    }

    public int[][] buildImage(String input) {
        int fast = 2;
        int low = 2;


        List<String> rowStrings = new LinkedList<>();
        while (fast < input.length()) {
            while (input.charAt(fast) != ']') {
                fast++;
            }
            rowStrings.add(input.substring(low, fast));
            low = fast + 3;
            fast += 3;
        }

        int[][] image = new int[rowStrings.size()][];
        int index = 0;
        for (String rowString : rowStrings) {
            String[] values = rowString.split(",");
            int[] row = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                row[i] = Integer.valueOf(values[i]);
            }
            image[index++] = row;
        }
        return image;
    }


}
