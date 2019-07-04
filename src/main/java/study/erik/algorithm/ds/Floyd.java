package study.erik.algorithm.ds;

import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/30
 **/
public class Floyd {

    /**
     * 弗洛伊德算法解决"顶点对最短距离问题"。
     * 这是个'动态规划'的解法。其思想提出的形式都是数组操作，而不是递归操作的。
     *
     * @param graph
     * @param pathMatrix
     * @param distanceMatrix
     */
    public void shortestPathFloyd(int[][] graph, boolean[][][] pathMatrix, int[][] distanceMatrix) {

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                distanceMatrix[i][j] = graph[i][j];

                for (int k = 0; k < pathMatrix[i][j].length; k++) {
                    pathMatrix[i][j][k] = false;
                }

                if (distanceMatrix[i][j] != Integer.MAX_VALUE) {
                    pathMatrix[i][j][i] = true;
                    pathMatrix[i][j][j] = true;
                }
            }
        }


        for (int i = 0; i < graph.length; i++) { //逐个添加结点到备选库。这个节加入结点是对所有'定点对'而言的。
            for (int start = 0; start < graph.length; start++) {
                for (int target = 0; target < graph.length; target++) {
                    if (start == target) { //小优化-erik。当start=target时，说明一定
                        continue;
                    }
                    int newDistance = distanceMatrix[start][i] + distanceMatrix[i][target];
                    if (newDistance < distanceMatrix[start][target]) {
                        distanceMatrix[start][target] = newDistance;
                        for (int j = 0; j < graph.length; j++) {
                            //更新路径；不只是将pathMatrix[start][target][i]设置为true，这么简单。应该就是严格的跟新路径。
                            pathMatrix[start][target][j] = pathMatrix[start][i][j] || pathMatrix[i][target][j];
                        }
                    }

                }
            }
        }
    }

    @Test
    public void test_shortestPathFloyd() {
        int[][] graph = new int[][]{{0, 4, 11}, {6, 0, 2}, {3, Integer.MAX_VALUE, 0}};

        boolean[][][] pathMatrix = new boolean[graph.length][graph.length][graph.length];
        int[][] distanceMatrix = new int[graph.length][graph.length];

        shortestPathFloyd(graph, pathMatrix, distanceMatrix);
        System.out.println("ths shortest distance matrix:");
        Util.printMatrix(distanceMatrix);
        System.out.println("------------------\n\n\n");
        printPathMatrix(pathMatrix);


    }



    /**
     * 打印定点对之间的的路径。
     * 不足：应该从起始点开始到结束点结束。
     * @param pathMatrix
     */
    public void printPathMatrix(boolean[][][] pathMatrix) {

        StringBuilder stringBuilder;
        StringBuilder pathStringBuilder;
        for (int i = 0; i < pathMatrix.length; i++) {
            stringBuilder = new StringBuilder();
            for (int j = 0; j < pathMatrix[i].length; j++) {
                pathStringBuilder = new StringBuilder();
                for (int k = 0; k < pathMatrix[i][j].length; k++) {
                    if (pathMatrix[i][j][k]) {
                        pathStringBuilder.append((char) (k + 'a'));
                    }
                }
                stringBuilder.append(pathStringBuilder.toString()).append("\t\t");
            }
            System.out.println(stringBuilder.toString());
        }
    }


}
