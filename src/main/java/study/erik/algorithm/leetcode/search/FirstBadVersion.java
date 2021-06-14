/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.search;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : FirstBadVersion.java, v 0.1 2021年06月13日 7:54 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class FirstBadVersion {

    public boolean isBadVersion(int version) {
        boolean[] result = {false, false, false, true, true};
        return result[version - 1];
    }

    @LetCodeCommit(title = "First Bad Version")
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    @Parameter
    public int n;
    @Parameter(1)
    public int bad;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {5, 4},
                //{1, 1}
        };
    }

    @Test
    public void test() {
        Assert.assertEquals(bad, firstBadVersion(n));
    }
}