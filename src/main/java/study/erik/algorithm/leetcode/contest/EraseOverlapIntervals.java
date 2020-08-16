package study.erik.algorithm.leetcode.contest;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author erik.wang
 * @date 2020-08-15 23:37
 */
public class EraseOverlapIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {

        if (intervals.length <= 1) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        int r1 = o1[0] - o2[0];
                        int r2 = o1[1] - o2[1];
                        return r2 != 0 ? r2 : r1;
                    }
                }
        );

        int count = 0;
        int index = 0;
        int probe = 1;

        while (probe < intervals.length) {
            if (intervals[probe][0] < intervals[index][1] && intervals[probe][1] >= intervals[index][1]) {
                probe++;
                count++;
            } else {
                index = probe;
                probe++;
            }
        }
        return count;
    }


    @Test
    public void test_solution_1() {
        Assert.assertEquals(1, eraseOverlapIntervals(build("[[1,2],[2,3],[3,4],[1,3]]")));
    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals(2, eraseOverlapIntervals(build("[[1,2],[1,2],[1,2]]")));
    }

    @Test
    public void test_solution_3() {
        Assert.assertEquals(0, eraseOverlapIntervals(build("[[1,2],[2,3]]")));
    }


    public int[][] build(String input) {
        input = input.replace("[", "");
        input = input.replace("]", "");

        String[] split = input.split(",");
        int[][] result = new int[split.length / 2][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = Integer.valueOf(split[i * 2]);
            result[i][1] = Integer.valueOf(split[i * 2 + 1]);
        }
        return result;
    }
}
