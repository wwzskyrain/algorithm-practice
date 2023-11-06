package study.erik.algorithm.job.zijie.simulation01;

import java.util.HashMap;
import java.util.Map;

public class Main_3 {

    public int minChanges(int[] nums, int k) {

        int count = 0;
        for (int i = 0; i < k; i++) {
            int j = i;
            Map<Integer, Integer> map = new HashMap<>();
            int c = 0;
            int maxValue = 0;
            while (j < nums.length) {
                int n = nums[j];
                c++;
                Integer value = map.getOrDefault(n, 0);
                value++;
                map.put(n,value);
                maxValue = Math.max(maxValue, value);
                j = j + k;
            }
            count+=(c - maxValue);
        }
        return count;
    }

}
