package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

@LetCodeCommit(title = "352. Data Stream as Disjoint Intervals")
class SummaryRanges {

    private TreeSet<MyInterval> treeSet;

    public static class MyInterval {
        int min;
        int max;

        public MyInterval(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

    public SummaryRanges() {
        //用max来标识set中元素的唯一性和大小
        treeSet = new TreeSet<>(Comparator.comparingInt(MyInterval::getMax));
    }

    //真是手把手写呀
    public void addNum(int val) {
        MyInterval newInterval = new MyInterval(val, val);
        if (treeSet.contains(newInterval)) {
            return;
        }
        MyInterval higher = treeSet.higher(newInterval);

        if (higher != null) {
            //直接包圆，把这个case都包掉
            if (val < higher.min - 1) {
                MyInterval lower = treeSet.lower(newInterval);
                if (lower == null) { //全case
                    treeSet.add(newInterval);
                } else if (lower.max + 1 == val) {
                    lower.max++;
                } else {
                    treeSet.add(newInterval);
                }
            } else if (val == higher.min - 1) {
                higher.min--;
                MyInterval lower = treeSet.lower(newInterval);
                if (lower != null && lower.max + 1 == val) {
                    higher.min = lower.min;
                    treeSet.remove(lower);
                }
            }
        } else {
            MyInterval floor = treeSet.lower(newInterval);
            if (floor == null) {
                treeSet.add(newInterval);
            } else if (val == floor.max + 1) {
                floor.max++;
            } else {
                treeSet.add(newInterval);
            }
        }
    }

    public int[][] getIntervals() {
        int[][] allIntervals = new int[treeSet.size()][2];
        int i = 0;
        for (MyInterval myInterval : treeSet) {
            allIntervals[i++] = new int[] {myInterval.min, myInterval.max};
        }
        return allIntervals;
    }

    public static void main(String[] args) {
        test_1();
        test_2();
    }

    public static void test_1() {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);      // arr = [1]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        // return [[1, 1]]
        summaryRanges.addNum(3);      // arr = [1, 3]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        // return [[1, 1], [3, 3]]
        summaryRanges.addNum(7);      // arr = [1, 3, 7]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        // return [[1, 1], [3, 3], [7, 7]]
        summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        // return [[1, 3], [7, 7]]
        summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        // return [[1, 3], [6, 7]]
    }

    public static void test_2() {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        //
        summaryRanges.addNum(9);
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
        summaryRanges.addNum(2);
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));

        summaryRanges.addNum(8);
        System.out.println(Arrays.deepToString(summaryRanges.getIntervals()));
    }

}