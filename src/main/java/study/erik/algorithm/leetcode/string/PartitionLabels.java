/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.string;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yueyi
 * @version : PartitionLabels.java, v 0.1 2022年03月21日 9:17 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PartitionLabels {

    @LetCodeCommit(title = "763. Partition Labels")
    public List<Integer> partitionLabels(String s) {
        if (s.length() == 0) {
            return Collections.singletonList(0);
        }
        int[] lastIndex = new int[26];
        //只需要关注lastIndex
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        List<Integer> result = new ArrayList<>();
        int curStartIndex = 0;
        int curEndIndex = 0;
        for (int i = 0; i <= s.length(); i++) {
            // 这就算找到了一个区间了.
            if (i > curEndIndex) {
                result.add(curEndIndex - curStartIndex + 1);
                if (i == s.length()) {
                    break;
                }
                curEndIndex = curStartIndex = i;
            }
            int maxIndex = lastIndex[s.charAt(i) - 'a'];
            curEndIndex = Math.max(curEndIndex, maxIndex);
        }
        return result;
    }

    @Parameter
    public String        s;
    @Parameter(1)
    public List<Integer> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ababcbacadefegdehijhklij", Arrays.asList(9, 7, 8)},
                {"eccbbbbdec", Arrays.asList(10)},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, partitionLabels(s));
    }
}