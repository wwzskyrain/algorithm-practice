package study.erik.algorithm.leetcode.divide;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erik.wang
 * @date 2020-05-07 23:42
 */
public class ExpressionAddOperators {

    @Test
    public void test_() {
        String num = "123";
        int target = 6;
        List<String> strings = addOperators(num, target);
        System.out.println(strings);
    }

    /**
     * title = Expression Add Operators
     */
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if (num == null || num.length() == 0) {
            return rst;
        }
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }

    /**
     * 解
     * 这个题的难点在于写代码。其实也不难了。
     * 这是很正常的dfs代码。
     * 这个不算是'分支'思想，这是回溯
     *
     * @param results
     * @param cResult
     * @param numsInput
     * @param target
     * @param pos
     * @param cValue
     * @param operatedNum 这次操作的数据的值，为下次操作的*操作做数据铺垫
     */
    public void helper(List<String> results, String cResult, String numsInput, int target, int pos, long cValue, long operatedNum) {
        if (pos == numsInput.length()) {
            if (target == cValue) {
                results.add(cResult);
            }
            return;
        }
        for (int i = pos; i < numsInput.length(); i++) {
            if (i != pos && numsInput.charAt(pos) == '0') {
                //如果pos是'0'，则只能做次0，后面就不能再继续计算了，即不能有'012'这类前导0的数据
                break;
            }
            long cur = Long.parseLong(numsInput.substring(pos, i + 1));
            if (pos == 0) {
                helper(results, cResult + cur, numsInput, target, i + 1, cur, cur);
            } else {
                helper(results, cResult + "+" + cur, numsInput, target, i + 1, cValue + cur, cur);

                helper(results, cResult + "-" + cur, numsInput, target, i + 1, cValue - cur, -cur);

                helper(results, cResult + "*" + cur, numsInput, target, i + 1, cValue - operatedNum + operatedNum * cur, operatedNum * cur);
            }
        }
    }

}
