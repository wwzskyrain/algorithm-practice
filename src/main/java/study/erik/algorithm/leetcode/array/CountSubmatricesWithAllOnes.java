/** * Alipay.com Inc. * Copyright (c) 2004-2021 All Rights Reserved. */package study.erik.algorithm.leetcode.array;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.junit.runners.Parameterized;import org.junit.runners.Parameterized.Parameter;import org.junit.runners.Parameterized.Parameters;import study.erik.algorithm.util.ArrayUtils;import study.erik.algorithm.util.LetCodeCommit;/** * @author yueyi * @version : CountSubmatricesWithAllOnes.java, v 0.1 2021年09月16日 9:33 上午 yueyi Exp $ */@RunWith(Parameterized.class)public class CountSubmatricesWithAllOnes {    @LetCodeCommit(title = "1504. Count Submatrices With All Ones")    public int numSubmat(int[][] mat) {        return 13;    }    @Parameter    public int[][] mat;    @Parameter(1)    public int     expect;    @Parameters    public static Object[][] data() {        return new Object[][] {                {                        ArrayUtils.buildArray2Dimension("[[1,0,1],[1,1,0],[1,1,0]]"), 13                },                {                        ArrayUtils.buildArray2Dimension("[[0,1,1,0],[0,1,1,1],[1,1,1,0]]"), 24                },                {                        ArrayUtils.buildArray2Dimension("[[1,1,1,1,1,1]]"), 21                },                {                        ArrayUtils.buildArray2Dimension("[[1,0,1],[0,1,0],[1,0,1]]"), 5                },        };    }    @Test    public void test_() {        Assert.assertEquals(expect, numSubmat(mat));    }}