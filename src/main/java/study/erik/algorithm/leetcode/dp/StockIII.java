package study.erik.algorithm.leetcode.dp;

public class StockIII {

    /*
state:
local[i][j] 表示前i天，至多进行j次交易，第i天必须sell的最大收益
global[i][j]表示前i天，至多进行j此交易，第i天可以不sell的最大收益
function:
gain = prices[i]-prices[i-1]
local[i][j] = max(global[i-1][j-1]+gain,local[i-1][j]+gain)
global[i][j] = max(global[i-1][j],local[i][j])
intialization:
global[0][i] = 0 local[0][i] = 0;
answer:global[n][k]
*/

    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0)
            return 0;
        int n = prices.length;
        //分为两种情况，当k>=n/2时，可以进行最大次数的交易。就是随便买，随便卖
        if (k >= n / 2) {
            int maxPro = 0;
            for (int i = 1; i < n; i++)
                maxPro += (prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0);
            return maxPro;
        }
        //第二种情况
        int[][] global = new int[n][k + 1];
        int[][] local = new int[n][k + 1];

        for (int i = 0; i <= k; i++) {
            local[0][i] = 0;
            global[0][i] = 0;
        }

        for (int i = 1; i < n; i++) {
            int gain = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1], local[i - 1][j]) + gain;
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }

        return global[n - 1][k];
    }

    public int maxProfit(int[] prices) {
        if(prices==null || prices.length==0)
            return 0;
        int[] local = new int[3];
        int[] global = new int[3];
        for(int i=0;i<prices.length-1;i++)
        {
            int diff = prices[i+1]-prices[i];
            for(int j=2;j>=1;j--)
            {
                local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
                global[j] = Math.max(local[j],global[j]);
            }
        }
        return global[2];
    }

}


