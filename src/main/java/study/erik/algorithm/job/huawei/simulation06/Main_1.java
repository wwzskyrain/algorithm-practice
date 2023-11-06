package study.erik.algorithm.job.huawei.simulation06;

import java.util.Arrays;

public class Main_1 {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int total = 0;
        for (int c = nums.length - 1; c >= 0; c--) {
            int cN = nums[c];
            int a = 0;
            int b = c - 1;
            while (a < b) {
                int aN = nums[a];
                int bN = nums[b];
                if (aN + bN > cN) {
                    total += (b - a);
                    b--;
                } else {
                    a++;
                }
            }
        }
        return total;
    }

}
