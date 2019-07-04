package study.erik.algorithm.nowcoder;

import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/29
 **/
public class JosephusCircle {

    public int LastRemaining_Solution(int n, int m) {

        if (n < 1 || m < 1) {
            return -1;
        }

        int last = 0;
        for (int i = 2; i <= n; i++) {

            last = (last + m) % i;
            System.out.printf("i=%d, last=%d\n", i, last);

        }

        return last;

    }

    public int LastRemaining_Solution1(int n, int m) {

        if (n < 1 || m < 1) {
            return -1;
        }

        int last = 0;
        for (int i = 2; i <= n; i++) {

            last = last + m % i;
            System.out.printf("i=%d, last=%d\n", i, last);

        }

        return last;

    }

    @Test
    public void test() {
        LastRemaining_Solution(10, 4);
        LastRemaining_Solution1(10, 4);
    }

}
