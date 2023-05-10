/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2023 All Rights Reserved.
 */
package study.erik.jdk.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author yueyi
 * @version : TimerDemo.java, v 0.1 2023年05月09日 08:58 yueyi Exp $
 */
public class TimerDemo {

    public static void main(String[] args) {
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("time:" + new Date());
            }
        };
        timer.schedule(timerTask, 5000);
    }

}