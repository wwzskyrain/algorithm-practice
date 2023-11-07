package study.erik.algorithm.job.huawei.simulation08;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/7 13:25
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "")
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;
        int totalCost = 0;
        int start = 0;
        int left = 0;
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            int g = gas[i] + left;
            if (g < cost[i]) { //到不了i+1了
                start = i + 1;
                left = 0;
            } else {
                left = g - cost[i];
            }
        }
        return totalGas >= totalCost ? start : -1;
    }


}
