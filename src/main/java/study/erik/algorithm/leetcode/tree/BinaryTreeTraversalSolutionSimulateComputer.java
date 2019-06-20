package study.erik.algorithm.leetcode.tree;

import org.junit.Test;
import study.erik.algorithm.leetcode.tree.helper.TreeNode;

import java.util.*;

/**
 * @author erik.wang
 * @date 2019/06/16
 * // 想用模拟计算机的递归运行来实现二叉树的非递归遍历
 **/
public class BinaryTreeTraversalSolutionSimulateComputer {

    public static class StackFrame {

        public TreeNode treeNode;
        //0:已访当前节点，1:已访问左结点，2:已访问右结点
        public Integer statusId;

        public StackFrame(TreeNode treeNode, Integer statusId) {
            this.treeNode = treeNode;
            this.statusId = statusId;
        }

    }

    /**
     * title=Binary Tree Preorder Traversal
     * link=https://leetcode.com/problems/binary-tree-preorder-traversal/
     * 模拟计算机在执行二叉树的递归解法时的执行方式。
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        Deque<StackFrame> stack = new ArrayDeque();
        if (root != null) {
            result.add(root.val);
            stack.push(new StackFrame(root, 0));
        }

        while (!stack.isEmpty()) {

            StackFrame stackFrame = stack.peek();
            switch (stackFrame.statusId) {
                case 0:
                    if (stackFrame.treeNode.left != null) {
                        TreeNode leftNode = stackFrame.treeNode.left;
                        result.add(leftNode.val);
                        stack.push(new StackFrame(leftNode, 0));
                    }
                    stackFrame.statusId = 1;
                    break;
                case 1:
                    if (stackFrame.treeNode.right != null) {
                        TreeNode rigthNode = stackFrame.treeNode.right;
                        result.add(rigthNode.val);
                        stack.push(new StackFrame(rigthNode, 0));
                    }
                    stackFrame.statusId = 2;
                    break;
                case 2:
                    stack.pop();
                    break;
            }

        }
        return result;
    }

    @Test
    public void test_preorderTraversal() {

        TreeNode root = buildTree();
        List<Integer> result = preorderTraversal(root);
        System.out.println(result);
    }

    /**
     * title= Binary tree postorder Traversal
     * link=https://leetcode.com/problems/binary-tree-postorder-traversal/
     * 后续遍历二叉树，模拟计算机递归遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, Integer> node2Status = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            stack.push(root);
            node2Status.put(root, 0);
        }

        while (!stack.isEmpty()) {
            TreeNode peekNode = stack.peek();
            switch (node2Status.get(peekNode)) {
                case 0: //初始化
                    if (peekNode.left != null) {
                        stack.push(peekNode.left);
                        node2Status.put(peekNode.left, 0);
                    }
                    node2Status.put(peekNode, 1);
                    break;
                case 1: //左指针已入站
                    if (peekNode.right != null) {
                        stack.push(peekNode.right);
                        node2Status.put(peekNode.right, 0);
                    }
                    node2Status.put(peekNode, 2);
                    break;
                case 2: //右指针已入站
                    result.add(peekNode.val);
                    stack.pop();
                    break;
            }
        }
        return result;
    }

    @Test
    public void test_postorderTraversal() {

        TreeNode root = buildTree();
        List<Integer> result = postorderTraversal(root);
        System.out.println(result);
    }

    /**
     * title = Binary Tree Inorder Traversal
     * link = https://leetcode.com/problems/binary-tree-inorder-traversal/
     *  二叉树的中序遍历
     *  解法：模拟计算机执行二叉树的递归解法；
     *  1.栈stack元素，逻辑上是要模拟计算机中的'栈帧'的，但是为了节省一个'栈帧'类似的数据结构，就用一个map给替代了。
     *  2.我们知道一个栈帧就是一个函数调用，所以我们这里模拟的也就是函数调用。
     *  3.这里的函数调用，我们只关心两点，一是函数的标识，这个我们用当前节点来表示；二是函数执行的下一条指令地址，这里我们用一个Integer来标识；
     *      然后我们用一个Map将这两个封装在一起，而不是另外自定义一个数据结构或者Class
     *  4.Map<当前函数调用栈帧，程序计数器>；map的value也需要简化；因为熟悉二叉树的递归调用的朋友都知道，递归调用函数只有三句话，是多么的简单。
     *      所以这里实际并没有用指令编号作为map的value，而是抽象出四种状态值；
     *  5.最后补充一句：因为在计算机执行递归的过程中，虽然嵌套了很多递归调用，但是它是线性的，我们完全可以模拟的。而且按照这个思路，理论上，我们其实可以将
     *      所有的递归调用转化成非递归调用。
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        Map<TreeNode, Integer> node2Status = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            stack.push(root);
            node2Status.put(root, 0);
        }

        while (!stack.isEmpty()) {
            TreeNode peekNode = stack.peek();
            switch (node2Status.get(peekNode)) {
                case 0: //状态：初始化 --处理左子树--> 状态：左子树已处理
                    if (peekNode.left != null) {
                        stack.push(peekNode.left);
                        node2Status.put(peekNode.left, 0);
                    }
                    node2Status.put(peekNode, 1);
                    break;
                case 1: //状态：左子树已处理 --访问当前节点--> 状态：处理右子树
                    result.add(peekNode.val);
                    node2Status.put(peekNode, 2);
                    break;
                case 2: //状态:已访问当前节点 --处理右子树--> 状态:退出前
                    if (peekNode.right != null) {
                        stack.push(peekNode.right);
                        node2Status.put(peekNode.right, 0);
                    }
                    node2Status.put(peekNode, 3);
                    break;
                case 3://状态：推出前 --出栈--> 状态：函数结束
                    stack.pop();
                    break;
            }
        }
        return result;

    }

    @Test
    public void test_inorderTraversal() {

        TreeNode root = buildTree();
        List<Integer> result = inorderTraversal(root);
        System.out.println(result);
    }


    public static TreeNode buildTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        return root;
    }


}
