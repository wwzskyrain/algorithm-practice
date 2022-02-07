/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package study.erik.algorithm.jdk.util;

/**
 * @author yueyi
 * @version : IfStreamInterface.java, v 0.1 2022年01月22日 5:12 下午 yueyi Exp $
 */
public interface IfStreamInterface {

    Boolean getBoolExpress();

    Runnable getTrueRunnable();

    Runnable getFalseRunnable();

    default void run() {
        if (Boolean.TRUE.equals(getBoolExpress())) {
            getTrueRunnable().run();
        } else {
            getFalseRunnable().run();
        }
    }

}