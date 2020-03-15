package study.erik.algorithm.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author erik.wang
 * @date 2020-03-13 15:02
 * @description
 */
public class DecodeWaysI {

    public int numDecodings(String s) {
        return solution(s);
    }

    /*
    这个问题本来很简单，但是却落入了检查参数的泥潭。而这个参数，又很类似模式匹配。
    所以，算了不做了。
     */
    public int solution(String s) {
        if (s == null || s.length() == 0 || s.startsWith("0")) {
            return 0;
        }
        boolean foundZero = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (foundZero) {
                    return 0;
                } else {
                    foundZero = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) != '0') {
                sb.append(s.charAt(i - 1));
            } else {
                sb.append('A');
            }
        }
        return dfs(sb.toString());
    }

    public int dfs(String s) {
        if (s.length() == 1) {
            return 1;
        }
        Set<String> w = new HashSet<>(Arrays.asList(
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "23", "24", "25", "26"));
        if (s.length() == 2) {
            if (w.contains(s.substring(0, 2))) {
                return 2;
            } else {
                return 1;
            }
        }

        if (w.contains(s.substring(0, 2))) {
            return dfs(s.substring(1)) + dfs(s.substring(2));
        } else if (s.startsWith("10") || s.startsWith("20")) {
            return dfs(s.substring(2));
        } else {
            return dfs(s.substring(1));
        }
    }

    @Test
    public void test_solution() {
        Assert.assertEquals(1, solution("110"));
//        Assert.assertEquals(0, solution1("00"));
//        Assert.assertEquals(1, solution1("101"));
//        Assert.assertEquals(1, solution1("10"));
//        Assert.assertEquals(2, solution1("12"));
//        Assert.assertEquals(3, solution1("226"));
    }
}
