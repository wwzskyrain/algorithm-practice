package study.huawei.medium;

import java.util.*;

//HJ32 密码截取——最长回文子串
//https://www.nowcoder.com/practice/3cd4621963e8454594f00199f4536bb1?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class TruncatePassword {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String password = in.nextLine();
        int n = password.length();
        int[][] dp = new int[n][n];
        int ret = dfs(dp, 0, n - 1, password);
        System.out.println(ret);
    }


    public static int dfs(int[][] dp, int i, int j, String p) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (i == j) {
            dp[i][j] = 1;
            return dp[i][j];
        }
        if (i - 1 == j) {
            dp[i][j] = 0;
            return 0;
        }
        if (p.charAt(i) == p.charAt(j)) {
            int l = dfs(dp, i + 1, j - 1, p);
            if (l == j - i - 1) {
                dp[i][j] = l + 2;
            } else {
                dp[i][j] = Math.max(dfs(dp, i + 1, j, p), dfs(dp, i, j - 1, p));
            }
        } else {
            dp[i][j] = Math.max(dfs(dp, i + 1, j, p), dfs(dp, i, j - 1, p));
        }
        return dp[i][j];
    }
}
