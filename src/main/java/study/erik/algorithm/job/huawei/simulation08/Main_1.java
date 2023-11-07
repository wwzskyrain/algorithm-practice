package study.erik.algorithm.job.huawei.simulation08;

public class Main_1 {

    public int findComplement(int num) {
        long n = num;
        long ret = 0;
        int i = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                ret |= (1 << i);
            }
            n /= 2;
        }
        return (int) ret;
    }

}
