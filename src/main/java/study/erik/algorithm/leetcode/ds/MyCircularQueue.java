/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author yueyi
 * @version : MyCircleQueue.java, v 0.1 2022年04月02日 7:52 上午 yueyi Exp $
 */
@LetCodeCommit(title = "622. Design Circular Queue")
public class MyCircularQueue {

    private int[]   q;
    private int     head;
    private int     tail;
    private boolean empty;
    private boolean full;

    public MyCircularQueue(int k) {
        this.q = new int[k];
        head = 0;
        tail = 0;
        empty = true;
        full = false;
    }

    public boolean enQueue(int value) {
        if (full) {
            return false;
        }

        q[tail] = value;
        tail = (tail + 1) % q.length;
        if (tail == head) {
            full = true;
        }
        if (empty) {
            empty = false;
        }
        return true;
    }

    public boolean deQueue() {
        if (empty) {
            return false;
        }
        head = (head + 1) % q.length;
        if (head == tail) {
            empty = true;
        }
        if (full) {
            full = false;
        }
        return true;
    }

    public int Front() {
        return empty ? -1 : q[head];
    }

    public int Rear() {
        int tailIndex = (tail + q.length - 1) % q.length;
        return empty ? -1 : q[tailIndex];
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isFull() {
        return this.full;
    }

    public static void main(String[] args) {
        test_1();
    }

    public static void test_1() {

        MyCircularQueue queue = new MyCircularQueue(3);
        System.out.println(queue.enQueue(1));
        System.out.println(queue.enQueue(2));
        System.out.println(queue.enQueue(3));
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());
        System.out.println(queue.isFull());
        System.out.println(queue.deQueue());
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());

    }

}