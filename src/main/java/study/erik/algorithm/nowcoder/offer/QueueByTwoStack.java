package study.erik.algorithm.nowcoder.offer;

import java.util.Stack;

/**
 * @author erik.wang
 * @date 2020-09-13 09:02
 */
public class QueueByTwoStack {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack2.push(node);
    }

    public int pop() {
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        return stack1.pop();
    }

}
