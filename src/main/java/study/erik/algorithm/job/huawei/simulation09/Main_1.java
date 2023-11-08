package study.erik.algorithm.job.huawei.simulation09;

import java.util.*;

public class Main_1 {
    // Definition for a Node.
    public static class Node {
        public int val;
        public List<Node> neighbors;

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

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node nodeCopy = new Node(node.val, new ArrayList<>());
        map.put(node, nodeCopy);
        for (Node neighbor : node.neighbors) {
            nodeCopy.neighbors.add(cloneGraph(neighbor));
        }
        return nodeCopy;
    }

    Map<Node, Node> map = new HashMap<>();
}
