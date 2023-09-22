package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-05-02 10:02
 *
 */
@LetCodeCommit(title = "664. Strange Printer")
public class StrangePrinter {

    @Test
    public void test_() {
        Assert.assertEquals(0, strangePrinter(""));
        Assert.assertEquals(3, strangePrinter("abc"));
        Assert.assertEquals(3, strangePrinter("axyxa"));
        Assert.assertEquals(2, strangePrinter("aba"));
        Assert.assertEquals(2, strangePrinter("aaabbb"));
    }

    /**
     * title = Strange Printer
     *
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        return solutionWithDp(s);
    }

    /**
     * 成绩：15.15%  和 6.67% 很低很低，为什么呢，因为我用了递归函数来实现的dp吧。这里就不想再修改了。
     * 解法分析：分治解法。分治的好处时，可以分治可以把大的复杂的问题小而简单化。其中复杂是因为问题规模比较大。这里不对归并排序的分支法做具体讨论。
     * 该题使用的是分治算法吗？好像这也是dp中常用的一种变化形式呢。确实，这和矩阵连乘的解法是几乎完全一样的(不要扣几乎完全这个字眼，要去体会作者的意思就好了)
     * 但是下面我还是按照分治的思路讲吧，毕竟这是我的思路
     * 1.   分解问题：到何种程度呢，到不能再分，即一个element的时候
     * 2.   合并问题：这有大讲究，一定要选择一个通用的合并策略，而且要注意具体的合并case，
     * 比如，（先定义下f(s) 为打印字符串s的最小turn，o(s)表示打印s的具体操作）
     * f(a)和f(b)，则f(ab)呢，要具体怎么操作呢？有两种情况
     * case1：先f(a)在f(b)，即先打a，再打b
     * case2：先打aa，再在第二个a上打印b。
     * 虽然两种情况都能完成"ab"的打印，而且这两种情况f(ab)都是2，但是确实有不同的，case1只是把o(a)和o(b)的简单相加，而case2则不同的。
     * 再请看打印"aba"的情况，我们已经知道ab"，"a"分别情况下的打印，那么
     * case1的话，就是o(ab)和o(a)，确实也能完成o(aba)的目标
     * case2的话，就是先o(aaa)，再在第二个a处，打印b，这是。
     * 总结case2的打法：还是aba的例子，先打印aaa；这是因为第二个a和源字符串b不对，就在第二个a处打印b，一直打印到最后一个字符
     * ，当最后一个字符刚好已经和源字符相同时，就不打印最后一个字符，这样就是只打印一个b。
     * 这样对应着合并f(s1)和f(s2)的时候，如果s1的最后一个元素和s2的最后一个元素相同，那么在按照case2合并
     * 到s2的倒数第二个字符就可以了。最后一个字符已经在s1的最后一个打印时是打印正确了。
     *
     * 总结：本来想一探分治法的就能，可是越分析约觉得不够严谨。算了
     *
     * 关键就是要研究合并的情况：根据题意，合并两个o(a)和o(b)时，只有两种操作，就是上面想到的那样case1和case2，而case2要比case1更优，所以全篇都用case2来合并。。
     *
     * @param s
     * @return
     */
    public int solutionWithDp(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        StringBuilder sb = new StringBuilder();
        char preChar = '0';
        for (int i = 0; i < s.length(); i++) {
            char c;
            if ((c = s.charAt(i)) != preChar) {
                sb.append(c);
                preChar = c;
            }
        }

        int[][] dp = new int[sb.length()][sb.length()];
        return dpSolution(sb.toString(), 0, sb.length() - 1, dp);
    }

    /**
     * 用备忘录的方法来填dp
     * 返回dp[i,j]：用这个奇怪的打印机打印sb[i...j]所花费的最小turn
     *
     * @param sb
     * @param i
     * @param j
     * @param dp dp[i,j] 表示用这个奇怪的打印机打印sb[i...j]所花费的最小turn
     * @return
     */
    public int dpSolution(String sb, int i, int j, int[][] dp) {
        int result;
        if ((result = dp[i][j]) != 0) {
            return result;
        }

        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        if (i + 1 == j) {
            return 2;
        }
        result = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            //k 属于 [i,j)
            int oneSolution = dpSolution(sb, i, k, dp) + dpSolution(sb, k + 1, j, dp);
            if (sb.charAt(k) == sb.charAt(j)) {
                oneSolution--;
            }
            result = Math.min(result, oneSolution);
        }

        dp[i][j] = result;
        return result;
    }


}
