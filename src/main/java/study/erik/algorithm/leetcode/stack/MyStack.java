package study.erik.algorithm.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {

    Queue<Integer>[] queues = new Queue[2];
    int              i      = 0;

    public MyStack() {
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedList<>();
        }
    }

    public void push(int x) {
        Queue<Integer> queue = queues[i % 2];
        queue.offer(x);
    }

    public int pop() {
        Queue<Integer> queue = queues[i % 2];
        i++;
        Queue<Integer> nextQueue = queues[i % 2];
        int size = queue.size();
        while (size > 1) {
            nextQueue.offer(queue.remove());
            size--;
        }
        return queue.remove();
    }

    public int top() {
        Queue<Integer> queue = queues[i % 2];
        i++;
        Queue<Integer> nextQueue = queues[i % 2];
        int size = queue.size();
        while (size > 1) {
            nextQueue.offer(queue.remove());
            size--;
        }
        Integer lastNode = queue.remove();
        nextQueue.offer(lastNode);
        return lastNode;
    }

    public boolean empty() {
        return queues[i % 2].isEmpty();
    }

    @Test
    public void test_() {
        //        ["MyStack","push","push","top","pop","empty"]
        //[[],[1],[2],[],[],[]]
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        Assert.assertEquals(2, myStack.top());
        Assert.assertEquals(2, myStack.top());
        Assert.assertFalse(myStack.empty());
    }
}