package study.erik.algorithm.nowcoder.zijie;


import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-28 16:32
 * title = https://www.nowcoder.com/test/question/66b68750cf63406ca1db25d4ad6febbf?pid=8537209&tid=35198062
 * 总结：这道题tm就是扯淡，首先根本没有什么算法可言，就是细节，这还好。让人受不了的是，用nextLine就超时，用nextInt就能过
 * 1.  这其实就是牛客网做的垃圾，为什么还需要用户来处理输入输出，垃圾的牛客
 */
public class UserPreference {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Integer articleNum = scanner.nextInt();
        int[] articles = new int[articleNum];
        for (int i = 0; i < articles.length; i++) {
            articles[i] = scanner.nextInt();
        }
        Integer windowsNum = scanner.nextInt();
        int[] windows = new int[windowsNum * 3];

        for (int i = 0; i < Integer.valueOf(windowsNum); i++) {
            windows[i * 3 + 0] = scanner.nextInt();
            windows[i * 3 + 1] = scanner.nextInt();
            windows[i * 3 + 2] = scanner.nextInt();
        }
        int[] result = solution(articles, windows);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] solution(int[] articles, int[] windows) {

        int[] result = new int[windows.length / 3];

        for (int i = 0; i < result.length; i++) {
            int l = windows[i * 3];
            int r = windows[i * 3 + 1];
            int k = windows[i * 3 + 2];
            result[i] = help(articles, l, r, k);

        }
        return result;
    }

    public static int help(int[] articles, int l, int r, int k) {

        int c = 0;
        for (int i = l - 1; i <= r - 1; i++) {
            if (articles[i] == k) {
                c++;
            }
        }
        return c;
    }


}
