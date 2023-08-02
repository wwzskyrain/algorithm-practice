package study.erik.algorithm.leetcode.hard.union;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/8/2 ，时间：16:09
 * 作者：yueyi
 * 描述：
 */
//@RunWith(Parameterized.class)
public class GCDSortOfAnArray {

    @LetCodeCommit(title = "1998. GCD Sort of an Array",
            diff = "h",
            selfRemark = "这个题目挺简单的，虽然我没有直接写出来。" +
                    "1.这个转化实在是有点低级——gcd暗含着分组" +
                    "2.O(logN)求num的因数数组。")
    public boolean gcdSort(int[] nums) {
        int maxValue = Arrays.stream(nums).max().getAsInt();
        sieve(maxValue + 1);
        UnionFind uf = new UnionFind(maxValue + 1);
        for (int x : nums)
            for (int f : getPrimeFactors(x))
                uf.Union(x, f);
        int[] sortedArr = new int[nums.length];
        System.arraycopy(nums, 0, sortedArr, 0, nums.length);
        Arrays.sort(sortedArr);
        for (int i = 0; i < nums.length; ++i)
            if (uf.find(nums[i]) != uf.find(sortedArr[i]))
                return false; // can't swap nums[i] with sortedArr[i]
        return true;
    }

    int[] spf;

    public void sieve(int n) { // O(Nlog(logN)) ~ O(N)
        spf = new int[n];
        for (int i = 2; i < n; ++i) spf[i] = i;
        for (int i = 2; i * i < n; i++) {
            if (spf[i] != i) continue; // skip if `i` is not a prime number
            for (int j = i * i; j < n; j += i)
                if (spf[j] > i) spf[j] = i; // update to the smallest prime factor of j
        }
    }

    List<Integer> getPrimeFactors(int n) { // O(logN)
        List<Integer> factors = new ArrayList<>();
        while (n > 1) {
            factors.add(spf[n]);
            n /= spf[n];
        }
        return factors;
    }

    class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]); // Path compression
        }

        void Union(int u, int v) {
            int pu = find(u), pv = find(v);
            if (pu != pv) parent[pu] = pv;
        }
    }


    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{
                {true, ArrayUtils.buildArray("[7,21,3]")},
                {false, ArrayUtils.buildArray("[5,2,6,2]")},
                {true, ArrayUtils.buildArray("[10,5,9,3,15]")},
        });
    }

    @Parameterized.Parameter
    public boolean expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
//        Assert.assertEquals(expect, gcdSort(nums));

        sieve(10);
        System.out.println(getPrimeFactors(6));
        System.out.println(getPrimeFactors(9));
    }

}
