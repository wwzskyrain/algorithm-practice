/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.shortestpath;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author yueyi
 * @version : ShortestPathToGetAllKeys.java, v 0.1 2022年08月04日 00:21 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ShortestPathToGetAllKeys {

    @LetCodeCommit(title = "864. Shortest Path to Get All Keys")
    public int shortestPathAllKeys(String[] grid) {
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length();
        int startX = -1, startY = -1;
        int keyNum = 0;
        for (int i = 0; i < grid.length; i++) {
            char[] chs = grid[i].toCharArray();
            for (int j = 0; j < chs.length; j++) {
                char c = chs[j];
                if (c == '@') {
                    startX = i;
                    startY = j;
                } else if (c >= 'a' && c <= 'z') {
                    keyNum++;
                }
            }
        }

        Queue<Point> q = new LinkedList<>();
        Point startP = new Point(startX, startY, 0);
        q.offer(startP);
        Set<String> visit = new HashSet<>();
        visit.add(startP.toString());
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Point curPoint = q.poll();
                int curKey = curPoint.key;
                if (curKey == ((1 << keyNum) - 1)) {
                    return level;
                }
                for (int[] dir : dirs) {
                    int x = curPoint.x + dir[0];
                    int y = curPoint.y + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x].charAt(y) != '#') {
                        char nextC = grid[x].charAt(y);
                        Point nextPoint;
                        if (isKey(nextC)) {
                            // 钥匙，更新当前curKey.
                            // 注意：point.key是一路收集下来的。但是不同路径则不同的key集合的.
                            int newKey = 1 << (nextC - 'a') | curKey;
                            nextPoint = new Point(x, y, newKey);
                        } else if (isLock(nextC)) {
                            int needKey = nextC - 'A';
                            if (((curKey >> needKey) & 1) == 1) {
                                // 等打开锁
                                nextPoint = new Point(x, y, curKey);
                            } else {
                                // 不能打开锁，就跳过
                                continue;
                            }
                        } else { // .
                            nextPoint = new Point(x, y, curKey);
                        }
                        if (visit.add(nextPoint.toString())) {
                            q.offer(nextPoint);
                        }
                    }
                }
                size--;
            }
            level++;
        }
        return -1;
    }

    public boolean isLock(char c) {
        return c >= 'A' && c <= 'Z';
    }

    public boolean isKey(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static class Point {
        int x;
        int y;
        int key;

        @Override
        public String toString() {
            return String.format("%d-%d-%d", x, y, key);
        }

        public Point(int x, int y, int key) {
            this.x = x;
            this.y = y;
            this.key = key;
        }
    }

    @Parameter
    public String[] grid;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"@.a..", "###.#", "b.A.B"}, 8},
                {new String[] {"@..aA", "..B#.", "....b"}, 6},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, shortestPathAllKeys(grid));
    }

}