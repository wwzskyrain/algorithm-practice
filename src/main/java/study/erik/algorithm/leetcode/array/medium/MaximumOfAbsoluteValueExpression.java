/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MaximumOfAbsoluteValueExpression.java, v 0.1 2023年03月11日 17:11 yueyi Exp $
 */
public class MaximumOfAbsoluteValueExpression {

    @LetCodeCommit(title = "1131. Maximum of Absolute Value Expression",
            selfRemark = "这个题其实是个数学题，可惜自从高二之后，我的数学的感觉就下来了。"
                    + "这个题目的关键是拆解绝对值，这个题能这样做的关键是三个绝对值的和，一单有一个减，这题就只能用brute force了。"
                    + "问题是，为什么可以有一个绝对值式子与拆解的四个式子有什么等价关系。"
                    + "关系就是四个式子中的最大值就是绝对值的值，为什么？因为题目的式子是三个绝对值之和，所以，拆解中任何一个绝对值是反向的，必然导致该式子不是最大值，因为出现了减，而最大值那个是原式子的。"
                    + "再说，四个式子的最大值在for循环维度的最大值，也是本题的解了。")
    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int max1, max2, max3, max4;
        max1 = max2 = max3 = max4 = Integer.MIN_VALUE;
        int min1, min2, min3, min4;
        min1 = min2 = min3 = min4 = Integer.MAX_VALUE;
        for (int i = 0; i < arr1.length; i++) {
            int v1 = arr1[i] + arr2[i] + i;
            max1 = Math.max(max1, v1);
            min1 = Math.min(min1, v1);

            int v2 = arr1[i] + arr2[i] - i;
            max2 = Math.max(max2, v2);
            min2 = Math.min(min2, v2);

            int v3 = arr1[i] - arr2[i] - i;
            max3 = Math.max(max3, v3);
            min3 = Math.min(min3, v3);

            int v4 = arr1[i] - arr2[i] + i;
            max4 = Math.max(max4, v4);
            min4 = Math.min(min4, v4);
        }
        int diff1 = max1 - min1;
        int diff2 = max2 - min2;
        int diff3 = max3 - min3;
        int diff4 = max4 - min4;

        return Math.max(Math.max(diff1, diff2), Math.max(diff3, diff4));
    }

}