package study.erik.algorithm.leetcode.huawei.medium;

import java.util.*;

//HJ75 公共子串计算
//https://www.nowcoder.com/practice/98dc82c094e043ccb7e0570e5342dd1b?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
public class LongestCommonSubstring {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        int[][] dp = new int[a.length()][b.length()];
        // dp[i][j]表示以a[i]和以b[j]结尾的最长子字符串的长度（属于带限定的原问题，或者说原问题的局部解）。
        // 至于原问题的解，就遍历所有的局部求出最值即可。
        int max = 0;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) != b.charAt(j)) {
                    //必然不可能从a和b中找到两个子字符串相等，因为a[i]!=b[j]
                    dp[i][j] = 0;
                } else {
                    if (i < 1 || j < 1) {
                        //边界处理
                        dp[i][j] = 1;
                    } else {
                        // 问题递归
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    //遍历所有局部的全部。
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        System.out.println(max);
    }

}
