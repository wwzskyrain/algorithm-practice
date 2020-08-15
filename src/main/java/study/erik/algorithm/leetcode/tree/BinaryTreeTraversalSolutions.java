package study.erik.algorithm.leetcode.tree;


import study.erik.algorithm.leetcode.util.TreeNode;

import java.util.*;
import java.util.stream.Collectors;


/**
 * 二叉树遍历总结：
 * 1.
 */
public class BinaryTreeTraversalSolutions {

    public static List<Integer> nodeValueListWithPreOrder = new ArrayList<>();


    /**
     * 先序遍历二叉树-非递归算法
     * 最基础的方法-结点法-出栈法-
     * 优点：操作流程比较简单明了
     * 缺点：不能变形为"中序遍历"
     * 这个方法可以利用对称思想变形为"后序遍历"
     *
     * @param root
     * @return
     */
    public List<Integer> preOrderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        stack.push(root);
        while (!stack.isEmpty()) {

            TreeNode currentTreeNode = stack.pop();
            result.add(currentTreeNode.val);

            if (currentTreeNode.right != null) {
                //右孩子先进栈-也就是后出栈
                stack.push(currentTreeNode.right);
            }

            if (currentTreeNode.left != null) {
                stack.push(currentTreeNode.left);
            }
        }
        return result;

    }

    /**
     * 先序遍历二叉树-非递归算法
     * stack+探针法
     * 同样可以利用"对称思想"变形为"后序遍历。
     * 可变形为'中序遍历'
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversalI(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        TreeNode pointer = root;
        while (!stack.isEmpty() || pointer != null) {

            if (pointer != null) {

                result.add(pointer.val);
                stack.push(pointer);
                //该进栈还是要进栈，只是访问的时机变了
                pointer = pointer.left;
            } else {
                TreeNode popTreeNode = stack.pop();
                pointer = popTreeNode.right;
            }
        }
        return result;

    }


    /**
     * 中序遍历二叉树-非递归算法
     * stack+探针法
     * 与先序遍历的区别只在于：先序遍历是入栈前访问，中序遍历是出栈访问
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        TreeNode probe = root;

        while (!stack.isEmpty() || probe != null) {

            if (probe != null) {     //先左后右
                stack.push(probe);
                probe = probe.left;
            } else {
//           左指针为空或者右指针为空。
//           左指针为空的话，就是要访问该节点了
                probe = stack.pop();
                result.add(probe.val);
                probe = probe.right;
            }

        }
        return result;

    }


    /**
     * 后续遍历二叉树-非递归算法
     * 这个方法不好记，从中可以学到"最近访问结点标记法"来对付从右分支连续出栈的情况。
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        TreeNode node = root;
        TreeNode lastVisitedNode = null;

        do {
//      左指针统统进栈
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            lastVisitedNode = null;

            while (!stack.isEmpty()) {
                node = stack.peek();
//              如果栈顶元素的右指针为null，或者他的右指针已经被访问过了，就可以访问它了。
                if (node.right == null || node.right == lastVisitedNode) {
                    result.add(node.val);
                    lastVisitedNode = stack.pop();
//                  访问过之后不要退出循环，有可能是连续的右指针退出。
                } else {
                    node = node.right;
                    break;
                }
            }
        } while (!stack.isEmpty());

        return result;

    }

    /**
     * 指针法
     * 这种解法叫做"逆转先序遍历"
     *
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversalII(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.addFirst(p.val);  // Reverse the process of preOrder
                p = p.right;             // Reverse the process of preOrder
            } else {
                TreeNode node = stack.pop();
                p = node.left;           // Reverse the process of preOrder
            }
        }
        return result;
    }

    /**
     * stack+探针法+lastVisited 方法实现后序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversalV(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();

        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        TreeNode pointerNode = root;
        TreeNode lastVisitedNode = null;

        while (!stack.isEmpty() || pointerNode != null) {

            if (pointerNode != null) {
                stack.push(pointerNode);
                pointerNode = pointerNode.left;
            } else {

                TreeNode peekNode = stack.peek();
                //当peekNode的右子树为空，或者 上一个访问的节点就是peekNode的有结点，peekNode才能出栈被访问。
                if (peekNode.right == null || peekNode.right == lastVisitedNode) {
                    peekNode = stack.pop();
                    result.add(peekNode.val);
                    lastVisitedNode = peekNode;
                    pointerNode = null; //必须将pointerNode置空的
                } else {
                    pointerNode = peekNode.right;
                }

            }
        }

        return result;

    }


    /**
     * 题目：Binary Tree Level Order Traversal
     * 层次遍历二叉树；广度遍历的一种
     * 用两个queue来交替存放当前层次的节点们
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode>[] queues = new Queue[]{new LinkedList<>(), new LinkedList<>()};

        int i = 0;
        queues[i].add(root);
        while (!queues[i % 2].isEmpty()) {

            Queue<TreeNode> queue = queues[i % 2];

            List<Integer> levelTreeNodes = new ArrayList<>();
            i++;

            while (!queue.isEmpty()) {

                TreeNode treeNode = queue.poll();

                levelTreeNodes.add(treeNode.val);
                if (treeNode.left != null) {
                    queues[i % 2].add(treeNode.left);
                }

                if (treeNode.right != null) {
                    queues[i % 2].add(treeNode.right);
                }
            }

            result.add(levelTreeNodes);
        }

        return result;
    }

    /**
     * title=Binary Tree Level Order Traversal II
     * 解法：将上一题的结果——list逆序一下就好了。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode>[] queues = new Queue[]{new LinkedList<>(), new LinkedList<>()};

        int i = 0;
        queues[i].add(root);
        while (!queues[i % 2].isEmpty()) {

            Queue<TreeNode> queue = queues[i % 2];

            List<Integer> levelTreeNodes = new ArrayList<>();
            i++;

            while (!queue.isEmpty()) {

                TreeNode treeNode = queue.poll();

                levelTreeNodes.add(treeNode.val);
                if (treeNode.left != null) {
                    queues[i % 2].add(treeNode.left);
                }

                if (treeNode.right != null) {
                    queues[i % 2].add(treeNode.right);
                }
            }

            result.add(0, levelTreeNodes);
        }

        return result;
    }

    /**
     * title=Average of Levels in Binary Tree
     * 解法：遍历算法已经有了，再在其上做一些雕虫小技
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> result = new ArrayList<>();
        List<List<Integer>> resultList = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode>[] queues = new Queue[]{new LinkedList<>(), new LinkedList<>()};

        int i = 0;
        queues[i].add(root);
        while (!queues[i % 2].isEmpty()) {

            Queue<TreeNode> queue = queues[i % 2];

            List<Integer> levelTreeNodes = new ArrayList<>();
            i++;

            while (!queue.isEmpty()) {

                TreeNode treeNode = queue.poll();

                levelTreeNodes.add(treeNode.val);
                if (treeNode.left != null) {
                    queues[i % 2].add(treeNode.left);
                }

                if (treeNode.right != null) {
                    queues[i % 2].add(treeNode.right);
                }
            }

            resultList.add(levelTreeNodes);
        }
        return resultList.stream().map(list ->
                list.stream().mapToDouble(Double::valueOf).average().getAsDouble()
        ).collect(Collectors.toList());

    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        boolean direction = true;
        while (!queue.isEmpty()) {

            int currentLevelNodeNumbers = queue.size();
            List<Integer> currentLevelNodeValueTemp = new ArrayList<>();
            for (int i = 0; i < currentLevelNodeNumbers; i++) {

                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
                currentLevelNodeValueTemp.add(treeNode.val);
            }

            if (!direction) {
                Collections.reverse(currentLevelNodeValueTemp);
            }
            direction = !direction;
            result.add(currentLevelNodeValueTemp);

        }
        return result;
    }

    /**
     * title=Maximum Width of Binary Tree
     * 解法：算法基础是BFS；题目所定义的width，可以通过给所有节点编号，通过本层的最小号和最大号的差而得到。
     * 在"完全二叉树"中，所以节点都有一个唯一的编号的。
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {

        int width = 0;
        if (root == null) {
            return width;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        Map<TreeNode, Integer> nodeToNumber = new HashMap<>();
        nodeToNumber.put(root, 1);
        while (!queue.isEmpty()) {
            int currentLevelNodeNumbers = queue.size();
            int leftMost = 1;
            int rightMost = 1;
            for (int i = 0; i < currentLevelNodeNumbers; i++) {

                TreeNode treeNode = queue.poll();

                if (i == 0) {
                    leftMost = nodeToNumber.get(treeNode);
                }

                if (i == currentLevelNodeNumbers - 1) {
                    rightMost = nodeToNumber.get(treeNode);
                }

                Integer number = nodeToNumber.get(treeNode);

                TreeNode left = treeNode.left;
                if (left != null) {
                    queue.offer(left);
                    nodeToNumber.put(left, number * 2);
                }
                TreeNode right = treeNode.right;
                if (right != null) {
                    queue.offer(right);
                    nodeToNumber.put(right, number * 2 + 1);
                }
            }
// 别忘了加一
            width = Math.max(width, rightMost - leftMost + 1);
        }
        return width;

    }

    /**
     * title=Recover Binary Search Tree
     * 题目描述：似乎不需太多题目注解，就是"修复二叉搜索树"，而且保持其结构不变；空间要求O(1)。
     * 错了："被毁坏的二叉树"，即输入的二叉树中，只有两个节点被"错误的交换"了，所以只需要找到他俩，并交换即可。
     * 解法分析：即使是遍历二叉树要么递归，要么迭代
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {

        if (root == null) {
            return;
        }
        findFirstAndSecondElement(root);
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;

    }

    private TreeNode firstElement = null;
    private TreeNode secondElement = null;
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void findFirstAndSecondElement(TreeNode root) {

        if (root == null) {
            return;
        }

        findFirstAndSecondElement(root.left);

//  分别找到第一个、第二个错位节点。判断条件主要是： 前驱.val > root.val
        if (firstElement == null && prevElement.val > root.val) {
            firstElement = prevElement;
        }

        if (firstElement != null && prevElement.val > root.val) {
            secondElement = root;
        }

        prevElement = root;
        findFirstAndSecondElement(root.right);
    }

    /**
     * title=Balanced Binary Tree
     * 开心：完全凭着直觉书写，一气呵成，一蹴而就。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {

        if (root == null) {
            return true;
        }
        //这里有自动剪枝的，因为任何一个子树不平衡，都能很层层返回的
        return Math.abs(depth(root.left) - depth(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);

    }

    public int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }


    /**
     * title=Flatten Binary Tree to Linked List
     * 解法：先序遍历：非递归先序遍历+前驱节点
     *
     * @param root
     */
    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        stack.push(root);
        while (!stack.isEmpty()) {

            TreeNode pop = stack.pop();
            //  先序遍历-start doing something.
            if (pre == null) {
                pre = pop;
            } else {
                pre.right = pop;
                pre.left = null;
                pre = pre.right;

            }
            //  先序遍历-end doing something.

            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }

    }


    private TreeNode prev = null;

    /**
     * wa,这个解法 faster than 100%.
     * 后序遍历：右-左-根
     * 加上链表头插法
     * 绝了。
     *
     * @param root
     */
    public void flattenII(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;

    /**
     * title=Populating Next Right Pointers in Each Node
     * 解法：这个题目用"层次优先遍历"最为简单，可惜空间复杂度是O(n),人家题目有要求，只能是常量级别的。
     * 所以用了一个迭代法，真的是迭代法吗？其实是利用了结点中的next指针来完成的。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {

        Node result = root;

        while (root != null) {

            Node nextLevelRoot = null;
            Node pre = null;
            while (root != null) {

                if (root.left != null) {
                    if (nextLevelRoot == null) {
                        nextLevelRoot = root.left;
                    }
                    if (pre != null) {
                        pre.next = root.left;
                    }
                    root.left.next = root.right;
                    pre = root.right;
                    root.right.next = null;
                }

                root = root.next;
            }

            root = nextLevelRoot;
        }

        return result;
    }

    /**
     * 递归解法，因为它的思想真的很简介
     * 而且，表现最佳 faster than 100.00% ，less than 96.25%
     *
     * @param root
     * @return
     */
    public Node connectI(Node root) {

        if (root == null) {
            return null;
        }

        if (root.left != null) {
            root.left.next = root.right;
        }

        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);

        return root;
    }


}


