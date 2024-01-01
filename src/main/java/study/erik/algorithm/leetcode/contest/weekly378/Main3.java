package study.erik.algorithm.leetcode.contest.weekly378;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/1/1 15:51
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main3 {

    @LetCodeCommit(title = "2982. Find Longest Special Substring That Occurs Thrice II",
            selfRemark = "AC了")
    public int maximumLength(String s) {
        PriorityQueue<Integer>[] queues = new PriorityQueue[26];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new PriorityQueue<>();
        }
        int l = s.length();
        int low = 0;

        for (int i = 0; i < l; i++) {
            int c = s.charAt(i);
            PriorityQueue<Integer> queue = queues[c - 'a'];
            int length;
            if (c != s.charAt(low)) {
                low = i;
            }
            length = i - low + 1;
            queue.add(length);
            while (queue.size() > 3) {
                queue.poll();
            }
        }
        int max = -1;
        for (PriorityQueue<Integer> queue : queues) {
            if (queue.size() >= 3) {
                max = Math.max(max, queue.peek());
            }
            while (queue.size() > 1) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                int maxCounter = queue.poll();
                if(maxCounter > 2) {
                    max = Math.max(max, maxCounter - 2);
                }
            }
        }

        return max;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {-1, "bbc"},
                {2, "aaaa"},
                {-1, "abcdef"},
                {1, "abcaba"},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public String s;


    @Test
    public void test() {
        Assert.assertEquals(expect, maximumLength(s));
    }

}
