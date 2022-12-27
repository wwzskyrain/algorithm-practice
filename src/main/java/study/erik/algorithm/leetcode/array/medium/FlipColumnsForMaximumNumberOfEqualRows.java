/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : FlipColumnsForMaximumNumberOfEqualRows.java, v 0.1 2022年12月27日 10:48 yueyi Exp $
 */
public class FlipColumnsForMaximumNumberOfEqualRows {

    @LetCodeCommit(title = "1072. Flip Columns For Maximum Number of Equal Rows",
            diff = "m",
            selfRemark = "题目有点绕，稍稍分析，也就是计算有多少对互补的行。"
                    + "当然，互补只能发生在两个值上，那这里的互补就是有重复值的的情况了。"
                    + "然后用map统计一下就行了")
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> map = new HashMap<>();
        int max = 0;
        for (int[] row : matrix) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int i : row) {
                sb1.append(i);
                sb2.append(1 - i);
            }
            int v = map.getOrDefault(sb1.toString(), 0) + 1;
            map.put(sb1.toString(), v);
            max = Math.max(max, v);
            v = map.getOrDefault(sb2.toString(), 0) + 1;
            map.put(sb2.toString(), v);
            max = Math.max(max, v);
        }
        return max;
    }
}