package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-12 19:21
 */
public class MergeIntervalsTest {

    @LetCodeCommit(no = 56, title = "Merge Intervals", time = 96, space = 71,
            selfRemark = "这个题好像之前做过，难道是太细节了，所以没有提交成功；这个题有很多related呢，看来比较容易出题",
            types = LetCodeCommit.Type.Detail,
            diff = "m",
            related = {"Insert Interval", "Teemo Attacking", "Range Module", "Partition Labels", "Interval List Intersections"})
    public int[][] merge(int[][] intervals) {

        if (intervals.length == 0) {
            return new int[][]{};
        }

        if (intervals.length == 1) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return ((o1[0] - o2[0]) != 0) ? (o1[0] - o2[0]) : (o1[1] - o2[1]);
            }
        });

        List<int[]> result = new LinkedList<>();

        //h表示前面的那个
        int h = 1;
        //l表示后面的那个
        int l = 0;
        int start = intervals[l][0];
        while (h < intervals.length) {

            if (intervals[l][1] >= intervals[h][1]) {
                // 当前l段完全包括了h段
                h++;
                continue;
            }
            if (intervals[l][1] < intervals[h][0]) {
                // 当前l段和h段完全没有重合
                result.add(new int[]{start, intervals[l][1]});
                l = h;
                start = intervals[l][0];
                h++;
                continue;
            }
            //那就是交叉了，
            //l要复制h，不要l++
            l = h;
            h++;
        }
        result.add(new int[]{start, intervals[l][1]});

        return result.toArray(new int[][]{});
    }

    @Test
    public void test_case_0() {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
        int[][] expect = new int[][]{{1, 6}, {8, 10}, {15, 18}};
        Assert.assertTrue(Arrays.deepEquals(expect, merge(intervals)));
    }

    @Test
    public void test_case_1() {
        int[][] intervals = new int[][]{{1, 4}, {4, 5}};
        System.out.println(Arrays.deepToString(merge(intervals)));
        int[][] expect = new int[][]{{1, 5}};
        Assert.assertTrue(Arrays.deepEquals(expect, merge(intervals)));
    }


    @Test
    public void test_case_2() {
        int[][] intervals = new int[][]{{1, 4}, {0, 4}};
        System.out.println(Arrays.deepToString(merge(intervals)));

    }


    @Test
    public void test_case_3() {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }


    @Test
    public void test_case_4() {
        int[][] intervals = new int[][]{{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

    @Test
    public void test_case_5() {
        int[][] intervals = new int[][]{{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
        System.out.println(Arrays.deepToString(merge(intervals)));
        int[][] expect = new int[][]{{1, 3}, {4, 7}};
        Assert.assertTrue(Arrays.deepEquals(expect, merge(intervals)));
    }


    @Test
    public void test_case_6() {
        int[][] intervals = new int[][]{{4, 5}, {2, 4}, {4, 6}, {3, 4}, {0, 0}, {1, 1}, {3, 5}, {2, 2}};
        System.out.println(Arrays.deepToString(merge(intervals)));
        int[][] expect = new int[][]{{0, 0}, {1, 1}, {2, 6}};
        Assert.assertTrue(Arrays.deepEquals(expect, merge(intervals)));
    }


}
