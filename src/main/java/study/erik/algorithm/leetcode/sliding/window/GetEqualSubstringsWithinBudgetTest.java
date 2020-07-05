package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-07-04 23:46
 * title = Get Equal Substrings Within Budget
 */
public class GetEqualSubstringsWithinBudgetTest {


    @Test
    public void test_() {

        Assert.assertEquals(equalSubstring("krrgw", "zjxss", 19), 2);
        Assert.assertEquals(equalSubstring("abcd", "bcdf", 3), 3);
        Assert.assertEquals(equalSubstring("abcd", "cdef", 3), 1);
        Assert.assertEquals(equalSubstring("abcd", "acde", 0), 1);
    }


    /**
     * m题，难不住我，调一调就过了，然而成绩低的可以，7和6，接受不了。
     * 分析：已经是一遍扫描了；换用了数组来保存差值，成绩提升了，34和37。神奇呀，空间利用率应该更高呀。算了，睡觉哈。
     *
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {

        int low, high, count, result;
        low = high = count = result = 0;

        int[] diff = new int[s.length()];
        for (int i = 0; i < diff.length; i++) {
            diff[i] = Math.abs(((int) s.charAt(i)) - (int) t.charAt(i));
        }

        while (high < diff.length) {

            if ((count += diff[high]) <= maxCost) {
                result = Math.max(result, high - low + 1);
            } else {
                while ((count -= diff[low++]) > maxCost) {
                }
            }
            high++;
        }
        return result;
    }

    /**
     * lee大神的解法：平移滑动窗口法
     * @param s
     * @param t
     * @param k
     * @return
     */
    public int equalSubstring1(String s, String t, int k) {
        int n = s.length(), i = 0, j;
        for (j = 0; j < n; ++j) {
            k -= Math.abs(s.charAt(j) - t.charAt(j));
            if (k < 0) {

                k += Math.abs(s.charAt(i) - t.charAt(i));
                ++i;
                //右边方向平移一个，此时k不一定就不<0了；但是k>0时的窗口[i,j]保持了下来，因为平移嘛
                //只要一直这样保持下去，就可以了。
            }
        }
        return j - i;
    }


}
