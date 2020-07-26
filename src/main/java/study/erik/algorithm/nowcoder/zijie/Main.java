package study.erik.algorithm.nowcoder.zijie;

import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-23 14:20
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer cityNum = Integer.valueOf(scanner.nextLine());
        int[][] ticketMap = new int[cityNum][cityNum];
        for (int i = 0; i < cityNum; i++) {
            ticketMap[i] = new int[cityNum];
            String[] ticketStrings = scanner.nextLine().split(" ");
            for (int j = 0; j < ticketStrings.length; j++) {
                ticketMap[i][j] = Integer.valueOf(ticketStrings[j]);
            }
        }
        boolean[] visited = new boolean[ticketMap.length];
        visited[0] = true;
        int[] path = new int[ticketMap.length];
        path[0] = 0;
        int[] result = new int[1];
        result[0] = Integer.MAX_VALUE;
        minExpensive(ticketMap, visited, path, 1, 0, result);
        System.out.println(result[0]);

    }


    public static void minExpensive(int[][] ticketMap, boolean[] visited, int[] path, int pathIndex, int curExpen, int[] result) {

        if (curExpen > result[0]) {
            return;
        }
        int curCity;
        if (pathIndex == ticketMap.length) {
            curCity = path[pathIndex - 1];
            result[0] = Math.min(result[0], curExpen + ticketMap[curCity][0]);
            return;
        }

        for (int i = 0; i < ticketMap.length; i++) {
            if (visited[i]) {
                continue;
            }
            curCity = path[pathIndex - 1];
            path[pathIndex] = i;
            visited[i] = true;
            minExpensive(ticketMap, visited, path, pathIndex + 1, curExpen + ticketMap[curCity][i], result);
            visited[i] = false;
        }
    }

}
