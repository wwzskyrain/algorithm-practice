package study.erik.algorithm.leetcode.graph;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-08-27 08:37
 */
public class ReconstructItinerary {

    @LetCodeCommit(title = "332. Reconstruct Itinerary", selfRemark = "就是一个to遍历，太麻烦，不做。而且很多人在踩")
    public List<String> findItinerary(List<List<String>> tickets) {
        return null;
    }

    @Test
    public void test_solution() {
        List<List<String>> tickets = Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));
        List<String> except = Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO");
        Assert.assertEquals(except, findItinerary(tickets));
    }

}
