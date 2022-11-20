/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : PowerfulIntegers.java, v 0.1 2022年11月20日 19:41 yueyi Exp $
 */
public class PowerfulIntegers {

    @LetCodeCommit(title = "970. Powerful Integers")
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> res = new HashSet<>();
        for (int i = 1; i < bound; i = i * x) {
            for (int j = 1; j + i <= bound; j = j * y) {
                int n = i + j;
                if (n <= bound) {
                    res.add(n);
                }
                if (y == 1) {
                    break;
                }
            }
            if (x == 1) {
                break;
            }
        }
        return new ArrayList<>(res);
    }
}