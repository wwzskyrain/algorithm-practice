/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.bit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : PowerOfFour.java, v 0.1 2021年05月31日 6:52 上午 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class PowerOfFour {

    @LetCodeCommit(title = "Power of Four",
            selfRemark = "题目的有意思之处在不用循环。另外，这个题目可以穷举吗？不可以，如果用穷举，"
                    + "那么就必须用循环，所以不能用。当然，循环移位也是循环，也不好。"
                    + "然后就是位运算了。"
                    + "位运算的魔力在于其可以表示集合，而且按需索取。"
                    + "这里用了两个技巧，缺一不可。一是位运算算2的幂次方，二是校验1出现在奇数位。"
                    + "另外还有一个解法是数学解法，4的幂次方对2取模为1，对3取模为2。我们不建议走这条路")
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        int mark = 0x2AAAAAAA;
        return (n & (n - 1)) == 0 && (n & mark) == 0;
    }

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {16, true}, {5, false}, {1, true},
                {2, false}
        };
    }

    @Parameter
    public Integer n;
    @Parameter(1)
    public Boolean expect;

    @Test
    public void test_() {
        Assert.assertEquals(expect, isPowerOfFour(n));
    }
}
