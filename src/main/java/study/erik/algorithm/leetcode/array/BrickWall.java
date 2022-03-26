/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : BrickWall.java, v 0.1 2022年03月25日 5:22 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class BrickWall {

    @LetCodeCommit(title = "554. Brick Wall",
            diff = "m",
            selfRemark = "题意不错，不过解法挺直观的。")
    public int leastBricks(List<List<Integer>> wall) {

        Map<Integer, Integer> count = new HashMap<>();
        for (List<Integer> bricks : wall) {
            int preSum = 0;
            for (Integer brick : bricks) {
                preSum += brick;
                count.put(preSum, count.getOrDefault(preSum, 0) + 1);
            }
        }
        Integer wallLength = wall.get(0).stream().reduce(0, Integer::sum);
        count.remove(wallLength);
        Integer maxCount = count.values().stream().max(Integer::compare).orElse(0);
        return wall.size() - maxCount;
    }

    @Parameter
    public List<List<Integer>> wall;
    @Parameter(1)
    public int                 expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildList2Dimension("[[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]"), 2},
                {ArrayUtils.buildList2Dimension("[[1],[1],[1]]"), 3},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, leastBricks(wall));
    }
}