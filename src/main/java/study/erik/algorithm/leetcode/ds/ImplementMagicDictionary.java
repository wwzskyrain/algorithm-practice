/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : ImplementMagicDictionary.java, v 0.1 2022年04月17日 4:28 下午 yueyi Exp $
 */
@LetCodeCommit(title = "676. Implement Magic Dictionary",
        diff = "m",
        selfRemark = "一个很正常的trie树的应用.")
public class ImplementMagicDictionary {

    public static class MagicDictionary {

        public class TrieNode {
            TrieNode[] children;
            boolean    isWord;

            TrieNode() {
                children = new TrieNode[26];
            }
        }

        public TrieNode root;

        public MagicDictionary() {
            root = new TrieNode();
        }

        public void buildDict(String[] dictionary) {
            for (String word : dictionary) {
                char[] chars = word.toCharArray();
                TrieNode cur = root;
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (cur.children[index] == null) {
                        cur.children[index] = new TrieNode();

                    }
                    if (i == chars.length - 1) {
                        cur.children[index].isWord = true;
                    }
                    cur = cur.children[index];
                }
            }
        }

        public boolean search(String searchWord) {

            char[] chars = searchWord.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char saveChar = chars[i];
                for (int j = 0; j < 26; j++) {
                    if (saveChar - 'a' == j) {
                        continue;
                    }
                    chars[i] = (char) (j + 'a');
                    if (searchWithRoot(chars, root)) {
                        return true;
                    }
                }
                chars[i] = saveChar;
            }
            return false;
        }

        private boolean searchWithRoot(char[] word, TrieNode root) {
            TrieNode cur = root;
            for (int i = 0; i < word.length; i++) {
                int index = word[i] - 'a';
                if (cur.children[index] == null) {
                    return false;
                }
                cur = cur.children[index];
            }
            return cur.isWord;
        }
    }

    @Test
    public void test() {
        test_1();
        test_2();
    }

    public static void test_2() {
        //        ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
        //[[], [["hello","hallo","leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
        MagicDictionary dictionary = new MagicDictionary();
        dictionary.buildDict(new String[] {"hello", "hallo", "leetcode"});

        Assert.assertTrue(dictionary.search("hello"));
        Assert.assertTrue(dictionary.search("hhllo"));
        Assert.assertFalse(dictionary.search("hell"));
        Assert.assertFalse(dictionary.search("leetcoded"));

    }

    public static void test_1() {
        MagicDictionary dictionary = new MagicDictionary();
        dictionary.buildDict(new String[] {"hello", "leetcode"});
        Assert.assertFalse(dictionary.search("hello"));
        Assert.assertTrue(dictionary.search("hhllo"));
        Assert.assertFalse(dictionary.search("hell"));
        Assert.assertFalse(dictionary.search("leetcoded"));
    }
}