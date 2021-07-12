package study.erik.algorithm.ds;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author erik.wang
 * @date 2019/06/30
 **/
public class Dijkstra {

    //注意：权值无穷大不能使用 Integer.MAX_VALUE表示，因为程序中会基于这个值做加法，从而导致移出的。
    static final Integer MAX_VALUE = 10000;

    /**
     * 找最短路径和最短距离
     * 还可以写成广度搜索+优先级队列来优化
     *
     * @param graph            路径网
     * @param start            开始节点
     * @param pathMatrix       从start出发，到每个节点的最短路径
     * @param shortestDistance 从start到每个节点的最短距离
     */
    public void shortestPath(int[][] graph, int start, boolean[][] pathMatrix, int[] shortestDistance) {

        boolean[] finished = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            finished[i] = false;
            shortestDistance[i] = graph[start][i];
            pathMatrix[i] = new boolean[graph.length];
            if (shortestDistance[i] < MAX_VALUE) {
                pathMatrix[i][start] = true;
                pathMatrix[i][i] = true;
            }
        }

        finished[start] = true; //以计算的最短路径的集合
        shortestDistance[start] = 0;
        for (int i = 1; i < graph.length; i++) {

            //先找到这一次待加入的节点；
            // 可以用优先级队列来优化
            int minIndex = 0;
            int min = MAX_VALUE;
            for (int j = 0; j < shortestDistance.length; j++) {
                if (!finished[j] && shortestDistance[j] < min) {
                    min = shortestDistance[j];
                    minIndex = j;
                }

            }
            //找到后，把它标志位为已经入节点
            finished[minIndex] = true;

            for (int j = 0; j < graph.length; j++) {
                if (!finished[j]) {
                    int newDistance = graph[minIndex][j] + min; //如果用Integer.MAX_VALUE表示没有边-无穷大，则这里newDistance会移出为负值，而导致
                    //下面的判断成立而进入if语句。
                    if (newDistance < shortestDistance[j]) {
                        shortestDistance[j] = newDistance;
                        pathMatrix[j] = Arrays.copyOf(pathMatrix[minIndex], pathMatrix[j].length);
                        pathMatrix[minIndex][j] = true; //加上这最后一条(minIndex,j)
                    }
                }
            }
        }
    }

    /**
     * 迪杰特斯拉算法的优先级优化算法
     *
     * @param graph 节点图
     * @param start 开始节点
     * @return shortestDistance 输出最短路径
     */
    public int[] shortestPathWithPriorityQueue(int[][] graph, int start) {

        int[] shortestDistance = new int[graph.length];

        PriorityQueue<NodeAndWeight> frontierPriorityQueue = new PriorityQueue<>();
        frontierPriorityQueue.add(new NodeAndWeight(start, graph[start][start]));
        boolean[] reached = new boolean[graph.length];
        reached[start] = true;
        // 初始化最短距离数组
        for (int i = 0; i < shortestDistance.length; i++) {
            shortestDistance[i] = MAX_VALUE;
        }
        shortestDistance[start] = 0;

        while (!frontierPriorityQueue.isEmpty()) {
            // currentNode 当前离start节点最近的为访问到的节点
            NodeAndWeight currentNode = frontierPriorityQueue.poll();
            reached[currentNode.node] = true;
            for (int i = 0; i < graph[currentNode.node].length; i++) {
                int latestDistance = shortestDistance[currentNode.node] + graph[currentNode.node][i];
                if (!reached[i] && latestDistance < shortestDistance[i]) {
                    shortestDistance[i] = latestDistance;
                    frontierPriorityQueue.add(new NodeAndWeight(i, latestDistance));
                }
            }
        }
        return shortestDistance;
    }

    public static class NodeAndWeight implements Comparator<NodeAndWeight> {
        public int node;
        public int weight;

        public NodeAndWeight(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compare(NodeAndWeight node1, NodeAndWeight node2) {
            return Integer.valueOf(node1.weight).compareTo(node2.weight);
        }

        @Override
        public boolean equals(Object obj) {
            return obj instanceof NodeAndWeight &&
                    ((NodeAndWeight) obj).node == this.node;
        }
    }

    @Test
    public void test_shortestPath() {
        int[][] graph = new int[][] {
                {MAX_VALUE, MAX_VALUE, 10, MAX_VALUE, 30, 100},
                {MAX_VALUE, MAX_VALUE, 5, MAX_VALUE, MAX_VALUE, MAX_VALUE},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, 50, MAX_VALUE, MAX_VALUE},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 10},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, 20, MAX_VALUE, 60},
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE}
        };
        boolean[][] pathMatrix = new boolean[graph.length][graph.length];
        int[] shortestDistance = new int[graph.length];
        shortestPath(graph, 0, pathMatrix, shortestDistance);

        System.out.println(Arrays.toString(shortestDistance));
    }

}
