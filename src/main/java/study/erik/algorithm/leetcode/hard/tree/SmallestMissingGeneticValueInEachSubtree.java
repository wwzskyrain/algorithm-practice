package study.erik.algorithm.leetcode.hard.tree;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/8/3 ，时间：11:07
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "2003. Smallest Missing Genetic Value in Each Subtree")
public class SmallestMissingGeneticValueInEachSubtree {


    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        A = nums;
        for (int i = 0; i < n; i++) {
            m.computeIfAbsent(parents[i], k -> new ArrayList<>()).add(i);
        }
        int[] res = new int[n];
        Arrays.fill(res, 1);
        int idx = -1; //value的1的节点
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                idx = i;
                break;
            }
        }

        if (idx == -1) return res; //如果不存在，那么就是res都是1，直接返回了。
        int s = idx;
        int pre = -1; // pre handled node
        while (s != -1) {
            //访问路径root->...->1；倒序访问。
            List<Integer> list = m.get(s);
            if (list != null) {
                for (int j : m.get(s)) {
                    if (j == pre) {
                        //上一次便利过这个兄弟节点了
                        continue; // skip handled}
                    }
                    dfs(j);
                }
            }
            set.add(A[s]); //这个set是累积使用了的。
            while (set.contains(miss)) {
                miss++;
            }
            res[s] = miss;

            pre = s; // update pre and s
            s = parents[s]; // path from 1 up to root

        }
        return res;
    }

    void dfs(int i) {
        set.add(A[i]);
        List<Integer> list = m.get(i);
        if (list == null) return;
        for (int j : list) {
            dfs(j);
        }
    }

    HashMap<Integer, List<Integer>> m = new HashMap<>();
    int[] A;
    int miss = 1;
    HashSet<Integer> set = new HashSet<>();


    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {2, 2, ArrayUtils.buildArray2Dimension("[[1,1],[2,1],[1,2],[2,2]]"), 2},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter
    public int[] parents;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        Assert.assertEquals(expect, smallestMissingValueSubtree(parents, nums));
    }

}
