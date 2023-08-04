package study.erik.algorithm.leetcode.hard.string;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

/**
 * 日期：2023/8/4 ，时间：09:19
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class SmallestKLengthSubsequenceWithOccurrencesOfALetter {

    @LetCodeCommit(title = "2030. Smallest K-Length Subsequence With Occurrences of a Letter",
            diff = "h",
            selfRemark = "这是一个求子序列，而且最小的那个。" +
                    "暴力解法肯定超时。但是却没有一个通用的高明的解法。" +
                    "只能具体分析。这样的题目不具有很好的考察能力，不过也可以具体问题具体分析的能力。")
    public String smallestSubsequence(String s, int k, char letter, int r) {
        int n_letters = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == letter) n_letters++;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            while (!stack.isEmpty()
                    && stack.peek() > c  //单调递增stack
                    && (s.length() - i + stack.size() > k) //保证后面还有足够多的char
                    && (stack.peek() != letter || n_letters > r)) { //如果是letter，保证剩下的n_letter>剩下的需要重复的次数
                if (stack.pop() == letter) r++;
            }

            if (stack.size() < k) {
                if (c == letter) {
                    stack.push(c);
                    r--;
                } else if (k - stack.size() > r) {
                    stack.push(c);
                }
            }

            if (c == letter) {
                n_letters--;
            }
        }

        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) sb.append(c);
        return sb.toString();
    }


    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][]{{"eet", "leet", 3, 'e', 1}, {"ecde", "leetcode", 4, 'e', 2}, {"bb", "bb", 2, 'b', 2},});
    }

    @Parameterized.Parameter
    public String expect;
    @Parameterized.Parameter(1)
    public String s;
    @Parameterized.Parameter(2)
    public int k;
    @Parameterized.Parameter(3)
    public char letter;
    @Parameterized.Parameter(4)
    public int r;


    @Test
    public void test() {
        Assert.assertEquals(expect, smallestSubsequence(s, k, letter, r));
    }

}
