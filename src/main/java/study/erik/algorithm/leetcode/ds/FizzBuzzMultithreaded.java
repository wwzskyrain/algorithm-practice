/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @author yueyi
 * @version : FizzBuzzMultithreaded.java, v 0.1 2023年02月08日 09:29 yueyi Exp $
 */
public class FizzBuzzMultithreaded {

    @LetCodeCommit(title = "1195. Fizz Buzz Multithreaded",
            selfRemark = "多同步块的使用.")
    public static class FizzBuzz {
        private int n;

        public FizzBuzz(int n) {
            this.n = n;
            i = new AtomicInteger(1);
        }

        AtomicInteger i;

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            synchronized (this) {
                while (i.get() <= n) {
                    if (i.get() % 3 == 0 && i.get() % 5 != 0) {
                        printFizz.run();
                        i.addAndGet(1);
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            synchronized (this) {
                while (i.get() <= n) {
                    if (i.get() % 5 == 0 && i.get() % 3 != 0) {
                        printBuzz.run();
                        i.addAndGet(1);
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            synchronized (this) {
                while (i.get() <= n) {
                    if (i.get() % 3 == 0 && i.get() % 5 == 0) {
                        printFizzBuzz.run();
                        i.addAndGet(1);
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                while (i.get() <= n) {
                    if (i.get() % 3 != 0 && i.get() % 5 != 0) {
                        printNumber.accept(i.get());
                        i.addAndGet(1);
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }
    }

}