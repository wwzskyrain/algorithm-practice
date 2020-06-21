package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-06-20 13:11
 * title = Subarrays with K Different Integers
 */
public class SubarraysWithKDifferentIntegers {


    @Test
    public void test_() {

        int[] A1 = {1, 2, 1, 3, 4};
        Assert.assertEquals(3, subarraysWithKDistinct(A1, 3));

        int[] A = {1, 2, 1, 2, 3};
        Assert.assertEquals(7, subarraysWithKDistinct(A, 2));

    }

    /**
     * 这是一个神奇的解法，如果按照最基本的解法，可以两层遍历；但肯定超时，这是一个hard题目
     * 这是一位中国大神的解法：leetcode上中国大神特别多。链接：https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/523136/JavaC%2B%2BPython-Sliding-Window
     * 这里整体思路是转换成atMostKDistinct，即，exist(K) = atMost(K) - atMost(K-1)
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * 求A中，distinct元素最多K个的所有子串
     * 复杂度：这是一个二维循环，正确说是一个二次遍历，一个是j一个是i；
     *
     * @param A
     * @param K
     * @return
     */
    private int atMostKDistinct(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;
        int total = 0;
        for (int j = 0; j < A.length; j++) {
            Integer c = map.getOrDefault(A[j], 0);
            if (c == 0) {
                K--;
            }
            map.put(A[j], c + 1);

            while (K < 0) {
                c = map.get(A[i]);
                if (c == 1) {
                    K++;
                }
                map.put(A[i++], c - 1);
            }
            //累加 j-i+1，这个特别的别扭，但也是最精彩之处。也这是套路所在
            total += j - i + 1;
        }
        return total;
    }

}
