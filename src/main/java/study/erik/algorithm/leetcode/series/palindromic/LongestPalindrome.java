/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.palindromic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : LongestPalindrome.java, v 0.1 2022年02月20日 5:48 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class LongestPalindrome {

    @LetCodeCommit(title = "409. Longest Palindrome",
            selfRemark = "easy",
            tag = "easy")
    public int longestPalindrome(String s) {
        int[] map = new int[52];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c > 'Z') {
                map[c - 'a' + 26]++;
            } else {
                map[c - 'A']++;
            }
        }
        int ret = 0;
        boolean hasOdd = false;
        for (int count : map) {
            if (count % 2 == 0) {
                ret += count;
            } else {
                hasOdd = true;
                ret += count - 1;
            }
        }
        return ret + (hasOdd ? 1 : 0);
    }

    @Parameter
    public String s;
    @Parameter(1)
    public int    expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth",
                        983},
                {"abccccdd", 7},
                {"a", 1},
                {"bb", 2},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, longestPalindrome(s));
    }

}