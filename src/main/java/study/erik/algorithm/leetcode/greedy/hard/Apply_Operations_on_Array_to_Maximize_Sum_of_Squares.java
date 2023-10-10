package study.erik.algorithm.leetcode.greedy.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 日期：2023/10/9 ，时间：09:51
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Apply_Operations_on_Array_to_Maximize_Sum_of_Squares {

    @LetCodeCommit(title = "2897. Apply Operations on Array to Maximize Sum of Squares",
            selfRemark = "求最大值，而操作可以无限次，一看就不能用经典的解法，贪心吧。" +
                    "贪心策略见注释。")
    public int maxSum(List<Integer> nums, int k) {
        //牵涉到大数求余的，不要客气直接上long.
        long MOD = (long) 1e9 + 7;
        long ret = 0L;
        int[] count = new int[32];
        //先统计一下所有的位数。
        for (Integer num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) > 0) {
                    count[i]++;
                }
            }
        }
        //然后从新分配这些位数到k个数字中，分配原则就是从高位到低位，有位就分。
        while (k-- > 0) {
            long x = 0;
            for (int i = 31; i >= 0; i--) {
                if (count[i] > 0) {
                    x = (x + (1 << i)) % MOD;
                    count[i]--;
                }
            }
            x = (x * x) % MOD;
            ret = (ret + x) % MOD;
        }
        return (int) ret;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {261, ArrayUtils.buildList("[2,6,5,8]"), 2},
                {90, ArrayUtils.buildList("[4,5,4,7]"), 3},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public List<Integer> nums;
    @Parameterized.Parameter(2)
    public int k;

    @Test
    public void test() {
        Assert.assertEquals(expect, maxSum(nums, k));
    }

}
