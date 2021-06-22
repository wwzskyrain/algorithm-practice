/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.sword;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : CharOrder.java, v 0.1 2021年06月22日 7:38 上午 yueyi Exp $
 */
public class CharOrder {

    /**
     * 剑指offer的38题
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        List<String> allOrders = new ArrayList<>();
        dfs(s.toCharArray(), 0, allOrders);
        return allOrders.toArray(new String[0]);
    }

    public void dfs(char[] s, int index, List<String> allOrder) {
        if (index < s.length) {
            for (int i = index; i < s.length; i++) {
                if (hasSameChar(s, i, index)) {
                    continue;
                }
                char tempChar = s[index];
                s[index] = s[i];
                s[i] = tempChar;

                dfs(s, index + 1, allOrder);

                tempChar = s[index];
                s[index] = s[i];
                s[i] = tempChar;
            }
        } else {
            allOrder.add(new String(s));
        }
    }

    private boolean hasSameChar(char[] s, int i, int index) {
        for (int j = i - 1; j >= index; j--) {
            if (s[j] == s[i]) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        //String s = "abc";
        String s = "aca";
        //String s = "abc";
        String[] permutation = permutation(s);
        for (String s1 : permutation) {
            System.out.println(s1);
        }
    }

}