/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : RabbitsInForest.java, v 0.1 2022年05月06日 07:46 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RabbitsInForest {

    @LetCodeCommit(title = "781. Rabbits in Forest",
            selfRemark = "这个题目比较有意思，有点像小学时的应用题.")
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }
        int c = 0;
        for (Integer answer : map.keySet()) {
            Integer v = map.get(answer);
            c += (v + answer) / (answer + 1) * (answer + 1);
        }
        return c;
    }
}