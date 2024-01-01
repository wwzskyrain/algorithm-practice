package study.erik.algorithm.leetcode.array.easy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/12/29 23:12
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Buy_Two_Chocolates {

    @LetCodeCommit(title = "2706. Buy Two Chocolates")
    public int buyChoco(int[] prices, int money) {
        List<Integer> l = new LinkedList<>();
        for (int i : prices) {
            int s = l.size();
            switch (s) {
                case 0:
                    l.add(i);
                    break;
                case 1:
                    if (l.get(0) > i) {
                        l.add(0, i);
                    } else {
                        l.add(1, i);
                    }
                    break;
                case 2:
                    if (l.get(0) >= i) {
                        l.set(1, l.get(0));
                        l.set(0, i);
                    } else if (l.get(1) >= i) {
                        l.set(1, i);
                    }
            }
        }
        int s = l.get(0) + l.get(1);
        return s > money ? money : money - s;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {47, ArrayUtils.buildArray("[90,29,6,74]"), 82},
                {0, ArrayUtils.buildArray("[1,2,2]"), 3},
                {3, ArrayUtils.buildArray("[3,2,3]"), 3},

        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public int[] prices;
    @Parameterized.Parameter(2)
    public int money;

    @Test
    public void test() {
        Assert.assertEquals(expect, buyChoco(prices, money));
    }

}
