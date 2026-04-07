package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.Map;
import java.util.Scanner;

//HJ147 最大 FST 距离
//https://www.nowcoder.com/practice/6295f81acd1b4fb59c8beed92577f64b?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3Fdifficulty%3D3%26page%3D1%26pageSize%3D50%26search%3D%26tpId%3D37%26type%3D37
public class MaxDistanceFST {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = in.nextInt();
        }
        System.out.println(maxFst(data));
    }

    public static long maxFst(int[] data) {
        // dist(𝑖,𝑗)=∣𝑖^2−𝑗^2∣+∣𝐴𝑖^2−𝐴𝑗^2∣
        // P = i^2 + Ai^2    Q = i^2 - Ai^2
        // Ret = max (
        //  max(P) - min(P),
        //  max(Q) - min(Q) )
        long maxP = Long.MIN_VALUE;
        long minP = Long.MAX_VALUE;
        long maxQ = Long.MIN_VALUE;
        long minQ = Long.MAX_VALUE;
        long p, q;
        for (int i = 1; i <= data.length; i++) {
            p = (long) i * i + (long) data[i - 1] * data[i - 1];
            q = (long) i * i - (long) data[i - 1] * data[i - 1];
            maxP = Math.max(maxP, p);
            minP = Math.min(minP, p);
            maxQ = Math.max(maxQ, q);
            minQ = Math.min(minQ, q);
        }
        return Math.max(maxP - minP, maxQ - minQ);
    }
}
