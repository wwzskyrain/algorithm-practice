package study.erik.algorithm.job.huawei.simulation01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/2 09:49
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_1 {

    @LetCodeCommit(title = "")
    public int numberOfPatterns(int m, int n) {
        int total = 0;
        for (int i = m; i <= n; i++) {
            numberForM(i);
            total += dp[i];
        }
        return total;
    }

    public void numberForM(int m) {
        Stack<Integer> stack = new Stack<>();
        for (int j = 1; j < 10; j++) {
            stack.push(j);
            dfs(stack, m);
            stack.pop();
        }
    }

    int[] dp = new int[10];

    public void dfs(Stack<Integer> path, int m) {
        if (path.size() == m) {
            dp[m]++;
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (path.contains(i)) {
                continue;
            }
            int peek = path.peek();
            int middle = getMiddle(peek, i);
            if (middle != 0 && !path.contains(middle)) {
                continue;
            }
            path.push(i);
            dfs(path, m);
            path.pop();
        }
    }

    Map<String, Integer> middleMap = new HashMap<>();

    public void init() {
        middleMap.put("1-3", 2);
        middleMap.put("1-7", 4);
        middleMap.put("1-9", 5);

        middleMap.put("2-8", 5);
        middleMap.put("3-7", 5);
        middleMap.put("3-9", 6);

        middleMap.put("4-6", 5);
        middleMap.put("7-9", 8);
    }

    public int getMiddle(int i, int j) {
        if (middleMap.isEmpty()) {
            init();
        }
        if (i < j) {
            String key = String.format("%d-%d", i, j);
            return middleMap.getOrDefault(key, 0);
        }
        return getMiddle(j, i);
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {9, 1, 1},
                {65, 1, 2},
                {56, 2, 2},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int m;
    @Parameterized.Parameter(2)
    public int n;

    @Test
    public void test() {
        Assert.assertEquals(expect, numberOfPatterns(m, n));
    }

}
