package study.erik.algorithm.leetcode.backtracking;

import org.junit.Test;

import java.util.*;

/**
 * @author erik.wang
 * @date 2019/06/02
 **/
public class PhoneNumber {

    /**
     * Letter Combinations of a Phone Number
     * 解法：深度搜索多叉树。也是子集树
     * 战绩：faster than 21.37%， less than 99.36% 不理想的很
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {

        if (digits.length() == 0) {
            return new ArrayList<>();
        }

        Map<Character, List<Character>> map = new HashMap<>();

        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('q', 'p', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        List<String> letterCombination = new ArrayList<>();
        findLetterCombinations(map, letterCombination, digits, new ArrayList<>());
        return letterCombination;

    }

    public void findLetterCombinations(Map<Character, List<Character>> map, List<String> letterCombination, String digits, List<Character> currentStr) {

        if (currentStr.size() == digits.length()) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < currentStr.size(); i++) {
                stringBuilder.append(currentStr.get(i));
            }
            letterCombination.add(stringBuilder.toString());
            return;
        }

        int currentLength = currentStr.size();

        char letter = digits.charAt(currentLength);

        List<Character> characters = map.get(Character.valueOf(letter));

        for (Character character : characters) {

            currentStr.add(currentLength, character);

            findLetterCombinations(map, letterCombination, digits, currentStr);

            currentStr.remove(currentLength);
        }

    }

    @Test
    public void test() {
        List<String> letterCombinations = letterCombinations("7");
        System.out.println(letterCombinations);
    }


}
