package study.erik.algorithm.leetcode.string.palindromic;

import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-08-30 15:11
 */
public class LongestPalindromicSubString {


    public String longestPalindromicSubString(String string) {

        if (string.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder().append(string.charAt(0));
        for (int i = 1; i < string.length(); i++) {
            sb.append("#").append(string.charAt(i));
        }
        string = sb.toString();
        int[] len = new int[string.length()];
        int maxLenIndex = 0;
        for (int i = 0; i < string.length(); i++) {
            int l, r;
//          先判断在加
            while ((l = i - len[i] - 1) >= 0 &&
                    (r = i + len[i] + 1) < string.length() &&
                    string.charAt(l) == string.charAt(r)) {
                len[i]++;
            }
            if (len[i] > len[maxLenIndex]) {
                maxLenIndex = i;
            }
        }

        String longestSubString = string.substring(maxLenIndex - len[maxLenIndex], maxLenIndex + len[maxLenIndex] + 1).replaceAll("#", "");
        return longestSubString;
    }

    @Test
    public void test_solution_1() {
        String subString = longestPalindromicSubString("123242329");
        System.out.println(subString);
    }

    @Test
    public void test_solution_2() {

        String subString = longestPalindromicSubString("123242321");
        System.out.println(subString);
    }


    @Test
    public void test_solution() {

    }


}
