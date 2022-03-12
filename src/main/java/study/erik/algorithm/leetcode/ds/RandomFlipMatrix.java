/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author yueyi
 * @version : RandomFlipMatrix.java, v 0.1 2022年03月11日 7:28 上午 yueyi Exp $
 */
@LetCodeCommit(title = "519. Random Flip Matrix",
        diff = "m",
        selfRemark = "真是一个好题目。"
                + "首先这个交换法，很多人都能想到，包括我。"
                + "但是实现的时候，有就可能超时或者超空间了。"
                + "比如我的List法和二位数组法。前者超空间，后者超时。"
                + "这里用了map，而且反过来用，把产生交换的数据记到map中，很好，节省了内存也提高了查询效率——map的key少了自然就查的快了。"
                + "这里有个小坑，已在代码中注释。")
public class RandomFlipMatrix {

    private Map<Integer, Integer> map;
    private Random                random = new Random(2);
    private int                   m;
    private int                   n;
    private int                   total;

    public RandomFlipMatrix(int m, int n) {
        this.m = m;
        this.n = n;
        map = new HashMap<>();
        reset();
    }

    public int[] flip() {
        int index = random.nextInt(total--);
        Integer realIndex = map.get(index);
        if (realIndex == null) {
            // 还没被替换过
            realIndex = index;
        }
        // 小坑：value必须是`map.getOrDefault(total, total)`，而不能直接是total.
        // 比如，total=4,index=2,out 2, in total-1=3.
        // then, total=3,index=1,out 1, in total-1=2. see, 2 in again,2 always will out again.
        // so this time, instead of 2, 3 should in. because 3 = map.get(total=2)

        map.put(index, map.getOrDefault(total, total));
        // 这里是更妙的呢。这里就实现了交换。这样每次reset就不需要clear掉map了。
        map.put(total, realIndex);

        return new int[] {realIndex / this.n, realIndex % this.n};
    }

    public void reset() {
        total = this.m * this.n;
    }

    public static void main(String[] args) {
        RandomFlipMatrix randomFlipMatrix = new RandomFlipMatrix(3, 1);
        System.out.println(Arrays.toString(randomFlipMatrix.flip()));
        System.out.println(Arrays.toString(randomFlipMatrix.flip()));
        System.out.println(Arrays.toString(randomFlipMatrix.flip()));
        randomFlipMatrix.reset();
        System.out.println(Arrays.toString(randomFlipMatrix.flip()));
    }
}