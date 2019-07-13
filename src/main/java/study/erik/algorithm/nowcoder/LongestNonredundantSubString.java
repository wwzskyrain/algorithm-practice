package study.erik.algorithm.nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: erik.wang
 * @Date: 2019-07-13
 */
public class LongestNonredundantSubString {

    /**
     * 题意分解：求最长的无重复字符的子字符串，就是求两个相邻重复字符之间的长度的最值了。
     * 解法：用一个int[26]记录所有字符上一次出现的索引，则在遍历字符串时，当前字符的
     * 下标-该字符上次出现的下标，就是该重复出现字符两次出现所夹的子字符串的长度了。
     * @param targetString
     * @return
     */
    public int longestNonredundantSubStringI(String targetString) {

        if (targetString == null || targetString.length() == 0) {
            return 0;
        }

        int[] dict = new int[26];

        int length = targetString.length();
        int maxLength = 0;


        for (int i = 0; i < length; i++) {

            int toUpdateIndex = targetString.charAt(i) - 'a';
            int startIndex = dict[toUpdateIndex];
            if (startIndex > 0) {
                maxLength = Math.max(maxLength, i + 1 - startIndex);
            }
            dict[toUpdateIndex] = i + 1; //这里是i+1，不是i；
//          这样处理可以省去对dict的初始化，不然就要将dic[..]初始化为-1，以区分第一字符的下标为0的情况。
        }
        return maxLength;
    }


    @Test
    public void test_longestNonredundantSubStringI() {
        Assert.assertEquals(3, longestNonredundantSubStringI("abcabcabc"));
    }

    /**
     * 连续子字符串的问题，使用动态规划最合适不过；所以这里便是使用dp的解题思路
     * 使用dp解连续子字符串问题的思路我已经熟知，不同的是对子字符串的约束。
     * 这里的约束是无重复字符。可以使用set，但是这里用起来就显得太重了。
     * 这里用上一种解法的int[]数组。其中的值是字符上次出现的下标。
     *
     * @param targetString
     * @return
     */
    public int longestNonredundantSubStringII(String targetString) {


        int[] dictionaries = new int[26];

        int length = targetString.length();
        int maxLength = 0;
        int preLength = 0;

        for (int i = 0; i < length; i++) {

            int charIndex = targetString.charAt(i) - 'a'; //'a'字符的下标存放在数组的0位置，'b'字符的下标存放在数组1位置。。。
            int lastAppearIndex = dictionaries[charIndex];

            if (lastAppearIndex > 0 && i - lastAppearIndex < preLength) {
                preLength = i - lastAppearIndex;
            } else {
                preLength++;
                maxLength = Math.max(maxLength, preLength);
            }
            dictionaries[charIndex] = i + 1; //这里保存字符在targetString的index+1，以区分dictionaries中元素被初始化为0情况。
        }
        return maxLength;
    }

}
