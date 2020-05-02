package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-01 20:45
 */
public class NonnegativeIntegerswithoutConsecutiveOnes {

    @Test
    public void test_solution() {
        Assert.assertEquals(6, findIntegers(8));
        Assert.assertEquals(5, findIntegers(5));
    }

    /**
     * 成绩：97 and 16 为什么空间占用那么多呢？
     * 做题历程：首先我独立想出了，m位中会有多少个符合题设的数字。而且是一维dp实现。
     * 但是，如何减去那些大于当前n的数字呢，我一时没有弄明白。
     * 在diss区看到一个这个剔除方法：https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/discuss/103749/Java-Solution-DP
     * 这个算法我还是没有完全明白，不过可以举几个例子
     * 比如以11开头的'11xxxx'，则我们的result=zone[L - 1] + one[L - 1] 中刚好没有大于这个值的，所以直接返回result。
     * 如果是101010这样的数字，记做num，则这个数字本身就是我们result中的最大值，我们result中的数字都小于或者等于num，这时也返回result就可以了。
     * 但是如果是100开头的数字，即第二个0的下标为index，index从右往左算，则我们的result中，就多算了one[index]的情况
     * 同理，我们就多计算出了所有00中第二个0的下标处的one[index]
     *
     * @param num
     * @return
     */
    public int findIntegers(int num) {
        return solution(num);
    }

    public int solution(int num) {

        String str = Integer.toBinaryString(num);
        int length = str.length();
        int[] zones = new int[length];
        int[] ones = new int[length];
        //以0结尾的二进制的组合方式，每一种组合方式都代表了一个数值
        zones[0] = 1;
        //以1结尾的二进制的组合方式，每一种组合方式都代表了一个数值
        ones[0] = 1;
        for (int i = 1; i < length; i++) {
            ones[i] = zones[i - 1];
            zones[i] = zones[i - 1] + ones[i - 1];
        }

        int result = zones[length - 1] + ones[length - 1];
        int i = 1;
        while (i < length) {
            if (str.charAt(i - 1) == '1' && str.charAt(i) == '1') {
                break;
            } else if (str.charAt(i - 1) == '0' && str.charAt(i) == '0') {
                result -= ones[length - i - 1];
            }
            i++;
        }

        return result;
    }


}
