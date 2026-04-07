package study.erik.algorithm.nowcoder.huawei.medium;

import java.util.Scanner;

//HJ55 挑7
//https://www.nowcoder.com/practice/ba241b85371c409ea01ac0aa1a8d957b?tpId=37&tags=&title=&difficulty=3&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
//划水的一题
public class Challenge7 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if(has7(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static boolean has7(int n) {
        if (n % 7 == 0) {
            return true;
        }
        while (n > 0) {
            if (n % 10 == 7) {
                return true;
            }
            n = n / 10;
        }
        return false;
    }

}
