package study.erik.algorithm.leetcode.string.longest;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-21 19:10
 */
public class LongestCommonPrefix {

    @LetCodeCommit(title = "14. Longest Common Prefix", time = 44, space = 33,
            selfRemark = "在做longest专题时，做的")
    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;

        for (String str : strs) {
            minLength = Math.min(str.length(), minLength);
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < minLength) {
            char c = strs[0].charAt(i);
            int j;
            for (j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return sb.toString();
                }
            }
            sb.append(c);
            i++;
        }
        return sb.toString();
    }

    @Test
    public void test_solution_1() {
        Assert.assertEquals("fl", longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    @Test
    public void test_solution_2() {
        Assert.assertEquals("", longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    public void test_solution_3() {
        Assert.assertEquals("", longestCommonPrefix(new String[]{}));
    }


}
