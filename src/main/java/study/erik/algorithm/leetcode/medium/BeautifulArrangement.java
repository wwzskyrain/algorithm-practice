/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : BeautifulArrangement.java, v 0.1 2021年08月16日 11:56 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BeautifulArrangement {

    @LetCodeCommit(title = "Beautiful Arrangement")
    public int countArrangement(int n) {

        List<Integer>[] lists = new List[n + 1];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            lists[i].add(i);
            int time = 2;
            while (time * i <= n) {
                int multi = time * i;
                lists[i].add(multi);
                lists[multi].add(i);
                time++;
            }
        }
        int[] total = new int[1];
        backtracks(lists, 1, new boolean[n + 1], total);
        return total[0];
    }

    /**
     * 回溯法， 独立做出来，会费了四十分钟吧，时间有点久。
     * 成绩不好，28%。把hashSet换成了boolean[]，成绩提高到83%。
     * 最后，回溯法是确定无疑的
     *
     * @param lists
     * @param i
     * @param visited
     * @param total
     */
    public void backtracks(List<Integer>[] lists, int i, boolean[] visited, int[] total) {
        if (i == lists.length) {
            total[0]++;
            return;
        }
        List<Integer> toVisitedList = lists[i];
        for (int j = 0; j < toVisitedList.size(); j++) {
            Integer toVisited = toVisitedList.get(j);
            if (visited[toVisited]) {
                continue;
            }
            visited[toVisited] = true;
            backtracks(lists, i + 1, visited, total);
            visited[toVisited] = false;
        }
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {2, 2},
                {1, 1},
        };
    }

    @Test
    public void test_1() {
        Assert.assertEquals(expect, countArrangement(n));
    }
}