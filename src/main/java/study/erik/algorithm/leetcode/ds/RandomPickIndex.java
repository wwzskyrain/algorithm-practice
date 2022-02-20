/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author yueyi
 * @version : RandomPickIndex.java, v 0.1 2022年02月20日 4:24 下午 yueyi Exp $
 */
@LetCodeCommit(title = "398. Random Pick Index",
        selfRemark = "m题，done")
public class RandomPickIndex {

    private Map<Integer, List<Integer>> map    = new HashMap<>();
    private Random                      random = new Random(System.currentTimeMillis());

    public RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> indices = map.getOrDefault(num, new ArrayList<>());
            map.put(num, indices);
            indices.add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indices = map.get(target);
        return indices.get(random.nextInt(indices.size()));
    }

    public static void main(String[] args) {
        RandomPickIndex pick = new RandomPickIndex(ArrayUtils.buildArray("[1, 2, 3, 3, 3]"));
        System.out.println(pick.pick(3));
        System.out.println(pick.pick(1));
        System.out.println(pick.pick(3));
    }

}