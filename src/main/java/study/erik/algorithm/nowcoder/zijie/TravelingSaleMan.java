package study.erik.algorithm.nowcoder.zijie;

import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-23 14:20
 * title = 这是著名的旅行商问题-卖货郎问题
 * url = https://www.nowcoder.com/question/next?pid=16516564&qid=362293&tid=35146752
 */
public class TravelingSaleMan {

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

        System.out.println(min(ticketMap));

//        boolean[] visited = new boolean[ticketMap.length];
//        visited[0] = true;
//        int[] path = new int[ticketMap.length];
//        path[0] = 0;
//        int[] result = new int[1];
//        result[0] = Integer.MAX_VALUE;
//        minExpensive(ticketMap, visited, path, 1, 0, result);
//        System.out.println(result[0]);

    }


    public static int min(int[][] ticketMap) {

        int cityNum = ticketMap.length;
//        dp[i][j] 表示从i出发，经过j的城市，再到0号城市的最近路径
        int subCity = (1 << cityNum) - 1;
        int[][] dp = new int[cityNum][subCity];
        for (int j = 0; j < subCity; j++) {
            for (int i = 0; i < cityNum; i++) {
                //j=0，表示不经过任何城市中转，直接去到i
                //j==1 表示，i经过{0}城市，到0城市的最短距离，这个可以直接取
                if (j == 0 || j == 1) {
                    dp[i][j] = ticketMap[i][0];
                    continue;
                }

                dp[i][j] = Integer.MAX_VALUE;

                for (int k = 0; k < cityNum; k++) {
                    int c = 1 << k;
                    if (j < c) {
                        break;
                    }
                    if ((j & c) == c) {
                        dp[i][j] = Math.min(dp[i][j], ticketMap[i][k] + dp[k][j - c]);
                    }
                }
            }
        }
//      {subCity-1} 这个城市群就减去了0城市
        return dp[0][subCity - 1];
    }


    /**
     * 回溯法解题，估计会栈溢出的，不过思想还是很好的
     *
     * @param ticketMap
     * @param visited
     * @param path
     * @param pathIndex
     * @param curExpen
     * @param result
     */
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
