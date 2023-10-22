package study.erik.algorithm.leetcode.greedy.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/10/15 ，时间：07:51
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Avoid_Flood_in_The_City {

    @LetCodeCommit(title = "1488. Avoid Flood in The City")
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] ret = new int[n];
        Arrays.fill(ret, 1); //必须抽干一个，默认是1
        TreeSet<Integer> set = new TreeSet<>();
        Map<Integer, Integer> lastLakeIndexMap = new HashMap<>();
        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {
                set.add(i);
            } else {
                ret[i] = -1; //这是一定的
                // ceiling: the least element greater than or equal to e, or null if there is no such element
                // higher:  the least element greater than e, or null if there is no such element
                // floor:   the greatest element less than or equal to e, or null if there is no such element
                int lake = rains[i];
                if (lastLakeIndexMap.containsKey(lake)) {
                    int lastLakeIndex = lastLakeIndexMap.get(lake);
                    Integer theDryDay = set.higher(lastLakeIndex);
                    if (theDryDay == null) {
                        return new int[0];
                    }
                    ret[theDryDay] = lake;
                    set.remove(theDryDay);
                }
                //必须要准备着去抽干这个湖的。
                lastLakeIndexMap.put(lake, i);
            }
        }
        return ret;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[-1,1,-1,2,-1,3,-1,2,1,1,-1,-1,-1]"), ArrayUtils.buildArray("[1,0,2,0,3,0,2,0,0,0,1," +
                        "2,3]")},
                {ArrayUtils.buildArray("[-1,-1,-1,-1]"), ArrayUtils.buildArray("[1,2,3,4]")},
                {ArrayUtils.buildArray("[-1,-1,2,1,-1,-1]"), ArrayUtils.buildArray("[1,2,0,0,2,1]")},
                {ArrayUtils.buildArray("[-1,69,1,1,-1]"), ArrayUtils.buildArray("[69,0,0,0,69]")},
                {ArrayUtils.buildArray("[]"), ArrayUtils.buildArray("[1,2,0,1,2]")},
                });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[] rains;

    @Test
    public void test() {
        Assert.assertArrayEquals(expect, avoidFlood(rains));
    }

}
