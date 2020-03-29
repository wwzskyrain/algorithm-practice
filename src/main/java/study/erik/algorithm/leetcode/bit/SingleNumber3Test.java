package study.erik.algorithm.leetcode.bit;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author erik.wang
 * @date 2020-03-23 09:13
 * @description
 */
public class SingleNumber3Test {

    /**
     * title = Single Number 3
     * url = https://leetcode.com/problems/single-number-iii/
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        return solution1(nums);
    }

    /**
     * 成绩：99% 和  6%<p>
     * 思路：基于第一题的思路，用异或把哪些成对出现的过滤调——让成对出现的归零
     * 不过这里相对于第一题，还需要一个trick的。
     * 必须先把这一题分界成第一题之后才能求解。
     * <p><p>
     * 分解思路：假设要找的那两个数字是a和b。
     * 那么只要把a和b分开，比如分别归到两组；
     * 同时把那些成对的也双双归于其中一组。
     * 这样问题就分界为第一题了。
     * <p><p>
     * 如何分成两个组：ab比有一位是不同的，即该bit上，a是0而b是1，或者反过来。
     * 以此为基础，把所有的数据分成两组。这时，那些成对出现的数据，由于在该bit上
     * 肯定同0或同1，也就会双双携手归一边了。
     * 好了，可以用第一题的解法了。具体代码如下：
     * <p>
     * 扩展：再谈"让成对出现的归零"
     * 如何让'三人行出现的归零呢'？这一问就是该系列的第二题。
     * 在第二题中，用了位运算来做了'对三求模'运算。
     *
     * @param nums
     * @return
     */
    public int[] solution1(int[] nums) {
        int allXor = 0;
        for (int num : nums) {
            allXor ^= num;
        }
        int probe = 1;
        while ((allXor & probe) == 0) {
            probe = probe << 1;
        }
        int foundA = 0;
        int foundB = 0;
        for (int num : nums) {
            if ((num & probe) == 0) {
                foundA ^= num;
            } else {
                foundB ^= num;
            }
        }
        return new int[]{foundA, foundB};
    }

    @Test
    public void test_solution() {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }
}
