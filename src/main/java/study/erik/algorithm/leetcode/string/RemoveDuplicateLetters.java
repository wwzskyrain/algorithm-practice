package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : RemoveDuplicateLetters.java, v 0.1 2020/10/12 12:42 yueyi Exp $
 */
public class RemoveDuplicateLetters {

    @LetCodeCommit(title = "316. Remove Duplicate Letters")
    public String removeDuplicateLetters(String s) {
        return null;
    }

    @Test
    public void test_solution_1() {
        String s = "bcabc";
        String e = "abc";
        Assert.assertEquals(e, removeDuplicateLetters(s));
    }

    @Test
    public void test_solution_2() {
        String s = "cbacdcbc";
        String e = "acdb";
        Assert.assertEquals(e, removeDuplicateLetters(s));
    }




}
