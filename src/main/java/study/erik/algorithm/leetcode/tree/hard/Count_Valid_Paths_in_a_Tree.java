package study.erik.algorithm.leetcode.tree.hard;


import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/9/25 ，时间：14:00
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Count_Valid_Paths_in_a_Tree {

    @LetCodeCommit(title = "2867. Count Valid Paths in a Tree",
            selfRemark = "这是一个新题。" +
                    "youtube上'每日一题'推荐的，同时它也给了一个解法：unin-find。" +
                    "之所以能用union-find算法，因为需要分组并计数，然后计算。它的去重算法有点惊艳——除以2." +
                    "本体的解法就更符合题设——树了，当然其实是一个图。" +
                    "用了dfs，令人惊艳的一点是：归并孩子节点，并继续和后续的孩子节点进行计算。")
    public long countPaths(int n, int[][] edges) {
        /*
         * 按照树/图的思路去解答
         * */
        initPrimeSet(n);
        List<List<Integer>> conn = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            conn.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            conn.get(edge[0]).add(edge[1]);
            conn.get(edge[1]).add(edge[0]);
        }
        long[] ret = new long[1];
        dfs(1, 0, conn, ret);
        return ret[0];
    }

    public static class Pair {
        public int noPrime;
        public int onePrime;

        public Pair(int noPrime, int onePrime) {
            this.noPrime = noPrime;
            this.onePrime = onePrime;
        }
    }

    public Pair dfs(int root, int parent, List<List<Integer>> conn, long[] ret) {
        boolean isPrime = primeList.contains(root);
        Pair p = new Pair(isPrime ? 0 : 1, isPrime ? 1 : 0);
        for (int child : conn.get(root)) {
            if (child == parent) {
                continue;
            }
            Pair childPair = dfs(child, root, conn, ret);
            ret[0] += (p.noPrime * childPair.onePrime + p.onePrime * childPair.noPrime);

            //要累加孩子节点的noPrime和onePrime了。
            if (isPrime) {
                //当前节点是prime了，所以p.noPrime就锁死为0了，但是onePrime需要累加上孩子节点的noPrime。
                p.onePrime += childPair.noPrime;
            } else {
                //当前节点不是prime，那么累加时就对应相加喽。
                p.noPrime += childPair.noPrime;
                p.onePrime += childPair.onePrime;
            }
        }
        return p;
    }

    public List<Integer> primeList = new ArrayList<>();

    public void initPrimeSet(int n) {
        primeList.add(2);
        primeList.add(3);
        primeList.add(5);
        for (int i = 6; i <= n; i++) {
            boolean isPrime = true;
            for (Integer pri : primeList) {
                if (i % pri == 0) {
                    isPrime = false;
                    break;
                }
                if (pri > i / 2) {
                    break;
                }
            }
            if (isPrime) {
                primeList.add(i);
            }

        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {4, 5, ArrayUtils.buildArray2Dimension("[[1,2],[1,3],[2,4],[2,5]]")},
                {6, 6, ArrayUtils.buildArray2Dimension("[[1,2],[1,3],[2,4],[3,5],[3,6]]")},
                {16, 10, ArrayUtils.buildArray2Dimension("[[10,9],[2,10],[1,10],[3,2],[6,10],[4,3],[8,6],[5,8],[7,6]]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;
    @Parameterized.Parameter(2)
    public int[][] edges;

    @Test
    public void test() {
        Assert.assertEquals(expect, countPaths(n, edges));
    }

}
