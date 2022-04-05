package study.erik.algorithm.leetcode.ds;

import study.erik.algorithm.util.LetCodeCommit;

@LetCodeCommit(title = "641. Design Circular Deque",
        selfRemark = "用full和empty来表示满和空，用head和tail表示头元素和微元素.")
class MyCircularDeque {

    int     k;
    int     head;
    int     tail;
    int[]   dequeue;
    boolean full; // 当tail顺时与head毗邻，或者head逆时针与tail毗邻。
    boolean empty; // 当tail=head时，出队之后，就是empty;

    public MyCircularDeque(int k) {
        this.k = k;
        dequeue = new int[k];
        full = false;
        empty = true;
    }

    public boolean insertFront(int value) {
        if (empty) {
            head = tail = 0;
            dequeue[head] = value;
            empty = false;
        } else if (full) {
            return false;
        } else {
            head = (head + k - 1) % k;
            dequeue[head] = value;
        }
        int nextHead = (head + k - 1) % k;
        if (nextHead == tail) {
            full = true;
        }
        return true;
    }

    public boolean insertLast(int value) {
        if (full) {
            return false;
        } else if (empty) {
            head = tail = 0;
            dequeue[head] = value;
            empty = false;
        } else {
            tail = (tail + 1) % k;
            dequeue[tail] = value;
        }
        int nextTail = (tail + 1) % k;
        if (nextTail == head) {
            full = true;
        }
        return true;
    }

    public boolean deleteFront() {
        if (empty) {
            return false;
        }
        if (full) {
            full = false;
        }
        if (head == tail) {
            empty = true;
        } else {
            head = (head + 1) % k;
        }
        return true;
    }

    public boolean deleteLast() {
        if (empty) {
            return false;
        }
        if (full) {
            full = false;
        }
        if (head == tail) {
            empty = true;
        } else {
            tail = (tail + k - 1) % k;
        }
        return true;
    }

    public int getFront() {
        if (empty) {
            return -1;
        }
        return dequeue[head];
    }

    public int getRear() {
        if (empty) {
            return -1;
        }
        return dequeue[tail];
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isFull() {
        return full;
    }

    public static void main(String[] args) {
        MyCircularDeque meDeque = new MyCircularDeque(3);
        System.out.println(meDeque.insertLast(1));
        System.out.println(meDeque.insertLast(2));
        System.out.println(meDeque.insertFront(3));
        System.out.println(meDeque.insertFront(4));

        System.out.println(meDeque.getRear());
        System.out.println(meDeque.isFull());
        System.out.println(meDeque.deleteLast());
        System.out.println(meDeque.deleteFront());
        System.out.println(meDeque.getFront());
    }
}