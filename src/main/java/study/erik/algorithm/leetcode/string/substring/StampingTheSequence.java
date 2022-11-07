/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string.substring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yueyi
 * @version : StampingTheSequence.java, v 0.1 2022年11月07日 22:29 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class StampingTheSequence {

    @LetCodeCommit(title = "936. Stamping The Sequence")
    public int[] movesToStamp(String stamp, String target) {
        int startCount = 0;
        char[] S = stamp.toCharArray();
        char[] T = target.toCharArray();
        boolean[] visited = new boolean[T.length];
        List<Integer> resList = new ArrayList<>();
        while (startCount < target.length()) {
            boolean onceReplace = false;
            for (int i = 0; i <= T.length - S.length; i++) {
                if (!visited[i] && canReplace(T, S, i)) {
                    startCount += doReplace(T, S, i);
                    onceReplace = true;
                    resList.add(i);
                    visited[i] = true;
                }
            }
            if (!onceReplace) {
                // 一圈都没有可以替换的，那就卡住了
                return new int[0];
            }
        }
        int resSize = resList.size();
        int[] res = new int[resSize];

        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(resSize - i - 1);
        }
        return res;
    }

    private boolean canReplace(char[] target, char[] stamp, int i) {
        for (int j = 0; j < stamp.length; j++) {
            if (i + j >= target.length) {
                return false;
            }
            if (target[i + j] != '*' && target[i + j] != stamp[j]) {
                return false;

            }
        }
        return true;
    }

    private int doReplace(char[] target, char[] stamp, int i) {
        int starCount = 0;
        for (int j = 0; j < stamp.length; j++) {
            if (target[i + j] != '*') {
                target[i + j] = '*';
                starCount++;
            }
        }
        return starCount;
    }

    @Parameter
    public String stamp;
    @Parameter(1)
    public String target;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"mda", "mdadddaaaa"},
                {"abc", "ababc"},
                {"abca", "aabcaca"},
        };
    }

    @Test
    public void test_() {
        System.out.println(Arrays.toString(movesToStamp(stamp, target)));
    }

}