/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.interval;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : FindServersThatHandledMostNumberOfRequests.java, v 0.1 2023年06月04日 07:03 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1606. Find Servers That Handled Most Number of Requests",
        diff = "h",
        selfRemark = "这是一个interval题目。一般不难，就用优先级队列和treeMap等")
public class FindServersThatHandledMostNumberOfRequests {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int max = 0;
        int[] counter = new int[k];
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        Comparator<Pair<Integer, Integer>> c = Comparator.comparingInt(Pair::getKey);
        PriorityQueue<Pair<Integer, Integer>> busyServers = new PriorityQueue<>(c);
        for (int i = 0; i < arrival.length; i++) {
            int curTime = arrival[i];
            int endTime = load[i] + curTime;
            while (!busyServers.isEmpty() && busyServers.peek().getKey() <= curTime) {
                Integer finishedServer = busyServers.poll().getValue();
                availableServers.add(finishedServer);
            }
            int iTh = i % k;
            Integer assignServer = availableServers.ceiling(iTh);
            if (assignServer == null) {
                assignServer = availableServers.pollFirst();
            }
            if (assignServer == null) {
                // drop
                continue;
            }
            busyServers.add(new Pair<>(endTime, assignServer));
            availableServers.remove(assignServer);
            counter[assignServer]++;
            max = Math.max(max, counter[assignServer]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = counter.length - 1; i >= 0; i--) {
            if (counter[i] == max) {
                list.add(i);
            }
        }
        return list;
    }

    @Parameter
    public int   k;
    @Parameter(1)
    public int[] arrival;
    @Parameter(2)
    public int[] load;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {3, ArrayUtils.buildArray("[1,2,3,4,8,9,10]"), ArrayUtils.buildArray("[5,2,10,3,1,2,2]")},
                {3, ArrayUtils.buildArray("[1,2,3,4,5]"), ArrayUtils.buildArray("[5,2,3,3,3]")},
                {3, ArrayUtils.buildArray("[1,2,3,4]"), ArrayUtils.buildArray("[1,2,1,2]")},
                {3, ArrayUtils.buildArray("[1,2,3]"), ArrayUtils.buildArray("[10,12,11]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(busiestServers(k, arrival, load));
    }
}