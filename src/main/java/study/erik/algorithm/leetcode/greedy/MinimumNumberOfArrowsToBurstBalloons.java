package study.erik.algorithm.leetcode.greedy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.AttentionErik;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yueyi
 * @version : MinimumNumberOfArrowsToBurstBalloons.java, v 0.1 2020/10/11 08:39 yueyi Exp $
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    @LetCodeCommit(title = "Minimum Number of Arrows to Burst Balloons")
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] < o2[1] ? -1 : o1[1] == o2[1] ? 0 : 1;
            }
        });
        boolean[] visited = new boolean[points.length];
        int arrowShot = 0;
        for (int i = 0; i < points.length; i++) {
            if (visited[i]) {
                continue;
            }
            arrowShot++;
            visited[i] = true;
            int position = points[i][1];
            for (int j = i; j < points.length; j++) {
                if (!visited[j] && points[j][0] <= position) {
                    visited[j] = true;
                }
            }
        }
        return arrowShot;
    }


    @Test
    public void test_solution_1() {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        Assert.assertEquals(2, findMinArrowShots(points));
    }

    @Test
    public void test_solution_2() {
        int[][] points = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        Assert.assertEquals(4, findMinArrowShots(points));
    }

    @Test
    public void test_solution_3() {
        int[][] points = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        Assert.assertEquals(2, findMinArrowShots(points));
    }


    @Test
    public void test_solution_4() {
        int[][] points = {{1, 2}};
        Assert.assertEquals(1, findMinArrowShots(points));
    }


    @Test
    public void test_solution_5() {
        int[][] points = {{2, 3}, {2, 3}};
        Assert.assertEquals(1, findMinArrowShots(points));
    }

    @Test
    public void test_solution_6() {
        int[][] points = {{-2147483646, -2147483645}, {2147483646, 2147483647}};
        Assert.assertEquals(2, findMinArrowShots(points));
    }

    @AttentionErik(content = "compare时，不用简单的用 a-b, 会有溢出的情况，要用逻辑运算符")
    @Test
    public void test_() {
        Assert.assertTrue(-2147483645 < 2147483647);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        int d1 = -2147483645;
        int d2 = 2147483647;
        System.out.println(d2 - d1);
        Integer[] arr = {-2147483645, 2147483647};
        Arrays.sort(arr, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(arr));
    }
}
