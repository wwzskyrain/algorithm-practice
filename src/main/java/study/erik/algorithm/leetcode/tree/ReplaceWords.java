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

import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : ReplaceWords.java, v 0.1 2022年04月05日 5:57 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ReplaceWords {

    @LetCodeCommit(title = "648. Replace Words",
            diff = "m",
            selfRemark = "字典树，手写，成绩不错呢。"
                    + "注意这个字典的节点里有个isWord属性，"
                    + "而不用有字符值这个属性")
    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = buildTrieTree(dictionary);
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(getShortestWord(word, root)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String getShortestWord(String word, TrieNode root) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (root.children == null || root.children[c - 'a'] == null) {
                break;
            }
            sb.append(c);
            root = root.children[c - 'a'];
            if (root.isWord) {
                return sb.toString();
            }
        }
        return word;
    }

    public TrieNode buildTrieTree(List<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode curNode = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curNode.children == null) {
                    curNode.children = new TrieNode[26];
                }
                if (curNode.children[c - 'a'] == null) {
                    curNode.children[c - 'a'] = new TrieNode();
                }
                curNode = curNode.children[c - 'a'];
            }
            curNode.isWord = true;
        }
        return root;
    }

    public static class TrieNode {
        private TrieNode[] children;
        private boolean    isWord;
    }

    @Parameter
    public List<String> dictionary;
    @Parameter(1)
    public String       sentence;
    @Parameter(2)
    public String       expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {Arrays.asList("a", "aa", "aaa", "aaaa"), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa", "a a a a a a a a bbb baba a"},
                {Arrays.asList("cat", "bat", "rat"), "the cattle was rattled by the battery", "the cat was rat by the bat"},
                {Arrays.asList("a", "b", "c"), "aadsfasf absbs bbab cadsfafs", "a a b c"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, replaceWords(dictionary, sentence));
    }

}