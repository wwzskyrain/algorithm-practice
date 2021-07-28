/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree.segment;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.List;

/**
 * @author yueyi
 * @version : CountOfSmallerNumbersAfterSelf.java, v 0.1 2021年07月27日 8:17 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CountOfSmallerNumbersAfterSelf {

    @LetCodeCommit(title = "Count of Smaller Numbers After Self")
    public List<Integer> countSmaller(int[] nums) {
        return resolve(nums);
    }

    public List<Integer> resolve(int[] nums) {
        return null;
    }
}