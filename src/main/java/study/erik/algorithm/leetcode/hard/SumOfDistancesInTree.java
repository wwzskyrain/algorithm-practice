package study.erik.algorithm.leetcode.hard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

@RunWith(Parameterized.class)
public class SumOfDistancesInTree {

    Map<Integer, Set<Integer>> tree = new HashMap<>();
    int[]                      count;
    int[]                      ans;
    int                        N;

    @LetCodeCommit(title = "834. Sum of Distances in Tree",
            selfRemark = "首先用迭代边(每加入一条边就广播式更新)的思路是要不得的，"
                    + "这样会导致大量的计算。"
                    + "用树的思维来执行，确实很容易掌握其计算空间，时间复杂度也就是O(n)——树节点级别.",
            diff = "h")
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        count = new int[n];
        ans = new int[n];
        N = n;
        Arrays.fill(count, 1);
        IntStream.range(0, n).forEach(i -> tree.put(i, new HashSet<>()));

        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        int root = 0;
        postDfs(root, -1);
        preDfs(root, -1);
        return ans;
    }

    public void postDfs(int root, int parent) {
        for (Integer child : tree.get(root)) {
            if (child == parent) {
                continue;
            }
            postDfs(child, root);
            count[root] += count[child];
            ans[root] += ans[child] + count[child];
        }
    }

    public void preDfs(int root, int parent) {
        for (Integer child : tree.get(root)) {
            if (child == parent) {
                continue;
            }
            ans[child] = ans[root] - count[child] + N - count[child];
            preDfs(child, root);
        }
    }

    @Parameter
    public int[][] edges;
    @Parameter(1)
    public int     n;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[0,1],[0,2],[2,3],[2,4],[2,5]]"), 6},
                {ArrayUtils.buildArray2Dimension("[]"), 1},
                {ArrayUtils.buildArray2Dimension("[[1,0]]"), 2},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(sumOfDistancesInTree(n, edges)));
    }

}