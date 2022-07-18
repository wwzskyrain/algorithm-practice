package study.erik.algorithm.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(Parameterized.class)
public class RectangleAreaII {

    public int rectangleArea(int[][] rectangles) {
        int OPEN = 1, CLOSE = -1;
        int[][] events = new int[rectangles.length * 2][];
        Set<Integer> Xvals = new HashSet();
        int t = 0;

        for (int[] rec : rectangles) {
            if ((rec[0] < rec[2]) && (rec[1] < rec[3])) {
                events[t++] = new int[] {rec[1], OPEN, rec[0], rec[2]};
                events[t++] = new int[] {rec[3], CLOSE, rec[0], rec[2]};
                Xvals.add(rec[0]);
                Xvals.add(rec[2]);
            }
        }

        Arrays.sort(events, 0, t, (a, b) -> Integer.compare(a[0], b[0]));

        Integer[] X = Xvals.toArray(new Integer[0]);
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap();
        for (int i = 0; i < X.length; ++i) {Xi.put(X[i], i);}

        Node active = new Node(0, X.length - 1, X);
        long ans = 0;
        long cur_x_sum = 0;
        int cur_y = events[0][0];

        for (int[] event : events) {
            if (event == null) {break;}
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
            ans += cur_x_sum * (y - cur_y);
            cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
            cur_y = y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }

    public static class Node {
        int start, end;
        Integer[] X;
        Node      left, right;
        int  count;
        long total;

        //只生成当前节点
        public Node(int start, int end, Integer[] X) {
            this.start = start;
            this.end = end;
            this.X = X;
            left = null;
            right = null;
            count = 0;
            total = 0;
        }

        public int getRangeMid() {
            return start + (end - start) / 2;
        }

        public Node getLeft() {
            if (left == null) {left = new Node(start, getRangeMid(), X);}
            return left;
        }

        public Node getRight() {
            if (right == null) {right = new Node(getRangeMid(), end, X);}
            return right;
        }

        /**
         * 完成更新当前节点并计算区间和。
         */
        public long update(int i, int j, int val) {
            if (i >= j) {return 0;}
            if (start == i && end == j) {
                count += val;
            } else {
                // [i,j]不能完整的被线段树的当前线段节点匹配，所以就要去左右子线段中拼接完成.
                getLeft().update(i, Math.min(getRangeMid(), j), val);
                getRight().update(Math.max(getRangeMid(), i), j, val);
            }

            if (count > 0) {
                //表示确实有这段区间
                total = X[end] - X[start];
            } else {
                total = getLeft().total + getRight().total;
            }

            return total;
        }
    }

    public int solutionWithLineOperation(int[][] rectangles) {
        int OPEN = 0, CLOSE = 1;
        int[][] events = new int[rectangles.length * 2][];
        int t = 0;
        for (int[] rec : rectangles) {
            events[t++] = new int[] {rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[] {rec[3], CLOSE, rec[0], rec[2]};
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> active = new ArrayList();
        int cur_y = events[0][0];
        long ans = 0;
        for (int[] event : events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];

            // Calculate query
            long query = 0;
            int cur = -1;
            for (int[] xs : active) {
                cur = Math.max(cur, xs[0]);
                query += Math.max(xs[1] - cur, 0);
                cur = Math.max(cur, xs[1]);
            }

            ans += query * (y - cur_y);

            if (typ == OPEN) {
                active.add(new int[] {x1, x2});
                Collections.sort(active, (a, b) -> Integer.compare(a[0], b[0]));
            } else {
                for (int i = 0; i < active.size(); ++i) {
                    if (active.get(i)[0] == x1 && active.get(i)[1] == x2) {
                        active.remove(i);
                        break;
                    }
                }
            }

            cur_y = y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }

    @LetCodeCommit(title = "850. Rectangle Area II",
            diff = "h",
            selfRemark = "这是一个最朴素的方法，就是先打散然后在一块一块的拼出来")
    public int solutionWithBruteForce(int[][] rectangles) {

        Set<Integer> Xvals = new HashSet();
        Set<Integer> Yvals = new HashSet();
        // 1.所有的x坐标和y坐标给集合，消除重复的点
        for (int[] rec : rectangles) {
            Xvals.add(rec[0]);
            Xvals.add(rec[2]);
            Yvals.add(rec[1]);
            Yvals.add(rec[3]);
        }

        // 2.x坐标和y坐标排序
        Integer[] imapx = Xvals.toArray(new Integer[0]);
        Arrays.sort(imapx);
        Integer[] imapy = Yvals.toArray(new Integer[0]);
        Arrays.sort(imapy);

        // 3.x坐标和y坐标给索引化
        Map<Integer, Integer> mapx = new HashMap();
        Map<Integer, Integer> mapy = new HashMap();
        for (int i = 0; i < imapx.length; ++i) {mapx.put(imapx[i], i);}
        for (int i = 0; i < imapy.length; ++i) {mapy.put(imapy[i], i);}

        // 4.
        boolean[][] grid = new boolean[imapx.length][imapy.length];
        for (int[] rec : rectangles) {
            for (int x = mapx.get(rec[0]); x < mapx.get(rec[2]); ++x) {
                for (int y = mapy.get(rec[1]); y < mapy.get(rec[3]); ++y) {
                    grid[x][y] = true;
                }
            }
        }

        long ans = 0;
        for (int x = 0; x < grid.length; ++x) {
            for (int y = 0; y < grid[0].length; ++y) {
                if (grid[x][y]) {ans += (long) (imapx[x + 1] - imapx[x]) * (imapy[y + 1] - imapy[y]);}
            }
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }

    @Parameter
    public int[][] rectangles;
    @Parameter(1)
    public int     expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                //{ArrayUtils.buildArray2Dimension("[[0,0,2,2],[1,0,2,3],[1,0,3,1]]"), 6},
                {ArrayUtils.buildArray2Dimension("[[0,0,1,1],[3,0,4,1]]"), 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, rectangleArea(rectangles));
    }
}