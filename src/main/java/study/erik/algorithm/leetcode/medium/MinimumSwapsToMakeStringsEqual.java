package study.erik.algorithm.leetcode.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-21 15:51
 * @description
 */
public class MinimumSwapsToMakeStringsEqual {

    /**
     * title = Minimum Swaps to Make Strings Equal
     * url = https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/
     * @param s1
     * @param s2
     * @return
     */
    public int minimumSwap(String s1, String s2) {
        return solution1(s1, s2);
    }

    /**
     * 成绩：双百——我最优秀的一次解答了。
     * 这是一个计算型的题目。但是前提是找到操作的规律，然后才能根据规律来计算。
     * 注意：与计算型题目相对的是'模拟类题目'。模拟什么？模拟操作。操作的时候，统计操作步骤，得出解。
     * 如果能找到操作的规律，就能把'模拟类题目'变成'计算型题目'，而且后者的成绩很定不俗。
     * 下面来说这个题目的规律：
     * 1.   首先过滤调s[i] == s[j]的，因为他们不需要在交换，不存在'迂回反而是捷径'的机会。
     * 2.   统计出s[i] != s[j]的个数，如果是奇数，直接返回-1，说明不可能交换成功
     *      因为奇数个是不能最终交换成功的，比如一对
     * 3.   如果x的个数是偶数，那么y的个数也是偶数：偶数 - 偶数 = 偶数
     * 4.   这是就要介绍一下，变换法则了。
     *      变换1：s1[i] -- s2[j] (其中，i != j) ，效果很明显：把s1[i] == s2[i] 而且 s1[j]=s2[j]了；这样这两对就完成交换了。
     *      变化2：当处理完变换1之后，就只剩下s1='xy'，或者 s1='yx'了（s2和s1互补，这是也就唯一确定的）。这时，针对这种具体的情况
     *             变换两次，解决问题。
     * 5.   综合变换1和变换2，写出计算过程。
     * @param s1
     * @param s2
     * @return
     */
    public int solution1(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }

        if ((s1 == null || s2 == null) || s1.length() != s2.length()) {
            throw new IllegalArgumentException("");
        }

        int xCount = 0, yCount = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (s1.charAt(i) == 'x') {
                    xCount++;
                } else {
                    yCount++;
                }
            }
        }
        int diffCount = xCount + yCount;
        if (diffCount % 2 != 0) {
            return -1;
        }
        if (xCount % 2 == 0) { //全部由变换1搞定
            return diffCount / 2;
        } else { //(diffCount / 2)个变换1 + 1个变换2.
            return (diffCount / 2) + 1;
        }
    }


    @Test
    public void test_solution1() {

        String s21 = "xxyyxyxyxx", s22 = "xyyxyxxxyx";
        Assert.assertEquals(4, solution1(s21, s22));

        String s11 = "xx", s12 = "xy";
        Assert.assertEquals(-1, solution1(s11, s12));


        String s1 = "xx", s2 = "yy";
        Assert.assertEquals(1, solution1(s1, s2));
    }

}
