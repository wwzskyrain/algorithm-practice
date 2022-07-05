/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

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
 * @version : ShortEncodingOfWords.java, v 0.1 2022年07月05日 21:52 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ShortEncodingOfWords {

    @LetCodeCommit(title = "820. Short Encoding of Words",
            diff = "m",
            selfRemark = "这个题目理解之后，确实挺简单的。"
                    + "转化为去除后缀的问题。"
                    + "然后用string的endWith"
                    + "或者用trie树最好.")
    public int minimumLengthEncoding(String[] words) {
        TireNode root = new TireNode();
        Map<TireNode, Integer> map = new HashMap<>();
        for (String word : words) {
            char[] chars = word.toCharArray();
            TireNode cur = root;
            for (int i = chars.length - 1; i >= 0; i--) {
                cur = cur.get(chars[i]);
            }
            map.put(cur, word.length());
        }

        int ans = 0;
        for (TireNode node : map.keySet()) {
            if (node.count == 0) {
                ans += (map.get(node) + 1);
            }
        }
        return ans;
    }

    public static class TireNode {
        int        count;
        TireNode[] children;

        public TireNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TireNode();
            }
            count++;
            return children[c - 'a'];
        }

        TireNode() {
            count = 0;
            children = new TireNode[26];
        }
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"time", "me", "bell"}, 10},
                {new String[] {"t"}, 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minimumLengthEncoding(words));
    }
}