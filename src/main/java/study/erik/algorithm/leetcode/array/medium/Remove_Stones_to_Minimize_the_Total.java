package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/24 08:06
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Remove_Stones_to_Minimize_the_Total {

    @LetCodeCommit(title = "1962. Remove Stones to Minimize the Total")
    public int minStoneSum(int[] piles, int k) {
        SortedMap<Integer, Integer> map = new TreeMap<>();
        for (int pile : piles) {
            map.merge(pile, 1, Integer::sum);
        }
        while (k-- > 0) {
            int maxKey = map.lastKey();
            int maxKeyValue = map.get(maxKey);
            int floodKey = maxKey / 2 + maxKey % 2;
            map.merge(floodKey, 1, Integer::sum);
            maxKeyValue--;
            if (maxKeyValue == 0) {
                map.remove(maxKey);
            } else {
                map.put(maxKey, maxKeyValue);
            }
        }
        int t = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            t += (entry.getKey() * entry.getValue());
        }
        return t;
    }

    public int minStoneSum1(int[] piles, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int pile : piles) {
            q.add(pile);
        }
        while (k-- > 0) {
            int pile = q.poll();
            int nextPile = pile / 2 + (pile % 2);
            q.add(nextPile);
        }
        int t = 0;
        for (Integer e : q) {
            t += e;
        }
        return t;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {12, ArrayUtils.buildArray("[5,4,9]"), 2},
                {12, ArrayUtils.buildArray("[4,3,6,7]"), 3},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] piles;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, minStoneSum1(piles, k));
    }

}
