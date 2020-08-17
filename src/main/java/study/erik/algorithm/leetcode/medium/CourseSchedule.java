package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-17 19:36
 */
public class CourseSchedule {

    @LetCodeCommit(no = 207, title = "Course Schedule", time = 11, space = 64, diff = "m",
            selfRemark = "第一次就想到了dfs来找环，但是没过；dfs保持，换了一下所使用的数据结构，比如把二位数组换成二位list，把set换成数组，才勉强通过。" +
                    "一定有bfs快速的招数；啊，试了下别人的bfs，也没有快太多，才33",
            related = {"Course Schedule II", "Course Schedule III", "Minimum Height Trees"})
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            lists.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            List<Integer> children = lists.get(prerequisite[0]);
            children.add(prerequisite[1]);
        }

        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            visited[i] = true;
            if (dfs(lists, i, visited)) {
                return false;
            }
            for (int i1 = 0; i1 < visited.length; i1++) {
                visited[i1] = false;
            }
        }
        return true;
    }

    /**
     * 有环吗
     *
     * @param lists
     * @param c
     * @param visited
     * @return 有环吗
     */
    private boolean dfs(List<List<Integer>> lists, int c, boolean[] visited) {

        boolean haveCircle = false;
        List<Integer> children = lists.get(c);
        for (Integer child : children) {
            if (!visited[child]) {
                visited[child] = true;
                haveCircle = haveCircle || dfs(lists, child, visited);
                if (haveCircle) {
                    return true;
                }
                visited[child] = false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test_solution_1() {
        Assert.assertFalse(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }

    @Test
    public void test_solution_2() {
        Assert.assertTrue(canFinish(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}));
    }


}
