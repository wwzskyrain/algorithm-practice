package study.erik.algorithm.leetcode.tree.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/4/4 19:05
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class All_Ancestors_of_a_Node_in_a_Directed_Acyclic_Graph {

    @LetCodeCommit(title = "2192. All Ancestors of a Node in a Directed Acyclic Graph")
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] in = new int[n];
        for (int[] edge : edges) {
            g[edge[0]].add(edge[1]);
            in[edge[1]]++;
        }
        List<Set<Integer>> parents = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parents.add(new HashSet<>());
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int p = q.poll();
            for (Integer child : g[p]) {
                Set<Integer> childParent = parents.get(child);
                Set<Integer> parentParent = parents.get(p);
                parentParent.forEach(childParent::add);
                childParent.add(p);
                in[child]--;
                if (in[child] == 0) {
                    q.add(child);
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++) {
            new ArrayList<>(parents.get(i));
            List<Integer> p = new ArrayList<>(parents.get(i));
            p.sort(Integer::compare);
            ans.add(p);
        }
        return ans;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {8, ArrayUtils.buildArray2Dimension("[[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]")},
                {5, ArrayUtils.buildArray2Dimension("[[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]")},
        });
    }

    @Parameterized.Parameter
    public int n;
    @Parameterized.Parameter(1)
    public int[][] edges;

    @Test
    public void test() {
        List<List<Integer>> ancestors = getAncestors(n, edges);
        ancestors.forEach(System.out::println);
    }

}
