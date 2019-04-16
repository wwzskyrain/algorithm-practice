package study.erik.algorithm.leetcode.tree;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @date 2019/04/10
 **/
public class BSF {


    /**
     * title = Word Ladder
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        DfsToFindLadder(endWord, new HashSet<>(wordList), new LinkedList<String>(Arrays.asList(beginWord)));
        return ladderMinLength == Integer.MAX_VALUE ? 0 : ladderMinLength;
    }

    /**
     * 这是讨论区的一个解法，很有针对性和思想性，但是其套路不属于"递归思想"；
     * 解法解析：以beginWord和endWord为出发点，每次加入其所有可变字符串使其相向而行
     * 精彩点：1.相向而行得到的结果可以保证是最优值，因为每次壮大都是基于整个解空间的
     * 2.beginSet和endSet按体积大小进行交换。
     * 缺点：不能够求出最优值的最优解
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthI(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<String>(), endSet = new HashSet<String>();

        int len = 1;

        HashSet<String> visited = new HashSet<String>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {

            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> temp = new HashSet<String>();

            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordList.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = temp;
            len++;
        }

        return 0;

    }

    /**
     * 这是官方给出的解答
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthII(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<String, ArrayList<String>>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        ArrayList<String> transformations =
                                allComboDict.getOrDefault(newWord, new ArrayList<String>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        // Queue for BFS
        Queue<Pair<String, Integer>> Q = new LinkedList<Pair<String, Integer>>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }


    public static void main(String[] args) {

        BSF bsf = new BSF();
        String beginWord = "qa";
        String endWord = "sq";
        List<String> wordList = Arrays.asList("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye");
        int length = bsf.ladderLength(beginWord, endWord, wordList);

        System.out.println(length);

    }

    private int ladderMinLength = Integer.MAX_VALUE;

    /**
     * 这个函数应该是有效的，就是可能会超时，因为解空间太大了。
     *
     * @param endWord
     * @param wordSet
     * @param stack
     */
    private void DfsToFindLadder(String endWord, Set<String> wordSet, Deque<String> stack) {

        if (wordSet.isEmpty()) {
            return;
        }

        String peekWord = stack.peek();

        List<String> targetWords = wordSet.stream().filter(word -> check(word, peekWord)).collect(Collectors.toList());

        for (int i = 0; i < targetWords.size(); i++) {
            String targetWord = targetWords.get(i);
            if (stack.contains(targetWord)) {
                continue;
            }

            stack.push(targetWord);
            wordSet.remove(targetWord);
            if (targetWord.equals(endWord)) {
                ladderMinLength = Math.min(ladderMinLength, stack.size());
            }
            DfsToFindLadder(endWord, wordSet, stack);

            stack.pop();
            wordSet.add(targetWord);
        }
    }

    private boolean check(String word, String targetWord) {
        if (word.length() != targetWord.length()) {
            return false;
        }

        int length = word.length();
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (word.charAt(i) != targetWord.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

}
