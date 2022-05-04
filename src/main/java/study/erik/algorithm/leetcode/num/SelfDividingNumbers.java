/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.num;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yueyi
 * @version : SelfDividingNumbers.java, v 0.1 2022年04月28日 22:17 yueyi Exp $
 */
public class SelfDividingNumbers {

    @LetCodeCommit(title = "728. Self Dividing Numbers")
    public List<Integer> selfDividingNumbers(int left, int right) {
        return IntStream.range(left, right + 1)
                .filter(this::divisible)
                .boxed()
                .collect(Collectors.toList());
    }

    private boolean divisible(int n){
        int t = n;
        while (n > 0){
            int d = n % 10;
            if(d == 0 || t % d != 0){
                return false;
            }
            n /= 10;
        }
        return true;
    }

}