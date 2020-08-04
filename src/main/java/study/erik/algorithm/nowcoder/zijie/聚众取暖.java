package study.erik.algorithm.nowcoder.zijie;

import java.util.*;

/**
 * @author erik.wang
 * @date 2020-07-30 09:11
 */
public class 聚众取暖 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        String chars = line[0];
        int m = Integer.valueOf(line[1]);
        Map<Character, List<Integer>> charTable = new HashMap<>();
        for (int i = 0; i < chars.length(); i++) {
            List<Integer> list = charTable.getOrDefault(chars.charAt(i), new ArrayList<>());
            list.add(i);
            charTable.put(chars.charAt(i), list);
        }
        int max = 1;
        for (List<Integer> list : charTable.values()) {
            if (list != null && list.size() > 0) {
                int[] pos = new int[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    pos[i] = list.get(i);
                }
                max = Math.max(solve(pos, m), max);
            }
        }
        System.out.println(max);

    }

    /**
     * 返回在m内的最长'相同字符的字符串'
     * 总结：这是一个种区间dp的。切忌，区间dp不要一味的用len来迭代
     * 合并趋势：往中间挤挤吧
     * 递归公式：整体来讲这个递归公式的求解还是很简单的。
     *
     * @param pos
     * @param m
     */
    public static int solve(int[] pos, int m) {

        int maxL = 1;
        //dp[i][j] 表示把 第i个元素和第j个元素连带两者之间的元素挪到一起需要的最小代价，其中i<=j
        //i==j, dp = 0;
        //i=j - 1， dp=p[j]-p[i]-(j-i);
        //dp[i][j] = dp[i+1][j-1] + p[j] - p[i] - (j - i);
        int[][] dp = new int[pos.length][pos.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[dp.length];
            for (int j = 0; j < dp[i].length; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < dp.length; j++) {
                int cost = pos[j] - pos[i] - (j - i);
                if (j != i + 1) {
                    cost += dp[i + 1][j - 1];
                }
                dp[i][j] = cost;
                if (cost <= m) {
                    //这一步是结合题意的
                    maxL = j - i + 1;
                }
            }
        }
        return maxL;
    }

}
