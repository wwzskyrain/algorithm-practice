package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.Scanner;

//HJ41 称砝码（不用set去重）
public class WeightCode {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int maxWeight = 0;
        int[][] codes = new int[n][2];
        for (int i = 0; i < n; i++) {
            codes[i][0] = in.nextInt(); // 砝码重量
        }
        for (int i = 0; i < n; i++) {
            codes[i][1] = in.nextInt(); // 砝码个数
            maxWeight += codes[i][0] * codes[i][1]; // 计算全部加起来，也就是最多可以称多重
        }
        boolean[] b = new boolean[maxWeight + 1];
        b[0] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= codes[i][1]; j++) {
                int w = codes[i][0];
                // 倒序来加砝码（正序则会出现重复使用当前块砝码的问题）——项羽自举了
                for (int k = b.length - 1; k - w >= 0; k--) {
                    if (b[k - w]) {
                        b[k] = true;
                    }
                }
            }
        }
        // 统计一下
        int ret = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i]) {
                ret++;
            }
        }
        System.out.println(ret);
    }
}
