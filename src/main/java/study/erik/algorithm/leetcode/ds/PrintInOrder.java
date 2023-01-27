/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yueyi
 * @version : PrintInOrder.java, v 0.1 2023年01月12日 21:03 yueyi Exp $
 */
public class PrintInOrder {

    public static class Foo {

        private Lock lock = new ReentrantLock();
        Condition oneCondition;
        Condition twoCondition;
        boolean   oneDone = false;
        boolean   twoDone = false;

        public Foo() {
            oneCondition = lock.newCondition();
            twoCondition = lock.newCondition();
        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            try {
                printFirst.run();
                oneDone = true;
                oneCondition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {

            lock.lock();
            try {
                while (!oneDone) {
                    oneCondition.await();
                }
                printSecond.run();
                twoDone = true;
                twoCondition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            lock.lock();
            try {
                while (!twoDone) {
                    twoCondition.await();
                }
                printThird.run();
            } finally {
                lock.unlock();
            }
        }

    }

}