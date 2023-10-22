package study.erik.algorithm.leetcode.weekly001;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/10/18 ，时间：12:04
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Count_of_Sub_Multisets_With_Bounded_Sum {

    @LetCodeCommit(title = "2902. Count of Sub-Multisets With Bounded Sum",
            selfRemark = "这个题目，思考了大半天。终于弄通了。请参考灵山茶师傅的解题： https://leetcode.cn/problems/count-of-sub-multisets-with-bounded-sum/solutions/2482876/duo-zhong-bei-bao-fang-an-shu-cong-po-su-f5ay/" +
                    "解题思路：" +
                    "1.cnt一下每种数字的个数。这个cnt就是我们下面f定义和遍历的基础" +
                    "2.定义f[i][j]表示表示用前i种数组合和成j的组合方式的个数。" +
                    "3.定义第i个数是x，个数为c" +
                    "则f[i][j] = f[i-1][j - 0*x] + f[j-1][j - 1*x] + f[j-1][j - 2*x] + ... + f[j-1][j-c*x] " +
                    "//这个定义很背包的，不解释啦。" +
                    "定义很简单，下面就是对这个定义的优化计算了。" +
                    "这个优化结果是 f[i][j] = f[i][j-x] - f[i-1][j-(c+1)*x] //当然，j-(c+1)*x>=0的时候才会有这个减项")
    public int countSubMultisets(List<Integer> nums, int l, int r) {
        final long MOD = (long) 1e9 + 7;
        Map<Integer, Integer> counterMap = new HashMap<>();
        int total = 0;
        for (Integer num : nums) {
            counterMap.merge(num, 1, Integer::sum);
            total += num;
        }
        if (total < l) {
            return 0;
        }
        r = Math.min(total, r);
        long[] f = new long[r + 1];
        //有多少个0，就有多少个前缀，比如 0， 00，000，哈哈哈，另外，没有0呢，所以再加上一个1
        f[0] = counterMap.getOrDefault(0, 0) + 1;
        counterMap.remove(0); // 0算过了。
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : counterMap.entrySet()) {
            int x = e.getKey();
            int c = e.getValue();
            //注意这里必须是clone，要保留f[i-1]
            long[] newF = f.clone();
            int deltaIndex = (c + 1) * x;
            sum += c * x;
            for (int j = x; j < Math.min(sum + 1, f.length); j++) {
                // 不要一位这里会多算newF[j]说实话，
                // 其实有了这个自加才有了对这个和
                // { f[i-1][j - 0*x] + f[j-1][j - 1*x] + f[j-1][j - 2*x] + ... + f[j-1][j-c*x] }
                // 其中第1个加数就是 newF[j]，你信吗哈哈。
                newF[j] = (newF[j] + newF[j - x]) % MOD;
                if (j >= deltaIndex) {
                    newF[j] = (newF[j] - f[j - deltaIndex] + MOD) % MOD;
                }
            }
            f = newF;
        }
        long ret = 0L;
        for (int i = l; i <= r; i++) {
            ret = (ret + f[i]) % MOD;
        }
        return ((int) ret);
    }

    public int countSubMultisets2(List<Integer> nums, int l, int r) {
        final long MOD = (long) 1e9 + 7;
        Map<Integer, Integer> counterMap = new HashMap<>();
        int total = 0;
        for (Integer num : nums) {
            counterMap.merge(num, 1, Integer::sum);
            total += num;
        }
        if (total < l) {
            return 0;
        }
        r = Math.min(total, r);
        long[] f = new long[r + 1];
        //有多少个0，就有多少个前缀，比如 0， 00，000，哈哈哈，另外，没有0呢，所以再加上一个1
        f[0] = counterMap.getOrDefault(0, 0) + 1;
        counterMap.remove(0); // 0算过了。
        int sum = 0;
        for (Map.Entry<Integer, Integer> e : counterMap.entrySet()) {
            int x = e.getKey();
            int c = e.getValue();
            int deltaIndex = (c + 1) * x;
            for (int i = x; i < f.length; i++) {
                //按照x为跨度来做preSum
                f[i] = (f[i] + f[i - x]) % MOD;
            }
            for (int i = f.length - 1; i >= deltaIndex; i--) {
                //前缀和的差=delta：这里也揭示了这个f[i]的本质就是中间（c+1）项的和
                f[i] = (f[i] - f[i - deltaIndex] + MOD) % MOD;
            }
        }
        long ret = 0L;
        for (int i = l; i <= r; i++) {
            ret = (ret + f[i]) % MOD;
        }
        return ((int) ret);
    }


    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                {1, ArrayUtils.buildList("[1,2,2,3]"), 6, 6},
                {7, ArrayUtils.buildList("[2,1,4,2,7]"), 1, 5},
                {9, ArrayUtils.buildList("[1,2,1,3,5,2]"), 3, 5},
                });
    }

    @Parameterized.Parameter
    public int expect;
    @Parameterized.Parameter(1)
    public List<Integer> nums;
    @Parameterized.Parameter(2)
    public int l;
    @Parameterized.Parameter(3)
    public int r;

    @Test
    public void test() {
        Assert.assertEquals(expect, countSubMultisets2(nums, l, r));
    }


}
