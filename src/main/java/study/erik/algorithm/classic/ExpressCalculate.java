package study.erik.algorithm.classic;

import org.junit.Test;

/**
 * @author erik.wang
 * @date 2020-03-29 15:43
 */
public class ExpressCalculate {

    private static class Node {
        public char value;
        public Node left = null;
        public Node right = null;

        public Node(char value) {
            this.value = value;
        }
    }

    /**
     * 这是根据一个四则运算式，构造了语法树。
     * 前序、中序、后序，分别可以得到前缀表达式、中缀表达式、后缀表达式。
     * 其中，可以直接根据前缀表达式和后缀表达式来计算表达式的值。请参见文章：https://blog.csdn.net/linraise/article/details/20459751
     * 局限性：这个实现是有局限性的，那就是需要用()来表达任何的'计算'。
     * 向往：能够直接根据中序表达式来变化为后缀表达式，然后根据后缀表达式来计算。这两步都是有算法的，参加文章：https://blog.csdn.net/u012507347/article/details/52245233
     * @param input
     * @return
     */
    public Node dfs(String input) {
        if (input.length() == 0) {
            return null;
        }
        if (input.charAt(0) == '(') {
            input = input.substring(1, input.length());
        }

        int index = 0;
        Node left;
        if (input.charAt(0) == '(') {
            index = indexOf(input);
            left = dfs(input.substring(0, index));
        } else {
            left = new Node(input.charAt(0));
        }
        Node root = new Node(input.charAt(index + 1));
        root.left = left;

        if (input.charAt(index + 2) == '(') {
            root.right = dfs(input.substring(index + 2, input.length()));
        } else {
            root.right = new Node(input.charAt(index + 2));
        }
        return root;
    }

    @Test
    public void test() {
        String input = "((A+((B-C)/D))*E)";
        Node root = dfs(input);
        System.out.println(root);
    }

    private int indexOf(String input) {
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    if (count == 0) {
                        return i;
                    }
                    break;
                default:
            }
        }
        return 0;
    }

}
