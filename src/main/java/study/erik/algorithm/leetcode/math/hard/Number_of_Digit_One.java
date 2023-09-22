package study.erik.algorithm.leetcode.math.hard;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Collection;

/**
 * 日期：2023/9/19 ，时间：11:51
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Number_of_Digit_One {

    @LetCodeCommit(title = "233. Number of Digit One",
            selfRemark = "突然发现，这个题目的这个解法真的很完美呢——思路清晰、复杂度低。" +
                    "然后，还有一种写法，更精炼，见solution2，同样的思想的")
    public int countDigitOne(int n) {
        String s = String.valueOf(n);
        int m = s.length();
        if (m == 1) return n > 0 ? 1 : 0;
        // 计算第 i 位前缀代表的数值，和后缀代表的数值
        // 例如 abcde 则有 ps[2] = ab; ss[2] = de
        int[] ps = new int[m], ss = new int[m];
        ss[0] = Integer.parseInt(s.substring(1));
        for (int i = 1; i < m - 1; i++) {
            ps[i] = Integer.parseInt(s.substring(0, i));
            ss[i] = Integer.parseInt(s.substring(i + 1));
        }
        ps[m - 1] = Integer.parseInt(s.substring(0, m - 1));
        // 分情况讨论
        int ans = 0;
        for (int i = 0; i < m; i++) {
            // x 为当前位数值，len 为当前位后面长度为多少
            int x = s.charAt(i) - '0', len = m - i - 1;
            int prefix = ps[i], suffix = ss[i];
            int tot = 0;
            //case1：在区间[0,prefix)中，无论x是什么，都可以在x位置放1，并且变化的次数为x之后有多少位——取幂即可。
            tot += prefix * Math.pow(10, len);
            //case2：挨着prefix打满的情况了，此时要看x的值了。
            if (x == 0) {
                //case2-1：完蛋，没有1出现的机会
            } else if (x == 1) {
                //case2-2：1出现了，出现的次数=后缀空间+1
                tot += suffix + 1;
            } else {
                //case2-3：x比1大，所以1已经出现过了，出现多少次？看prefix几位了。
                tot += Math.pow(10, len);
            }
            ans += tot;
        }
        return ans;
    }

    /**
     * 和上面的解法是同一个思想，不通点在于代码写法。这是youtube上每日一题的解法。此外还有一个解法，见solution3
     *
     * @param n
     * @return
     */
    public int countDigitOneSolution2(int n) {
        String s = String.valueOf(n);
        int N = s.length();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            //左边的数字
            int a = n / (int) Math.pow(10, i);
            cnt += a * (int) Math.pow(10, i - 1);
            int digit = s.charAt(N - i) - '0';
            if (digit > 1) {
                cnt += Math.pow(10, i - 1);
            } else if (digit == 1) {
                cnt += n % (int) Math.pow(10, i - 1) + 1;
            }
        }
        return cnt;
    }

    /**
     * 这个是我2016年提交的一个代码，思路也一样，写法比较新颖。
     *
     * @param n
     * @return
     */
    public int countDigitOneSolution3(int n) {
        long count = 0;
        long factor = 1;

        while (n / factor != 0) {
            long lower = n % factor;
            long cur = (n / factor) % 10;
            long higher = n / (factor * 10);

            if (cur < 2) {
                count += higher * factor;
                if (cur == 1) count += lower + 1;
            } else {
                count += (higher + 1) * factor;
            }
            factor *= 10;
        }
        return (int) count;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {6, 13},
                {0, 0},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int n;

    @Test
    public void test() {
        Assert.assertEquals(expect, countDigitOne(n));
    }

}
