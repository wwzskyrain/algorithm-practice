package study.huawei.medium;

import java.util.Scanner;

//HJ52 计算字符串的编辑距离
//https://www.nowcoder.com/practice/3959837097c7413a961a135d7104c314?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class DistanceBetweenWords {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        // dp[i][j]表示 a[0...i]到b[0...j]的编辑距离（即问题定义）；
        // 则dp[i][j] 取下面三种情况的最小值
        // dp[i-1][j] + 1， 解释：dp[i-1][j]指把a[0...i-1]通过编辑距离对应的操作转变成了b[j]，所以这个时候把a[i]删除（花费1个操作）即完成a[i]到b[j]的转变
        // dp[i][j-1] + 1， 解释：同理
        // dp[i-1][j-1] + 1，解释：dp[i-1][j-1]表示a[0...i-1]已经转变为b[0...j-1]了，此时只需把a[i]字符转变成b[i]即可。如何a[i]=b[j]，则不花费操作，否则花费一个操作——替换
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                // 注意：对a[i]于b[j]的判断，要加()提升优先级，否则会破坏三目运算符的完整性。
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1));
            }
        }
        System.out.println(dp[a.length()][b.length()]);
    }
}
