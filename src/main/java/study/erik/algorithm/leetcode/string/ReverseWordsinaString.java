/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : ReverseWordsinaString.java, v 0.1 2021年12月10日 12:22 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class ReverseWordsinaString {

    @LetCodeCommit(title = "151. Reverse Words in a String")
    public String reverseWords(String s) {

        int i = 0;
        int length = s.length();
        List<String> allWorld = new LinkedList<>();
        while (i < length) {
            while (i < length && s.charAt(i) == ' ') {
                i++;
            }
            int wordStartIndex = i;
            if (i == length) {
                break;
            }
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            allWorld.add(0, s.substring(wordStartIndex, i));
        }
        return allWorld.stream().collect(Collectors.joining(" "));
    }

    @Parameter
    public String s;
    @Parameter(1)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"the sky is blue", "blue is sky the"},
                {"  hello world  ", "world hello"},
                {"a good   example", "example good a"},
                {"  Bob    Loves  Alice   ", "Alice Loves Bob"},
                {"  Bob    Loves  Alice   ", "Alice Loves Bob"},
                {"Alice does not even like bob", "bob like even not does Alice"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, reverseWords(s));
    }

}