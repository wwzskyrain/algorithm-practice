package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-08-19 15:14
 */
public class TaskScheduler {

    @LetCodeCommit(no = 621, title = "Task Scheduler",
            selfRemark = "这是个贪心的题目？好吧，是的。贪心题，在我看来一般都很简单的，虽然它很难，因为贪心策略不是那么明显，更加不好证明贪心策略有效，但是我还是觉得它很简单，因为我一般选择时间证明而不是理论证明，哈哈哈。" +
                    "但是今天就载了呀，连贪心策略都没有想出来。而且当前的解法里，也是看的答案，而且不得不承认，这个解法真是精彩，但是不通用哈。",
            diff = "m",
            related = {"Reorganize String"})
    public int leastInterval(char[] tasks, int n) {

        int[] map = new int[26];
        for (char c : tasks) {
            map[c - 'A']++;
        }
        Arrays.sort(map);
        int i = 25;
        while (i >= 0 && map[i] == map[25]) {
            i--;
        }
        return Math.max(tasks.length, (map[25] - 1) * (n + 1) + 25 - i);
    }

    @Test
    public void test_solution_1() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        Assert.assertEquals(8, leastInterval(tasks, n));
    }


    @Test
    public void test_solution_2() {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 0;
        Assert.assertEquals(6, leastInterval(tasks, n));
    }


    @Test
    public void test_solution_3() {
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 2;
        Assert.assertEquals(16, leastInterval(tasks, n));
    }


}
