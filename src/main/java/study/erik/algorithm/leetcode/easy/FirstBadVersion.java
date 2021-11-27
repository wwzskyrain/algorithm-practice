/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FirstBadVersion.java, v 0.1 2021年11月26日 9:12 上午 yueyi Exp $
 */
public class FirstBadVersion {

    @LetCodeCommit(title = "278. First Bad Version")
    public int firstBadVersion(int n) {
        int l = 1;
        int h = n;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (!isBadVersion(m)) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return l;
    }

    private boolean isBadVersion(int version) {
        return 4 == version;
    }

    @Test
    public void test_() {
        Assert.assertEquals(4, firstBadVersion(5));
    }

}