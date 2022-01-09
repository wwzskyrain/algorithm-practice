/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.util;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yueyi
 * @version : MatcherUtils.java, v 0.1 2022年01月09日 11:06 上午 yueyi Exp $
 */
public class MatcherUtils {

    public static <T> Matcher<T> allOfEquals(T... v1s) {
        return CoreMatchers.allOf(
                Arrays.stream(v1s)
                        .map(IsEqual::new)
                        .collect(Collectors.toList()));
    }

}