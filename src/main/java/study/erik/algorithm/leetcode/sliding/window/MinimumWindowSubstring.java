package study.erik.algorithm.leetcode.sliding.window;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author erik.wang
 * @date 2020-07-13 22:12
 * title = Minimum Window Substring
 */
public class MinimumWindowSubstring {

    @Test
    public void test_() {

        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC", "ABC"));
        Assert.assertEquals("A", minWindow("A", "A"));

    }

    /**
     * 成绩：80 和 23
     * 很开心，独自想出来的，虽然代码上有点绕，但是基本还是符合滑动窗口框架的。
     * 经验：尽量用hash表来自己实现数组，因为换成hashMap之后，性能下降很多
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        if (t.length() > s.length()) {
            return "";
        }

        //分别有多少个字符没有包含
        int[] hash = new int[64];
        for (int i = 0; i < t.length(); i++) {
            hash[t.charAt(i) - 'A']++;
        }
        for (int i = 0; i < hash.length; i++) {
            hash[i] = hash[i] == 0 ? Integer.MAX_VALUE : hash[i];
        }

        //尚有多少个字符没有包含
        int count = t.length();

        int l, r;
        l = 0;

        int minWindowSize = Integer.MAX_VALUE;
        String minWindow = "";

        for (r = 0; r < s.length(); r++) {
            int hashIndex = s.charAt(r) - 'A';
            if (hash[hashIndex] == Integer.MAX_VALUE) {
                continue;
            }
            int c = --hash[hashIndex];
            if (c >= 0) {
                //只有c >= 0 时，才减少count
                count--;
            }
            if (count > 0) {
                //如果当前窗口还有字符没有包含，则r指针继续右移
                continue;
            }
            while (true) {
                hashIndex = s.charAt(l++) - 'A';
                if (hash[hashIndex] == Integer.MAX_VALUE) {
                    continue;
                }
                c = ++hash[hashIndex];
                if (c > 0) {
                    // 只有大于0时，才增加count，表示还有count个字符没有包含
                    count++;
                }
                if (c == 1) {
                    //如果从0到1，表示又多了一个没有包含的字符。这也说明包含当前这个字符的窗口是一个局部最小有效窗口，因为少了这个字符，就有欠下了一个字符了
                    if (minWindowSize > r - l + 2) {
                        minWindow = s.substring(l - 1, r + 1);
                        minWindowSize = minWindow.length();
                    }
                    break;
                }
            }
        }
        return minWindow;
    }

}
