package study.erik.algorithm.job.huawei.simulation01;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/2 10:20
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int a = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int b = i + 1;
            int c = n - 1;
            while (b < c) {
                if (b > i + 1 && nums[b] == nums[b - 1]) {
                    b++;
                    continue;
                }
                int sum = nums[b] + nums[c];
                if (sum == -a) {
                    ret.add(Arrays.asList(a, nums[b], nums[c]));
                    b++;
                } else if (sum < -a) {
                    b++;
                } else {
                    c--;
                }
            }
        }
        return ret;
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {new ArrayList<>(), ArrayUtils.buildArray("[0,0,0,0]")},
                {new ArrayList<>(), ArrayUtils.buildArray("[-1,0,1,2,-1,-4]")},
                {new ArrayList<>(), ArrayUtils.buildArray("[]")},
                {new ArrayList<>(), ArrayUtils.buildArray("[0]")},

        });
    }

    @Parameterized.Parameter
    public List<List<Integer>> expect;
    @Parameterized.Parameter(1)
    public int[] nums;


    @Test
    public void test() {
        List<List<Integer>> lists = threeSum(nums);
        lists.forEach(System.out::println);
    }

}
