package study.erik.algorithm.leetcode.stack;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Deque;
import java.util.LinkedList;

import static study.erik.algorithm.util.QuestionType.Stack;

@LetCodeCommit(no = 11, title = "155. Min Stack", diff = "e",
        selfRemark = "用双向链表实现一个最小栈，这样就没有扩容的烦恼了。但是每次都new内存，还是不太好，不如预分配；" +
                "而预分配有不能追加分配，在扩容时只能再分配并且迁移",
        related = "max stack-不要做了，一样的思路",
        types = Stack)
public class MinStack {

    private Deque<Integer> data;
    private Deque<Integer> minData;
    private int size;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        data = new LinkedList<>();
        minData = new LinkedList<>();
        size = 0;
    }

    public void push(int x) {
        data.addLast(x);
        int min = size > 0 ? Math.min(minData.getLast(), x) : x;
        minData.addLast(min);
        size++;
    }

    public void pop() {
        data.removeLast();
        minData.removeLast();
        size--;
    }

    public int top() {
        return data.getLast();
    }

    public int getMin() {
        return minData.getLast();
    }


    @Test
    public void test_solution() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}