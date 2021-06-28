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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yueyi
 * @version : OpenTheLock.java, v 0.1 2021年06月27日 10:16 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class OpenTheLock {

    @LetCodeCommit(title = "Open the Lock")
    public int openLock(String[] deadends, String target) {
        return resolveWithBFS(deadends, target);
    }

    public int resolveWithBFS(String[] deadends, String target) {

        Queue<String> queue = new LinkedList<>();
        Set<String> hasVisited = new HashSet<>();
        for (String deadend : deadends) {
            hasVisited.add(deadend);
            if (deadend.equals(target) || deadend.equals("0000")) {
                return -1;
            }
        }
        if(target.equals("0000")){
            return 0;
        }
        hasVisited.add("0000");
        queue.add("0000");
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                String remove = queue.remove();
                char[] source = remove.toCharArray();
                for (int j = 0; j < 4; j++) {
                    char c = source[j];
                    char cAdd1 = (char) ((c - '0' + 1) % 10 + '0');
                    source[j] = cAdd1;
                    String sAdd1 = String.valueOf(source);
                    if (target.equals(sAdd1)) {
                        return level;
                    }
                    if (!hasVisited.contains(sAdd1)) {
                        queue.add(sAdd1);
                        hasVisited.add(sAdd1);
                    }

                    char c_1 = (char) ((c - '0' + 9) % 10 + '0');
                    source[j] = c_1;
                    String s_1 = String.valueOf(source);
                    if (target.equals(s_1)) {
                        return level;
                    }
                    if (!hasVisited.contains(s_1)) {
                        queue.add(s_1);
                        hasVisited.add(s_1);
                    }
                    source[j] = c;
                }
            }
        }
        return -1;
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
                {new String[] {"8888"}, "0009", 1},
                {new String[] {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888", -1},
                {new String[] {"0000"}, "8888", -1}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, openLock(deadends, target));
    }

}