/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.tree.hard;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yueyi
 * @version : MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits.java, v 0.1 2023年05月16日 07:25 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {

    @LetCodeCommit(title = "1505. Minimum Possible Integer After at Most K Adjacent Swaps On Digits",
            diff = "h",
            selfRemark = "这是一个bit的题目，属于高级数据结构了。"
                    + "我们之前学过一次，这次再次学习，应该能记得清楚了"
                    + "bit数还有个名字叫做 Fenwick Tree")
    public String minInteger(String num, int k) {
        List<Integer>[] arr = new List[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new LinkedList<>();
        }
        char[] numChars = num.toCharArray();
        for (int i = 0; i < numChars.length; i++) {
            char numChar = numChars[i];
            arr[numChar - '0'].add(i);
        }
        String ans = "";
        BIT bit = new BIT(num.length());
        boolean[] used = new boolean[num.length()];
        while (k > 0 && ans.length() < num.length()) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].isEmpty()) {
                    continue;
                }

                Integer index = arr[i].get(0);
                int hasSwapped = bit.preSum(index);
                int cost = index - hasSwapped;
                if (cost > k) {
                    //下一个数字，而不是在当前数字的下一个index
                    continue;
                }
                k -= cost;
                bit.add(index, 1);
                char digital = (char) ('0' + i);
                ans += digital;
                arr[i].remove(0);
                used[index] = true;
                break;//这一圈1-9结束了，再来一圈
            }
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                ans += numChars[i];
            }
        }
        return ans;
    }

    public static class BIT {
        int   n;
        int[] s;

        public BIT(int n) {
            this.n = n + 1;
            s = new int[this.n];
        }

        public void add(int i, int v) {
            int realI = i + 1;
            while (realI < n) {
                s[realI] += v;
                realI += lowBit(realI);
            }
        }

        public int preSum(int i) {
            int realI = i + 1;
            int sum = 0;
            while (realI > 0) {
                sum += s[realI];
                realI -= lowBit(realI);
            }
            return sum;
        }

        private int lowBit(int i) {
            return i & (-i);
        }
    }

    @Parameter
    public String num;
    @Parameter(1)
    public int    k;
    @Parameter(2)
    public String expect;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"4321", 4, "1342"},
                {"100", 1, "010"},
                {"36789", 1000, "36789"},
        };
    }

    @Test
    public void test_() {
        Assert.assertEquals(expect, minInteger(num, k));
    }

}