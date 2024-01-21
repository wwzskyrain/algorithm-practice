package study.erik.algorithm.leetcode.contest.weekly380;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2024/1/14 11:12
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main3 {

    @LetCodeCommit(title = "")
    public long findMaximumNumber(long k, int x) {
        long n = 1;
        Map<Long, Long> memo = new HashMap<>();
        while (true) {
            Long t = getNumForSetBit(n, x, memo);
            k -= t;
            if (k < 0) {
                return n - 1;
            }
            n++;
        }
    }

    public Long getNumForSetBit(Long n, int x, Map<Long, Long> memo) {
        Long v = memo.get(n);
        if (v != null) {
            return v;
        }
        Long t = 0L;
        boolean first = true;
        while (n > 0) {
            if (first) {
                n = n >> (x - 1);
                first = false;
            } else {
                n = n >> x;
            }
            if ((n & 1) == 1) {
                t++;
            }
        }
        v = t;
        memo.put(n, v);
        return v;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {3, 3278539330613L, 5},
                {6, 9, 1},
                {9, 7, 2},
        });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public long k;
    @Parameterized.Parameter(2)
    public int x;

    @Test
    public void test() {
        Assert.assertEquals(expect, findMaximumNumber(k, x));
    }

}
