/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.series.palindromic;

import org.junit.Assert;
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
 * @version : PalindromePartitioning.java, v 0.1 2021年08月07日 9:33 下午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PalindromePartitioning {

    @LetCodeCommit(title = "Palindrome Partitioning")
    public List<List<String>> partition(String s) {
        return resolve(s);
    }

    public List<List<String>> resolve(String s) {
        List<List<String>> partitions = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int low = 0, high = i;
            while (low <= high && s.charAt(low) == s.charAt(high)) {
                low++;
                high--;
            }
            if (low > high) {
                List<List<String>> subPartitions = resolve(s.substring(i + 1));
                if (subPartitions.size() == 0) {
                    List<String> partition = new ArrayList<>();
                    partition.add(s.substring(0, i + 1));
                    partitions.add(partition);
                } else {
                    for (List<String> subPartition : subPartitions) {
                        List<String> partition = new ArrayList<>();
                        partition.add(s.substring(0, i + 1));
                        partition.addAll(subPartition);
                        partitions.add(partition);
                    }
                }
            }
        }
        return partitions;
    }

    @Parameter
    public String             s;
    @Parameter(1)
    public List<List<String>> expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"aab", Arrays.asList(Arrays.asList("a", "a", "b"), Arrays.asList("aa", "b"))},
                {"a", Arrays.asList(Arrays.asList("a"))},
        };
    }

    @Test
    public void test_expect() {
        Assert.assertEquals(expect, partition(s));
    }

}