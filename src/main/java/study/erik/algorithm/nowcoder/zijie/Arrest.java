package study.erik.algorithm.nowcoder.zijie;

import java.util.Scanner;

/**
 * @author erik.wang
 * @date 2020-07-19 18:26
 */
public class Arrest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int n = Integer.valueOf(line[0]);
        if (n <= 2) {
            System.out.println(-1);
            return;
        }
        int d = Integer.valueOf(line[1]);
        line = scanner.nextLine().split(" ");
        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = Integer.valueOf(line[i]);
        }
        arrest(positions, d);
    }


    /**
     * 这个题目挺难做的
     * 1.这是个滑动窗口求所有解个数的题目，如果用三层循环、两层循环都会超时的.
     * 所以，这是一个很重要的解法：r指针一直往右走就可以了，不用担心l指针。
     * 而且，求所有布局时，还用了C(n,2)的乘法，也是很好了。
     * 2.注意，int的相乘和相加都会导致int溢出的，所以，不妨用long来计数，而且，参加计数的所有运算都最好用long哈。
     *
     * @param positions
     * @param maxDistance
     */
    public static void arrest(int[] positions, int maxDistance) {

        long count = 0;
        int mod = 99997867;
        int r = 2, l = 0;
        while (r < positions.length) {
            if (positions[r] - positions[l] > maxDistance) {
                l++;
            } else if (r - l < 2) {
                r++;
            } else {
                long n = r - l;
                count = (count + n * (n - 1) / 2) % mod;
                r++;
            }
        }
        System.out.println(count);
    }
}
