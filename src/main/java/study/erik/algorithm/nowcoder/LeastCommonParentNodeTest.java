package study.erik.algorithm.nowcoder;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-07-08 22:41
 * title = 二叉树的最近公共子节点
 * url = https://www.nowcoder.com/questionTerminal/66a813f33f1e499e816ddca216aa1983
 */
public class LeastCommonParentNodeTest {

    public static class Node {
        public Node left;
        public Node right;
        public int value;

        public Node(int value) {
            this.value = value;
        }

    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String firstLine = in.nextLine();

        String[] inputLine = firstLine.split(" ");
        int nodeSize = Integer.valueOf(inputLine[0]);
        int rootValue = Integer.valueOf(inputLine[1]);

        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < nodeSize; i++) {
            inputLine = in.nextLine().split(" ");
            Integer nodeValue = Integer.valueOf(inputLine[0]);
            Node node = map.getOrDefault(nodeValue, new Node(nodeValue));
            Integer leftValue = Integer.valueOf(inputLine[1]);
            if (!leftValue.equals(0)) {
                Node leftNode = map.getOrDefault(leftValue, new Node(leftValue));
                map.put(leftNode.value, leftNode);
                node.left = leftNode;
            }

            Integer rightValue = Integer.valueOf(inputLine[2]);
            if (!rightValue.equals(0)) {
                Node rightNode = map.getOrDefault(rightValue, new Node(rightValue));
                map.put(rightNode.value, rightNode);
                node.right = rightNode;
            }
            map.put(node.value, node);
        }

        inputLine = in.nextLine().split(" ");
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> cachedResult = new HashMap<>();
        int questionSize = Integer.valueOf(inputLine[0]);
        for (int i = 0; i < questionSize; i++) {
            inputLine = in.nextLine().split(" ");
            Integer leftValue = Integer.valueOf(inputLine[0]);
            Integer rightValue = Integer.valueOf(inputLine[1]);
            Integer foundResult = cachedResult.get(String.format("%d_%d", leftValue, rightValue));
            if (foundResult != null) {
                result.add(foundResult);
                continue;
            }

            foundResult = cachedResult.get(String.format("%d_%d", rightValue, leftValue));
            if (foundResult != null) {
                result.add(foundResult);
                continue;
            }

            Node commonNode = solution(map.get(rootValue), leftValue, rightValue);
            result.add(commonNode.value);
            cachedResult.put(String.format("%d_%d", leftValue, rightValue), commonNode.value);
            cachedResult.put(String.format("%d_%d", rightValue, leftValue), commonNode.value);
        }
        result.forEach(System.out::println);

    }

    /**
     * 返回当前二叉树中现有结点的基础上，与所有目标结点的最近的公共祖先结点；
     * 注意：
     * 1.这个返回值就是原问题的解；
     * 2.这个解还有一个隐藏的特性：当解！=null时，说明有目标节点在当前树中；利用这个特性，我们就可以在递归假设的结果上来构造上层结果了。
     * 3.这个算法可以扩展吗？可以的；可以扩展为多个目标结点
     * 4.如果想不通这个解法，可以试想
     * 4.1  只有一个目标节点的情况
     * 4.2  一个目标结点在另一个目标结点的路径上
     *
     * @param root
     * @param left
     * @param right
     * @return
     */
    public static Node solution(Node root, int left, int right) {

        if (root == null) {
            return null;
        }
        if (root.value == left || root.value == right) {
            return root;
        }

        Node leftParent = solution(root.left, left, right);
        Node rightParent = solution(root.right, left, right);
        if (leftParent != null && rightParent != null) {
            //如果左解和右解都不为空，说明目标结点在两个子树中都出现过，此时就说明root是他们的最近祖先了。这一步需要细品
            return root;
        }
//        return leftParent != null ? leftParent : rightParent;
        if (leftParent != null) {
            //左解不为空，说明目标结点在左子树中出现过。而且左解是目标结点们最近的公共祖先
            return leftParent;
        } else {
            //否则就直接返回右解，右解可能为空，也可能是目标节点们的最近的公共祖先
            return rightParent;
        }
    }

}
