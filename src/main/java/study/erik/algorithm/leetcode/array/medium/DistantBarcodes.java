/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : DistantBarcodes.java, v 0.1 2022年12月18日 15:10 yueyi Exp $
 */
public class DistantBarcodes {

    @LetCodeCommit(title = "1054. Distant Barcodes",
            diff = "m",
            selfRemark = "安排数组，好像做个这个题目了")
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int barcode : barcodes) {
            map.put(barcode, map.getOrDefault(barcode, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Comparator.comparing(v -> map.get(v)).reversed());
        int i = 0;
        int[] res = new int[barcodes.length];
        for (Integer v : list) {
            Integer count = map.get(v);
            while (count > 0) {
                res[i] = v;
                i += 2;
                if (i >= barcodes.length) {
                    i = 1;
                }
                count--;
            }
        }
        return res;
    }
}