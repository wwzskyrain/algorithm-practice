/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : MinimumGeneticMutation.java, v 0.1 2022年02月24日 7:46 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumGeneticMutation {

    @LetCodeCommit(title = "433. Minimum Genetic Mutation",
            selfRemark = "之所以bfs有效，是因为next之间都是平等的。"
                    + "至于要形式化证明，需要考虑假设法")
    public int minMutation(String start, String end, String[] bank) {
        if (start.equalsIgnoreCase(end)) {
            return 0;
        }
        int level = 0;
        Set<String> bankSet = Arrays.stream(bank).collect(Collectors.toSet());
        char[] charSet = new char[] {'A', 'C', 'G', 'T'};
        Set<String> visited = new HashSet<>();
        Deque<String> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            level++;
            while (queueSize > 0) {
                String cur = queue.poll();
                queueSize--;
                char[] curCharArray = cur.toCharArray();
                for (int i = 0; i < curCharArray.length; i++) {
                    char oddC = curCharArray[i];
                    for (char c : charSet) {
                        curCharArray[i] = c;
                        String next = new String(curCharArray);
                        if (!visited.contains(next) && bankSet.contains(next)) {
                            if (next.equalsIgnoreCase(end)) {
                                return level;
                            }
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                    curCharArray[i] = oddC;
                }
            }
        }
        return -1;
    }

    @Parameter
    public String   start;
    @Parameter(1)
    public String   end;
    @Parameter(2)
    public String[] bank;
    @Parameter(3)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"AACCGGTT", "AACCGGTA", new String[] {}, -1},
                {"AACCGGTT", "AAACGGTA", new String[] {"AACCGGTA", "AACCGCTA", "AAACGGTA"}, 2},
                {"AAAAACCC", "AACCCCCC", new String[] {"AAAACCCC", "AAACCCCC", "AACCCCCC"}, 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minMutation(start, end, bank));
    }

}