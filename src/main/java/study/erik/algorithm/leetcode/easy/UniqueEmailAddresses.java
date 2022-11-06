/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : UniqueEmailAddresses.java, v 0.1 2022年11月05日 15:15 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class UniqueEmailAddresses {

    @LetCodeCommit(title = "929. Unique Email Addresses")
    public int numUniqueEmails(String[] emails) {
        Set<String> normalized = new HashSet<>();
        for (String email : emails) {
            int l = email.length();
            int atIndex = l - 1;
            while (atIndex >= 0 && email.charAt(atIndex) != '@') {
                atIndex--;
            }

            int addIndex = 0;
            while (addIndex < l && addIndex < atIndex && email.charAt(addIndex) != '+') {
                addIndex++;
            }
            String local = email.substring(0, addIndex);
            normalized.add(local.replace(".", "") + email.substring(atIndex));
        }
        return normalized.size();
    }

    @Parameter
    public String[] emails;
    @Parameter(1)
    public int      expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {new String[] {"test.email+alex@leetcode.com", "test.email@leetcode.com"}, 1},
                {new String[] {"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"}, 3},
        };
    }

    @Test
    public void test_1() {
        String[] s = new String[] {"a@leetcode.com", "b@leetcode.com", "c@leetcode.com"};
        Assert.assertEquals(expect, numUniqueEmails(emails));
    }
}