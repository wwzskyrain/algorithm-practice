package study.erik.algorithm.leetcode.array.medium;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

@RunWith(Parameterized.class)
public class MaximumBeauty {

    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        for (int i = 1; i < items.length; i++) {
            items[i][1] = Math.max(items[i][1], items[i - 1][1]);
        }
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = binarySearch(items, queries[i]);
        }
        return result;
    }

    public int binarySearch(int[][] items, int q) {
        int l = 0, r = items.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (items[mid][0] > q) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l == 0) {
            // 此时所有物品价格均大于查询价格
            return 0;
        } else {
            // 返回小于等于查询价格的物品的最大美丽值
            return items[l - 1][1];
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {16, new String[]{"abcw", "baz", "foo", "bar", "xtfn", "abcdef"}},
                {4, new String[]{"a", "ab", "abc", "d", "cd", "bcd", "abcd"}},
                {0, new String[]{"a", "aa", "aaa", "aaaa"}},
        });
    }

    @Parameterized.Parameter
    public int[][] items;
    @Parameterized.Parameter(1)
    public int[] queries;


    @Test
    public void test() {
//        Assert.assertEquals(expect, maximumBeauty(words));
    }

}
