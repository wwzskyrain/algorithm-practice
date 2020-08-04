package study.erik.algorithm.nowcoder.zijie;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author erik.wang
 * comment：头条第一面
 */
public class IslandNumber {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        List<String> input = new ArrayList<>();
        String line;

        while (in.hasNextLine()) {
            line = in.nextLine();
            if (line.length() > 0) {
                input.add(line);
            } else {
                break;
            }
        }
        int[][] land = new int[input.size()][];
        for (int i = 0; i < land.length; i++) {
            String str = input.get(i);
            land[i] = new int[str.length()];
            for (int j = 0; j < str.length(); j++) {
                land[i][j] = str.charAt(j) == '1' ? 1 : 0;
            }
        }

        System.out.println(landNumber(land));
    }

    public static int landNumber(int[][] g) {

        boolean[][] visited = new boolean[g.length][];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = new boolean[g[i].length];
        }
        int count = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] == 1 && !visited[i][j]) {
                    count++;
                    dfsVisit(g, i, j, visited);
                }
            }
        }
        return count;
    }

    /**
     * @param g
     * @param i
     * @param j
     * @param visited
     */
    public static void dfsVisit(int[][] g, int i, int j, boolean[][] visited) {

        int i1 = i;
        int j1 = j + 1;
        if (i1 >= 0 && i1 < g.length && j1 >= 0 && j1 < g[i].length && g[i1][j1] == 1 && !visited[i1][j1]) {
            visited[i1][j1] = true;
            dfsVisit(g, i1, j1, visited);
        }

        i1 = i + 1;
        j1 = j;

        if (i1 >= 0 && i1 < g.length && j1 >= 0 && j1 < g[i].length && g[i1][j1] == 1 && !visited[i1][j1]) {
            visited[i1][j1] = true;
            dfsVisit(g, i1, j1, visited);
        }

        i1 = i;
        j1 = j - 1;

        if (i1 >= 0 && i1 < g.length && j1 >= 0 && j1 < g[i].length && g[i1][j1] == 1 && !visited[i1][j1]) {
            visited[i1][j1] = true;
            dfsVisit(g, i1, j1, visited);
        }

        i1 = i - 1;
        j1 = j;

        if (i1 >= 0 && i1 < g.length && j1 >= 0 && j1 < g[i].length && g[i1][j1] == 1 && !visited[i1][j1]) {
            visited[i1][j1] = true;
            dfsVisit(g, i1, j1, visited);
        }
    }

    @Test
    public void test_case_1() {
        int[][] g = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}};
        Assert.assertEquals(1, landNumber(g));
    }


    @Test
    public void test_case_2() {
        int[][] g = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1}};
        Assert.assertEquals(3, landNumber(g));
    }

}
