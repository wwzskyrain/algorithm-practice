/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import javafx.util.Pair;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : StockSpannerSolution.java, v 0.1 2022年10月07日 22:44 yueyi Exp $
 */
public class StockSpannerSolution {
    @LetCodeCommit(title = "901. Online Stock Span",
            time = 73, space = 51, selfRemark = "很简单，一个单调栈即可")
    public static class StockSpanner {

        private List<Pair<Integer, Integer>> stack;

        public StockSpanner() {
            stack = new ArrayList<>();
        }

        public int next(int price) {
            int r = 1;
            for (int i = stack.size() - 1; i >= 0; i--) {
                Pair<Integer, Integer> p = stack.get(i);
                Integer curPrice = p.getKey();
                if (price < curPrice) {
                    break;
                }
                r += p.getValue();
                stack.remove(i);
            }
            stack.add(new Pair<>(price, r));
            return r;
        }
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));
        System.out.println(stockSpanner.next(85));
    }

}