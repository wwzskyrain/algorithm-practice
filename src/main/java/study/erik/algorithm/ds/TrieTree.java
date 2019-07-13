package study.erik.algorithm.ds;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class TrieNode {
    boolean isLeaf; //这个属性很重要，表示到该节点时，锁搜索的路径是一个'词语'。
    Map<Character, TrieNode> content = new HashMap<Character, TrieNode>();

    // Initialize your data structure here.
    public TrieNode() {
    }
}

/**
 * 1.这是数据结构之-字典树-
 * 2.当前的函数 inert、search、startWith
 * <p>
 * 问题：
 * 1.size()方法如何实现
 * 2.delete()方法如何实现
 */
public class TrieTree {

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        TrieNode node = root;
        TrieNode tempNode = null;
        ;
        for (int i = 0, len = word.length(); i < len; i++) {
            Character c = word.charAt(i);
            tempNode = node.content.get(c);
            if (tempNode == null) {
                tempNode = new TrieNode();
                node.content.put(c, tempNode);
            }
            node = tempNode;
        }
        node.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        TrieNode node = root;
        TrieNode tempNode = null;
        for (int i = 0, len = word.length(); i < len; i++) {
            Character c = word.charAt(i);
            tempNode = node.content.get(c);
            if (tempNode == null) {
                return false;
            }
            node = tempNode;
        }
        return node.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        TrieNode node = root;
        TrieNode tempNode = null;
        for (int i = 0, len = prefix.length(); i < len; i++) {
            Character c = prefix.charAt(i);
            tempNode = node.content.get(c);
            if (tempNode == null) {
                return false;
            }
            node = tempNode;
        }
        return true;
    }

    /**
     * delete word only if word is in trie.
     * @auth erik
     * @param word
     * @return true, delete success; false, word absent.
     */
    public boolean delete(String word) {

        if (word == null || word.length() == 0) {
            return false;
        }

        List<TrieNode> trace = new LinkedList<>();
        TrieNode node = root;
        TrieNode tempNode = null;
        for (int i = 0, len = word.length(); i < len; i++) {
            Character c = word.charAt(i);
            tempNode = node.content.get(c);
            if (tempNode == null) {
                return false;
            }
            trace.add(0, tempNode);
            node = tempNode;
        }

        if (node.isLeaf) {

            if (!node.content.isEmpty()) {
                return true;
            }

            TrieNode pre = trace.remove(0);
            for (TrieNode trieNode : trace) {

                trieNode.content.remove(pre);
                if (trieNode.isLeaf) {
                    break;
                }
                if (!trieNode.content.isEmpty()) {
                    break;
                }
                pre = trieNode;
            }
        }
        return false;
    }


    @Test
    public void test_insert_and_search() {

        TrieTree trieTree = new TrieTree();

        String one = "one";
        String one1 = "one1";
        trieTree.insert(one);
        trieTree.insert(one1);

        Assert.assertEquals(true, trieTree.search(one));

    }


    @Test
    public void test_start_with() {

        TrieTree trieTree = new TrieTree();

        trieTree.insert("123456789");

        Assert.assertTrue(trieTree.startsWith("1"));
        Assert.assertTrue(trieTree.startsWith("12"));
        Assert.assertTrue(trieTree.startsWith("123"));
        Assert.assertTrue(trieTree.startsWith("1234"));
        Assert.assertTrue(!trieTree.startsWith("12344"));

    }

}



