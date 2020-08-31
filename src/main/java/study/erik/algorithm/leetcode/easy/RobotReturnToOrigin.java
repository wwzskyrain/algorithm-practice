package study.erik.algorithm.leetcode.easy;

import org.junit.Assert;
import org.junit.Test;
import study.erik.algorithm.util.LetCodeCommit;

/**
 * @author erik.wang
 * @date 2020-08-28 11:43
 */
public class RobotReturnToOrigin {

    @LetCodeCommit(title = "657. Robot Return to Origin")
    public boolean judgeCircle(String moves) {
        int up = 0;
        int left = 0;

        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            switch (c) {
                case 'U':
                    up++;
                    break;
                case 'D':
                    up--;
                    break;
                case 'L':
                    left++;
                    break;
                case 'R':
                    left--;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        return up == 0 && left == 0;
    }

    @Test
    public void test_solution_1() {
        Assert.assertTrue(judgeCircle("UD"));
    }

    @Test
    public void test_solution_2() {
        Assert.assertFalse(judgeCircle("LL"));
    }

}
