package study.erik.algorithm.ds;

import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author erik.wang
 * @date 2019/06/30
 **/
public class Dijkstra {

    //注意：权值无穷大不能使用 Integer.MAX_VALUE表示，因为程序中会基于这个值做加法，从而导致移出的。
    static final Integer MAX_VALUE = 10000;
    
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

            //先找到这一次待加入的节点；其实放在上一个循环中来做更合适。
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
                        pathMatrix[j][j] = true; //加上这最后一条(minIndex,j)
                    }
                }
            }
        }
    }

    @Test
    public void test_shortestPath() {
        int[][] graph = new int[][]{
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
