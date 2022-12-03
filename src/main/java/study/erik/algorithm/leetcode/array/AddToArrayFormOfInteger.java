/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : AddToArrayFormOfInteger.java, v 0.1 2022年12月03日 13:45 yueyi Exp $
 */
public class AddToArrayFormOfInteger {

    @LetCodeCommit(title = "989. Add to Array-Form of Integer")
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> nList = Arrays.stream(num).boxed().collect(Collectors.toList());
        List<Integer> kList = new ArrayList<>();
        while (k > 0) {
            kList.add(0, k % 10);
            k /= 10;
        }
        int i = nList.size() - 1;
        int j = kList.size() - 1;
        List<Integer> res = new ArrayList<>();
        int flag = 0;
        int n = 0;
        while (i >= 0 && j >= 0) {
            n = nList.get(i--) + kList.get(j--) + flag;
            flag = n / 10;
            res.add(0, n % 10);
        }
        while (i >= 0) {
            n = nList.get(i--) + flag;
            flag = n / 10;
            res.add(0, n % 10);
        }
        while (j >= 0) {
            n = kList.get(j--) + flag;
            flag = n / 10;
            res.add(0, n % 10);
        }
        if (flag == 1) {
            res.add(0, 1);
        }
        return res;

    }

}