/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package study.erik.algorithm.leetcode.easy;

import study.erik.algorithm.util.LetCodeCommit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yueyi
 * @version : PascalTriangle.java, v 0.1 2021年11月25日 9:20 上午 yueyi Exp $
 */
public class PascalTriangle {

    @LetCodeCommit(title = "Pascal's Triangle",
            selfRemark = "太简单了")
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 1) {
            List<List<Integer>> res = new ArrayList<>();
            res.add(Collections.singletonList(1));
            return res;
        }
        List<List<Integer>> result = generate(numRows - 1);
        List<Integer> last = result.get(result.size() - 1);
        List<Integer> newLast = new ArrayList<>();
        newLast.add(1);
        for (int i = 1; i < last.size(); i++) {
            newLast.add(last.get(i) + last.get(i - 1));
        }
        newLast.add(1);
        result.add(newLast);
        return result;
    }

}