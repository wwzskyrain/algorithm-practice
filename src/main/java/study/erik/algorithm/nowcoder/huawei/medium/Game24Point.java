package study.erik.algorithm.nowcoder.huawei.medium;

//HJ67 24点游戏算法
//https://www.nowcoder.com/practice/fbc417f314f745b1978fc751a54ac8cb?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game24Point {

    public static double diff = 1e-6;
    public static double target = 24;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data.add(in.nextDouble());
        }
        System.out.println(dfs(data));
    }

    public static boolean findFlag = false;

    public static boolean dfs(List<Double> data) {
        if (data.size() == 1) {
            return Math.abs(data.get(0) - target) < diff;
        }
        // 枚举所有的两数计算结果
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                if (i == j) {
                    continue;
                }
                // 下一轮计算
                List<Double> nextData = new ArrayList<>();
                for (int k = 0; k < data.size(); k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    nextData.add(data.get(k));
                }

                // 4种计算，并把计算结果依次放到下一轮进行计算。
                // 注意，这里不用考虑什么小括号，因为在当前cal(a,b)的时候，已经是相当于小括号了。
                double a = data.get(i);
                double b = data.get(j);
                List<Double> ret = new ArrayList<>();
                ret.add(a + b);
                ret.add(a - b);
                ret.add(a * b);
                ret.add(a / b);
                for (int k = 0; k < ret.size(); k++) {
                    double r = ret.get(k);
                    nextData.add(r);
                    if (dfs(nextData)) {
                        return true;
                    }
                    // 回溯
                    nextData.remove(nextData.size() - 1);
                }
            }
        }
        return false;
    }
}
