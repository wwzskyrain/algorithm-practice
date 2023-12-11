package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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
     * 再次理解：定名-平移有效窗口法。
     * 不同于基础滑动窗口题目，这是一个'补偿性滑动窗口题'。
     * 本题中，有效窗口平移时，不需要更新(减少)maxCount——多数元素的数量
     * 1.在窗口左边界缩小时，没有维护maxCount的定义
     *  这是因为
     *  1.不需要：因为最优值就是最大窗口，当窗口中最多字符的个数max_count越大，窗口最有。所以，我们只用关心更大值就好了，不用在考虑max_count变小的情况。
     *  2.维护的代价太大了。要动态排序，涉及到原来数据的--和新数据的++，这就别想了。
     */
    public int characterReplacement(String s, int k) {

        int[] count = new int[26];
        int maxCount = 0;
        int start = 0;
        int ret = 0;
        for (int end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                //一直维护着窗口有效
                //这里不需要用while，一个if就足够了。但是while更符合思路。
                count[s.charAt(start++) - 'A']--;
            }
//            if (end - start + 1 - maxCount > k) {
//                //一直维护着窗口有效
//                //这里不需要用while，一个if就足够了。但是while更符合思路。
//                count[s.charAt(start++) - 'A']--;
//            }
            ret = Math.max(ret, end - start + 1);
        }
        return ret;
//      return s.length() - start;  //直接这样也可以，但是又需要一层思考：按照上面说的，有效窗口只会越来越大，而这里s.length() - start就是最大的有效窗口。
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

