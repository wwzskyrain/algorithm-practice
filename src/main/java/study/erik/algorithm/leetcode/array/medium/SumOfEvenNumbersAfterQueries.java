/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : SumOfEvenNumbersAfterQueries.java, v 0.1 2022年11月28日 09:31 yueyi Exp $
 */
public class SumOfEvenNumbersAfterQueries {

    @LetCodeCommit(title = "985. Sum of Even Numbers After Queries")
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int oriSum = 0;
        for (int num : nums) {
            oriSum += num % 2 == 0 ? num : 0;
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int val = query[0];
            int index = query[1];
            if (nums[index] % 2 == 0) {
                oriSum-=nums[index];
            }
            nums[index] += val;
            if(nums[index] % 2== 0){
                oriSum += nums[index];
            }
            res[i] = oriSum;
        }
        return res;
    }

}