package study.erik.algorithm.leetcode.sliding.window;

import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-18 14:20
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String firstLine = in.nextLine();
        String[] firstLineNumbers = firstLine.split(" ");
        int n = Integer.valueOf(firstLineNumbers[0]);
        int m = Integer.valueOf(firstLineNumbers[1]);
        String secondLine = in.nextLine();
        String[] secondLineNumbers = secondLine.split(" ");

        int[] data = new int[secondLineNumbers.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = Integer.valueOf(secondLineNumbers[i]);
        }
        System.out.println(resolve(n, m, data));

    }

    public static int resolve(int n, int m, int[] window) {

        int[] map = new int[m + 1];
        int count = m;

        int r, l;
        r = l = 0;
        int result = Integer.MAX_VALUE;
        while (r < n) {
            int d = window[r++];
            if (d == 0) {
                l = r;
                map = new int[m + 1];
                count = m;
                continue;
            }
            int c = map[d];
            if (c == 0) {
                count--;
            }
            map[d]++;
            while (count == 0) {
                result = Math.min(result, r - l);
                d = window[l++];
                c = map[d];
                if (c == 1) {
                    count++;
                }
                map[d]--;
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

}
