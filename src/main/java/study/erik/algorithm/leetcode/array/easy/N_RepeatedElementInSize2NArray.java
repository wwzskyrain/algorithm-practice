/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.easy;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yueyi
 * @version : N_RepeatedElementInSize2NArray.java, v 0.1 2022年11月20日 10:50 yueyi Exp $
 */
public class N_RepeatedElementInSize2NArray {

    @LetCodeCommit(title = "961. N-Repeated Element in Size 2N Array")
    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(set.contains(num)){
                return num;
            }
            set.add(num);
        }
        return -1;
    }

}