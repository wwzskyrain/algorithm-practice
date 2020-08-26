package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-08-26 23:00
 */
public class LetterCombinationsOfAPhoneNumber {

    @LetCodeCommit(title = "17. Letter Combinations of a Phone Number", diff = "m",
            selfRemark = "没什么好说的，就是dfs而已")
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        String[] chars = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> words = new LinkedList<>();
        dfs(digits, 0, chars, new char[digits.length()], words);
        return words;
    }

    public void dfs(String digits, int i, String[] chars, char[] word, List<String> words) {

        if (i < digits.length()) {
            char c = digits.charAt(i);
            String letters = chars[c - '2'];
            for (int j = 0; j < letters.length(); j++) {
                word[i] = letters.charAt(j);
                dfs(digits, i + 1, chars, word, words);
            }

        } else {
            words.add(String.valueOf(word));
        }
    }

    @Test
    public void test_solution() {
        List<String> strings = letterCombinations("23");
        System.out.println(strings);
    }

}
