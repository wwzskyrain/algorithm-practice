package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.Scanner;

//HJ16 购物单
// https://www.nowcoder.com/practice/f9c6f980eeec43ef85be20755ddbeaf4?tpId=37&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=3&judgeStatus=&tags=&title=
public class ShoppingList {

    /**
     * 输入：
     * 50 5
     * 20 3 5
     * 20 3 5
     * 10 3 0
     * 10 2 0
     * 10 1 0
     * 输出：
     * 130
     *  注意1：输入输出，见上级经验.md
     *  注意2：01背包问题
     *      1. 这里就是01背包问题的小小特例-给主件加上两个附近（不可能扩展的，就只能2个了）
     *      2. 标准的01背包问题，dp也是二维的，我记成一维的了，所以作不下去。dp[i][j]表示前i个物品在空间j时的最大价值；
     *      3. 标准01背包问题的递推公式中dp[i][j]=max(dp[i-1][j], dp[i-1][j-i空间] + i价值)，确实都是与dp[i-1]来比较。
     *      4. 但是这里max中的第二项要分4中情况讨论，所以这个max是需要最多5个值进行去最值。
     *
     *
     **/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int budget = in.nextInt();
        int good = in.nextInt();
        Good[] goods = new Good[good + 1];
        int i = 1;
        for (; i <= good; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            int master = in.nextInt();
            Good g = new Good();
            g.v = v;
            g.w = w;
            g.m = master;
            goods[i] = g;
        }
        for (i = 1; i < goods.length; i++) {
            int master = goods[i].m;
            if (master != 0) {
                Good masterGood = goods[master];
                if (masterGood.sub1 == 0) {
                    masterGood.sub1 = i;
                    continue;
                }
                if (masterGood.sub2 == 0) {
                    masterGood.sub2 = i;
                }
            }
        }
        // dp[i][j]表示前i个物品中在预算j时可以获取到的最大
        int[][] dp = new int[good + 1][budget + 1];
        for (i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (goods[i].m > 0) {
                    //相当于跳过附件
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                Good g = goods[i];
                int p0 = g.v;
                int w0 = g.w * p0;
                dp[i][j] = dp[i - 1][j]; // ！！！ 这一步早早地比较掉
                if (j >= p0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - p0] + w0);
                }
                if (g.sub1 > 0) {
                    int p1 = p0 + goods[g.sub1].v;
                    int w1 = w0 + goods[g.sub1].w * goods[g.sub1].v;
                    if (j >= p1) {
                        // 不要在与dp[i-1][j]比较了，都含在了dp[i][j]中了。
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - p1] + w1);
                    }

                }
                if (g.sub2 > 0) {
                    int p2 = p0 + goods[g.sub2].v;
                    int w2 = w0 + goods[g.sub2].w * goods[g.sub2].v;
                    if (j >= p2) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - p2] + w2);
                    }
                }
                if (g.sub1 > 0 && g.sub2 > 0) {
                    int p3 = p0 + goods[g.sub1].v + goods[g.sub2].v;
                    int w3 = w0 + goods[g.sub1].w * goods[g.sub1].v + goods[g.sub2].w * goods[g.sub2].v;
                    if (j >= p3) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - p3] + w3);
                    }
                }
            }
        }
        System.out.println(dp[good][budget]);
    }

    public static class Good {
        public int v; // price
        public int w; // weight;
        public int m; // master no
        public int sub1 = 0;
        public int sub2 = 0;
    }

}
