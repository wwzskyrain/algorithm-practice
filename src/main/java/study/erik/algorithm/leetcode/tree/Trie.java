package study.erik.algorithm.leetcode.tree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author erik.wang
 * @date 2020-03-09 17:56
 * @description
 */
public class Trie {

    private TrieNode root = new TrieNode();

    private class TrieNode {
        private boolean isWord;
        private Map<Character, TrieNode> map = new HashMap<>();

        public Map<Character, TrieNode> getMap() {
            return this.map;
        }

        public void word() {
            this.isWord = true;
        }

        public boolean isWord() {
            return isWord;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {

        TrieNode currentTrieNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            TrieNode childNode = currentTrieNode.getMap().get(c);
            if (childNode == null) {
                childNode = new TrieNode();
                currentTrieNode.getMap().put(c, childNode);
            }
            currentTrieNode = childNode;
            if (i == word.length() - 1) {
                currentTrieNode.word();
            }
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode currentNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            TrieNode childNode = currentNode.getMap().get(c);
            if (childNode == null) {
                return false;
            }
            currentNode = childNode;
            if (i == word.length() - 1) {
                return currentNode.isWord();
            }
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode currentNode = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            TrieNode childNode = currentNode.getMap().get(c);
            if (childNode == null) {
                return false;
            }
            currentNode = childNode;
        }
        return true;
    }

    @Test
    public void test() {
        Trie trie = new Trie();

        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }

}
