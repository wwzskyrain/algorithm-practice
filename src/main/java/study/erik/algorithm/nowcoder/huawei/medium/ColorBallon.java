package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.Scanner;

//HJ111 气球谜题
//https://www.nowcoder.com/practice/3b5ebe9b5f944ccda84517bb748a6c0f?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
// 枚举颜色组合的解法也很棒，比如这个
public class ColorBallon {
    /*
    5
    00000
    1 2 3 4 5
    * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();
        int[] t = new int[n];

        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
        }

        System.out.println(solve(n, s, t));
    }

    public static long solve(int n, String s, int[] t) {
        // 预处理：cost[c][i] = 前i个变成颜色c的代价
        long[][] cost = new long[3][n + 1];

        for (int i = 1; i <= n; i++) {
            int color = s.charAt(i - 1) - '0';
            for (int c = 0; c < 3; c++) {
                cost[c][i] = cost[c][i - 1];
                if (color != c) {
                    cost[c][i] += t[i - 1];
                }
            }
        }

        int[][] perms = {
                {0,1,2}, {0,2,1},
                {1,0,2}, {1,2,0},
                {2,0,1}, {2,1,0}
        };

        long ans = Long.MAX_VALUE;

        for (int[] p : perms) {
            ans = Math.min(ans, calc(n, cost, p));
        }

        return ans;
    }

    // 固定顺序 p[0] -> p[1] -> p[2]
    private static long calc(int n, long[][] cost, int[] p) {
        long res = Long.MAX_VALUE;

        long best = Long.MAX_VALUE;

        for (int j = 0; j <= n; j++) {

            if (j > 0) {
                best = Math.min(best,
                        cost[p[0]][j - 1] - cost[p[1]][j - 1]);
            } else {
                best = 0;
            }

            long cur = best
                    + cost[p[1]][j]
                    + (cost[p[2]][n] - cost[p[2]][j]);

            res = Math.min(res, cur);
        }

        return res;
    }
}
