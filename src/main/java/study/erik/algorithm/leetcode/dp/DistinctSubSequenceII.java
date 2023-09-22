package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-03-13 18:51
 * @description
 */
@LetCodeCommit(title = "940.Distinct Subsequences II",
        selfRemark = "")
public class DistinctSubSequenceII {

    public int distinctSubseqII(String S) {
        return solution(S);
    }

    /**
     * 成绩：40% 和 20% 并不是很好
     * 解法思路：首先承认，我起初的思路是不对的；起初，我想的是做组合，即生成所有的子序列，而且要用一个set持有，然后返回set的大小。
     * 显然，其空间消耗太多。而且题目中已经提示，子序列会很大。所以我知道这个思路肯定不行。于是上网发现了下面的解法。
     * 首先题目提示，还有26个小写字母，其次要删除相同的子序列；所以这不是数学意义上的集合的所有子集的个数，至少子集是不会重复的。
     * 首先分析子序列的一个特性：追加累加性，这个不用证明（尚需描述清楚），因为很明显。
     * 基于"追加累加性"，结合只有26个小写字母，所以这是就可以。。。
     * 好，理论讲解完毕，不管你懂了没有。
     * 下面来说解题过程：用seq[0..25]表示当前序列S[0...i]的以a/b/../z结尾的子序列个数；
     * 此时，对于S[i+1]，（假设为字符C）的加入，则seq[C-'a']是之前seq的累加+1.最后一个1是不算C
     * 之前的所有子序列，而独独只有S[i+1]单个字母为子序列。这个1不会与之前相同的以C为结尾的重复吗？
     * 当然不重复了，假设之前已经有一个C出现过，那么我们担心的是之前C作为一个单字符的子序列和当前C作为一个单字符的子序列会重复。
     * 其实而当C出现时，之前的单字符子序列就会被追加当前C，既形成CC，就不存在C，这个单字符子序列了。
     * 所以，我们+1，以当前C作为单字符自序列就不重复了。
     *
     * @param S
     * @return
     */
    public int solution(String S) {
        if (S == null || S.length() == 0) {
            return 0;
        }
        if (S.length() == 1) {
            return 1;
        }

        int M = 1000000007;
        int[] seq = new int[26];

        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            int sum = 0;
            for (int j = 0; j < seq.length; j++) {
                sum = (sum + seq[j]) % M;
            }
            seq[index] = sum + 1; //这个1是有来头的——之前打头的那个子字符串已经不存在了，因为已经加上后缀了。
        }
        int result = 0;
        for (int i = 0; i < seq.length; i++) {
            result = (result + seq[i]) % M;
        }
        return result;
    }

    @Test
    public void test_solution() {
        Assert.assertEquals(7, solution("abc"));
        Assert.assertEquals(6, solution("aba"));
        Assert.assertEquals(3, solution("aaa"));

    }

}
