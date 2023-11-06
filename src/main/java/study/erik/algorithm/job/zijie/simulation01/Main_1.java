package study.erik.algorithm.job.zijie.simulation01;

public class Main_1 {

    public int maxAscendingSum(int[] nums) {
        int sum = 0;
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(i == 0 || nums[i] > nums[i - 1]) {
                sum += nums[i];
            }else {
                sum = nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

}
