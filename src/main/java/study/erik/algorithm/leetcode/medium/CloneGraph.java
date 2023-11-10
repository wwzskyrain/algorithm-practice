package study.erik.algorithm.leetcode.medium;

import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-08-12 13:37
 */
public class CloneGraph {

    public static class Node {
        public int val;
        public List<Node> neighbors;

        public int getVal() {
            return val;
        }

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    @LetCodeCommit(no = 133, title = "Clone Graph", time = 61, space = 59,
            selfRemark = "没有意思，就是一个图的深拷贝。" +
                    "额，今天在模拟题目中碰到了，耗一个小时都没做出来。而且看这曾经的做法，距离真经也太远了吧。")
    public Node cloneGraph(Node node) {
        if(node == null) { //特殊用例，空图
            return null;
        }
        if(map.containsKey(node)){ //已经完成copy，就直接返回
            return map.get(node);
        }
        Node nodeCopy = new Node(node.val);
        map.put(node, nodeCopy);
        for (Node neighbor : node.neighbors) {
            Node neighborCopy  = cloneGraph(neighbor);
            //记住，这里只是单边copy
            nodeCopy.neighbors.add(neighborCopy);
        }
        return nodeCopy;
    }

    Map<Node,Node> map = new HashMap<>();

    @Test
    public void test_case_0() {
        Node node = build_adj_node();
        Node copiedNode = cloneGraph(node);
        print(copiedNode);
    }

    public Node build_adj_node() {
        int[][] adjList = new int[][]{{2, 4}, {1, 3}, {2, 4}, {1, 3}};
        Map<Integer, Node> num2NodeMap = new HashMap<>();
        for (int i = 0; i < adjList.length; i++) {
            Node node = num2NodeMap.getOrDefault(i + 1, new Node(i + 1));
            num2NodeMap.put(i + 1, node);
            for (int j = 0; j < adjList[i].length; j++) {
                int v = adjList[i][j];
                Node neighbor = num2NodeMap.getOrDefault(v, new Node(v));
                num2NodeMap.put(v, neighbor);
                node.neighbors.add(neighbor);
            }
        }
        return num2NodeMap.get(1);
    }

    public void print(Node node) {
        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> queuedValueSet = new HashSet<>();
        queue.add(node);

        queuedValueSet.add(node.val);

        while (!queue.isEmpty()) {
            Node headNode = queue.remove();
            nodes.add(headNode);
            for (Node neighbor : headNode.neighbors) {
                if (!queuedValueSet.contains(neighbor.val)) {
                    queue.add(neighbor);
                    queuedValueSet.add(neighbor.val);

                }
            }
        }

        nodes.sort(Comparator.comparing(Node::getVal));
        for (Node node1 : nodes) {
            StringBuilder sb = new StringBuilder();
            for (Node neighbor : node1.neighbors) {
                sb.append(neighbor.val).append(",");
            }
            System.out.println(sb.toString());
        }
    }

    @Test
    public void test_case_1() {
        int[][] adjList = new int[][]{{2, 4}, {1, 3}, {2, 4}, {1, 3}};
        Map<Integer, Node> num2NodeMap = new HashMap<>();
        for (int i = 0; i < adjList.length; i++) {
            Node node = num2NodeMap.getOrDefault(i + 1, new Node(i + 1));
            num2NodeMap.put(i + 1, node);
            for (int j = 0; j < adjList[i].length; j++) {
                int v = adjList[i][j];
                Node neighbor = num2NodeMap.getOrDefault(v, new Node(v));
                num2NodeMap.put(v, neighbor);
                node.neighbors.add(neighbor);
            }
        }
        Node node = num2NodeMap.get(1);
        System.out.println(node);


        List<Node> nodes = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        Set<Integer> queuedValueSet = new HashSet<>();
        queue.add(node);

        queuedValueSet.add(node.val);

        while (!queue.isEmpty()) {
            Node headNode = queue.remove();
            nodes.add(headNode);
            for (Node neighbor : headNode.neighbors) {
                if (!queuedValueSet.contains(neighbor.val)) {
                    queue.add(neighbor);
                    queuedValueSet.add(neighbor.val);

                }
            }
        }

        nodes.sort(Comparator.comparing(Node::getVal));
        for (Node node1 : nodes) {
            StringBuilder sb = new StringBuilder();
            for (Node neighbor : node1.neighbors) {
                sb.append(neighbor.val).append(",");
            }
            System.out.println(sb.toString());
        }
    }


}
