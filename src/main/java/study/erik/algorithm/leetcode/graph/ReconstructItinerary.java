package study.erik.algorithm.leetcode.graph;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author erik.wang
 * @date 2020-08-27 08:37
 */
@RunWith(Parameterized.class)
public class ReconstructItinerary {

    @LetCodeCommit(title = "332. Reconstruct Itinerary",
            selfRemark = "就是一个to遍历，太麻烦，不做。而且很多人在踩")
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            PriorityQueue<String> tos = map.getOrDefault(from, new PriorityQueue<>());
            tos.add(ticket.get(1));
            map.put(from, tos);
        }
        String initFrom = "JFK";
        Deque<String> stack = new LinkedList<>();
        stack.push(initFrom);
        dfs(map, stack);
        return new ArrayList<>(stack);
    }

    public boolean dfs(Map<String, PriorityQueue<String>> map, Deque<String> stack) {
        if (map.isEmpty()) {
            return true;
        }
        String curFrom = stack.peek();
        PriorityQueue<String> tos = map.get(curFrom);
        if (tos == null) {
            return false;
        }
        Iterator<String> iterator = tos.iterator();
        while (iterator.hasNext()) {
            String to = iterator.next();
            stack.push(to);
            iterator.remove();
            if (dfs(map, stack)) {
                return true;
            }
            tos.add(to);
        }
        return false;
    }

    @Parameter
    public List<List<String>> tickets;
    @Parameter(1)
    public List<String>       expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {Arrays.asList(Arrays.asList("MUC", "LHR"),
                        Arrays.asList("JFK", "MUC"),
                        Arrays.asList("SFO", "SJC"),
                        Arrays.asList("LHR", "SFO")),
                        Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO")},

                {Arrays.asList(Arrays.asList("JFK", "SFO"),
                        Arrays.asList("JFK", "ATL"),
                        Arrays.asList("SFO", "ATL"),
                        Arrays.asList("ATL", "JFK"),
                        Arrays.asList("SFO", "ATL")),
                        Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO")},
        };
    }

    @Test
    public void test_solution() {
        Assert.assertEquals(expect, findItinerary(tickets));
    }

}
