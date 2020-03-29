package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-22 20:51
 * @description
 */
public class SingleNumber2Test {

    public int singleNumber(int[] nums) {
        return solution2(nums);
    }

    /**
     * 这是第一个解法，并不算是'位运算'的解法，虽然其中也是针对'bit位'来操作的。
     * 不过解题思路还是正确的，那就是统计每个位上出现的1的个数，如果有一个数字的该位
     * 是1，那么这个数字有两种情况。case1，这个数字还会出现两次；case2这个数字只出现
     * 一次。我们如果把第一中情况记做0，把第二种情况记做1，那么我们就知道了theOne在该位
     * 上的值了。如果区分呢，那就是累加并对三求模运算。再细节的就不写了，太晚了。
     *
     * @param nums
     * @return
     */
    public int solution1(int[] nums) {
        int[] results = new int[32];
        for (int i = 0; i < nums.length; i++) {
            //把nums[i]按照每个bit位进行统计
            int num = nums[i];
            for (int j = 0; j < 32; j++) {
                results[j] += (num & 1);
                num = num >> 1;
                results[j] = results[j] % 3;
            }
        }
        int theOne = 0;
        for (int i = 31; i >= 0; i--) {
            //组装出theOne。
            theOne = (theOne | results[i]);
            if (i != 0) {
                theOne = theOne << 1;
            }
        }
        return theOne;
    }

    /**
     * 这一题我们讲究了很久呢，而且小有心得。
     * 接着solution1的思路，我们这里用两位来做表示这个状态机。
     *
     * @param nums
     * @return
     */
    public int solution2(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
//            ones = ones ^ nums[i] & (~twos);
            ones = twos & (ones | nums[i]) | (~twos & ones ^ nums[i]);
            twos = twos ^ nums[i] & ones;
        }
        return ones;
    }


    @Test
    public void test() {
        int[] nums = {2, 2, 3, 2};
        Assert.assertEquals(3, singleNumber(nums));

        int[] nums1 = {0, 1, 0, 1, 0, 1, 99};
        Assert.assertEquals(99, singleNumber(nums1));

        int[] nums2 = {43, 16, 45, 89, 45, -2147483648, 45, 2147483646, -2147483647, -2147483648, 43, 2147483647, -2147483646, -2147483648, 89, -2147483646, 89, -2147483646, -2147483647, 2147483646, -2147483647, 16, 16, 2147483646, 43};
        Assert.assertEquals(2147483647, singleNumber(nums2));

        int[] nums3 = {-2, -2, 1, 1, -3, 1, -3, -3, -4, -2};
        Assert.assertEquals(-4, singleNumber(nums3));
    }

}
