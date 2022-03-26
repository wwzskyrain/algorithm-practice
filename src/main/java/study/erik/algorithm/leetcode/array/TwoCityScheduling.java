/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author yueyi
 * @version : TwoCityScheduling.java, v 0.1 2022年03月25日 4:50 下午 yueyi Exp $
 */
public class TwoCityScheduling {

    @LetCodeCommit(title = "1029. Two City Scheduling",
            diff = "m",
            selfRemark = "题设有点牵强，不过从解法看，还是不错的。"
                    + "1.贪心算法，也就是直接求解。需要有很高的理解能力"
                    + "2.dp算法，直接求解空间。这个思路不能忘了。"
                    + "之所以这里只写了贪心算法，是因为可以把它写成stream形式。"
                    + "两句话，太简洁了。而且还能学到IntStream的使用已经limit的使用.")
    public int twoCitySchedCost(int[][] costs) {

        Integer totalCostToCityA = Arrays.stream(costs).map(c -> c[0])
                .reduce(0, Integer::sum);
        Integer discountCost = Arrays.stream(costs).map(c -> c[1] - c[0])
                .sorted()
                .limit(costs.length / 2)
                .reduce(0, Integer::sum);

        return totalCostToCityA + discountCost;
    }
}