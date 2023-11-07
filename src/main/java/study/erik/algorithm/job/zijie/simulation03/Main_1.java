package study.erik.algorithm.job.zijie.simulation03;

import study.erik.algorithm.util.LetCodeCommit;

public class Main_1 {

    @LetCodeCommit(title = "丑数")
    public boolean isUgly(int n) {
        while (n > 1) {
            if (n % 2 != 0) {
                break;
            }
            n /= 2;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                break;
            }
            n /= 3;
        }
        while (n > 1) {
            if (n % 5 != 0) {
                break;
            }
            n /= 5;
        }
        return n == 1;
    }


}
