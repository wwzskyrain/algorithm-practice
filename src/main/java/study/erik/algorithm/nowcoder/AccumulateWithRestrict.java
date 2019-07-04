package study.erik.algorithm.nowcoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author erik.wang
 * @date 2019/06/29
 **/
public class AccumulateWithRestrict {

    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = n > 0 && (sum += Sum_Solution(n - 1)) > -1;
        return sum;
    }

    @Test
    public void test_Sum_Solution() {
        Assert.assertEquals(6,Sum_Solution(3));
        Assert.assertEquals(5050,Sum_Solution(100));
    }

}
