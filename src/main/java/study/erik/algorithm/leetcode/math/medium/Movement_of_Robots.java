package study.erik.algorithm.leetcode.math.medium;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/10/10 ，时间：11:34
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Movement_of_Robots {

    @LetCodeCommit(title = "2731. Movement of Robots",
            selfRemark = "这个题目描述起来有点多，但是不复杂。" +
                    "但是直接根据题意去思考，就复杂了。必须做好转化。" +
                    "1.碰撞前后，两个交换，其实可以看做穿透，因为所有的指点都一样的。" +
                    "2.两两距离和的计算，计算a[i]与它前面的所有节点的距离和。然后总体求和就好了。" +
                    "3.注意取模的粒度")
    public int sumDistance(int[] nums, String s, int d) {
        long MOD = (long) 1e9 + 7;
        int n = nums.length;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            //直接当做穿透，所以d秒之后，大家就是直接按照初始方向走了d个单位。
            a[i] = nums[i] + (s.charAt(i) == 'R' ? d : -d);
        }
        //排序不影响最后的和计算。
        Arrays.sort(a);
        long ans = 0;
        long sum = a[0];
        for (int i = 1; i < n; i++) {
            //注意呀，不要无脑的取模呀，比如
            // (a[i] * i)%MOD - sum, 这就可能导致问题呀，比如a[i] * i = MOD，这个时候结果就不对啦。
            // 所以他们是一个整体。(a[i] * i - sum)。
            ans = (ans + (a[i] * i - sum) % MOD) % MOD; //放心啦，这里可以优化的，可以最后在取模的，因为long范围任你+也不会溢出啊。
            sum += a[i];
        }
        return (int) ans;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1000000006, ArrayUtils.buildArray("[1,1000000007]"), "RR", 0},
                {8, ArrayUtils.buildArray("[-2,0,2]"), "RLL", 3},
                {5, ArrayUtils.buildArray("[1,0]"), "RL", 2},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;
    @Parameterized.Parameter(2)
    public String s;
    @Parameterized.Parameter(3)
    public int d;


    @Test
    public void test() {
        Assert.assertEquals(expect, sumDistance(nums, s, d));
    }

}
