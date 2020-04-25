package study.erik.algorithm.leetcode.hard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-04-18 22:22
 */
public class InsertInterval {


    public int[][] insert(int[][] intervals, int[] newInterval) {

        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        if (intervals.length == 1) {
            // TODO: 2020-04-18 太细节了，不谢了，没意思
        }

        List<int[]> result = new ArrayList<>();

        int[] newCap = new int[2];
        boolean foundNewStart = false;
        boolean foundNewEnd = false;
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                result.add(interval);
                continue;
            }
            if (!foundNewStart) {
                if (newInterval[0] < interval[1]) {
                    newCap[0] = Math.min(interval[0], newInterval[0]);
                    foundNewStart = true;
                    continue;
                }
            } else {
                if (!foundNewEnd) {
                    if (newInterval[1] < interval[1]) {
                        if (newInterval[1] < interval[0]) {
                            newCap[1] = newInterval[1];
                            result.add(newCap);
                            result.add(interval);
                        } else {
                            newCap[1] = interval[1];
                            result.add(newCap);
                        }
                        foundNewEnd = true;
                    }
                } else {
                    result.add(interval);
                }
            }
        }
        int[][] newIntervals = result.toArray(new int[0][]);
        return newIntervals;
    }

    @Test
    public void test_() {

        System.out.println(Arrays.deepToString(insert(new int[][]{{1, 5}}, new int[]{2, 3})));

        int[][] intervals1 = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval1 = new int[]{4, 8};
        System.out.println(Arrays.deepToString(insert(intervals1, newInterval1)));

        int[][] intervals = new int[][]{{1, 3}, {6, 9}};
        int[] newInterval = new int[]{2, 5};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

}
