package study.erik.algorithm.leetcode.series.intervals;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.leetcode.util.Interval;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/10/5 ，时间：15:43
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Employee_Free_Time {

    @LetCodeCommit(title = "759. Employee Free Time",
            selfRemark = "这个题目是hard呀，其实也不难嘛，就是操作interval有点不方便，不过TreeMap是利器。" +
                    "我们完全自主ac的，厉害。" +
                    "不过其中有两处细节，是提交调试出来的。" +
                    "我们还写了Interval这个类，以及其构造类。")
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (List<Interval> intervals : schedule) {
            for (Interval interval : intervals) {
                //细节1：如果start相同，则只需要考虑更长end的那个interval。
                map.merge(interval.start, interval.end, Math::max);
            }
        }
        List<Interval> ret = new ArrayList<>();
        Integer curStart = map.firstKey();
        Integer curEnd = map.get(curStart);
        map.remove(curStart);
        while (!map.isEmpty()) {
            Integer firstKey = map.firstKey();
            if (firstKey > curEnd) {
                //细节1：[94,94]这种不算。
                ret.add(new Interval(curEnd, firstKey));
                curEnd = map.get(firstKey);
                map.remove(firstKey);
            } else {
                Integer firstValue = map.get(firstKey);
                if (firstValue <= curEnd) {
                    map.remove(firstKey);
                } else {
                    curEnd = firstValue;
                    map.remove(firstKey);
                }
            }
        }
        return ret;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {Interval.buildList("[11,12],[21,23],[29,30],[62,69],[73,76]"), Interval.buildList2Dimension("[[[0,1],[12,19],[42,54],[78,80],[89,93]],[[5,10],[30,62],[69,73],[80,83],[90,100]],[[13,21],[23,29],[42,43],[86,91],[97,100]],[[0,11],[24,25],[30,58],[76,93],[94,97]],[[1,3],[46,50],[56,58],[71,73],[77,99]]]")},
                {Interval.buildList("[26,27],[36,39],[87,91]"), Interval.buildList2Dimension("[[[7,24],[29,33],[45,57],[66,69],[94,99]],[[6,24],[43,49],[56,59],[61,75],[80,81]],[[5,16],[18,26],[33,36],[39,57],[65,74]],[[9,16],[27,35],[40,55],[68,71],[78,81]],[[0,25],[29,31],[40,47],[57,87],[91,94]]]")},
                {Interval.buildList("[3,4]"), Interval.buildList2Dimension("[[[1,2],[5,6]],[[1,3]],[[4,10]]]")},
                {Interval.buildList("[5,6],[7,9]"), Interval.buildList2Dimension("[[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]")},
                });
    }

    @Parameterized.Parameter
    public List<Interval> expect;
    @Parameterized.Parameter(1)
    public List<List<Interval>> schedule;

    @Test
    public void test() {
        Assert.assertEquals(expect.toString(), employeeFreeTime(schedule).toString());
    }

}
