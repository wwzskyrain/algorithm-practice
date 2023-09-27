package study.erik.algorithm.leetcode.advance.BIT;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/9/27 ，时间：10:09
 * 作者：yueyi
 * 描述：这里主要介绍树状数组的解法。
 * 该题目有四种解法：
 * 1. 从后遍历+插入法
 * 2. 归并排序法
 * 3. 树状数组（本类重点）
 * 4. 线段树
 */
@RunWith(Parameterized.class)
public class Count_of_Smaller_Numbers_After_Self {

    @LetCodeCommit(title = "315. Count of Smaller Numbers After Self")
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        if (len == 0) {
            return res;
        }

        // 使用二分搜索树方便排序
        Set<Integer> set = new TreeSet();
        for (int i = 0; i < len; i++) {
            set.add(nums[i]);
        }

        // 排名表：把之前的数据划分到1-n的空间。
        // 细节点：
        // 1.重复元素的排名相同；
        // 2.排名从1开始，因为方便后面bit的计算。
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (Integer num : set) {
            map.put(num, rank);
            rank++;
        }

        //本题目运用了bit的前缀和的功能。
        FenwickTree fenwickTree = new FenwickTree(set.size() + 1);
        // 从后向前填表
        for (int i = len - 1; i >= 0; i--) {
            // 1、查询排名
            rank = map.get(nums[i]);
            // 2、在树状数组排名的那个位置 + 1
            fenwickTree.update(rank, 1);
            // 3、查询一下小于等于“当前排名 - 1”的元素有多少
            res.add(fenwickTree.query(rank - 1));
        }
        Collections.reverse(res);
        return res;
    }

    private static class FenwickTree {
        private int[] tree;
        private int len;

        public FenwickTree(int n) {
            this.len = n;
            tree = new int[n + 1];
        }

        // 单点更新：将 index 这个位置 + 1
        public void update(int i, int delta) {
            // 从下到上，最多到 size，可以等于 size
            while (i <= this.len) {
                tree[i] += delta;
                i += lowbit(i);
            }
        }


        // 区间查询：查询小于等于 index 的元素个数
        // 查询的语义是"前缀和"
        public int query(int i) {
            // 从右到左查询
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }

        public int lowbit(int x) {
            return x & (-x);
        }
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {ArrayUtils.buildArray("[2,1,1,0]"), ArrayUtils.buildArray("[5,2,6,1]")},
                {ArrayUtils.buildArray("[0]"), ArrayUtils.buildArray("[-1]")},
                {ArrayUtils.buildArray("[0,0]"), ArrayUtils.buildArray("[-1,-1]")},
                });
    }

    @Parameterized.Parameter
    public int[] expect;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        Assert.assertArrayEquals(expect, ArrayUtils.convertListTo(countSmaller(nums)));
    }

}
