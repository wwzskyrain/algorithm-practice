/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yueyi
 * @version : LongestDuplicateSubstring.java, v 0.1 2022年12月23日 08:32 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestDuplicateSubstring {

    @LetCodeCommit(title = "1044. Longest Duplicate Substring",
            selfRemark = "这是一个很不错的题目。"
                    + "可以用dp解法，思路很简单，代码也很简单，但是空间复杂度太高，n平方。"
                    + "这里用二叉查找来求最值，也是一个很好地思路。这里还需要用方法来判断两个子字符串是否相等，这里用到了rabinKarp方法，"
                    + "思路是用哈希值+等值判断。这完全是hashmap的key的散列方法，所知这里也可以取巧用hashmap来实现."
                    + "然而，这个题的testcase真他妈的扯淡，比如第一个，用文本工具都查不到expect的两个子字符串。"
                    + "就这吧，睡觉。")
    public String longestDupSubstring(String s) {
        int low = 0;
        int maxLength = 0;
        int optIndex = 0;
        int high = s.length();
        while (low < high) {
            int mid = low + (high - low) / 2;
            int index = check(s, mid);
            if (index > -1) {
                //存在mid长度的重复子字符串
                low = mid + 1;
                maxLength = mid;
                optIndex = index;
            } else {
                high = mid;
            }
        }
        if (maxLength > 0) {
            return s.substring(optIndex, optIndex + maxLength);
        }
        return "";
    }

    int MOD = (int) 1e7 + 7;

    /**
     *
     * @param s
     * @param len
     * @return
     */
    public int check(String s, int len) {
        if (len < 1) {
            return -1;
        }
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() - len + 1; i++) {
            String subStr = s.substring(i, i + len);
            Integer index = map.get(subStr);
            if (index != null) {
                return index;
            } else {
                map.put(subStr, i);
            }
        }
        return -1;
    }

    public int rabinKarp(String s, int len, int[] pow) {
        if (len < 1) {
            return -1;
        }
        Map<Long, Integer> map = new HashMap<>();
        long code = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            code = ((code * 26) + (c - 'a')) % MOD;
        }
        map.put(code, 0);
        for (int i = 1; i <= s.length() - len; i++) {
            int preChar = s.charAt(i - 1);
            code = ((code - (long) (preChar - 'a') * pow[len - 1]) % MOD + MOD) % MOD;
            code = (code * 26 + (s.charAt(i + len - 1) - 'a')) % MOD;
            Integer index = map.get(code);
            if (index != null) {
                if (s.substring(index, index + len).equals(s.substring(i, i + len))) {return index;}
            }
            map.put(code, i);
        }
        return -1;
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ttwphlndxvcruhoaapgcfovcqopxbyzcidwhbwmpbdaiyanfhotksdvamvtpzvvugyryqyxujwbcxffxnsbtbabrcyndqrtdljjjayplrhrdkbeerdgfoghpwvrdtbxpoqpbsvwksgihlaxrkilhnqewfsqwvqqnzfbebdjqheieunlfxdkqrqcsxgdbrtgmsotvvzdeijnxztqpvlmfpjicsfrdmtbtqqmiqsdhgtoqlszvfqlysnoipqytxpmsrvxlsurceauhouzdqalnwoguxywmjdtlblrauelxmjwcxxnatjfkachlnzxdhxqqwyojstzcrmykisgylckdjylqcuzwtksklgmhxgfzmrmobjgianujssoonukgwtqkyoehkxfqznqolerjueurpxnhzvegghwzqqyakilucywadvqayrrsotfmntebgnvzrsstulludafuyunheufvmlriccizxjxvbpaeofopflwimtxahjacxwwplojj",
                        "fovcqopx"},
                {"banana", "ana"},
                {"abcd", ""},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestDupSubstring(s));
    }
}