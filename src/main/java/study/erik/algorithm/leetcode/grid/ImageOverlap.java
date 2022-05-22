/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import javafx.util.Pair;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : ImageOverlap.java, v 0.1 2022年05月21日 10:40 yueyi Exp $
 */
public class ImageOverlap {

    @LetCodeCommit(title = "835. Image Overlap",
            diff = "m",
            selfRemark = "一个很不错的应用题。这里采用了线性矢量方法")
    public int largestOverlap(int[][] img1, int[][] img2) {
        List<Pair<Integer, Integer>> pairs1 = allOne(img1);
        List<Pair<Integer, Integer>> pairs2 = allOne(img2);

        Map<Pair<Integer, Integer>, Integer> map = new HashMap();
        int max = 0;
        for (Pair<Integer, Integer> pair1 : pairs1) {
            for (Pair<Integer, Integer> pair2 : pairs2) {
                Pair<Integer, Integer> vector =
                        new Pair<>(pair1.getKey() - pair2.getKey(), pair1.getValue() - pair2.getValue());
                Integer c = map.getOrDefault(vector, 0);
                c++;
                max = Math.max(max, c);
                map.put(vector, c);
            }
        }
        return max;
    }

    public List<Pair<Integer, Integer>> allOne(int[][] img) {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                if (img[i][j] == 1) {
                    pairs.add(new Pair<>(i, j));
                }
            }
        }
        return pairs;
    }
}