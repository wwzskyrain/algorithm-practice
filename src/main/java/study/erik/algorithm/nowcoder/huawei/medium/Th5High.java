package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.Scanner;

//HJ38 求小球落地5次后所经历的路程和第5次反弹的高度
//https://www.nowcoder.com/practice/2f6f9339d151410583459847ecc98446?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
// 太简单了，根据例题的讲解就可以写代码了
public class Th5High {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        double hDouble = (double) h;
        double l = hDouble;
        int i = 5;
        while (i > 0) {
            hDouble = hDouble / 2;
            if (i > 1) {
                l += hDouble * 2;
            }
            i--;
        }
        System.out.println(l);
        System.out.println(hDouble);
    }

}
