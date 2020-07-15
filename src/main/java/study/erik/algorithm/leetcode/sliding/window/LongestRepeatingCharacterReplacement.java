package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-06-08 15:41
 * title = Longest Repeating Character Replacement
 * url = https://leetcode.com/problems/longest-repeating-character-replacement/
 */
public class LongestRepeatingCharacterReplacement {


    @Test
    public void test_characterReplacement() {
        Assert.assertEquals(4, characterReplacement("AABABBA", 1));
        Assert.assertEquals(4, characterReplacement("ABAB", 2));
    }

    /**
     * 这是'滑动窗口'的第一题，直接看的答案；
     * 思路很清晰，不过我还是理解了很久；
     * 因为求最大值，所以，不需要缩小窗口：要么右移窗口，要么扩大窗口。
     * 其中，右移只是保持当前的有效窗口，至少在长度上是有效的；而扩大窗口以寻求更大的值
     * 再次理解：定名-平移有小窗口法。
     * 不同于基础滑动窗口题目，这是一个'补偿性滑动窗口题'。
     * 本题中，有效窗口平移时，不需要更新(减少)maxCount——多数元素的数量
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {

        int[] count = new int[26];
        int maxCount = 0;
        int start = 0;
        for (int end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            if (end - start + 1 - maxCount > k) {
                count[s.charAt(start++) - 'A']--;
            }
        }
        return s.length() - start;
    }


    /**
     * lee215(大神) 的解法；
     * 也是平移有效窗口法；
     *
     * @param s
     * @param k
     * @return
     */
    public int solution2(String s, int k) {
        int res = 0, maxf = 0;
        //res是result，其实是最终有效窗口的大小；
        int[] count = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            maxf = Math.max(maxf, ++count[s.charAt(i)]);
            if (res - maxf < k) {
                res++;
            } else {
                count[s.charAt(i - res)]--;
            }
        }
        return res;
    }


}

