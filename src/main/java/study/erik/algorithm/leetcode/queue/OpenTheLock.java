/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.queue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : OpenTheLock.java, v 0.1 2022年05月04日 09:50 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class OpenTheLock {

    @LetCodeCommit(title = "752. Open the Lock",
            selfRemark = "找最短路径——这类题我们写过太多了.")
    public int openLock(String[] deadends, String target) {

        Set<String> deadEndSet = Arrays.stream(deadends).collect(Collectors.toSet());
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String sequence = "0000";
        if (!deadEndSet.contains(sequence)) {
            queue.add(sequence);
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                String poll = queue.poll();
                if (poll.equals(target)) {
                    return step;
                }
                List<String> nextSequenceList = nextSequence(poll);
                for (String nextSequence : nextSequenceList) {
                    if (deadEndSet.contains(nextSequence) || visited.contains(nextSequence)) {
                        continue;
                    }
                    queue.offer(nextSequence);
                    visited.add(nextSequence);
                }
                size--;
            }
            step++;
        }
        return -1;
    }

    private List<String> nextSequence(String sequence) {
        char[] chars = sequence.toCharArray();
        List<String> allNextSequence = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            char cAdd1 = (c == '9' ? '0' : (char) (c + 1));
            chars[i] = cAdd1;
            allNextSequence.add(String.valueOf(chars));
            char cSubtract1 = (c == '0' ? '9' : (char) (c - 1));
            chars[i] = cSubtract1;
            allNextSequence.add(String.valueOf(chars));
            chars[i] = c;
        }
        return allNextSequence;
    }

    @Parameter
    public String[] deadends;
    @Parameter(1)
    public String   target;
    @Parameter(2)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"0201", "0101", "0102", "1212", "2002"}, "0202", 6},
                {new String[] {"0000"}, "8888", -1},
                {new String[] {"8888"}, "0009", 1},
                {new String[] {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888", -1},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, openLock(deadends, target));
    }

}