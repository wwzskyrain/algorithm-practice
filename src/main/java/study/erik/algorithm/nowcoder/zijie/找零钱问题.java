package study.erik.algorithm.nowcoder.zijie;

import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-28 07:09
 * title = https://www.nowcoder.com/question/next?pid=16516564&qid=362294&tid=35180226
 */
public class 找零钱问题 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        System.out.println(change(1024 - money));
    }

    public static int change(int money) {
        int[] changes = {64, 16, 4, 1};
        int i = 0;
        int count = 0;
        while (money > 0) {
            while (money >= changes[i] && money > 0) {
                money -= changes[i];
                count++;
            }
            i++;
        }
        return count;
    }
}
