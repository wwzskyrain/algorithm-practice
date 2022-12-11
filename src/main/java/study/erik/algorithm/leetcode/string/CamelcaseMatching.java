/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : CamelcaseMatching.java, v 0.1 2022年12月11日 15:07 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class CamelcaseMatching {

    @LetCodeCommit(title = "1023. Camelcase Matching")
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        return Arrays.stream(queries).map(s -> check(s, pattern)).collect(Collectors.toList());
    }

    private Boolean check(String s, String p) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (j < p.length()) {
                char pc = p.charAt(j);
                if (pc == c) {
                    j++;
                    continue;
                }
            }
            if (c >= 'A' && c <= 'Z') {
                return false;
            }
        }
        return j == p.length();
    }

    @Parameter
    public String[] queries;
    @Parameter(1)
    public String   pattern;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FB"},
                {new String[] {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FoBa"},
        };
    }

    @Test
    public void test_() {
        System.out.println(camelMatch(queries, pattern));
    }

}