package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-15 08:37
 */
public class FindTheClosestPalindrome {

    @LetCodeCommit(no = 564, title = "Find the Closest Palindrome",
            selfRemark = "这是个找回文数的题目，是个计算题，不是个操作题目；" +
                    "之前自己实现了一个用累加/减来寻找的，意料之中的超时。" +
                    "参考了dis，改为计算法，链接：(https://leetcode.com/problems/find-the-closest-palindrome/discuss/122957/Java-compare-five-candidates-get-result-easy-to-understand)" +
                    "")
    public String nearestPalindromic(String n) {


        int base = Integer.parseInt(n.substring(0, n.length() / 2 + n.length() % 2));
        List<Long> candidate = new LinkedList<>();

//      必在这五个数之中。
        candidate.add(getPalindromic(base, (n.length() % 2) != 0));
        candidate.add(getPalindromic(base + 1, (n.length() % 2) != 0));
        candidate.add(getPalindromic(base - 1, (n.length() % 2) != 0));

//      具有进位/借位的两种情况-主要是因为exclusive输入，如果不是这个条件，就不用考虑这两种种情况
        candidate.add(((long) Math.pow(10, n.length())) + 1);
        candidate.add(((long) Math.pow(10, n.length() - 1)) - 1);

        long result = 0;
        long minDistance = Long.MAX_VALUE;
        long input = Long.parseLong(n);
        for (Long cand : candidate) {
            if (cand == input) {
                continue;
            }
            long dis = Math.abs(cand - input);
            if (dis < minDistance) {
                minDistance = dis;
                result = cand;
            }
            if (dis == minDistance) {
                result = Math.min(result, cand);
            }
        }
        return String.valueOf(result);
    }

    public long getPalindromic(int base, boolean weakLastBit) {

        long palindromic = base;
        base = weakLastBit ? base / 10 : base;
        while (base > 0) {
            palindromic = palindromic * 10 + base % 10;
            base /= 10;
        }
        return palindromic;
    }


    @Test
    public void test_solution_1() {
        Assert.assertEquals("121", nearestPalindromic("122"));
    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals("1221", nearestPalindromic("1213"));
    }

    @Test
    public void test_solution_3() {
        Assert.assertEquals("9", nearestPalindromic("10"));
    }

    @Test
    public void test_solution_4() {
        Assert.assertEquals("0", nearestPalindromic("1"));
    }

    @Test
    public void test_solution_5() {
        Assert.assertEquals("101", nearestPalindromic("99"));
    }

}
