package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-03-14 09:25
 * @description
 */
@RunWith(Parameterized.class)
public class WordBreakI {

    @LetCodeCommit(title = "Word Break")
    public boolean wordBreak(String s, List<String> wordDict) {
        return solution1(s, wordDict);
    }

    public boolean solution1(String s, List<String> wordDict) {//s能否由字典拼成，就是原问题
        if (wordDict.contains(s)) {
            return true;
        }
        boolean contain = false;
        for (int i = 1; i < s.length(); i++) {
            boolean leftContain = solution1(s.substring(0, i), wordDict);
            if (!leftContain) {
                continue;
            }
            boolean rightContain = solution1(s.substring(i), wordDict);
            contain = contain || (leftContain && rightContain);
            if (contain) {
                break;
            }
        }
        return contain;
    }

    /**
     * 完全独立解答，ac了呢，虽然成绩有点低：10%和5%。
     * 首先，讨论区有一个N平方的解法，而且只需要一维的辅助数组。
     * 可是我看他的解法是在不理解，感觉会遗漏，最终先搁置吧，以后找到高人可以讨论下。链接留下：https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways
     * 再来说我的解法：我的这个解法是solution1的非递归版吧。递归版的代码很简单，但是超时的。
     * 定义dp[i,j]表示s的子串s[i,j]是否能由字典拼成，也就是原问题的定义。
     * 则 dp[i,j] = { 字典直接有一个s[i,j]的词条 || s[i,k]和s[k+1,j]这两个子串都能被字典拼成，其中k属于[i,j] }
     * 即 dp[i,j] = { 字典直接有一个s[i,j]的词条 || dp[i,k] && dp[k+1,j] (其中k属于[i,j]) }
     * 最后dp[0, s.length()-1]就是问题解.
     * 如果有经验的话，就知道，这个二位dp数组是不好计算的；具体计算步骤注释如下。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean solution2(String s, List<String> wordDict) {

        boolean[][] dp = new boolean[s.length()][];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new boolean[s.length()];
            // 细节，必须把char转成String，不然的话，contains返回为false，因为类型不匹配
            dp[i][i] = wordDict.contains(String.valueOf(s.charAt(i)));
        }

        //dp[i][j] 表示 字符串s[i...j]由字典拼成
        //dp[i][j] = s[i..j]在字典中 || （s[i...k]和s[k+1...j]都能由字典拼成）---k={i,i+1,...,j}

        // j这里并不是长度，因为这类dp二位数组我们也结果的。
        // j就是dp[i,j]中的j。比如 dp[0,1] dp[1,2]
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i < dp[i].length - j; i++) {
                // 这个for循环，是斜向下来赋值 dp[i][i+j]的。
                // 细节：subString的第二个参数一定要+1
                dp[i][i + j] = wordDict.contains(s.substring(i, i + j + 1));
                if (dp[i][i + j]) {
                    //细节：这里是continue，不是break。break的话，下一行的dp[i,i+j]就不算了。
                    continue;
                }
                boolean temp = false;
                for (int k = i; k < i + j; k++) {
                    temp = dp[i][k] && dp[k + 1][i + j];
                    if (temp) {
                        break;
                    }
                }
                dp[i][i + j] = temp;
            }
        }
        return dp[0][s.length() - 1];
    }

    @Test
    public void test_solution() {


        List<String> wordDict3 = new ArrayList<>(Arrays.asList("a"));
        Assert.assertTrue(solution2("a", wordDict3));

        List<String> wordDict2 = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        Assert.assertFalse(solution2("catsandog", wordDict2));

        List<String> wordDict1 = new ArrayList<>(Arrays.asList("apple", "pen"));
        Assert.assertTrue(solution2("applepenapple", wordDict1));

        List<String> wordDict = new ArrayList<>(Arrays.asList("leet", "code"));
        Assert.assertTrue(solution2("leetcode", wordDict));
    }

}
