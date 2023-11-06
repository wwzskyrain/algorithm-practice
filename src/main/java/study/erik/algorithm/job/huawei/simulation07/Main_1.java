package study.erik.algorithm.job.huawei.simulation07;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/5 22:20
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_1 {

    @LetCodeCommit(title = "")
    public int mySqrt(int x) {
        if(x == 0){
            return 0;
        }
        if(x == 1){
            return 1;
        }
        long l = 1;
        long h = x;
        while (l < h) {
            long m = l + (h - l) / 2;
            long a = m * m;
            if (a == x) {
                return (int)m;
            } else if (a < x) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return (int)l - 1;
    }


}
