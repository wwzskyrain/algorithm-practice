package study.erik.algorithm.job.zijie.simulation01;

import org.junit.Test;

public class Main_2 {

    public static class MyCircularDeque {

        int cap;
        int[] deq;
        int head;
        int tail;
        boolean empty = true;
        boolean full = false;

        // head = tail表示队空了；
        // head - 1 = tail or tail + 1 = head 队列满了
        public MyCircularDeque(int k) {
            cap = k + 1;
            deq = new int[cap];
            head = 0;
            tail = (head + 1) % cap;

        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            deq[head] = value;
            head = (head + cap - 1) % cap;
            if (head == tail) {
                this.full = true;
            }
            this.empty = false;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            deq[tail] = value;
            tail = (tail + cap + 1) % cap;
            if (tail == head) {
                this.full = true;
            }
            this.empty = false;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            head = (head + cap + 1) % cap;
            int h = (head + 1) % cap;
            if(h == tail) {
                this.empty = true;
            }
            this.full = false;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            tail = (tail + cap - 1) % cap;
            int t = (tail + cap - 1) % cap;
            if(head == t) {
                this.empty = true;
            }
            this.full = false;
            return true;
        }

        public int getFront() {
            if(this.isEmpty()) {
                return -1;
            }
            int h = (head + 1) % cap;
            return deq[h];
        }

        public int getRear() {
            if(this.empty) {
                return -1;
            }
            int t = (tail + cap - 1) % cap;
            return deq[t];
        }

        public boolean isEmpty() {
            return this.empty;
        }

        public boolean isFull() {
            return this.full;
        }
    }

    @Test
    public void test1() {
//  ["MyCircularDeque","insertFront","insertLast","getFront","insertLast","getFront","insertFront","getRear","getFront","getFront","deleteLast","getRear"]
//  [[5],[7],[0],[],[3],[],[9],[],[],[],[],[]]
        MyCircularDeque deque = new MyCircularDeque(5);
        System.out.println(deque.insertFront(7));
        System.out.println(deque.insertLast(0));
        System.out.println(deque.getFront()); //
        System.out.println(deque.insertLast(3));
        System.out.println(deque.getFront());
        System.out.println(deque.insertFront(9));
        System.out.println(deque.getRear());
        System.out.println(deque.getFront());
        System.out.println(deque.getFront());
        System.out.println(deque.deleteLast());
        System.out.println(deque.getRear());
    }

    @Test
    public void test() {

        MyCircularDeque deque = new MyCircularDeque(3);

        System.out.println(deque.insertLast(1));
        System.out.println(deque.insertLast(2));
        System.out.println(deque.insertFront(3));
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getRear());
        System.out.println(deque.isFull());
        System.out.println(deque.deleteLast());
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getFront());
//[[3],[1],[2],[3],[4],[],[],[],[4],[]]
    }

    @Test
    public void test3(){
       // ["MyCircularDeque","insertFront","deleteLast","getRear","getFront","getFront","deleteFront","insertFront","insertLast","insertFront","getFront","insertFront"]
        // [[4],[9],[],[],[],[],[],[6],[5],[9],[],[6]]

        MyCircularDeque deque = new MyCircularDeque(4);
        System.out.println(deque.insertFront(9));
        System.out.println(deque.deleteLast());
        System.out.println(deque.getRear());
        System.out.println(deque.getFront());
        System.out.println(deque.getFront());
        System.out.println(deque.deleteFront());
        System.out.println(deque.insertFront(6));
        System.out.println(deque.insertLast(5));
        System.out.println(deque.insertFront(9));
        System.out.println(deque.getFront());
        System.out.println(deque.insertFront(6));

    }


}
