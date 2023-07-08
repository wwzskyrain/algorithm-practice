/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.hard.query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author yueyi
 * @version : ClosestRoom.java, v 0.1 2023年07月08日 09:40 yueyi Exp $
 */
@RunWith(Parameterized.class)
@LetCodeCommit(title = "1847. Closest Room",
        diff = "h",
        selfRemark = "这个题目不应该是hard，因为这是一个静态查询。"
                + "静态查询一般可以结合静态查询的先后顺序来组织数据。"
                + "这样的题目我见到好几次了。")
public class ClosestRoom {

    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int m = rooms.length;
        int n = queries.length;
        int[] res = new int[n];
        Integer[] indexes = new Integer[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = i;
        }
        //
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        Arrays.sort(indexes, (a, b) -> queries[b][1] - queries[a][1]);
        TreeSet<Integer> ids = new TreeSet<>();
        int j = 0;
        for (int index : indexes) {
            int minSize = queries[index][1];
            while (j < m && rooms[j][1] >= minSize) {
                ids.add(rooms[j][0]);
                j++;
            }
            int targetRoomId = queries[index][0];
            Integer idFloor = ids.floor(targetRoomId);
            Integer idCeiling = ids.ceiling(targetRoomId);
            int ans;
            if (idFloor != null && idCeiling != null) {
                int abs1 = Math.abs(idFloor - targetRoomId);
                int abs2 = Math.abs(idCeiling - targetRoomId);
                ans = abs1 > abs2 ? idCeiling : idFloor;
            } else if (idFloor != null) {
                ans = idFloor;
            } else if (idCeiling != null) {
                ans = idCeiling;
            } else {
                ans = -1;
            }
            res[index] = ans;
        }
        return res;
    }

    @Parameter
    public int[][] rooms;
    @Parameter(1)
    public int[][] queries;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray2Dimension("[[2,2],[1,2],[3,2]]"),
                        ArrayUtils.buildArray2Dimension("[[3,1],[3,3],[5,2]]")},
                {ArrayUtils.buildArray2Dimension("[[1,4],[2,3],[3,5],[4,1],[5,2]]"),
                        ArrayUtils.buildArray2Dimension("[[2,3],[2,4],[2,5]]")},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(closestRoom(rooms, queries)));
    }

}