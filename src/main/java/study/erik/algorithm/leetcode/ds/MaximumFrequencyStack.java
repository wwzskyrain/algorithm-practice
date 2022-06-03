/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author yueyi
 * @version : MaximumFrequencyStack.java, v 0.1 2022年06月03日 10:54 yueyi Exp $
 */
public class MaximumFrequencyStack {

    @LetCodeCommit(title = "895. Maximum Frequency Stack",
            diff = "m",
            selfRemark = "这个解法用了maxFreq指针当栈的指针，很妙。")
    public static class FreqStack {

        private Map<Integer, Integer>        freq     = new HashMap<>();
        private Map<Integer, Stack<Integer>> stackMap = new HashMap<>();
        private int                          maxFreq  = 0;

        public FreqStack() {
        }

        public void push(int val) {
            int newFreq = freq.getOrDefault(val, 0) + 1;
            freq.put(val, newFreq);
            maxFreq = Math.max(maxFreq, newFreq);
            Stack<Integer> stack = stackMap.getOrDefault(newFreq, new Stack<>());
            stack.push(val);
            stackMap.put(newFreq, stack);
        }

        public int pop() {
            Stack<Integer> stack = stackMap.get(maxFreq);
            Integer pop = stack.pop();
            freq.put(pop, maxFreq - 1);
            if (stack.isEmpty()) {
                maxFreq--;
            }
            return pop;
        }
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);

        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}