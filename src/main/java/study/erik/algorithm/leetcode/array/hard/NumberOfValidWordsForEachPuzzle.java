/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.hard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yueyi
 * @version : NumberOfValidWordsForEachPuzzle.java, v 0.1 2023年01月07日 19:21 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class NumberOfValidWordsForEachPuzzle {

    @LetCodeCommit(title = "1178. Number of Valid Words for Each Puzzle")
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        return solutionWithBitMask(words, puzzles);
    }

    /**
     * 核心点是，puzzle的bitMask的子集最多只会匹配word一次，且这一次符合题意。
     *
     * @param words   words
     * @param puzzles puzzles
     * @return ret
     */
    public List<Integer> solutionWithBitMask(String[] words, String[] puzzles) {
        Map<Integer, Integer> bitMaskCounter = new HashMap<>();
        for (String word : words) {
            Integer bitMask = getBitMask(word);
            bitMaskCounter.put(bitMask, bitMaskCounter.getOrDefault(bitMask, 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (String puzzle : puzzles) {
            int firstBitMask = 1 << (puzzle.charAt(0) - 'a');
            int count = bitMaskCounter.getOrDefault(firstBitMask, 0);
            int bitMask = getBitMask(puzzle.substring(1));
            // 除charAt(0)之外
            for (int mask = bitMask; mask > 0; mask = (mask - 1) & bitMask) {
                count += bitMaskCounter.getOrDefault(firstBitMask | mask, 0);
            }
            result.add(count);
        }
        return result;
    }

    private int getBitMask(String word) {
        int mask = 0;
        for (char c : word.toCharArray()) {
            int bit = c - 'a';
            mask |= (1 << bit);
        }
        return mask;
    }

    public List<Integer> solutionWithTrie(String[] words, String[] puzzles) {
        int SIZE = 26; // total character size
        ArrayList<Integer[]> trie = new ArrayList<>(); // we use list to mimic the trie tree
        // 没有用指针结构，而用了引用，对子节点的引用，以子节点在trie这个list中的下表i来引用子节点。当然每个子节点node都是一个26个元素的int数组。
        trie.add(Collections.nCopies(SIZE, 0).toArray(new Integer[0]));
        ArrayList<Integer> count = new ArrayList<>();// 表示以node为结尾的单词的个数，麻蛋，原来与引文注释一个意思，原来因为注释是这个意思，哎，我的英文理解能力真差。
        count.add(0);// the number of words ending at node i
        for (String word : words) {
            // sort and remove duplicates
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            StringBuilder sb = new StringBuilder();
            sb.append(chars[0]);
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] != chars[i - 1]) {
                    sb.append(chars[i]);
                }
            }
            if (sb.length() <= 7) { // longer words are never valid
                // insert into trie
                int node = 0;
                for (char letter : sb.toString().toCharArray()) {
                    int i = letter - 'a';
                    if (trie.get(node)[i] == 0) { // push empty node
                        trie.add(Collections.nCopies(SIZE, 0).toArray(new Integer[0]));
                        count.add(0);
                        trie.get(node)[i] = trie.size() - 1;
                    }
                    node = trie.get(node)[i];
                }
                count.set(node, count.get(node) + 1);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (String puzzle : puzzles) {
            result.add(dfs(0, false, puzzle, trie, count));
        }
        return result;
    }

    // search for valid words
    int dfs(int node, boolean hasFirst, String puzzle, List<Integer[]> trie, List<Integer> cnt) {
        // 这里是对树的搜索？有点不对头，是对树按照puzzle的路子来找全路径——word的letter构成的路径，当然这里的word是
        // 被删除重复letter且排序之后的word。
        // 所以这里是对puzzle的dfs的检索吗？恐怕也不是，哦，恐怕是的，是图的dfs的检索，因为每个在puzzle中的letter都
        // 可以在下一次dfs时连接到，所以这里是puzzle的全letter的一个完全图，且不会有本letter到自身的边 ——（因为puzzle中没有重复字符，即使有也可以简化掉）
        // 这竟然就是图的dfs搜索，我tm的都感到陌生了。
        int total = hasFirst ? cnt.get(node) : 0;
        for (char letter : puzzle.toCharArray()) {
            int i = letter - 'a';
            if (trie.get(node)[i] > 0) {
                // 为什么要用letter == puzzle.charAt(0)来判断是否包含第一个元素呢？
                // letter就是puzzle的字符呀，letter还不知自己是不是puzzle中的第一个吗？
                // 哈哈，第一个进入for循环的letter肯定是at(0)的。但是能进这里if的，却不一定哟
                // 这里就看第一个letter在trie树的当前node层是否存在咯，如果第一个letter在本次trie中不存在
                // 那么能进到这里if的letter，其肯定不是第一个letter了（tm这不是废话了）.
                total += dfs(trie.get(node)[i], hasFirst || (letter == puzzle.charAt(0)), puzzle, trie, cnt);
            }
        }
        return total;
    }

    @Parameter
    public String[] words;
    @Parameter(1)
    public String[] puzzles;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"aaaa", "asas", "able", "ability", "actt", "actor", "access"},
                        new String[] {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"}},
                {new String[] {"apple", "pleas", "please"}, new String[] {"aelwxyz", "aelpxyz", "aelpsxy", "saelpxy", "xaelpsy"}}
        };
    }

    @Test
    public void test_() {
        System.out.println(findNumOfValidWords(words, puzzles));
    }

}