/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.dp.subsequence;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : IsSubsequence.java, v 0.1 2021年12月02日 7:57 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class IsSubsequence {

    @LetCodeCommit(title = "392. Is Subsequence",
            selfRemark = "这个题目有意思的。"
                    + "首先，它有很好的子问题属性，但是真的使用dp来解答，又会超时。"
                    + "说明它还有更好的属性来方便解答的。"
                    + "它的更好的问题特征是这样的，问题就出在，当t中有多个char"
                    + "与当前s[0]相等时，可以就认准第一个吗？答案是可以。")
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(0)) {
                // 这里就只选择第一个相同的字母即可。
                // 虽然不符合dp属性，但是这样做可以节省很多错误的case，从而节省时间，避免超时
                return isSubsequence(s.substring(1), t.substring(i + 1));
            }
        }
        return false;
    }

    @Parameter
    public String  s;
    @Parameter(1)
    public String  t;
    @Parameter(2)
    public boolean expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"abc", "ahbgdc", true},
                {"axc", "ahbgdc", false},
                {"rjufvjafbxnbgriwgokdgqdqewn",
                        "mjmqqjrmzkvhxlyruonekhhofpzzslupzojfuoztvzmmqvmlhgqxehojfowtrinbatjujaxekbcydldglkbxsqbbnrkhfdnpfbuaktupfftiljwpgglkjqunvithzlzpgikixqeuimmtbiskemplcvljqgvlzvnqxgedxqnznddkiujwhdefziydtquoudzxstpjjitmiimbjfgfjikkjycwgnpdxpeppsturjwkgnifinccvqzwlbmgpdaodzptyrjjkbqmgdrftfbwgimsmjpknuqtijrsnwvtytqqvookinzmkkkrkgwafohflvuedssukjgipgmypakhlckvizmqvycvbxhlljzejcaijqnfgobuhuiahtmxfzoplmmjfxtggwwxliplntkfuxjcnzcqsaagahbbneugiocexcfpszzomumfqpaiydssmihdoewahoswhlnpctjmkyufsvjlrflfiktndubnymenlmpyrhjxfdcq",
                        false}
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, isSubsequence(s, t));
    }
}
