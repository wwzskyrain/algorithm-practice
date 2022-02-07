/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.jdk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yueyi
 * @version : ListValuePropagation.java, v 0.1 2022年01月14日 11:16 上午 yueyi Exp $
 */
public class ListValuePropagation {

    /**
     * list是值传递，调用方必须接受函数返回值，否则当list是空时，函数讲无效。
     * 最好不要写这样的函数
     *
     * @param list
     * @return
     */
    public static List<String> test(List<String> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add("one");
        System.out.println(list);
        return list;
    }

    public static void main(String[] args) {
        List<String> list = null;
        // 这里重新赋值了
        list = test(list);
        System.out.println(list);
    }

}