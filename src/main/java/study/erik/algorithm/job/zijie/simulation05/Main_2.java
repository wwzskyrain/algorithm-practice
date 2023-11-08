package study.erik.algorithm.job.zijie.simulation05;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 11:31
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public int splitArray(int[] nums) {
        return splitDfs(nums, 0, nums.length - 1);
    }

    public int splitDfs(int[] nums, int i, int j) {
        if (i > j) {
            return 0;
        }
        int head = nums[i];
        int ret = Integer.MAX_VALUE;
        for (int k = j; k >= i; k--) {
            int kNum = nums[k];
            if (gcd(head, kNum) > 1) {
                return 1 + splitDfs(nums, k + 1, j);
            }
        }
        return ret;
    }

    public int gcd(int a, int b) {
        if (a < b) {
            return gcd(b, a);
        }
        while (b > 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {99, ArrayUtils.buildArray("[197597,26083,231529,216133,22483,74411,89087,218681,863,228421,214463,224863,5737,32941,216103,132689,159737,151241,164309,73643,45121,59981,68821,11197,54679,85213,138727,89657,102769,112121,136573,27059,77351,109891,94229,173617,224443,149531,84979,31013,219409,156749,108233,80107,90173,138899,151057,66683,66683,153911,69959,79451,75407,159319,7411,78571,128717,52057,55799,128201,125353,214763,12071,152657,81199,190391,96779,62659,27997,318559,299113,258691,258031,296713,297533,341477,273271,270659,296479,262693,270287,247769,246781,308509,289031,298559,246439,318713,317773,260879,322237,245851,276623,319237,352589,283463,235111,393203,917327,495371]")},
                {2, ArrayUtils.buildArray("[2,3,3,2,3,3]")},
                {4, ArrayUtils.buildArray("[2,3,5,7]")}
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] nums;

    @Test
    public void test() {
        Assert.assertEquals(expect, splitArray(nums));
    }

}
