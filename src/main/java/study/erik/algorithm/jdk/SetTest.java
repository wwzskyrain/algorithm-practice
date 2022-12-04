/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.jdk;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : SetTest.java, v 0.1 2022年12月03日 21:57 yueyi Exp $
 */
public class SetTest {

    @Test
    public void test() {
        Set<int[]> set = new HashSet<>();

        set.add(new int[] {1, 1});
        set.add(new int[] {2, 2});

        System.out.println(set.contains(new int[] {1, 1}));
    }
}