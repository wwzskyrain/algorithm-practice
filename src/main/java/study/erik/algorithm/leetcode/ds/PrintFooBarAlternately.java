/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import java.util.concurrent.Semaphore;

/**
 * @author yueyi
 * @version : PrintFooBarAlternately.java, v 0.1 2023年01月12日 21:22 yueyi Exp $
 */
public class PrintFooBarAlternately {

    public static class FooBar{
        private int n;
        Semaphore s  = new Semaphore(0);
        Semaphore s2 = new Semaphore(1);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                s2.acquire();
                printFoo.run();
                s.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                s.acquire();
                printBar.run();
                s2.release();
            }
        }
    }
}