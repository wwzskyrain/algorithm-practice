package study.erik.algorithm.job.huawei.simulation09;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.ArrayUtils;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.*;

/**
 * 日期：2023/11/8 11:01
 * 作者：yueyi
 * 描述：
 */
@RunWith(Parameterized.class)
public class Main_2 {

    @LetCodeCommit(title = "2120. 执行所有后缀指令",
            selfRemark = "直接模拟了，想不到好办法，也没有重复计算。" +
                    "所以这是一道毫无技术的题目")
    public int[] executeInstructions(int n, int[] startPos, String s) {
        int sLenght = s.length();
        int[] ret = new int[sLenght];
        for (int i = 0; i < sLenght; i++) {
            int x = startPos[0];
            int y = startPos[1];
            int count = 0;
            for (int j = i; j < sLenght; j++) {
                char c = s.charAt(j);
                switch (c) {
                    case 'R':
                        y++;
                        break;
                    case 'D':
                        x++;
                        break;
                    case 'L':
                        y--;
                        break;
                    case 'U':
                        x--;
                        break;
                }
                if (x < 0 || x >= n || y < 0 || y >= n) {
                    break;
                }
                count++;
            }
            ret[i] = count;
        }
        return ret;
    }




}
