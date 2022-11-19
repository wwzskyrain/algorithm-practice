/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.array.medium;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yueyi
 * @version : RevealCardsInIncreasingOrder.java, v 0.1 2022年11月19日 10:41 yueyi Exp $
 */
@RunWith(Parameterized.class)
public class RevealCardsInIncreasingOrder {

    @LetCodeCommit(title = "950. Reveal Cards In Increasing Order")
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Arrays.sort(deck);
        Queue<Integer> q = new LinkedList<>();
        for (int i = n - 1; i >= 0; --i) {
            if (q.size() > 0) q.add(q.poll());
            q.add(deck[i]);
        }
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            res[i] = q.poll();
        }
        return res;
    }

    @Parameter
    public int[] deck;

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {ArrayUtils.buildArray("[17,13,11,2,3,5,7]")},
        };
    }

    @Test
    public void test_() {
        deckRevealedIncreasing(deck);
    }

}