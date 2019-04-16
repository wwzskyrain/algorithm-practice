package study.erik.algorithm.leetcode.tree;

import java.util.*;

/**
 * @author erik.wang
 * @date 2019/04/06
 **/
public class TreeTraversalSolution {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {

        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();

        if (root == null) {
            return result;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            result.add(pop.val);
// 先右后左的进栈才能先左后右的出栈
            for (int i = pop.children.size() - 1; i >= 0; i--) {
                stack.push(pop.children.get(i));
            }
        }

        return result;

    }

    /**
     * 树(非二叉树)的后序遍历，不能用对称的思想来变形得到的。指针+lastVisited？
     *
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {

        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();
        Map<Node, Integer> nodeToIndex = new HashMap<>();
// TODO: 2019/4/6
        if (root == null) {
            return result;
        }

        Node pointer = root;
        nodeToIndex.put(pointer, 0);
        while (!stack.isEmpty() || pointer != null) {

            if (pointer != null) {
                stack.push(pointer);

            }
        }

        return result;

    }


}
