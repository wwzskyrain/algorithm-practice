/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.ds.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yueyi
 * @version : FindKthLeast.java, v 0.1 2022年11月25日 00:35 yueyi Exp $
 */
public class FindKLeastSort {

    public static class FindKLeast {
        private int   n;
        private int[] data;

        public FindKLeast(int n) {
            this.n = n + 1;
            data = new int[this.n];
            Arrays.fill(data, Integer.MAX_VALUE);
        }

        private void adjust(int d) {
            // 大顶堆
            if (data[1] <= d) {
                return;
            }
            int i = 1;
            for (; i < n; ) {
                int l = i * 2;
                int lV = l < n ? data[l] : 0;
                int r = l + 1;
                int rV = r < n ? data[r] : 0;
                if (d > lV && d > rV) {
                    // 左右两个都不够大，那d来做i的位置
                    break;
                }
                // 这左右两个较大的来填补i位置
                int maxIndex = lV > rV ? l : r;
                data[i] = data[maxIndex];
                i = maxIndex;
            }
            data[i] = d;
        }

        public int[] getData() {
            return this.data;
        }
    }

    @Test
    public void batchTest() {
        Random random = new Random(47);
        int errorCase = 0;
        int totalCase = 1000;
        for (int i = 0; i < totalCase; i++) {
            int max = 200;
            List<Integer> list = IntStream.range(1, max).boxed().collect(Collectors.toList());
            Collections.shuffle(list);
            List<Integer> shuffleList = new ArrayList<>(list);
            int k = random.nextInt(list.size() - 1);
            int[] sortedList = test(shuffleList, k);
            System.out.println("k = " + k);
            list.sort(Integer::compare);
            list.subList(0, k);
            for (int j = 1; j < sortedList.length; j++) {
                if (list.contains(sortedList[j])) {
                    continue;
                }
                // 打印
                System.out.println("shuffleList = " + shuffleList);
                System.out.println(Arrays.toString(sortedList));
                errorCase++;
                break;
            }
        }
        System.out.printf("totalCase = %d, errorCase = %d \n", totalCase, errorCase);

    }

    public static int[] test(List<Integer> data, int k) {
        FindKLeast findKLeast = new FindKLeast(k);
        for (Integer d : data) {
            findKLeast.adjust(d);
        }
        return findKLeast.getData();
    }

}