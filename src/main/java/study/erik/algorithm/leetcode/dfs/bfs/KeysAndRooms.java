package study.erik.algorithm.leetcode.dfs.bfs;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-31 09:15
 */
public class KeysAndRooms {

    @LetCodeCommit(title = "841. Keys and Rooms", diff = "m",
            selfRemark = "图的遍历，dfs和bfs都可以")
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, visited, 0);

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    public void dfs(List<List<Integer>> rooms, boolean[] visited, int room) {
        if (visited[room]) {
            return;
        }

        visited[room] = true;
        for (Integer r : rooms.get(room)) {
            dfs(rooms, visited, r);
        }
    }

    @Test
    public void test_solution_1() {
        List<List<Integer>> rooms = Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList());
        Assert.assertTrue(canVisitAllRooms(rooms));
    }

    @Test
    public void test_solution_2() {
        List<List<Integer>> rooms = Arrays.asList(Arrays.asList(1, 3), Arrays.asList(3, 0, 1), Arrays.asList(2), Arrays.asList());
        Assert.assertFalse(canVisitAllRooms(rooms));
    }


}
