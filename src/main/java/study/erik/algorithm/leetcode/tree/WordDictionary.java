/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : DesignAddAndSearchWordsDataStructure.java, v 0.1 2021年12月22日 8:08 上午 yueyi Exp $
 */
public class WordDictionary {

    @LetCodeCommit(title = "211. Design Add and Search Words Data Structure",
            selfRemark = "字典树-早上写了一个小时，还有一个case没搞定——word结尾节点。"
                    + "这会一下子就ok了，真是有趣味。",
            tag = "好题-字典树",
            related = "https://leetcode.com/problems/prefix-and-suffix-search/")
    public WordDictionary() {
        root = new TrieNode();
    }

    public static class TrieNode {
        TrieNode[] next;
        boolean    word;

        public TrieNode() {
            next = new TrieNode[26];
        }
    }

    public TrieNode root;

    public void addWord(String word) {
        TrieNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curNode.next[c - 'a'] == null) {
                curNode.next[c - 'a'] = new TrieNode();
            }
            curNode = curNode.next[c - 'a'];
        }
        curNode.word = true;
    }

    public boolean search(String word) {
        return doSearch(word, root);
    }

    public boolean doSearch(String word, TrieNode curNode) {
        if (word.length() == 0) {
            return curNode.word;
        }
        char c = word.charAt(0);
        String subWord = word.substring(1);
        if (c == '.') {
            for (TrieNode trieNode : curNode.next) {
                if (trieNode != null && doSearch(subWord, trieNode)) {
                    return true;
                }
            }
            return false;
        } else {
            return curNode.next[c - 'a'] != null && doSearch(subWord, curNode.next[c - 'a']);
        }
    }

    @Test
    public void test_1() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        Assert.assertFalse(wordDictionary.search("pad")); // return False
        Assert.assertTrue(wordDictionary.search("bad")); // return True
        Assert.assertTrue(wordDictionary.search(".ad")); // return True
        Assert.assertTrue(wordDictionary.search("b..")); // return True
    }

    @Test
    public void test_2() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        wordDictionary.addWord("a");
        Assert.assertTrue(wordDictionary.search(".")); // return False
        Assert.assertTrue(wordDictionary.search("a")); // return False
        Assert.assertFalse(wordDictionary.search("aa")); // return True
        Assert.assertTrue(wordDictionary.search("a")); // return True
        Assert.assertFalse(wordDictionary.search(".a")); // return True
        Assert.assertFalse(wordDictionary.search("a.")); // return True
    }

    @Test
    public void test_3() {
        // ["WordDictionary","addWord","addWord","search","search","search","search","search","search","search","search"]
        //[[],["a"],["ab"],["a"],["a."],["ab"],[".a"],[".b"],["ab."],["."],[".."]]
        //[null,null,null,true,true,true,false,true,false,true,true]
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("a");
        wordDictionary.addWord("ab");
        Assert.assertTrue(wordDictionary.search("a")); // return False
        Assert.assertTrue(wordDictionary.search("a.")); // return True
        Assert.assertTrue(wordDictionary.search("ab")); // return True
        Assert.assertFalse(wordDictionary.search(".a")); // return True
        Assert.assertTrue(wordDictionary.search(".b")); // return True
        Assert.assertFalse(wordDictionary.search("ab.")); // return True
        Assert.assertTrue(wordDictionary.search(".")); // return True
        Assert.assertTrue(wordDictionary.search("..")); // return True

    }
}