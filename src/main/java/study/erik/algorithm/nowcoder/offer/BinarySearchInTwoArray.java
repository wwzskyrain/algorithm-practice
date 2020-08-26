package study.erik.algorithm.nowcoder.offer;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author erik.wang
 * @date 2020-08-26 00:33
 */
public class BinarySearchInTwoArray {

    /**
     * desc: 在一个二位有序数组中，二分查找
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int[][] array) {

        if (array.length == 0) {
            return false;
        }
        int i = 0;
        int j = array[0].length - 1;
        while (i < array.length && j >= 0) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    @Test
    public void test_solution_1() {
        int target = 7;
        int[][] array = {{1, 2, 8, 9}, {4, 7, 10, 13}};
        Assert.assertTrue(Find(target, array));
    }

}
