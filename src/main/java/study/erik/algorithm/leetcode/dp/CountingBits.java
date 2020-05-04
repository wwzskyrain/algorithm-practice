package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-05-04 20:43
 */
public class CountingBits {

    @Test
    public void test_solution() {
        Assert.assertArrayEquals(new int[]{0, 1, 1}, countBits(2));
        Assert.assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, countBits(5));
    }

    /**
     * title = Counting Bits
     * <p>
     * 这是一道dp题目，就是找规律，就是不能每次都在独立计算一遍，就是要利用起之前计算过的解，就是找递归关系式
     * 但是这里要计算的二进制相关，所以少不了位操作。
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        return solutionWithDp2(num);
    }

    /**
     * f(i) = f(i/2) + (i % 2 == 0)?0:1
     * 这个递归关系，很容易理解，应为每一个数字i，在二进制表示的时候，都是它的i/2左移一位再加上i对2的余数。
     * 特别注意，对2整除和对2求余的位操作的实现
     *
     * @param num
     * @return
     */
    public int[] solutionWithDp1(int num) {
        int[] bits = new int[num + 1];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = bits[i >> 1] + ((i & 1) == 0 ? 0 : 1);
        }
        return bits;
    }

    /**
     * f(i) = f(i&(i-1)) + 1;
     * 这个递归关系就不好理解了，不过首先理解i&(i-1)与i的关系
     * i&(i-1)在二进制操作上就是抹去i的二进制表示中最右面的那个1；
     * 再解释解释：不用解释了，你就画画就可以了
     * 所以可得f(i) = f(i&(i-1)) + 1， 这里f表示i的二进制映射中1的个数
     *
     * @param num
     * @return
     */
    public int[] solutionWithDp2(int num) {
        int[] bits = new int[num + 1];
        for (int i = 1; i < bits.length; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }


}
