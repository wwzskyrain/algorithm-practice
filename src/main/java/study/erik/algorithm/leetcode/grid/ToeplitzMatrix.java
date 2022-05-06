/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.grid;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import study.erik.algorithm.util.LetCodeCommit;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yueyi
 * @version : ToeplitzMatrix.java, v 0.1 2022年05月04日 16:30 yueyi Exp $
 */
public class ToeplitzMatrix {

    @LetCodeCommit(title = "766. Toeplitz Matrix")
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int countPrimeSetBits(int left, int right) {
        Set<Integer> set = new HashSet(Arrays.asList(2,3,5,7,11,13,17,19));
        int c = 0;
        for(int i = left; i <= right; i++){
            int bitC = 0;
            int n = i;
            while(n > 0){
                bitC += (n%2);
                n /= 2;
            }
            if(set.contains(bitC)){
                c++;
            }
        }
        return c;
    }

    @Test
    public void test(){
        Assert.assertEquals(4, countPrimeSetBits(6, 10));
    }
}