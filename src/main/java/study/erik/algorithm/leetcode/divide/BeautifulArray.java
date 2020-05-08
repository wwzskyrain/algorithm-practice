package study.erik.algorithm.leetcode.divide;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @author erik.wang
 * @date 2020-05-05 21:54
 */
public class BeautifulArray {

    private static final Logger logger = LoggerFactory.getLogger(BeautifulArray.class);

    @Test
    public void test_solution() {

        logger.info("{}", beautifulArray(5));

    }


    /**
     * title = Beautiful Array
     * 这道题目的解答完全是看的diss，因为我知道自己是不可能做出来的
     * https://leetcode.com/problems/beautiful-array/discuss/186679/Odd-%2B-Even-Pattern-O(N)
     * 可是成绩并不高27和20，不过这个思想是很OK的。
     * 首先把问题分成偶数和基数两部分来构造，因为这样构造的两个漂亮数组，其先后合起来之后，也必将是漂亮数组。
     * 其次，由于A[k]*2 != A[i] + A[j] 完全符合线性。所以
     * 如果一直A，则A1 = A*2-1 ， A2=A*2 ，A1和A2分别都是'漂亮数组'
     * 而又因为A1是奇数漂亮数组，A2是偶数漂亮数组，所以，[A1, A2]也是漂亮数组。
     * @param N
     * @return
     */
    public int[] beautifulArray(int N) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        while (res.size() < N) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int i : res) {
                if (i * 2 - 1 <= N) {
                    tmp.add(i * 2 - 1);
                }
            }
            for (int i : res) {
                if (i * 2 <= N) {
                    tmp.add(i * 2);
                }
            }
            res = tmp;
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

}
