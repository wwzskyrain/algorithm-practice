package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

import static study.erik.algorithm.util.QuestionType.Graph;

/**
 * @author erik.wang
 * @date 2020-08-18 13:49
 */
public class CourseScheduleII {

    @LetCodeCommit(no = 32, title = "Course Schedule II", time = 32, space = 5, diff = "m",
            selfRemark = "拓扑排序来遍历有向无环图(也可能有环)",
    types = Graph,
            related = {"Course Schedule III",
            " Minimum Height Trees"}
    )
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] in = new int[numCourses];
        int[][] map = new int[numCourses][numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            in[prerequisites[i][0]]++;
            map[prerequisites[i][1]][prerequisites[i][0]] = 1;
        }
        int[] visited = new int[numCourses];
        int visitedIndex = 0;

        while (true) {
            int indexO = indexFirstZero(in);
            if (indexO >= 0) {
                visited[visitedIndex++] = indexO;
                in[indexO] = -1;
                for (int i = 0; i < map[indexO].length; i++) {
                    if (map[indexO][i] == 1) {
                        in[i]--;
                    }
                }
            } else {
                for (int i = 0; i < in.length; i++) {
                    if (in[i] != -1) {
                        return new int[0];
                    }
                }
                return visited;
            }
        }
    }

    public int indexFirstZero(int[] in) {
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test_solution_1() {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
    }

    @Test
    public void test_solution_2() {
        int[] expect1 = new int[]{0, 1, 2, 3};
        int[] expect2 = new int[]{0, 2, 1, 3};
        Assert.assertArrayEquals(expect1, findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));
    }

    @Test
    public void test_solution_3() {
        int[] expect1 = new int[0];
        Assert.assertArrayEquals(expect1, findOrder(2, new int[][]{{0, 1}, {1, 0}}));
    }


}
