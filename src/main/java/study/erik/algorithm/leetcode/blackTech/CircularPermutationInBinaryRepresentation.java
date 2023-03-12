/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.blackTech;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : CircularPermutationInBinaryRepresentation.java, v 0.1 2023年03月12日 15:13 yueyi Exp $
 */
public class CircularPermutationInBinaryRepresentation {

    @LetCodeCommit(title = "1238. Circular Permutation in Binary Representation")
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        // 在格雷码的基础上，都对start做一个抑或，不改变该序列的diff为一位的特性
        for (int i = 0; i < 1 << n; ++i) {res.add(start ^ i ^ i >> 1);}
        return res;
    }

}