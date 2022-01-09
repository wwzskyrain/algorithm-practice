/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : ListUtiles.java, v 0.1 2022年01月08日 8:51 下午 yueyi Exp $
 */
public class ListUtils {

    /**
     * @param value demo1: "[1,2,3]",   demo1: "1,2,3"
     * @return List<Integer>
     */
    public static List<Integer> build(String value) {
        int[] array = ArrayUtils.buildArray(value);
        return Arrays.stream(array).boxed().collect(Collectors.toList());
    }

}