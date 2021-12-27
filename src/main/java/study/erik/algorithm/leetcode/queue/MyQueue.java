package study.erik.algorithm.leetcode.queue;

import java.util.Deque;
import java.util.LinkedList;

class MyQueue {

    Deque<Integer> writeStack = new LinkedList<>();
    Deque<Integer> readStack  = new LinkedList<>();

    public MyQueue() {
    }

    public void push(int x) {
        writeStack.push(x);
    }

    public int pop() {
        if (readStack.isEmpty()) {
            int size = writeStack.size();
            while (size-- > 0) {
                readStack.push(writeStack.pop());
            }
        }
        return readStack.pop();
    }

    public int peek() {
        if (readStack.isEmpty()) {
            int size = writeStack.size();
            while (size-- > 0) {
                readStack.push(writeStack.pop());
            }
        }
        return readStack.peek();
    }

    public boolean empty() {
        return readStack.isEmpty() && writeStack.isEmpty();
    }
}